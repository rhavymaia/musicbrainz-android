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

package org.musicbrainz.mobile.string;

import java.util.Collection;
import java.util.LinkedList;

import org.musicbrainz.android.api.data.ArtistNameMbid;
import org.musicbrainz.android.api.data.Tag;
import org.musicbrainz.mobile.R;

import android.content.Context;

public class StringFormat {

    public static String commaSeparate(Collection<String> collection) {
        StringBuilder sb = new StringBuilder();
        if (collection.isEmpty()) {
            return sb.toString();
        }
        for (String item : collection) {
            sb.append(item + ", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public static String commaSeparateArtists(Collection<ArtistNameMbid> artists) {
        StringBuilder sb = new StringBuilder();
        for (ArtistNameMbid artist : artists) {
            sb.append(artist.getName() + ", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
    
    public static String commaSeparateTags(LinkedList<Tag> tags, Context context) {
        StringBuilder sb = new StringBuilder();
        if (tags.isEmpty()) {
            return context.getResources().getString(R.string.no_tags);
        }
        for (Tag tag : tags) {
            sb.append(tag.getText() + ", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}
