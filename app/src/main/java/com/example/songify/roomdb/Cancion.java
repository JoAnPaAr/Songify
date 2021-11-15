
package com.example.songify.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;


@Entity(tableName = "cancion")
@Generated("jsonschema2pojo")
public class Cancion {

    @Ignore
    @SerializedName("platform")
    @Expose
    private String platform;

    //ID de la cancion
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private String id;

    //Titulo de la cancion
    @ColumnInfo(name = "titulo")
    @SerializedName("title")
    @Expose
    private String title;

    //Artista de la cancion
    @ColumnInfo(name = "artista")
    @SerializedName("artist")
    @Expose
    private String artist;

    @Ignore
    @SerializedName("artistLink")
    @Expose
    private String artistLink;

    @Ignore
    @SerializedName("album")
    @Expose
    private String album;

    @Ignore
    @SerializedName("albumLink")
    @Expose
    private String albumLink;

    @Ignore
    @SerializedName("isrc")
    @Expose
    private String isrc;

    //Duracion de la cancion
    @ColumnInfo(name = "duracion")
    @SerializedName("duration")
    @Expose
    private String duration;

    @Ignore
    @SerializedName("trackLink")
    @Expose
    private String trackLink;

    //URL de la cancion
    @ColumnInfo(name = "url_mp3")
    @SerializedName("preview")
    @Expose
    private String preview;

    //Imagen de la cancion
    @ColumnInfo(name = "url_imagen")
    @SerializedName("picture")
    @Expose
    private String picture;

    @Ignore
    @SerializedName("addedDate")
    @Expose
    private Integer addedDate;

    //Ranking de la cancion
    @ColumnInfo(name = "ranking")
    @SerializedName("position")
    @Expose
    private String position;

    @Ignore
    @SerializedName("shareUrls")
    @Expose
    private List<Object> shareUrls = null;

    private boolean isFavorito;

    @Ignore
    private boolean isReproduciendo;

    @Ignore
    public Cancion(String id, String title, String artist, String duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    @Ignore
    public Cancion(String id, String title, String artist, String duration, String picture) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.picture = picture;
        this.isFavorito = false;
        this.isReproduciendo = false;
    }

    @Ignore
    public Cancion(String platform, String id, String title, String artist, String artistLink, String album, String albumLink, String isrc, String duration, String trackLink, String preview, String picture, Integer addedDate, String position, List<Object> shareUrls) {
        this.platform = platform;
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.artistLink = artistLink;
        this.album = album;
        this.albumLink = albumLink;
        this.isrc = isrc;
        this.duration = duration;
        this.trackLink = trackLink;
        this.preview = preview;
        this.picture = picture;
        this.addedDate = addedDate;
        this.position = position;
        this.shareUrls = shareUrls;
        this.isFavorito = false;
        this.isReproduciendo = false;
    }

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

    public boolean isFavorito() {
        return isFavorito;
    }

    public void setFavorito(boolean favorito) {
        isFavorito = favorito;
    }

    public boolean isReproduciendo() {
        return isReproduciendo;
    }

    public void setReproduciendo(boolean reproduciendo) {
        isReproduciendo = reproduciendo;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "platform='" + platform + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", artistLink='" + artistLink + '\'' +
                ", album='" + album + '\'' +
                ", albumLink='" + albumLink + '\'' +
                ", isrc='" + isrc + '\'' +
                ", duration='" + duration + '\'' +
                ", trackLink='" + trackLink + '\'' +
                ", preview='" + preview + '\'' +
                ", picture='" + picture + '\'' +
                ", addedDate=" + addedDate +
                ", position='" + position + '\'' +
                ", shareUrls=" + shareUrls +
                ", isFavorito=" + isFavorito +
                ", isReproduciendo=" + isReproduciendo +
                '}';
    }
}