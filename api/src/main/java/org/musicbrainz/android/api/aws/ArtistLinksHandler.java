package org.musicbrainz.android.api.aws;

import java.util.ArrayList;

import org.musicbrainz.android.api.data.Artist;
import org.musicbrainz.android.api.data.WebLink;

public class ArtistLinksHandler {
	
	public static ArrayList<WebLink> getArtistLinks(Artist artist){
		addAmazonSearchLink(artist);
		return artist.getLinks();
	}
	
	private static void addAmazonSearchLink(Artist artist){
		WebLink amazonLink = createAmazonSearchLink(artist);
		artist.addLink(amazonLink);
	}
	
	private static WebLink createAmazonSearchLink(Artist artist){
		AmazonSearchHandler amazonSearchHandler = new AmazonSearchHandler();
		String searchUrl = amazonSearchHandler.getSearchUrl(artist.getName());
		
		WebLink link = new WebLink();
		link.setType("Amazon");
		link.setUrl(searchUrl);
		
		return link;
	}
}
