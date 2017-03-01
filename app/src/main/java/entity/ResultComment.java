
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultComment implements Serializable {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("ntype")
    @Expose
    private String ntype;
    @SerializedName("nid")
    @Expose
    private Integer nid;
    @SerializedName("gtype")
    @Expose
    private String gtype;
    @SerializedName("gvalue")
    @Expose
    private String gvalue;
    @SerializedName("commentid")
    @Expose
    private Integer commentid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNtype() {
        return ntype;
    }

    public void setNtype(String ntype) {
        this.ntype = ntype;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getGtype() {
        return gtype;
    }

    public void setGtype(String gtype) {
        this.gtype = gtype;
    }

    public String getGvalue() {
        return gvalue;
    }

    public void setGvalue(String gvalue) {
        this.gvalue = gvalue;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

}
