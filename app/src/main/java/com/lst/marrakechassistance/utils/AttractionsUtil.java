package com.lst.marrakechassistance.utils;

import android.content.Context;
import android.util.Log;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.lst.marrakechassistance.Model.Attraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttractionsUtil {
    private final Context context;
    public AttractionsUtil(Context context) {
        this.context = context;
    }
    public List<Attraction> getAttractions(String query){
        ArrayList<Attraction> result = new ArrayList<>();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("script");
        List<PyObject> res = module.callAttr("attractions", query).asList();
        for (int i = 0; i < res.size(); i++) {
            String[] rr=res.get(i).toJava(String[].class);
            Attraction data = new Attraction();

            data.setName(rr[0]);
            data.setCategory(rr[1]);
            data.setAdress(rr[2]);
            data.setImg(rr[9]);
            data.setDescription(rr[3]);
            data.setOpen_dur(rr[6]);
            data.setSug_dur(rr[5]);
            data.setWebsite(rr[4]);
            data.setNear_res(rr[7]);
            data.setNear_att(rr[8]);
            data.setGps(rr[10]);
            result.add(data);
        }
        return result;
    }


    public List<Attraction> getAllAttractions(){
        ArrayList<Attraction> attractions = new ArrayList<>();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("script");
        List<PyObject> res = module.callAttr("getAttractions").asList();
        for (PyObject item : res) {
            Map<PyObject, PyObject> data = item.asMap();
            String name = data.get(PyObject.fromJava("Names")).toString();
            String address = data.get(PyObject.fromJava("adress")).toString();
            String description = data.get(PyObject.fromJava("description")).toString();
            String website = data.get(PyObject.fromJava("WebSite")).toString();
            String sugDur = data.get(PyObject.fromJava("Suggested duration")).toString();
            String openDur;
            try {
                openDur = data.get(PyObject.fromJava("Open duration")).toString();
            }catch (NullPointerException e){
                openDur = "";
            }
            String category = data.get(PyObject.fromJava("category")).toString();
            String nearRes = data.get(PyObject.fromJava("near_res")).toString();
            String nearAtt = data.get(PyObject.fromJava("near_att")).toString();
            String img = data.get(PyObject.fromJava("img_url")).toString();
            attractions.add(new Attraction(name, category,address,description ,website, sugDur, openDur,nearRes, nearAtt, img));
           // Log.d("Attractions", "Attraction: " + category);
        }
        return attractions;
    }
}
