package com.example.jobs.model;

public class TaskModel {
    String tskName,tskStrt,tskEnd,spin,tablId;
    int tskId;

    public String getTablId() {
        return tablId;
    }

    public void setTablId(String tablId) {
        this.tablId = tablId;
    }

    public TaskModel() {
    }

    public TaskModel(String tskName, String tskStrt, String tskEnd,String spin, int tskId,String tablId) {
        this.tskName = tskName;
        this.tskStrt = tskStrt;
        this.tskEnd = tskEnd;
        this.tskId = tskId;
        this.spin=spin;
        this.tablId=tablId;
    }

//    public int getTablId() {
//        return tablId;
//    }
//
//    public void setTablId(int tablId) {
//        this.tablId = tablId;
//    }

    public String getSpin() {
        return spin;
    }

    public void setSpin(String spin) {
        this.spin = spin;
    }

    public String getTskName() {
        return tskName;
    }

    public void setTskName(String tskName) {
        this.tskName = tskName;
    }

    public String getTskStrt() {
        return tskStrt;
    }

    public void setTskStrt(String tskStrt) {
        this.tskStrt = tskStrt;
    }

    public String getTskEnd() {
        return tskEnd;
    }

    public void setTskEnd(String tskEnd) {
        this.tskEnd = tskEnd;
    }

    public int getTskId() {
        return tskId;
    }

    public void setTskId(int tskId) {
        this.tskId = tskId;
    }
}
