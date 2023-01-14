package com.example.lab4server.exceptionHandlers;

import com.example.lab4server.dto.ExceptionMessageDto;
import com.example.lab4server.httpExcetions.HttpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionMessageDto> catchHttpException(HttpException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ExceptionMessageDto(e.getStatusCode().value(), e.getMessage()), e.getStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionMessageDto> catchValidationException(MethodArgumentNotValidException e) {
        String errorMsg = "validation is failed!";
        if (e.getErrorCount() > 0) {
            List<String> errorDetails = new ArrayList<>();
            for (ObjectError error : e.getBindingResult().getAllErrors()) {
                errorDetails.add(error.getDefaultMessage());
            }

            if (errorDetails.size() > 0) errorMsg = errorDetails.get(0);
        }
        log.error(errorMsg);
        return new ResponseEntity<>(new ExceptionMessageDto(e.getStatusCode().value(), errorMsg), e.getStatusCode());
    }
}
