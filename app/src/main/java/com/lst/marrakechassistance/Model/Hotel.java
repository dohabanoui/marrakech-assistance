package com.lst.marrakechassistance.Model;

import java.io.Serializable;

public class Hotel implements Serializable {
    private String title;
    private String address;
    private String Tel;
    private String Description;
    private String website;
    private String Price;
    private String Type;
    private String Properties;
    private String languages;
    private String Styles;
    private String ChekIn;
    private String CheckOut;
    private String info;
    private String near_res;
    private String near_att;
    private String Stars;
    private String imgUrl;




    public Hotel(String title, String address, String Tel, String website, String Description, String Price, String Type , String Properties, String languages, String Styles, String ChekIn , String CheckOut, String info, String near_res, String near_att, String Stars, String imgUrl) {
        this.title = title;
        this.address = address;
        this.Tel = Tel;
        this.website = website;
        this.Description = Description;
        this.Price = Price;
        this.Type = Type;
        this.Properties = Properties;
        this.languages = languages;
        this.Styles = Styles;
        this.ChekIn = ChekIn;
        this.CheckOut=CheckOut;
        this.info = info;
        this.near_res=near_res;
        this.near_att = near_att;
        this.Stars= Stars;
        this.imgUrl = imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getStars() {
        return Stars;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getProperties() {
        return Properties;
    }

    public void setProperties(String properties) {
        Properties = properties;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getStyles() {
        return Styles;
    }

    public void setStyles(String styles) {
        Styles = styles;
    }

    public String getChekIn() {
        return ChekIn;
    }

    public void setChekIn(String chekIn) {
        ChekIn = chekIn;
    }

    public String getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(String checkOut) {
        CheckOut = checkOut;
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

    public void setAddress(String address) {
        this.address = address;
    }


    public void setWebsite(String website) {
        this.website = website;
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



    public String getAddress() {
        return address;
    }


    public String getWebsite() {
        return website;
    }


}
