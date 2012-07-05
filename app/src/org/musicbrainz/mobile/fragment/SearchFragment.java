/*
 * Copyright (C) 2012 Jamie McDonald
 * 
 * This file is part of MusicBrainz for Android.
 * 
 * MusicBrainz for Android is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU General Public 
 * License as published by the Free Software Foundation, either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * MusicBrainz for Android is distributed in the hope that it 
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MusicBrainz for Android. If not, see 
 * <http://www.gnu.org/licenses/>.
 */

package org.musicbrainz.mobile.fragment;

import java.util.List;

import org.musicbrainz.mobile.R;
import org.musicbrainz.mobile.activity.SearchActivity;
import org.musicbrainz.mobile.intent.IntentFactory.Extra;
import org.musicbrainz.mobile.intent.zxing.IntentIntegrator;
import org.musicbrainz.mobile.suggestion.SuggestionHelper;
import org.musicbrainz.mobile.util.PreferenceUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class SearchFragment extends ContextFragment implements
		OnEditorActionListener, OnItemClickListener, OnClickListener {

    private AutoCompleteTextView searchField;
    private Spinner searchTypeSpinner;
    private SuggestionHelper suggestionHelper;
    
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater
				.inflate(R.layout.fragment_dash_search, container);
		searchTypeSpinner = (Spinner) layout.findViewById(R.id.search_spin);
		searchField = (AutoCompleteTextView) layout
				.findViewById(R.id.query_input);
		searchField.setOnEditorActionListener(this);
		layout.findViewById(R.id.search_btn).setOnClickListener(this);
		
		// Disable button if no recognition service is present
		Button speakButton = (Button) layout
				.findViewById(R.id.speak_btn);
		Activity activity = getActivity();
		PackageManager pm = activity.getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(
				RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);

		if (activities.size() == 0) {
			speakButton.setEnabled(false);
		} else {
			speakButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {					
					startVoiceRecognitionActivity();
				}
			});
		}

		return layout;
	}

 	/**
 	 * Fire an intent to start the voice recognition activity.
 	 */
 	private void startVoiceRecognitionActivity() { 		
 		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); 		
 		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
 				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
 		intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
 				"Voice recognition");
 		intent.putExtra("requestCode", 
 				IntentIntegrator.RECOGNIZE_SPEECH_REQUEST);
 		this.startActivityForResult(intent, 
 				IntentIntegrator.RECOGNIZE_SPEECH_REQUEST);
 	}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupSearchTypeSpinner();
        suggestionHelper = new SuggestionHelper(getActivity());
    }

    private void setupSearchTypeSpinner() {
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.searchType,
                android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(typeAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (PreferenceUtils.shouldProvideSearchSuggestions(context)) {
            searchField.setAdapter(suggestionHelper.getAdapter());
            searchField.setOnItemClickListener(this);
        } else {
            searchField.setAdapter(suggestionHelper.getEmptyAdapter());
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v.getId() == R.id.query_input && actionId == EditorInfo.IME_NULL) {
            startSearch();
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startSearch();
    }
    
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_btn) {
            startSearch();
        }
    }


    private void startSearch() {
        String query = searchField.getText().toString();

        if (query.length() > 0) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchField.getWindowToken(), 0);

            Intent searchIntent = new Intent(context, SearchActivity.class);
            searchIntent.putExtra(Extra.TYPE, getSearchTypeFromSpinner());
            searchIntent.putExtra(Extra.QUERY, query);
            startActivity(searchIntent);
        } else {
            Toast.makeText(context, R.string.toast_search_err, Toast.LENGTH_SHORT).show();
        }
        searchField.setText("");
    }

    private String getSearchTypeFromSpinner() {
        int spinnerPosition = searchTypeSpinner.getSelectedItemPosition();
        switch (spinnerPosition) {
        case 0:
            return Extra.ARTIST;
        case 1:
            return Extra.RELEASE_GROUP;
        case 2:
            return Extra.LABEL;
        case 3:
            return Extra.RECORDING;
        default:
            return Extra.ALL;
        }
    }
    
    public void setSearchField(String value) {
    	this.searchField.setText(value);
    }
}
