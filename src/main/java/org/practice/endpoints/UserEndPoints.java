package org.practice.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.practice.payload.User;

public class UserEndPoints {

    public static Response createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .when()
                .post(Routes.createUserUrl);
    }

    public static Response getUser(String userName) {
        return given()
                .pathParams("username", userName)
                .when()
                .get(Routes.getUserUrl);
    }

    public static Response updateUser(String userName, User user) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username", userName)
                .body(user)
                .when()
                .put(Routes.updateUserUrl);
    }

    public static Response deleteUser(String userName) {
        return given()
                .pathParams("username", userName)
                .when()
                .delete(Routes.deleteUserUrl);
    }
}
