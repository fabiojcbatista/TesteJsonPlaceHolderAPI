package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.headers.*;

import org.junit.BeforeClass;
import org.example.modelo.Post;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class JsonPlaceHolderAPITest {

    Post post = new Post(1, "Test: titulo", "Test: body", 3);

    @BeforeClass
    public static void urlbase() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmGetPosts_entaoDeveRetornarStatusCode200EBody() {
      
        Response response = given().
                contentType("application/json").
        when().
                get("posts").
        then().
                extract().
                response();

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("qui est esse", response.jsonPath().getString("title[1]"));
    
    }


    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmGetPosts_entaoDeveRetornarStatusCode200EBodySPEC() {
      
        given().
                spec(Request.get()).
        when().
                get("posts").
        then().
                spec(org.example.headers.Response.get());
    
    }

    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmGetComments_entaoDeveRetornarStatusCode200EBody() {
      
        Response response = given().
                contentType("application/json").
                param("postId", "2").
        when().
                get("comments").
        then().
                extract().
                response();

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("Meghan_Littel@rene.us", response.jsonPath().getString("email[3]"));
    
    }
   
    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmGetPostsPorId_entaoDeveRetornarStatusCode200() {
        given().
                contentType("application/json").
                pathParams("id", post.getId()).
        when().
                get("posts/{id}").
        then().
                statusCode(200).
                contentType("application/json");

    }


    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmPostNoPosts_entaoDeveRetornarStatusCode201() {
       
        String requestBody = "{\n" +
        "  \"title\": \"foo\",\n" +
        "  \"body\": \"bar\",\n" +
        "  \"userId\": \"1\" \n}";
       
        Response response = given().
             contentType("application/json").
             body(requestBody).
        when().
             post("posts").
        then().
             extract().response();

        Assert.assertEquals(201, response.statusCode());
        Assert.assertEquals("foo", response.jsonPath().getString("title"));
        Assert.assertEquals("bar", response.jsonPath().getString("body"));
        Assert.assertEquals("1", response.jsonPath().getString("userId"));
        Assert.assertEquals("101", response.jsonPath().getString("id"));


    }

    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmPutNoPosts_entaoDeveRetornarStatusCode200() {
        given().
             contentType("application/json").
             pathParams("id", post.getId()).
             body(post).
        when().
             put("posts/{id}").
        then().
             statusCode(200);

    }

    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmPutNoPosts_entaoDeveRetornarStatusCode200ComBody() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(post)
        .when()
                .put("/posts/1")
        .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("Test: titulo", response.jsonPath().getString("title"));
        Assert.assertEquals("Test: body", response.jsonPath().getString("body"));
        Assert.assertEquals("3", response.jsonPath().getString("userId"));
        Assert.assertEquals("1", response.jsonPath().getString("id"));
    }

    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmPatchNoPosts_entaoDeveRetornarStatusCode200() {

        String title = "{  \"title\":\"" +post.getTitle()+"\"}";
        given().
             contentType("application/json").
             pathParams("id", post.getId()).
             body(title).
        when().
             patch("posts/{id}").
        then().
             statusCode(200);

    }

    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmDeleteNoPosts_entaoDeveRetornarStatusCode200() {

        given().
             contentType("application/json").
             pathParams("id", post.getId()).
        when().
             delete("posts/{id}").
        then().
             statusCode(200);

    }

    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmGetNoPostsPorId_entaoDeveRetornarStatusCode200() {

        given().
             contentType("application/json").
             pathParams("id", post.getId()).
        when().
             get("posts?userId={id}").
        then().
             statusCode(200);

    }

    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmGetNoPostsPorComments_entaoDeveRetornarStatusCode200() {

        given().
             contentType("application/json").
             pathParams("id", post.getId()).
        when().
             get("posts/{id}/comments").
        then().
             statusCode(200);

    }

    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmGetNoAlbumPorPhotos_entaoDeveRetornarStatusCode200() {

        given().
             contentType("application/json").
             pathParams("id", post.getId()).
        when().
             get("albums/{id}/photos").
        then().
             statusCode(200);

    }

    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmGetNoUsersPorAlbum_entaoDeveRetornarStatusCode200() {

        given().
             contentType("application/json").
             pathParams("id", post.getId()).
        when().
             get("users/{id}/albums").
        then().
             statusCode(200);

    }

    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmGetNoUsersPorTodos_entaoDeveRetornarStatusCode200() {

        given().
             contentType("application/json").
             pathParams("id", post.getId()).
        when().
             get("users/{id}/todos").
        then().
             statusCode(200);

    }

    @Test
    public void dadoAPIJsonPlaceHolder_quandoRealizarUmGetNoUsersPorPosts_entaoDeveRetornarStatusCode200() {

        given().
             contentType("application/json").
             pathParams("id", post.getId()).
        when().
             get("users/{id}/posts").
        then().
             statusCode(200);

    }
   
}
