package com.lst.marrakechassistance.Model;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String name;
    private String address;
    private String phone;
    private String website;
    private String about;
    String price;
    private String cuisine;
    private String features;
    private String meals;
    private String guru_time;
    private String special_diets;
    private String info;
    private String near_res;
    private String near_hot;
    private String near_att;

    private String imgUrl;


    public Restaurant(String name, String address, String phone, String website, String about, String price , String cuisine, String features, String meals, String guru_time , String special_diets, String info, String near_res , String near_hot, String near_att, String imgUrl) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.about = about;
        this.price = price;
        this.cuisine = cuisine;
        this.features = features;
        this.meals = meals;
        this.guru_time = guru_time;
        this.special_diets = special_diets;
        this.info = info;
        this.near_res=near_res;
        this.near_hot=near_hot;
        this.near_att = near_att;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }



    public String getMeals() {
        return meals;
    }

    public String getGuru_time() {
        return guru_time;
    }

    public String getSpecial_diets() {
        return special_diets;
    }

    public String getInfo() {
        return info;
    }

    public String getNear_res() {
        return near_res;
    }

    public String getNear_hot() {
        return near_hot;
    }

    public String getNear_att() {
        return near_att;
    }


    public String getName() {
        return name;
    }
    public String getAbout() {
        return about;
    }

    public String getAddress() {
        return address;
    }
    public String getPrice() {
        return price;
    }

    public String getCuisine() {
        return cuisine;
    }
    public String getFeatures() {
        return features;
    }


    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }


}
