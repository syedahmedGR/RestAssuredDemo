package client.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Base Twitter Client class that sets up the api and token keys.
 * This class should be used as an extension for all twitter client classes.
 */
public class BaseTwitterClient {

    protected String baseUri;
    protected String apiKey;
    protected String apiSecretKey;
    protected String accessToken;
    protected String accessTokenSecret;
    private Properties properties = new Properties();
    private InputStream inputStream = null;

    public BaseTwitterClient() {
        // sets the base URI
        this.baseUri = "https://api.twitter.com/1.1";
        try {
            // path of the properties file
            this.inputStream = new FileInputStream("src/main/auth/auth.properties");
            // load the properties file
            this.properties.load(this.inputStream);
            // set the keys and tokens
            this.apiKey = this.properties.getProperty("apiKey");
            this.apiSecretKey = this.properties.getProperty("apiSecretKey");
            this.accessToken = this.properties.getProperty("accessToken");
            this.accessTokenSecret = this.properties.getProperty("accessTokenSecret");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (this.inputStream != null) {
                try {
                    this.inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Gets the full url
     *
     * @param endpoint path/resource being reached
     * @return full the full url
     */
    public String getFullUrl(String endpoint) {
        return this.baseUri + endpoint;
    }

}
