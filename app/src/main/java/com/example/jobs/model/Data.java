package com.example.jobs.model;

public class Data {
    String name;
    String strt;
    String end;
    String spin;
    int id;

    public Data() {
    }

    public Data(String name, String strt, String end, String spin,int id) {
        this.name = name;
        this.strt = strt;
        this.end = end;
        this.spin = spin;
       this.id=id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrt() {
        return strt;
    }

    public void setStrt(String strt) {
        this.strt = strt;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getSpin() {
        return spin;
    }

    public void setSpin(String spin) {
        this.spin = spin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
