package entity;

import java.io.Serializable;

/**
 * Created by h.vahidimehr on 03/03/2017.
 */

public class GetInfoReqSend implements Serializable {


    String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public GetInfoReqSend(String uid) {
        this.uid = uid;
    }
}
