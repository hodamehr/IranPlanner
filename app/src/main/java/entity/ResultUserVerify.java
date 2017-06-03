
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultUserVerify implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statuscode")
    @Expose
    private Integer statuscode;
    @SerializedName("Phone_Verify")
    @Expose
    private Integer phoneVerify;
    @SerializedName("Phone_Register")
    @Expose
    private Integer phoneRegister;

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

    public Integer getPhoneVerify() {
        return phoneVerify;
    }

    public void setPhoneVerify(Integer phoneVerify) {
        this.phoneVerify = phoneVerify;
    }

    public Integer getPhoneRegister() {
        return phoneRegister;
    }

    public void setPhoneRegister(Integer phoneRegister) {
        this.phoneRegister = phoneRegister;
    }

}
