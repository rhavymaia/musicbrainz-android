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
import org.musicbrainz.android.api.webservice.ResponseParser;

public class BarcodeSearchTest extends BaseXmlParsingTestCase {
    
    private static final String BARCODE_SEARCH_FIXTURE = "barcodeSearch_792258106329.xml";
    private String mbid;
    
    @Before
    public void doParsing() throws IOException {
        InputStream stream = getFileStream(BARCODE_SEARCH_FIXTURE);
        assertNotNull(stream);
        mbid = new ResponseParser().parseMbidFromBarcode(stream);
        stream.close();
    }

    @Test
    public void testBarcodeSearch() {
        assertEquals("7fe8ee44-68bb-31da-befc-bda6da88c379", mbid);
    }

}
