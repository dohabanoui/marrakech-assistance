package com.lst.marrakechassistance.Model;

import java.io.Serializable;

public class Restaurant extends Place implements Serializable {

    private String phone, website,cuisine, features, meals, guru_time, special_diets, price,info, near_res, near_hot, near_att, imgUrl;

    public Restaurant() {
    }

    public Restaurant(String name, String address, String description, String phone, String website, String cuisine, String features, String meals, String guru_time, String special_diets, String price, String info, String near_res, String near_hot, String near_att, String imgUrl) {
        super(name, address, imgUrl, description, "restaurant");
        this.phone = phone;
        this.website = website;
        this.cuisine = cuisine;
        this.features = features;
        this.meals = meals;
        this.guru_time = guru_time;
        this.special_diets = special_diets;
        this.price = price;
        this.info = info;
        this.near_res = near_res;
        this.near_hot = near_hot;
        this.near_att = near_att;
        this.imgUrl = imgUrl;
    }
    public String getPhone() {
        return phone;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getFeatures() {
        return features;
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

    public String getPrice() {
        return price;
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

    public String getImgUrl() {
        return imgUrl;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public void setSpecial_diets(String special_diets) {
        this.special_diets = special_diets;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setNear_hot(String near_hot) {
        this.near_hot = near_hot;
    }

    public void setNear_att(String near_att) {
        this.near_att = near_att;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
