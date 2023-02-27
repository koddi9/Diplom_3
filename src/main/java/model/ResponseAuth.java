package model;

public class ResponseAuth {

    String success;
    String accessToken;
    String refreshToken;
    User user;
    String message;

    public ResponseAuth() {
    }

    public ResponseAuth(String success, String accessToken, String refreshToken, User user, String message) {
        this.success = success;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
