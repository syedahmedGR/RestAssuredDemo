package tests.api;

import api.PostsPojo;
import api.TypicodeClient;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


public class TypicodeTest {

    protected TypicodeClient typicodeClient;

    @BeforeClass
    public void initialize() {
        this.typicodeClient = new TypicodeClient();
    }

    @Test
    public void testGetAllPosts() {
        this.typicodeClient.getAllPosts();
    }

    @Test
    public void testCreatePost() {
        ValidatableResponse validatableResponse =
                this.typicodeClient.createPost("{\n" +
                "\t\"title\": \"fahim\",\n" +
                "\t\"body\": \"test123\",\n" +
                "\t\"userId\": 6\n" +
                "}");
        validatableResponse.statusCode(201);
    }

    @Test
    public void testCreatePostWithPojo() {
        PostsPojo pojo = new PostsPojo();
        pojo.setTitle("Title from IJ");
        pojo.setBody("Body from IJ");
        pojo.setUserId(7);

        ValidatableResponse validatableResponse =
                this.typicodeClient.createPost(pojo);
        validatableResponse.statusCode(201);
        String expectedTitle = validatableResponse.extract().body().path("title");
        String expectedBody = validatableResponse.extract().body().path("body");
        int expectedUserId = validatableResponse.extract().body().path("userId");
        assertThat(expectedTitle, is(equalTo(pojo.getTitle())));
        assertThat(expectedBody, is(equalTo(pojo.getBody())));
        assertThat(expectedUserId, is(equalTo(pojo.getUserId())));
    }

}
