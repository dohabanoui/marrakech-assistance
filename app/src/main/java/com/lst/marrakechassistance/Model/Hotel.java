package com.lst.marrakechassistance.Model;

import java.io.Serializable;

public class Hotel implements Serializable {

    private String name, address, phone, website, description, price, type, properties, languages, styles, check_in,  check_out,info,near_res,near_att, stars, imgUrl;

    public Hotel() {
    }

    public Hotel(String name, String address, String phone, String website, String description, String price, String type, String properties, String languages, String styles, String check_in, String check_out, String info, String near_res, String near_att, String stars, String imgUrl) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.description = description;
        this.price = price;
        this.type = type;
        this.properties = properties;
        this.languages = languages;
        this.styles = styles;
        this.check_in = check_in;
        this.check_out = check_out;
        this.info = info;
        this.near_res = near_res;
        this.near_att = near_att;
        this.stars = stars;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getDescription() {
        return description;
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

    public String getCheck_in() {
        return check_in;
    }

    public String getCheck_out() {
        return check_out;
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

    public String getImgUrl() {
        return imgUrl;
    }
}
