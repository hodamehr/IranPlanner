
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;

public class ResultUserInfo implements Serializable{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statuscode")
    @Expose
    private Integer statuscode;
    @SerializedName("user_uid")
    @Expose
    private BigInteger userUid;
    @SerializedName("user_fname")
    @Expose
    private String userFname;
    @SerializedName("user_lname")
    @Expose
    private String userLname;
    @SerializedName("user_gender")
    @Expose
    private Integer userGender;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_birthday")
    @Expose
    private Integer userBirthday;
    @SerializedName("user_city_id")
    @Expose
    private Integer userCityId;
    @SerializedName("user_city_name")
    @Expose
    private String userCityName;
    @SerializedName("user_email_status")
    @Expose
    private Integer userEmailStatus;
    @SerializedName("user_phone_status")
    @Expose
    private Integer userPhoneStatus;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_newsletter")
    @Expose
    private Integer userNewsletter;

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

    public BigInteger getUserUid() {
        return userUid;
    }

    public void setUserUid(BigInteger userUid) {
        this.userUid = userUid;
    }

    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public String getUserLname() {
        return userLname;
    }

    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Integer userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Integer getUserCityId() {
        return userCityId;
    }

    public void setUserCityId(Integer userCityId) {
        this.userCityId = userCityId;
    }

    public String getUserCityName() {
        return userCityName;
    }

    public void setUserCityName(String userCityName) {
        this.userCityName = userCityName;
    }

    public Integer getUserEmailStatus() {
        return userEmailStatus;
    }

    public void setUserEmailStatus(Integer userEmailStatus) {
        this.userEmailStatus = userEmailStatus;
    }

    public Integer getUserPhoneStatus() {
        return userPhoneStatus;
    }

    public void setUserPhoneStatus(Integer userPhoneStatus) {
        this.userPhoneStatus = userPhoneStatus;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserNewsletter() {
        return userNewsletter;
    }

    public void setUserNewsletter(Integer userNewsletter) {
        this.userNewsletter = userNewsletter;
    }

}
