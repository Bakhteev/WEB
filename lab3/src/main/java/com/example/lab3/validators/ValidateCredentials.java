package com.example.lab3.validators;


public class ValidateCredentials {
    public static boolean validateCredentials(String email, String password){
        boolean emailMatches = email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        boolean passwordMatches = password.length() > 8;
        return passwordMatches && emailMatches;
    }
}
