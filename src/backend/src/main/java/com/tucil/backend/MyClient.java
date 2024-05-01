package com.tucil.backend;

import org.springframework.web.client.RestTemplate;

public class MyClient {

    public static void main(String[] args) {
        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the endpoint
        String url = "http://localhost:8080/api/data";

        // Define the query parameters
        String query1 = "value1";
        String query2 = "value2";

        // Make the GET request to the endpoint and retrieve the response
        String response = restTemplate.getForObject(url + "?query1={query1}&query2={query2}", String.class, query1, query2);

        // Process the response
        System.out.println("Response from backend: " + response);
    }
}
