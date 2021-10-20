
package com.example.songify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;


@Generated("jsonschema2pojo")
public class Cancion {

    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("artistLink")
    @Expose
    private String artistLink;
    @SerializedName("album")
    @Expose
    private String album;
    @SerializedName("albumLink")
    @Expose
    private String albumLink;
    @SerializedName("isrc")
    @Expose
    private String isrc;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("trackLink")
    @Expose
    private String trackLink;
    @SerializedName("preview")
    @Expose
    private String preview;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("addedDate")
    @Expose
    private Integer addedDate;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("shareUrls")
    @Expose
    private List<Object> shareUrls = null;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtistLink() {
        return artistLink;
    }

    public void setArtistLink(String artistLink) {
        this.artistLink = artistLink;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbumLink() {
        return albumLink;
    }

    public void setAlbumLink(String albumLink) {
        this.albumLink = albumLink;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTrackLink() {
        return trackLink;
    }

    public void setTrackLink(String trackLink) {
        this.trackLink = trackLink;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Integer addedDate) {
        this.addedDate = addedDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Object> getShareUrls() {
        return shareUrls;
    }

    public void setShareUrls(List<Object> shareUrls) {
        this.shareUrls = shareUrls;
    }

}
