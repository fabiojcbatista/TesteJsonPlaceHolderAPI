package org.example;

import io.restassured.RestAssured;
import org.example.entities.Post;
import org.junit.BeforeClass;
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
        given().
                contentType("application/json").
        when().
                get("posts").
        then().
                statusCode(200).
                contentType("application/json");

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
        given().
                contentType("application/json").
                body(post).
                when().
                post("posts").
                then().
                statusCode(201);

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
