package com.lst.marrakechassistance.utils;

import android.content.Context;
import android.util.Log;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.lst.marrakechassistance.Model.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlaceUtil {
    Context context;

    public PlaceUtil(Context context) {
        this.context = context;
    }
    public void setFavorite(Place place){
        if (!Python.isStarted()){
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("script");
        module.callAttr("add_to_favorites",place.getName(), place.getAddress(), place.getDescription(), place.getPlaceType(), place.getImgUrl());
    }
    public List<Place> getFavPlaces(){
        ArrayList<Place> places = new ArrayList<Place>();
        if (!Python.isStarted()){
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("script");
        List<PyObject> placeList = module.callAttr("get_all_favorites").asList();
        Log.e("length", String.valueOf(placeList.size()));
        for (PyObject pyPlace : placeList) {
            Map<PyObject, PyObject> data = pyPlace.asMap();
            String name = data.get(PyObject.fromJava("title")).toString();
            String address = data.get(PyObject.fromJava("address")).toString();
            String description = data.get(PyObject.fromJava("description")).toString();
            String type = data.get(PyObject.fromJava("type")).toString();
            String imgUrl = data.get(PyObject.fromJava("imgUrl")).toString();
            Place place = new Place(name, address,imgUrl, description,type);
            places.add(place);
        }
        return places;
    }

    public void removeFavorite(Place place){
        if (!Python.isStarted()){
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("script");
        module.callAttr("remove_from_favorites",place.getName());
    }
}
