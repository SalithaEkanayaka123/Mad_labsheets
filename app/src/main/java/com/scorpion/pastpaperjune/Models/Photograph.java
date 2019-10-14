package com.scorpion.pastpaperjune.Models;

public class Photograph {
    private int id;
    private String name;
    private int artistId;
    private String category;
    private byte[] image;

    public Photograph() {
    }

    public Photograph(int id, String name, int artistId, String category, byte[] image) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.category = category;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
