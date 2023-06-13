package com.lst.marrakechassistance.Model;



public class HotelModelClass {

    String name ;
    String description;
    String stars;
    String img;
    String type;
    String address;
    String website;
    String phone ;
    String near_att ;
    String near_res ;
    String props ;
    String price ;
    String gps;


    public HotelModelClass(String name, String description, String stars, String img, String type, String address, String website, String phone, String near_att, String near_res, String props, String price) {
        this.name = name;
        this.description = description;
        this.stars = stars;
        this.img = img;
        this.type = type;
        this.address = address;
        this.website = website;
        this.phone = phone;
        this.near_att = near_att;
        this.near_res = near_res;
        this.props = props;
        this.price = price;
    }

    @Override
    public String toString() {
        return "HotelModelClass{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stars='" + stars + '\'' +
                ", img='" + img + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", website='" + website + '\'' +
                ", phone='" + phone + '\'' +
                ", near_att='" + near_att + '\'' +
                ", near_res='" + near_res + '\'' +
                ", props='" + props + '\'' +
                ", price='" + price + '\'' +
                ", gps='" + gps + '\'' +
                '}';
    }

    public HotelModelClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String siteweb) {
        this.website = siteweb;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNear_att() {
        return near_att;
    }

    public void setNear_att(String near_att) {
        this.near_att = near_att;
    }

    public String getNear_res() {
        return near_res;
    }

    public void setNear_res(String near_res) {
        this.near_res = near_res;
    }

    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

}
