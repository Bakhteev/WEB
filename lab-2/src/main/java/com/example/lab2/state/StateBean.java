package com.example.lab2.state;

import com.example.lab2.models.Point;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class StateBean implements Serializable {

    private static final long serialVersionUID = 6371964782683150733L;
    LinkedHashMap<String, LinkedList<Point>> map = new LinkedHashMap<>();

    public void add(String sessionId, Point point) {
        if (map.containsKey(sessionId)) {
            map.get(sessionId).add(point);
        } else {
            map.put(sessionId, new LinkedList<>());
            map.get(sessionId).add(point);
        }
    }

    public LinkedList<Point> getList(String sessionId) {
        return map.get(sessionId);
    }

    public void setMap(LinkedHashMap<String, LinkedList<Point>> map) {
        this.map = map;
    }

    public LinkedHashMap<String, LinkedList<Point>> getMap() {
        return map;
    }

    public boolean contains(String sessionId){
        return map.containsKey(sessionId);
    }
}
