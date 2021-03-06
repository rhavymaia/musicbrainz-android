/*
 * Copyright (C) 2010 Jamie McDonald
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

package org.musicbrainz.mobile.adapter.list;

import java.util.List;

import org.musicbrainz.android.api.data.ArtistSearchStub;
import org.musicbrainz.mobile.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ArtistSearchAdapter extends ArrayAdapter<ArtistSearchStub> {

    private Activity context;
    private List<ArtistSearchStub> resultData;

    public ArtistSearchAdapter(Activity context, List<ArtistSearchStub> resultData) {
        super(context, R.layout.list_search_artist, resultData);
        this.context = context;
        this.resultData = resultData;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View artist = convertView;
        ArtistSearchHolder holder = null;

        if (artist == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            artist = inflater.inflate(R.layout.list_search_artist, parent, false);
            holder = new ArtistSearchHolder(artist);
            artist.setTag(holder);
        } else {
            holder = (ArtistSearchHolder) artist.getTag();
        }

        ArtistSearchStub artistData = resultData.get(position);
        holder.getArtistName().setText(artistData.getName());

        TextView option = holder.getDisambiguation();
        String disambiguation = artistData.getDisambiguation();
        option.setText(disambiguation);

        if (disambiguation != null) {
            option.setVisibility(View.VISIBLE);
        } else {
            option.setVisibility(View.GONE);
        }
        return artist;
    }

    private class ArtistSearchHolder {
        View base;
        TextView artistName = null;
        TextView disambiguation = null;

        ArtistSearchHolder(View base) {
            this.base = base;
        }

        TextView getArtistName() {
            if (artistName == null) {
                artistName = (TextView) base.findViewById(R.id.search_artist_name);
            }
            return artistName;
        }

        TextView getDisambiguation() {
            if (disambiguation == null) {
                disambiguation = (TextView) base.findViewById(R.id.search_artist_disambig);
            }
            return disambiguation;
        }
    }

}
