
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;

public class ResultComment implements Serializable {

    @SerializedName("uid")
    @Expose
    private BigInteger uid;
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
    private BigInteger commentid;

    public BigInteger getUid() {
        return uid;
    }

    public void setUid(BigInteger uid) {
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

    public BigInteger getCommentid() {
        return commentid;
    }

    public void setCommentid(BigInteger commentid) {
        this.commentid = commentid;
    }
}
