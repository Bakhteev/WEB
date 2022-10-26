package com.example.lab2.state;

import com.example.lab2.models.Point;

import java.io.Serializable;
import java.util.LinkedList;

public class StateBean implements Serializable {

    private static final long serialVersionUID = 6371964782683150733L;
    LinkedList<Point> list = new LinkedList<>();

    public void add(Point point){
        list.add(point);
    }

    public LinkedList<Point> getList() {
        return list;
    }

    public void setList(LinkedList<Point> list) {
        this.list = list;
    }
}
