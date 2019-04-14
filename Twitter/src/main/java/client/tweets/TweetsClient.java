package client.tweets;

import client.base.BaseTwitterClient;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TweetsClient extends BaseTwitterClient {

    protected final static String USER_TIMELINE_ENDPOINT = "/statuses/user_timeline.json";
    protected final static String TWEET_ENDPOINT = "/statuses/update.json";
    protected final static String DESTROY_ENDPOINT = "/statuses/destroy.json";

    /**
     * Retrieves the user's tweets timeline.
     *
     * @return ValidatableResponse for the endpoint
     */
    public ValidatableResponse getUserTimeline() {
        return given()
                .auth()
                .oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
               .when()
                .get(this.getFullUrl(USER_TIMELINE_ENDPOINT))
               .then();
    }

    /**
     * Creates a tweet by the user
     *
     * @param tweet the tweet to be created
     * @return ValidatableResponse for the endpoint
     */
    public ValidatableResponse createTweet(String tweet) {
        return given()
                .auth()
                .oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
               .when()
                .post(this.getFullUrl(TWEET_ENDPOINT))
               .then();
    }

    /**
     * Deletes the tweet of a user based on the tweet id
     *
     * @param tweetId the tweet id
     * @return ValidatableResponse for the endpoint
     */
    public ValidatableResponse deleteTweet(Long tweetId) {
       return given()
                .auth()
                .oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
               .queryParam("id", tweetId)
              .when()
               .post(this.getFullUrl(DESTROY_ENDPOINT))
              .then();
    }

}
