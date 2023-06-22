package com.lst.marrakechassistance.utils;

import android.content.Context;
import android.util.Log;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.lst.marrakechassistance.Model.BusLine;
import com.lst.marrakechassistance.Model.Station;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransportUtil {
    Context context;

    public TransportUtil(Context context) {
        this.context = context;
    }

    public List<BusLine> getAllTransport(){
        // return bus lines
        ArrayList<BusLine> lines = new ArrayList<>();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("script");
        List<PyObject> res = module.callAttr("getTransport").asList();
        for (PyObject item : res) {
            Map<PyObject, PyObject> data = item.asMap();
            String id = data.get(PyObject.fromJava("id")).toString();
            String lineNum = data.get(PyObject.fromJava("line")).toString();
            String depart = data.get(PyObject.fromJava("depart")).toString();
            String terminus = data.get(PyObject.fromJava("terminus")).toString();
            lines.add(new BusLine(id, lineNum, depart, terminus));
        }
        return lines;
    }


    public List<Station> getStationByID(String id) {
        ArrayList<Station> stations = new ArrayList<Station>();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("script");
        List<PyObject> res = module.callAttr("get_stations_by_line", id).asList();
        for (PyObject item : res) {
            Map<PyObject, PyObject> data = item.asMap();
             String stationId =data.get(PyObject.fromJava("station_id")).toString();
             String stationName = data.get(PyObject.fromJava("station_place")).toString();
             stations.add(new Station(Integer.parseInt(stationId), stationName));
        }
        return stations;
    }
}
