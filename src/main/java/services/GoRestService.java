package services;

import io.restassured.response.Response;
import models.CreateUserModel;
import models.UpdateUserModel;


public class GoRestService extends BaseService {

    public static Response createUser(final CreateUserModel createUserModel){
        return defaultRequestSpecification()
                .body(createUserModel)
                .when()
                .post("/public/v2/users");
    }

    public static Response updateUser(final UpdateUserModel updateUserModel, int id){
        return defaultRequestSpecification()
                .body(updateUserModel)
                .when()
                .put("/public/v2/users/"+id);
    }
    public static Response getUser(){
        return defaultRequestSpecification()
                .when()
                .get("/public/v2/users");
    }
}
