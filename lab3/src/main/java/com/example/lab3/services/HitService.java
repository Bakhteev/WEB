package com.example.lab3.services;

public class HitService {
    public boolean checkHit(float x, float y, float r) {
        if (x <= 0 && y >= 0)
            return (y <= x + r / 2);
        else if (x <= 0 && y <= 0)
            return (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2));
        else if (x >= 0 && y <= 0)
            return (x <= r && y >= -r / 2);
        else return false;
    }
}
