package com.lst.marrakechassistance.utils;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.lst.marrakechassistance.Model.AttractionModelClass;

import java.util.ArrayList;
import java.util.List;

public class AttractionsUtil {
    private final Context context;
    public AttractionsUtil(Context context) {
        this.context = context;
    }
    public List<AttractionModelClass> getAttractions(String query){
        ArrayList<AttractionModelClass> result = new ArrayList<>();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("my script");
        List<PyObject> res = module.callAttr("attractions", query).asList();
        for (int i = 0; i < res.size(); i++) {
            String[] rr=res.get(i).toJava(String[].class);
            AttractionModelClass data = new AttractionModelClass();

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
}
