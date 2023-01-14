package com.example.lab4server.httpExcetions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpException {
    public UnauthorizedException(String message){
        super(message);
        this.statusCode = HttpStatus.UNAUTHORIZED;
    }

    public UnauthorizedException(){
        super("Unauthorized");
        setStatusCode(HttpStatus.UNAUTHORIZED);
    }
}
