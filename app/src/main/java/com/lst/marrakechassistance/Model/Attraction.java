package com.lst.marrakechassistance.Model;

public class Attraction {
    String name;
    String category ;
    String adress ;
    String description ;
    String website;
    String sug_dur;
    String open_dur;
    String near_res ;
    String near_att;
    String img ;
    String gps;

    public Attraction(String name, String category, String adress, String description, String website, String sug_dur, String open_dur, String near_res, String near_att, String img) {
        this.name = name;
        this.category = category;
        this.adress = adress;
        this.description = description;
        this.website = website;
        this.sug_dur = sug_dur;
        this.open_dur = open_dur;
        this.near_res = near_res;
        this.near_att = near_att;
        this.img = img;
    }

    public Attraction() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSug_dur() {
        return sug_dur;
    }

    public void setSug_dur(String sug_dur) {
        this.sug_dur = sug_dur;
    }

    public String getOpen_dur() {
        return open_dur;
    }

    public void setOpen_dur(String open_dur) {
        this.open_dur = open_dur;
    }

    public String getNear_res() {
        return near_res;
    }

    public void setNear_res(String near_res) {
        this.near_res = near_res;
    }

    public String getNear_att() {
        return near_att;
    }

    public void setNear_att(String near_att) {
        this.near_att = near_att;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }
}
