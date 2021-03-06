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

package org.musicbrainz.android.api.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Release {

    private String mbid;
    private String releaseGroupMbid;
    private String barcode;
    private String asin;
    private ArrayList<ArtistNameMbid> artists = new ArrayList<ArtistNameMbid>();

    private String title;
    private String status;
    private String date;

    private float releaseGroupRating;
    private int releaseGroupRatingCount;
    private LinkedList<Tag> releaseGroupTags = new LinkedList<Tag>();

    private Collection<String> labels = new LinkedList<String>();
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public String getMbid() {
        return mbid;
    }

    public void setReleaseMbid(String releaseMbid) {
        this.mbid = releaseMbid;
    }

    public String getReleaseGroupMbid() {
        return releaseGroupMbid;
    }

    public void setReleaseGroupMbid(String releaseGroupMbid) {
        this.releaseGroupMbid = releaseGroupMbid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public ArrayList<ArtistNameMbid> getArtists() {
        return artists;
    }

    public void addArtist(ArtistNameMbid artist) {
        artists.add(artist);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getReleaseGroupRating() {
        return releaseGroupRating;
    }

    public void setReleaseGroupRating(float releaseGroupRating) {
        this.releaseGroupRating = releaseGroupRating;
    }

    public int getReleaseGroupRatingCount() {
        return releaseGroupRatingCount;
    }

    public void setReleaseGroupRatingCount(int releaseGroupRatingCount) {
        this.releaseGroupRatingCount = releaseGroupRatingCount;
    }

    public LinkedList<Tag> getReleaseGroupTags() {
        Collections.sort(releaseGroupTags);
        return releaseGroupTags;
    }

    public void setReleaseGroupTags(LinkedList<Tag> releaseGroupTags) {
        this.releaseGroupTags = releaseGroupTags;
    }
    
    public void addReleaseGroupTag(Tag tag) {
        releaseGroupTags.add(tag);
    }

    public Collection<String> getLabels() {
        return labels;
    }

    public void setLabels(LinkedList<String> labels) {
        this.labels = labels;
    }

    public void addLabel(String label) {
        labels.add(label);
    }

    public ArrayList<Track> getTrackList() {
        return tracks;
    }

    public void setTrackList(ArrayList<Track> trackList) {
        this.tracks = trackList;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

}
