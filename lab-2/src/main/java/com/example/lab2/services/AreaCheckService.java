package com.example.lab2.services;

import java.security.InvalidParameterException;
import java.util.LinkedList;

public class AreaCheckService {

    public void validate(int x, float y, float r) {
        LinkedList<Float> rValues = new LinkedList<Float>();
        rValues.add(1F);
        rValues.add(1.5F);
        rValues.add(2F);
        rValues.add(2.5F);
        rValues.add(3F);

        if (x < -5 || x > 3) {
            throw new InvalidParameterException("X must be from -5 to 3");
        }
        if (y < -3 || y > 3) {
            throw new InvalidParameterException("Y must be from -3 to 3");
        }
        if (!rValues.contains(r)) {
            throw new InvalidParameterException("R must be from -3 to 3");
        }
    }

    public Boolean checkHit(int x, float y, float r) {
        if (x >= 0 && y >= 0 && x <= r && y <= r) {
            return true;
        } else if (x >= 0 && y <= 0) {
            if (-y <= (r / 2 - x)) {
                return true;
            }
            return false;
        } else if (x <= 0 && y >= 0) {
            if ((Math.pow(x, 2) + Math.pow(y, 2)) <= Math.pow(r, 2)) {
                return true;
            }
            return false;
        } else return false;
    }
}
