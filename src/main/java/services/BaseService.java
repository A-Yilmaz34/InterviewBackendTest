package services;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseService {

    protected static RequestSpecification defaultRequestSpecification(){
        return restAssured()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer "enter  your Bearer token here");   //please enter your bearer token
    }

    protected static RequestSpecification restAssured() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.urlEncodingEnabled = false;

        return given()
                .config(RestAssuredConfig.config()
                        .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
    }
}
