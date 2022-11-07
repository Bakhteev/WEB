package com.example.lab2.state;

import com.example.lab2.models.Point;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class HitState implements Serializable {

    private static final long serialVersionUID = 6371964782683150733L;
    LinkedHashMap<Integer, LinkedList<Point>> map = new LinkedHashMap<>();

    public void add(int userId, Point point) {
        if (map.containsKey(userId)) {
            map.get(userId).add(point);
        } else {
            map.put(userId, new LinkedList<>());
            map.get(userId).add(point);
        }
    }

    public LinkedList<Point> getList(int userId) {
        return map.get(userId);
    }

    public void createUsersList(int userId){
        map.put(userId, new LinkedList<>());
    }
    public void setMap(LinkedHashMap<Integer, LinkedList<Point>> map) {
        this.map = map;
    }

    public LinkedHashMap<Integer, LinkedList<Point>> getMap() {
        return map;
    }

    public boolean contains(int userId){
        return map.containsKey(userId);
    }
}
