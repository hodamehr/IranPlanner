package entity;

import java.io.Serializable;

/**
 * Created by h.vahidimehr on 03/03/2017.
 */

public class updateProfileSend implements Serializable {

    String fname;
    String lname;
    String email;
    String phone;
    String gender;
    String password;
    String uid;
    String token;
    String andId;

    public updateProfileSend(String fname, String lname, String email, String phone, String gender, String password, String uid, String token, String androidId) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
        this.token = token;
        this.uid=uid;
        this.andId = androidId;
    }

    public String getAndId() {
        return andId;
    }

    public void setAndId(String andId) {
        this.andId = andId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
