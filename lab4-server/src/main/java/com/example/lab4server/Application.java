package com.example.lab4server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        context.getBean(DispatcherServlet.class).setThrowExceptionIfNoHandlerFound(true);
    }

}
