import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import models.CreateUserModel;
import models.UpdateUserModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.GoRestService;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

@Slf4j
public class UpdateUserTests {


    static UpdateUserModel testUpdateUser;

    /**
     * This method creates a user to provide data for update request tests
     */
    @BeforeAll
    public static void users_createUsers_success() {

        CreateUserModel createUserModel = new UserDataProvider().getUserData();
        ValidatableResponse response = GoRestService.createUser(createUserModel).then();
        log.info("Before Update user : " + response.extract().response().prettyPrint());
        testUpdateUser = response.extract().as(UpdateUserModel.class);

    }

    @Test
    public void users_updateUsers_success(){

         CreateUserModel randomUser = new UserDataProvider().getUserData();

        final UpdateUserModel updateUserModel = UpdateUserModel.builder()
                .name(randomUser.getName())
                .id(this.testUpdateUser.getId())
                .gender(randomUser.getGender())
                .email(randomUser.getEmail())
                .status("active")
                .build();

        log.info("created user model for update = " + updateUserModel.toString());

        ValidatableResponse body = GoRestService.updateUser(updateUserModel, testUpdateUser.getId())
                .then();


        log.info("response body after update user : " + body.extract().response());

        body.statusCode(SC_OK);
        body.body("name",equalTo(updateUserModel.getName()));
        body.body("gender",equalTo(updateUserModel.getGender()));
        body.body("email",equalTo(updateUserModel.getEmail()));
        body.body("status",equalTo(updateUserModel.getStatus()));
        body.body("id",equalTo(testUpdateUser.getId()));


    }
}
