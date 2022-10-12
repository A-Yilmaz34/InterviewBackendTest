import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import models.CreateUserModel;
import models.UpdateUserModel;
import org.junit.jupiter.api.Test;
import services.GoRestService;

import java.util.UUID;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@Slf4j
public class CreateUserTests {


    @Test
    public void users_createUsers_success() {

         CreateUserModel createUserModel = new UserDataProvider().getUserData();

        ValidatableResponse response = GoRestService.createUser(createUserModel).then();

        log.info("response body of created object" + response.extract().response());
        response
                .statusCode(SC_CREATED)
                .body("name", equalTo(createUserModel.getName()))
                .body("gender", equalTo(createUserModel.getGender()))
                .body("status", equalTo(createUserModel.getStatus()))
                .body("email", equalTo(createUserModel.getEmail()));




    }


    @Test
    public void users_createUsers_withBlankData_fail() {

        final CreateUserModel createUserModel = CreateUserModel.builder()
                .build();


        ValidatableResponse response = GoRestService.createUser(createUserModel).then();

        log.info("response body of created object" + response.extract().response());
        response
                .statusCode(SC_UNPROCESSABLE_ENTITY);
    }


    @Test
    public void users_createUsers_withDuplicatedValues_fail() {

        CreateUserModel createUserModel = new UserDataProvider().getUserData();


        ValidatableResponse response = GoRestService.createUser(createUserModel).then();

        log.info("response body of created object" + response.extract().response());

        ValidatableResponse dublicatedResponse = GoRestService.createUser(createUserModel).then();
        log.info("response body from dublicated create object request : " + response.extract().response());

        dublicatedResponse
                .statusCode(SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void getAllUsers_success() {


        ValidatableResponse response = GoRestService.getUser()
                .then()
                .statusCode(SC_OK);


        System.out.println("response pretty print = " + response.extract().response().prettyPrint());
    }

}
