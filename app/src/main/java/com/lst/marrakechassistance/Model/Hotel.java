package com.lst.marrakechassistance.Model;

import java.io.Serializable;

public class Hotel extends Place implements Serializable {


    private String address;
    private String phone;
    private String website;
    private String price;
    private String type;
    private String properties;
    private String languages;
    private String styles;
    private String info;
    private String near_res;
    private String near_att;
    private String stars;

    public Hotel() {
    }

    public Hotel(String name, String address, String phone, String website, String description, String price, String type, String properties, String languages, String styles, String check_in, String info, String near_res, String near_att, String stars, String imgUrl) {
        super(name, address, imgUrl,description, "hotel");
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.price = price;
        this.type = type;
        this.properties = properties;
        this.languages = languages;
        this.styles = styles;
        this.info = info;
        this.near_res = near_res;
        this.near_att = near_att;
        this.stars = stars;
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



    public void setType(String type) {
        this.type = type;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public void setNear_res(String near_res) {
        this.near_res = near_res;
    }

    public void setNear_att(String near_att) {
        this.near_att = near_att;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }





    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }



    public String getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getProperties() {
        return properties;
    }

    public String getLanguages() {
        return languages;
    }

    public String getStyles() {
        return styles;
    }

    public String getInfo() {
        return info;
    }

    public String getNear_res() {
        return near_res;
    }

    public String getNear_att() {
        return near_att;
    }

    public String getStars() {
        return stars;
    }

}
