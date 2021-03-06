/*
 * Copyright (C) 2011 Jamie McDonald
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

package org.musicbrainz.mobile.config;

public class Configuration {

    public static final boolean LIVE = false;
    public static final String TAG = "MusicBrainz";
    
    public static final String FEEDBACK_EMAIL = "support@musicbrainz.org";
    public static final String FEEDBACK_SUBJECT = "[MBAndroid] Feedback";

    public static final String USER_AGENT = "MBAndroid";
    public static final String CLIENT_NAME = "musicbrainz.android";
    
    public static final String WEBSITE = "http://musicbrainz.org/";
    
    public static final String URL_REGISTER = WEBSITE + "register";
    public static final String URL_FORGOT_PASS = WEBSITE + "lost-password";
    public static final String URL_DONATE = "http://metabrainz.org/donate";

    public static final String ARTIST_SHARE = WEBSITE + "artist/";
    public static final String RELEASE_SHARE = WEBSITE + "release/";
    
}
