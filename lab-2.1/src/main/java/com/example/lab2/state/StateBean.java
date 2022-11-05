package com.example.lab2.state;

import com.example.lab2.models.Point;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class StateBean implements Serializable {

    private static final long serialVersionUID = 6371964782683150733L;
    LinkedHashMap<String, LinkedList<Point>> map = new LinkedHashMap<>();

    public void add(String userId, Point point) {
        if (map.containsKey(userId)) {
            map.get(userId).add(point);
        } else {
            map.put(userId, new LinkedList<>());
            map.get(userId).add(point);
        }
    }

    public LinkedList<Point> getList(String userId) {
        return map.get(userId);
    }

    public void setMap(LinkedHashMap<String, LinkedList<Point>> map) {
        this.map = map;
    }

    public LinkedHashMap<String, LinkedList<Point>> getMap() {
        return map;
    }

    public boolean contains(String userId){
        return map.containsKey(userId);
    }
}
