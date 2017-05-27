package entity;

import java.io.Serializable;

/**
 * Created by h.vahidimehr on 03/03/2017.
 */

public class RegisterReqSend implements Serializable {


    String action;
    String email;
    String password;
    String fname;
    String lname;
    String gender;
    String cid;
    String phone;
    String token;
    String andId;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAndroidId() {
        return andId;
    }

    public void setAndroidId(String androidId) {
        this.andId = androidId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
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

    public RegisterReqSend(String action,
                           String email,
                           String password,
                           String fname,
                           String lname,
                           String gender,
                           String cid,
                           String phone,
                           String andId) {
        this.action = action;
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.cid = cid;
        this.phone = phone;
        this.andId = andId;


    }
}
