package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by h.vahidimehr on 03/03/2017.
 */

public class updateProfileSend implements Serializable {

//    String fname;
//    String lname;
//    String email;
//    String phone;
//    String gender;
//    String password;
//    String uid;
//    String token;
//    String andId;
//
//    public updateProfileSend(String fname, String lname, String email, String phone, String gender, String password, String uid, String token, String androidId) {
//        this.fname = fname;
//        this.lname = lname;
//        this.email = email;
//        this.phone = phone;
//        this.gender = gender;
//        this.password = password;
//        this.token = token;
//        this.uid=uid;
//        this.andId = androidId;
//    }
//
//    public String getAndId() {
//        return andId;
//    }
//
//    public void setAndId(String andId) {
//        this.andId = andId;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public String getUid() {
//        return uid;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getLname() {
//        return lname;
//    }
//
//    public void setLname(String lname) {
//        this.lname = lname;
//    }
//
//    public String getFname() {
//        return fname;
//    }
//
//    public void setFname(String fname) {
//        this.fname = fname;
//    }

    @SerializedName("user_uid")
    @Expose
    private String userUid;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_fname")
    @Expose
    private String userFname;
    @SerializedName("user_lname")
    @Expose
    private String userLname;
    @SerializedName("user_gender")
    @Expose
    private String userGender;
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


    public updateProfileSend(String userUid, String userFname, String userLname,
                             String userGender, String userEmail, String userBirthday,
                             String userCityId, String userCityName, String userPhoneStatus,
                             String userEmailStatus, String userPhone, String userNewsletter, String userCid) {
        this.userUid = userUid;
        this.userFname = userFname;
        this.userLname = userLname;
        this.userGender = userGender;
        this.userEmail = userEmail;
        this.userBirthday = userBirthday;
        this.userCityId = userCityId;
        this.userCityName = userCityName;
        this.userPhoneStatus = userPhoneStatus;
        this.userEmailStatus = userEmailStatus;
        this.userPhone = userPhone;
        this.userNewsletter = userNewsletter;
        this.userCid = userCid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getUserCid() {
        return userCid;
    }

    public void setUserCid(String userCid) {
        this.userCid = userCid;
    }

    public String getUserNewsletter() {
        return userNewsletter;
    }

    public void setUserNewsletter(String userNewsletter) {
        this.userNewsletter = userNewsletter;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPhoneStatus() {
        return userPhoneStatus;
    }

    public void setUserPhoneStatus(String userPhoneStatus) {
        this.userPhoneStatus = userPhoneStatus;
    }

    public String getUserEmailStatus() {
        return userEmailStatus;
    }

    public void setUserEmailStatus(String userEmailStatus) {
        this.userEmailStatus = userEmailStatus;
    }

    public String getUserCityName() {
        return userCityName;
    }

    public void setUserCityName(String userCityName) {
        this.userCityName = userCityName;
    }

    public String getUserCityId() {
        return userCityId;
    }

    public void setUserCityId(String userCityId) {
        this.userCityId = userCityId;
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
}
