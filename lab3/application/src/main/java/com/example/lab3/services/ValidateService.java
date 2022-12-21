package com.example.lab3.services;

public class ValidateService {
    public static boolean validateX(float x) {
        return x >= -5 && x <= 5;
    }

    public static boolean validateY(float y) {
        return y >= -5 && y <= 5;
    }

    public static boolean validateR(int r) {
        return r == 1 || r == 2 || r == 3 || r == 4 || r == 5;
    }
}

