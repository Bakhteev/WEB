package com.example.lab4server.httpExcetions;


import org.springframework.http.HttpStatus;

public class NotFountException extends HttpException {
    public NotFountException(String message){
        super(message);
        this.statusCode = HttpStatus.NOT_FOUND;
    }

    public NotFountException(){
        super("Not Found");
        setStatusCode(HttpStatus.NOT_FOUND);
    }
}
