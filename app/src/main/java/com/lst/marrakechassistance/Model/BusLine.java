package com.lst.marrakechassistance.Model;

import java.io.Serializable;

public class BusLine implements Serializable {
    String id, lineNum, depart, terminus;

    public BusLine(String id, String lineNum, String depart, String terminus) {
        this.id = id;
        this.lineNum = lineNum;
        this.depart = depart;
        this.terminus = terminus;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setTerminus(String terminus) {
        this.terminus = terminus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getLineNum() {
        return lineNum;
    }

    public String getDepart() {
        return depart;
    }

    public String getTerminus() {
        return terminus;
    }
}
