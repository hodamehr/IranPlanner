package entity;

import java.io.Serializable;

/**
 * Created by h.vahidimehr on 03/03/2017.
 */

public class CommentSend implements Serializable {
    //{"uid":"1","cid":"1","ntype":"attraction","nid":"1","gtype":"comment","gvalue":"khobi"}
    String uid;
    String cid;
    String ntype;
    String nid;
    String gtype;
    String gvalue;

    public CommentSend(String uid, String cid, String ntype, String nid, String gtype, String gvalue) {
        this.uid = uid;
        this.cid = cid;
        this.ntype = ntype;
        this.nid = nid;
        this.gtype = gtype;
        this.gvalue = gvalue;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGvalue() {
        return gvalue;
    }

    public void setGvalue(String gvalue) {
        this.gvalue = gvalue;
    }

    public String getGtype() {
        return gtype;
    }

    public void setGtype(String gtype) {
        this.gtype = gtype;
    }

    public String getNtype() {
        return ntype;
    }

    public void setNtype(String ntype) {
        this.ntype = ntype;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
