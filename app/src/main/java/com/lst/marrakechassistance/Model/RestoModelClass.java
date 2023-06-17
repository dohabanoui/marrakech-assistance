package com.lst.marrakechassistance.Model;

public class RestoModelClass {

    String name ;
    String address;
    String img ;
    String description;
    String phone;
    String website;
    String kitchen;
    String feats;
    String meals;
    String spec_diets;
    String price;
    String near_att;
    String near_hot;
    String gps;


    public RestoModelClass(String name, String address, String img, String description, String phone, String website, String kitchen, String feats, String meals, String spec_diets, String price, String near_att, String near_hot) {
        this.name = name;
        this.address = address;
        this.img = img;
        this.description = description;
        this.phone = phone;
        this.website = website;
        this.kitchen = kitchen;
        this.feats = feats;
        this.meals = meals;
        this.spec_diets = spec_diets;
        this.price = price;
        this.near_att = near_att;
        this.near_hot = near_hot;
    }

    public RestoModelClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getFeats() {
        return feats;
    }

    public void setFeats(String feats) {
        this.feats = feats;
    }

    public String getMeals() {
        return meals;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public String getSpec_diets() {
        return spec_diets;
    }

    public void setSpec_diets(String spec_diets) {
        this.spec_diets = spec_diets;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNear_att() {
        return near_att;
    }

    public void setNear_att(String near_att) {
        this.near_att = near_att;
    }

    public String getNear_hot() {
        return near_hot;
    }

    public void setNear_hot(String near_hot) {
        this.near_hot = near_hot;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

}
