
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultUserLogin implements Serializable{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statuscode")
    @Expose
    private String statuscode;
    @SerializedName("user_uid")
    @Expose
    private String userUid;
    @SerializedName("user_fname")
    @Expose
    private String userFname;
    @SerializedName("user_lname")
    @Expose
    private String userLname;
    @SerializedName("user_gender")
    @Expose
    private String userGender;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_birthday")
    @Expose
    private String userBirthday;
    @SerializedName("user_city_id")
    @Expose
    private String userCityId;
    @SerializedName("user_city_name")
    @Expose
    private String userCityName;
    @SerializedName("user_email_status")
    @Expose
    private String userEmailStatus;
    @SerializedName("user_phone_status")
    @Expose
    private String userPhoneStatus;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_newsletter")
    @Expose
    private String userNewsletter;
    @SerializedName("user_cid")
    @Expose
    private String userCid;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
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

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserCityId() {
        return userCityId;
    }

    public void setUserCityId(String userCityId) {
        this.userCityId = userCityId;
    }

    public String getUserCityName() {
        return userCityName;
    }

    public void setUserCityName(String userCityName) {
        this.userCityName = userCityName;
    }

    public String getUserEmailStatus() {
        return userEmailStatus;
    }

    public void setUserEmailStatus(String userEmailStatus) {
        this.userEmailStatus = userEmailStatus;
    }

    public String getUserPhoneStatus() {
        return userPhoneStatus;
    }

    public void setUserPhoneStatus(String userPhoneStatus) {
        this.userPhoneStatus = userPhoneStatus;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserNewsletter() {
        return userNewsletter;
    }

    public void setUserNewsletter(String userNewsletter) {
        this.userNewsletter = userNewsletter;
    }

    public String getUserCid() {
        return userCid;
    }

    public void setUserCid(String userCid) {
        this.userCid = userCid;
    }

}
