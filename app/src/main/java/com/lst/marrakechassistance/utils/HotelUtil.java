package com.lst.marrakechassistance.utils;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.lst.marrakechassistance.Model.Hotel;
import com.lst.marrakechassistance.Model.HotelModelClass;

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
            String title = data.get(PyObject.fromJava("Names")).toString();
            String address = data.get(PyObject.fromJava("address")).toString();
            String Tel = data.get(PyObject.fromJava("Tel")).toString();
            String website = data.get(PyObject.fromJava("website")).toString();
            String Description = data.get(PyObject.fromJava("Description")).toString();
            String Price = data.get(PyObject.fromJava("Price")).toString();
            String Type = data.get(PyObject.fromJava("Type")).toString();
            String Properties = data.get(PyObject.fromJava("Properties")).toString();
            String languages = data.get(PyObject.fromJava("languages")).toString();
            String Styles = data.get(PyObject.fromJava("Styles")).toString();
            String check_in = data.get(PyObject.fromJava("check in")).toString();
            String Check_out = data.get(PyObject.fromJava("Check-out")).toString();
            String info = data.get(PyObject.fromJava("info")).toString();
            String near_res = data.get(PyObject.fromJava("near_res")).toString();
            String near_att = data.get(PyObject.fromJava("near_att")).toString();
            String Stars = data.get(PyObject.fromJava("Stars")).toString();
            String imgUrl = data.get(PyObject.fromJava("img_url")).toString();

            hotels.add(new Hotel(title, address, Tel, website, Description,Price, Type, Properties,languages,Styles,check_in,Check_out,info,near_res,near_att,Stars, imgUrl));
        }
        return hotels;
    }


    public List<HotelModelClass> getHotels(String query){
        ArrayList<HotelModelClass> hotels = new ArrayList<>();
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
            HotelModelClass data = new HotelModelClass();
            data.setName(rr[0]);
            data.setType(rr[1]);
            data.setStars(rr[2]);
            data.setDescription(rr[3]);
            data.setProps(rr[9]);
            data.setAddress(rr[4]);
            data.setPhone(rr[5]);
            data.setWebsite(rr[6]);
            data.setNear_res(rr[7]);
            data.setNear_att(rr[8]);
            data.setImg(rr[11]);
            data.setGps(rr[12]);
            hotels.add(data);
        }
        return hotels;
    }
}
