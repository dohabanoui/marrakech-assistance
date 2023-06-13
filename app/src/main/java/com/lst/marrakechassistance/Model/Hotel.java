package com.lst.marrakechassistance.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Hotel implements Serializable {
    private String name;
    private String address;
    private String phone;
    private String website;
    private String type;
    private String price;
    private double lat, lng;
    float distance;

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDistance() {
        return distance;
    }

    public Hotel(String name, String address, String phone, String website, String type, String price) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.type = type;
        this.price = price;
    }

    public Hotel(String name, String address, String phone, String website, String price, String type, double lat, double lng) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.price = price;
        this.type = type;
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getLng() {
        return lng;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @NonNull
    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", type='" + type + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getType() {
        return type;
    }
}
