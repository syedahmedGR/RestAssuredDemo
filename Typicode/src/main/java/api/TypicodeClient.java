package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TypicodeClient {

    private String baseUri;
    private final static String POSTS_ENDPOINT = "/posts";

    public TypicodeClient() {
        this.baseUri = "https://jsonplaceholder.typicode.com";
    }

    // NOTE: this is ok but its preferred to validate the endpoints in the test class
    // This endpoint may have multiple scenarios where you can get a different status code
    // hence its preferred to have the return type as a validatable response
    public void getAllPosts() {
        given()
        .when()
                .get(this.baseUri + POSTS_ENDPOINT)
        .then()
                .statusCode(200);
    }

    /**
     * Creates a post
     *
     * @param json json object containing post information
     * @return ValidatableResponse for the endpoint
     */
    public ValidatableResponse createPost(Object json) {
        return given()
                .header("Content-Type", "application/json")
                .body(json)
               .when()
                .post(this.baseUri + POSTS_ENDPOINT)
               .then();
    }

    public Response createPostOne(Object json) {
        return given()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post(this.baseUri + POSTS_ENDPOINT);
    }

}
