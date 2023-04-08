package com.moh.hamadpulse.tableview.model;

import java.util.ArrayList;

public class MyRow {
    String id;
    String name;
    ArrayList<MyColumn> listMyCloumn;

    public MyRow(String id, String name) {
        this.id = id;
        this.name = name;
        this.listMyCloumn = new ArrayList<>();
    }

    public MyRow(String id, String name, ArrayList<MyColumn> listMyCloumn) {
        this.id = id;
        this.name = name;
        this.listMyCloumn = listMyCloumn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MyColumn> getListMyCloumn() {
        return listMyCloumn;
    }

    public void setListMyCloumn(ArrayList<MyColumn> listMyCloumn) {
        this.listMyCloumn = listMyCloumn;
    }
}
