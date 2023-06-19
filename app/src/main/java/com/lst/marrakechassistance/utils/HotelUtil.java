package com.lst.marrakechassistance.utils;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.lst.marrakechassistance.Model.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HotelUtil {
    private final Context context;
    public HotelUtil(Context context) {
        this.context = context;
    }
    public List<Hotel> getAllHotel(){
        ArrayList<Hotel> hotels= new ArrayList<>();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("script");
        List<PyObject> res = module.callAttr("getHotels").asList();
        for (PyObject item : res) {
            Map<PyObject, PyObject> data = item.asMap();
            String name = data.get(PyObject.fromJava("Names")).toString();
            String address = data.get(PyObject.fromJava("address")).toString();
            String phone = data.get(PyObject.fromJava("Tel")).toString();
            String website = data.get(PyObject.fromJava("website")).toString();
            String description = data.get(PyObject.fromJava("Description")).toString();
            String price = data.get(PyObject.fromJava("Price")).toString();
            String type = data.get(PyObject.fromJava("Type")).toString();
            String properties = data.get(PyObject.fromJava("Properties")).toString();
            String languages = data.get(PyObject.fromJava("languages")).toString();
            String styles = data.get(PyObject.fromJava("Styles")).toString();
            String check_in = data.get(PyObject.fromJava("check in")).toString();
            String check_out = data.get(PyObject.fromJava("Check-out")).toString();
            String info = data.get(PyObject.fromJava("info")).toString();
            String near_res = data.get(PyObject.fromJava("near_res")).toString();
            String near_att = data.get(PyObject.fromJava("near_att")).toString();
            String stars = data.get(PyObject.fromJava("Stars")).toString();
            String imgUrl = data.get(PyObject.fromJava("img_url")).toString();
            hotels.add(new Hotel(name,address,phone,website,description,price,type, properties, languages,styles, check_in, check_out, info, near_res,near_att, stars, imgUrl));
        }
        return hotels;
    }


    public List<Hotel> getHotels(String query){
        ArrayList<Hotel> hotels = new ArrayList<>();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("script");
        List<PyObject> res = module.callAttr("hotels", query).asList();
        for(int i=0;i<res.size();i++) {

            /* 0:Name1:Type 2:Stars  3:Description 4:address 5:Tel
            6:website 7:near_res 8:near_att 9:Properties 10:Styles 11:img*/
            String[] rr=res.get(i).toJava(String[].class);
            Hotel data = new Hotel();
            data.setName(rr[0]);
            data.setType(rr[1]);
            data.setStars(rr[2]);
            data.setDescription(rr[3]);
            data.setProperties(rr[9]);
            data.setAddress(rr[4]);
            data.setPhone(rr[5]);
            data.setWebsite(rr[6]);
            data.setNear_res(rr[7]);
            data.setNear_att(rr[8]);
            data.setImgUrl(rr[11]);
            hotels.add(data);
        }
        return hotels;
    }
}
