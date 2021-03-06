package org.musicbrainz.android.api.handler;

import java.util.ArrayList;

import org.musicbrainz.android.api.data.ReleaseGroupStub;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ReleaseGroupBrowseHandler extends MBHandler {

    private ArrayList<ReleaseGroupStub> results = new ArrayList<ReleaseGroupStub>();
    private ReleaseGroupStub stub;

    private int total = 0;

    public ArrayList<ReleaseGroupStub> getResults() {
        return results;
    }

    public int getTotal() {
        return total;
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {

        if (localName.equalsIgnoreCase("release-group")) {
            stub = new ReleaseGroupStub();
            String mbid = atts.getValue("id");
            stub.setMbid(mbid);
            stub.setType(atts.getValue("type"));
        } else if (localName.equalsIgnoreCase("title")) {
            buildString();
        } else if (localName.equalsIgnoreCase("first-release-date")) {
            buildString();
        } else if (localName.equalsIgnoreCase("release-group-list")) {
            total = Integer.parseInt(atts.getValue("count"));
        }
    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

        if (localName.equalsIgnoreCase("release-group")) {
            results.add(stub);
        } else if (localName.equalsIgnoreCase("title")) {
            stub.setTitle(getString());
        } else if (localName.equalsIgnoreCase("first-release-date")) {
            stub.setFirstRelease(getString());
        }
    }

}
