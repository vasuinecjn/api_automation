package org.practice.testcases;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.practice.endpoints.UserEndPoints;
import org.practice.payload.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User user;

    @BeforeClass
    public void setUp() {
        faker = new Faker();
        user = new User();

        user.setId(faker.idNumber().hashCode());
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());
        user.setPassword(faker.internet().password(5, 10));
        user.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void createUser() {
        Response response = UserEndPoints.createUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(priority = 2)
    public void getUserByName() {
        Response response = UserEndPoints.getUser(user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }
}