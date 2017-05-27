package entity;

import java.io.Serializable;

/**
 * Created by h.vahidimehr on 03/03/2017.
 */

public class LoginReqSend implements Serializable {


    String action;
    String email;
    String password;
    String token;
    String androidId;

    public LoginReqSend(String action, String email, String password, String token, String androidId) {
        this.action = action;
        this.email = email;
        this.password = password;
        this.token = token;
        this.androidId = androidId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
