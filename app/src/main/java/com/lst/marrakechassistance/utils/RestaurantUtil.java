package com.lst.marrakechassistance.utils;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.lst.marrakechassistance.Model.RestoModelClass;

import java.util.ArrayList;
import java.util.List;

public class RestaurantUtil {
    private final Context context;
    public RestaurantUtil(Context context) {
        this.context = context;
    }
    public List<RestoModelClass> getRestaurants(String query){
        ArrayList<RestoModelClass> list = new ArrayList<>();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("my script");
        List<PyObject> res = module.callAttr("restos", query).asList();
        for(int i=0;i<res.size();i++) {

            String[] rr=res.get(i).toJava(String[].class);
            RestoModelClass data = new RestoModelClass();

            data.setName(rr[0]);
            data.setAddress(rr[1]);
            data.setDescription(rr[2]);
            data.setPhone(rr[3]);
            data.setWebsite(rr[4]);
            data.setKitchen(rr[5]);
            data.setFeats(rr[6]);
            data.setMeals(rr[7]);
            data.setPrice(rr[8]);

            data.setNear_att(rr[10]);
            data.setNear_hot(rr[11]);

            data.setSpec_diets(rr[13]);
            data.setImg("r"+rr[14]);
            data.setGps(rr[15]);
            list.add(data);
        }
        return list;
    }
}
