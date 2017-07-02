package entity;

import java.io.Serializable;

/**
 * Created by h.vahidimehr on 03/03/2017.
 */

public class GoogleLoginReqSend implements Serializable {


    String action;
    String email;
    String name;
    String family;
    String googleId;
    String imageUrl;


    public GoogleLoginReqSend(String action, String imageUrl, String googleId, String family, String name, String email) {
        this.action = action;
        this.imageUrl = imageUrl;
        this.googleId = googleId;
        this.family = family;
        this.name = name;
        this.email = email;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
