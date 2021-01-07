package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class RestAssuredOAuth2 {


    private static String userAdminClientId = System.getenv("M2M_USER_ADMIN_CLIENT_ID");
    private static String userAdminClientSecret = System.getenv("M2M_USER_ADMIN_CLIENT_SECRET");

    private static String oauth2Payload = "{\n" +
            "  \"client_id\": \"" + userAdminClientId + "\",\n" +
            "  \"client_secret\": \"" + userAdminClientSecret + "\",\n" +
            "  \"audience\": \"https://some-url.com/user\",\n" +
            "  \"grant_type\": \"client_credentials\",\n" +
            "  \"scope\": \"user:admin\" \n}";





    public static String getAccessToken() {
        RestAssured.baseURI = "https://some-url.com/user";
        return given()
                .contentType(ContentType.JSON)
                .body(oauth2Payload)
                .post("/token")
                .then().extract().response()
                .jsonPath().getString("access_token");
    }



}
