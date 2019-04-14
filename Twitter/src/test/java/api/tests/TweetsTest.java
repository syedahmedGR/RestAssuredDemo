package api.tests;

import client.tweets.TweetsClient;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.not;

public class TweetsTest {

    protected TweetsClient tweetsClient;
    protected long tweetId;
    protected List<Long> tweetIdList;

    @BeforeClass
    public void setUp() {
        this.tweetsClient = new TweetsClient();
        // List for all the tweet ids which will be used for cleanup
        this.tweetIdList = new ArrayList<Long>();
    }

    @Test(description = "Retrieves the user's tweets from their timeline")
    public void testGetUserTimeline() throws Exception {
        this.tweetsClient.getUserTimeline();
    }

    @Test(description = "User can successfully tweet")
    public void testUserCanTweet() throws Exception {
        String tweet = "Test " + UUID.randomUUID();
        ValidatableResponse response = this.tweetsClient.createTweet(tweet);
        response.statusCode(HttpStatus.SC_OK);
        this.tweetId = response.extract().body().path("id");
        this.tweetIdList.add(this.tweetId);
    }

    @Test(description = "User unsuccessfully tweets the same tweet twice")
    public void testUserCannotTweetTheSameTweetTwiceInARow() throws Exception {
        String tweet = "Test " + UUID.randomUUID();
        ValidatableResponse response = this.tweetsClient.createTweet(tweet);
        response.statusCode(HttpStatus.SC_OK);
        this.tweetId = response.extract().body().path("id");
        this.tweetIdList.add(this.tweetId);
        response = this.tweetsClient.createTweet(tweet);
        response.statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test(dependsOnMethods = {"testUserCanTweet"}, description = "User can successfully delete a tweet", enabled = false)
    public void testUserCanDeleteTweet() throws Exception {
        ValidatableResponse response = this.tweetsClient.deleteTweet(this.tweetId);
        response.statusCode(HttpStatus.SC_OK)
                .and()
                .body("$", not(hasValue(this.tweetId)));
    }

    @AfterClass(description = "Deletes all the tweets created by the tests")
    public void cleanUp() {
        if (this.tweetIdList != null) {
            for (Long tweetId : this.tweetIdList) {
                this.tweetsClient.deleteTweet(tweetId);
            }
        }
    }

}
