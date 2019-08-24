package com.example.myapplication;

import java.io.Serializable;

public class Tasks implements Serializable {
    private int id;
    private String task;

    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }




    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Tasks(int id, String task, String date) {
        this.id = id;
        this.task = task;

        this.date = date;
    }

    public Tasks() {
    }
}
