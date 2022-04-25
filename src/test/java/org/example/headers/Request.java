package org.example.headers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Request {
    
    private static RequestSpecification request;

    public static RequestSpecification get(){

       request = new RequestSpecBuilder().
            setBaseUri("https://jsonplaceholder.typicode.com").
            setContentType(ContentType.JSON).
            build();
        return request;

    }

    
}
