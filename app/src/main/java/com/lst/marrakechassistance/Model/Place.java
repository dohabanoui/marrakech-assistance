package com.lst.marrakechassistance.Model;

import java.io.Serializable;

public class Place implements Serializable {
    private String name;
    private String address;
    private String imgUrl;
    private String description;
    private String placeType;
    private Boolean isFavorite;
    public Place() {
    }

    public Place(String name, String address, String imgUrl, String description, String placeType) {
        this.name = name;
        this.address = address;
        this.imgUrl = imgUrl;
        this.description = description;
        this.placeType = placeType;
        this.isFavorite = false;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }
    public String getPlaceType() {
        return placeType;
    }
    public Boolean getFavorite() {
        return isFavorite;
    }
    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
