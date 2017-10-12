package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by HoDA on 10/12/2017.
 */

public class SendEmailResetPassword implements Serializable {
    @SerializedName("value")
    @Expose
    private String email;

    public SendEmailResetPassword(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
