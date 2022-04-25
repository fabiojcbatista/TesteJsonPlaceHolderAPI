package org.example.headers;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class Response {
    
    private static ResponseSpecification response;
    
    public static ResponseSpecification get(){

        response = new ResponseSpecBuilder().
        expectStatusCode(200).
        expectContentType(ContentType.JSON).
        build();
        return response;

    }

   
}
