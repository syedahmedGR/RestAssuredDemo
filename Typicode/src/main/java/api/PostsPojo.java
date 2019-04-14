package api;

/**
 * Pojo class for Posts
 */
public class PostsPojo {

    private String title;
    private String body;
    private int userId;

    public PostsPojo() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
