package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.logging.Logger;

public class RestTest {

    private String baseURI;
    private static final Logger logger = Logger.getLogger(RestTest.class.getName());

    public RestTest() {
        baseURI = "https://the-internet.herokuapp.com/";
    }

    public void loginScenario(String username, String password) {
        logger.info("\nTesting with username: " + username + " and password: " + password);

        Response res = given()
                .urlEncodingEnabled(true)
                .param("username", username)
                .param("password", password)
                .when().post(baseURI + "authenticate")
                .then().extract().response();

        if (res.getStatusCode() == 200) {
            logger.info("Test passed!");
        } else {
            logger.info("Test failed with status code: " + res.getStatusCode());
            logger.info("Response Body: " + res.getBody().asString());
        }
    }

    public void runTests() {
        this.loginScenario("admin", "admin");
        this.loginScenario("tomsmith", "SuperSecretPassword");
        this.loginScenario("tomsmith", "SuperSecretPassword!");
    }

    public static void main(String[] args) {
        RestTest test = new RestTest();
        test.runTests();
    }
}