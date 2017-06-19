
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResult implements Serializable{

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Result_user_login")
    @Expose
    private ResultUserLogin resultUserLogin;
    @SerializedName("ParsDID")
    @Expose
    private ParsDID parsDID;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ResultUserLogin getResultUserLogin() {
        return resultUserLogin;
    }

    public void setResultUserLogin(ResultUserLogin resultUserLogin) {
        this.resultUserLogin = resultUserLogin;
    }

    public ParsDID getParsDID() {
        return parsDID;
    }

    public void setParsDID(ParsDID parsDID) {
        this.parsDID = parsDID;
    }

}
