package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by h.vahidimehr on 18/06/2017.
 */

public class EmailVerifyReq implements Serializable {
    @SerializedName("user_uid")
    @Expose
    private String userUid;

    public String getUserEmail() {
        return userEmail;
    }

    public EmailVerifyReq(String userEmail, String userUid) {
        this.userEmail = userEmail;
        this.userUid = userUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @SerializedName("user_email")
    @Expose

    private String userEmail;
}
