package com.example.lab2.controllers;

import jakarta.servlet.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class SignUpServletTest {
 private final String URL = "http://localhost:8000/signup";
    CloseableHttpClient client;

    @BeforeEach
    public void init() throws Exception {
        client = HttpClients.createDefault();
    }

    @Test
    void doPostWrong() throws IOException, ServletException {
        String json = "{\"email\":213213213, \"password\":222}";
        StringEntity entity = new StringEntity(json);
        HttpPost request = new HttpPost(URL);
        request.setEntity(entity);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(request);
        assertEquals(response.getStatusLine().getStatusCode(), 400);
    }

    @Test
    void doPostSuccess() throws IOException, ServletException {
        String json = "{\"email\":test@mail.ru, \"password\":123456789}";
        StringEntity entity = new StringEntity(json);
        HttpPost request = new HttpPost(URL);
        request.setEntity(entity);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(request);
        String tokensKey = response.getHeaders("Set-Cookie")[0].getValue().split("=")[0];
        assertEquals(tokensKey, "token");
    }
}