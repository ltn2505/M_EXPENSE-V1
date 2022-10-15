package com.jovanovic.stefan.sqlitetutorial;

import java.io.Serializable;

public class Trip implements Serializable {
    private int id;
    private String name;
    private String des;
    private String date;
    private String desc;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



    public Trip(int id, String name, String des, String date, String desc) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.date = date;
        this. desc=desc;
    }

    public Trip(String name, String des, String date, String desc) {
        this.name = name;
        this.des = des;
        this.date = date;
        this. desc=desc;
    }

    public Trip() {

    }



    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", des='" + des + '\'' +
                ", date='" + date + '\'' +
                ", desc=" + desc +
                '}';
    }
}
