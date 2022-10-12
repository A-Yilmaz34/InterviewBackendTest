import com.github.javafaker.Faker;
import models.CreateUserModel;

public class UserDataProvider {


    Faker faker = new Faker();
    private String name = faker.name().fullName();
    private String email = faker.internet().emailAddress();
    private String gender = faker.demographic().sex().toLowerCase();
    private String status = "active";


    public CreateUserModel getUserData() {

        return CreateUserModel.builder()
                .name(name)
                .gender(gender)
                .email(email)
                .status(status)
                .build();
    }

}
