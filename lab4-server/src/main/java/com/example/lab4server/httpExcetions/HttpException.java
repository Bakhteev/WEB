package com.example.lab4server.httpExcetions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class HttpException extends RuntimeException {
    HttpStatus statusCode;

    public HttpException(String message, HttpStatus statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public HttpException(String message){
        super(message);
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
