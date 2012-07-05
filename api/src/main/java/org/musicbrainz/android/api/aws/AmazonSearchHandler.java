package org.musicbrainz.android.api.aws;

public class AmazonSearchHandler {

    public String getSearchUrl(String searchTerm) {
        return "http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords=" + searchTerm.toLowerCase().replace(" ", "+");
    }
}
