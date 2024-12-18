package com.lst.marrakechassistance.utils;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.lst.marrakechassistance.Model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestaurantUtil {
    private final Context context;
    public RestaurantUtil(Context context) {
        this.context = context;
    }



    public List<Restaurant> getAllRestaurant(){
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("script");
        List<PyObject> res = module.callAttr("getRestaurants").asList();
        for (PyObject item : res) {
            Map<PyObject, PyObject> data = item.asMap();
            String name = data.get(PyObject.fromJava("Names")).toString();
            String address = data.get(PyObject.fromJava("ad_adress")).toString();
            String tel = data.get(PyObject.fromJava("ad_Phone")).toString();
            String price = data.get(PyObject.fromJava("ad_prices")).toString();
            String website = data.get(PyObject.fromJava("ad_WebSite")).toString();
            String about = data.get(PyObject.fromJava("ad_about")).toString();
            String cuisine = data.get(PyObject.fromJava("ad_cuisine")).toString();
            String features = data.get(PyObject.fromJava("ad_features")).toString();
            String meals = data.get(PyObject.fromJava("ad_meals")).toString();
            String guru_time = data.get(PyObject.fromJava("guru_time")).toString();
            String special_diets = data.get(PyObject.fromJava("special_diets")).toString();
            String info = data.get(PyObject.fromJava("info")).toString();
            String near_res = data.get(PyObject.fromJava("near_res")).toString();
            String near_hot = data.get(PyObject.fromJava("near_hot")).toString();
            String near_att = data.get(PyObject.fromJava("near_att")).toString();
            String imgUrl = data.get(PyObject.fromJava("img_url")).toString();
            restaurants.add(new Restaurant(name, address, about, tel, website, cuisine, features, meals, guru_time,special_diets,price ,info ,near_res ,near_hot ,near_att, imgUrl));
        }
        return restaurants;
    }



    public List<Restaurant> getRestaurants(String query){
        ArrayList<Restaurant> list = new ArrayList<>();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("script");
        List<PyObject> res = module.callAttr("restos", query).asList();
        for(int i=0;i<res.size();i++) {

            String[] rr=res.get(i).toJava(String[].class);
            Restaurant data = new Restaurant();

            data.setName(rr[0]);
            data.setAddress(rr[1]);
            data.setDescription(rr[2]);
            data.setPhone(rr[3]);
            data.setWebsite(rr[4]);
            data.setCuisine(rr[5]);
            data.setFeatures(rr[6]);
            data.setMeals(rr[7]);
            data.setPrice(rr[8]);

            data.setNear_att(rr[10]);
            data.setNear_hot(rr[11]);

            data.setSpecial_diets(rr[13]);
            data.setImgUrl(rr[14]);
            list.add(data);
        }
        return list;
    }
}
