package com.lst.marrakechassistance.Model;

public class Station {
    private int id;
    private String name;

    public Station(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
