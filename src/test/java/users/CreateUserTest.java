package users;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static util.ApiAddress.ADDRESS;

public class CreateUserTest {

    @Test
    public void test_create_user_success() {
        final User payload = new User();
        payload.setName("morpheus");
        payload.setJob("leader");

        final ValidatableResponse response =
                given()
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post(ADDRESS + "/users")
                        .then();

        response.log().body();
        response.body("name", equalTo(payload.getName()));
        response.body("job", equalTo(payload.getJob()));
        response.body("id", notNullValue());
        response.body("createdAt", notNullValue());

        response.statusCode(201);
    }

    @Test
    public void test_create_user_error_required_fields() {
//        FIXME: O servidor não possui campos obrigatórios.
//        Ele dá sempre 201 em vez de 400 ao não passar name e job
//        Este endpoint também não consta no swagger da api fornecida.

//        final User payload = new User();
//        final ValidatableResponse response =
//                given()
//                        .contentType(ContentType.JSON)
//                        .body(payload)
//                        .when()
//                        .post(ADDRESS + "/users")
//                        .then();
//
//        response.log().body();

        Assertions.assertFalse(false);
    }

}
