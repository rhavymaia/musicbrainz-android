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

package org.musicbrainz.mobile.test.handler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.musicbrainz.android.api.data.ArtistNameMbid;
import org.musicbrainz.android.api.data.Release;
import org.musicbrainz.android.api.data.Track;
import org.musicbrainz.android.api.webservice.ResponseParser;

public class ReleaseLookupTest extends BaseXmlParsingTestCase {
    
    private static final String RELEASE_LOOKUP_FIXTURE = "releaseLookup_77c85384-c846-34b1-b576-63bc00644a28.xml";
    private Release release;
    
    @Before
    public void doParsing() throws IOException {
        InputStream stream = getFileStream(RELEASE_LOOKUP_FIXTURE);
        assertNotNull(stream);
        release = new ResponseParser().parseRelease(stream);
        stream.close();
    }

    @Test
    public void testReleaseData() {
        assertEquals("77c85384-c846-34b1-b576-63bc00644a28", release.getMbid());
        assertEquals("60089b39-412b-326c-afc7-aaa47113d84f", release.getReleaseGroupMbid());
        assertEquals("781676658923", release.getBarcode());
        assertEquals("Miss Machine", release.getTitle());
        assertEquals("Official", release.getStatus());
        assertEquals("2004-08-02", release.getDate());
        assertEquals("The Dillinger Escape Plan", release.getArtists().get(0).getName());
        assertEquals("B00029J24Y", release.getAsin());
    }
    
    @Test
    public void testArtist() {
        ArtistNameMbid artist = release.getArtists().get(0);
        assertEquals("1bc41dff-5397-4c53-bb50-469d2c277197", artist.getMbid());
        assertEquals("The Dillinger Escape Plan", artist.getName());
    }
    
    @Test
    public void testReleaseGroupTags() {
        assertEquals(0, release.getReleaseGroupTags().size());
    }
    
    @Test
    public void testReleaseGroupRatings() {
        assertEquals(4.5, release.getReleaseGroupRating(), 0.01);
        assertEquals(2, release.getReleaseGroupRatingCount());
    }
    
    @Test
    public void testLabelList() {
        assertEquals(1, release.getLabels().size());
    }
    
    @Test 
    public void testTrackList() {
        assertEquals(11, release.getTrackList().size());
    }
    
    @Test
    public void testTrackData() {
        Track track = release.getTrackList().get(0);
        assertEquals(1, track.getPosition());
        assertEquals(147093, track.getDuration());
        assertEquals("Panasonic Youth", track.getTitle());
        assertEquals("a100efb8-0f18-44ed-8383-a503a4b7c728", track.getRecordingMbid());
    }

}
