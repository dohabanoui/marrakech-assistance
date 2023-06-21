package com.lst.marrakechassistance.Model;

import java.io.Serializable;

public class BusLine implements Serializable {
    private String ID;
    private String ligne_id;
    private String station;
    private String ligne_num;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLigne_id() {
        return ligne_id;
    }

    public void setLigne_id(String ligne_id) {
        this.ligne_id = ligne_id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getLigne_num() {
        return ligne_num;
    }

    public void setLigne_num(String ligne_num) {
        this.ligne_num = ligne_num;
    }

    public BusLine(String ID, String ligne_id, String station, String ligne_num) {
        this.ID = ID;
        this.ligne_id = ligne_id;
        this.station = station;
        this.ligne_num = ligne_num;

    }
}
