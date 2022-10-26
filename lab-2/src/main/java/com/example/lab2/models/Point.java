package com.example.lab2.models;

import java.io.Serializable;

public class Point implements Serializable {
    private static final long serialVersionUID = 2041275512219239990L;

    int x;
    float y;
    float r;

    boolean hitted;

    public Point() {
        this.x = 0;
        this.y = 0;
        this.r = 0;
        this.hitted = false;
    }


    public Point(int x, float y, float r, boolean hitted) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hitted = hitted;
    }

    public void setHitted(boolean hitted) {
        this.hitted = hitted;
    }

    public boolean isHitted() {
        return hitted;
    }

    public int getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setR(float r) {
        this.r = r;
    }
}
