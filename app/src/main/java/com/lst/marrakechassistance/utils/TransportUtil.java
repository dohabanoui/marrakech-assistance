package com.lst.marrakechassistance.utils;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.lst.marrakechassistance.Model.Transportt;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransportUtil {
    Context context;

    public TransportUtil(Context context) {
        this.context = context;
    }

    public List<Transportt> getAllTransport(){
        ArrayList<Transportt> transports = new ArrayList<>();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("script");
        List<PyObject> res = module.callAttr("getTransport").asList();
        for (PyObject item : res) {
            Map<PyObject, PyObject> data = item.asMap();
            String station_id = data.get(PyObject.fromJava("station_id")).toString();
            String ligne_id = data.get(PyObject.fromJava("ligne_id")).toString();
            String station_place = data.get(PyObject.fromJava("station_place")).toString();
            String ligne_num = data.get(PyObject.fromJava("ligne_num")).toString();


            transports.add(new Transportt(station_id, ligne_id, station_place, ligne_num));
        }
        return transports;
    }
}
