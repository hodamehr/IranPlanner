
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;

public class ResultUserEmailVerify implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statuscode")
    @Expose
    private Integer statuscode;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_uid")
    @Expose
    private BigInteger userUid;
    @SerializedName("user_email_status")
    @Expose
    private Integer userEmailStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public BigInteger getUserUid() {
        return userUid;
    }

    public void setUserUid(BigInteger userUid) {
        this.userUid = userUid;
    }

    public Integer getUserEmailStatus() {
        return userEmailStatus;
    }

    public void setUserEmailStatus(Integer userEmailStatus) {
        this.userEmailStatus = userEmailStatus;
    }

}
