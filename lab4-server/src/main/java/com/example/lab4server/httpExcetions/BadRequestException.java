package com.example.lab4server.httpExcetions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpException {

    public BadRequestException() {
        super("Bad request");
        this.statusCode = HttpStatus.BAD_REQUEST;
    }

    public BadRequestException(String message) {
        super(message);
        this.statusCode = HttpStatus.BAD_REQUEST;
    }
}

