package com.example.lab2.controllers;


import jakarta.servlet.ServletException;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AreaCheckServletTest {

    private final String URL = "http://localhost:8000";
    CloseableHttpClient client;

    @BeforeEach
    public void init() throws ServletException {
        client = HttpClients.createDefault();

    }

    @Test
    void doGetBadRequest() throws IOException {
        Header[] header = login();

        if(header.length == 0){
            header = registration();
        }
        int x = 10;
        int y = 100;
        int r = 12;

        String query = "/?x=" + x + "&y=" + y + "&r=" + r;
        HttpGet getRequest = new HttpGet(URL + query);
        getRequest.setHeader(header[0]);
        CloseableHttpResponse getResponse = client.execute(getRequest);
        System.out.println(Arrays.toString(getResponse.getAllHeaders()));
        assertEquals(400, getResponse.getStatusLine().getStatusCode());

    }

    @Test
    void doGetCreated() throws IOException {
        Header[] header = login();

        if(header.length == 0){
            header = registration();
        }
        int x = 1;
        int y = 2;
        int r = 3;

        String query = "/?x=" + x + "&y=" + y + "&r=" + r;
        HttpGet getRequest = new HttpGet(URL + query);
        getRequest.setHeader(header[0]);
        CloseableHttpResponse getResponse = client.execute(getRequest);
        assertEquals(201, getResponse.getStatusLine().getStatusCode());

    }

    private Header[] login() throws IOException {
        String json = "{\"email\":test@mail.ru, \"password\":123456789}";
        StringEntity entity = new StringEntity(json);
        HttpPost postRequest = new HttpPost(URL + "/login");
        postRequest.setEntity(entity);
        postRequest.setHeader("Accept", "application/json");
        postRequest.setHeader("Content-type", "application/json");
        CloseableHttpResponse postResponse = client.execute(postRequest);
        return postResponse.getHeaders("Set-Cookie");
    }

    private Header[] registration() throws IOException {
        String json = "{\"email\":test@mail.ru, \"password\":123456789}";
        StringEntity entity = new StringEntity(json);
        HttpPost postRequest = new HttpPost(URL + "/signup");
        postRequest.setEntity(entity);
        postRequest.setHeader("Accept", "application/json");
        postRequest.setHeader("Content-type", "application/json");
        CloseableHttpResponse postResponse = client.execute(postRequest);
        return postResponse.getHeaders("Set-Cookie");
    }
}