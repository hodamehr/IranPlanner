
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;

public class ResultComment implements Serializable {
    @SerializedName("user_id")
    @Expose
    private BigInteger userId;
    @SerializedName("comment_date")
    @Expose
    private String commentDate;
    @SerializedName("comment_body")
    @Expose
    private String commentBody;
    @SerializedName("comment_lang")
    @Expose
    private String commentLang;
    @SerializedName("comment_gtype")
    @Expose
    private String commentGtype;
    @SerializedName("comment_ntype")
    @Expose
    private String commentNtype;
    @SerializedName("comment_nid")
    @Expose
    private BigInteger commentNid;
    @SerializedName("comment_id")
    @Expose
    private BigInteger commentId;
    @SerializedName("user_fname")
    @Expose
    private String userFname;
    @SerializedName("user_lname")
    @Expose
    private String userLname;
    @SerializedName("user_gender")
    @Expose
    private String userGender;
    @SerializedName("user_register")
    @Expose
    private String userRegister;

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public String getCommentLang() {
        return commentLang;
    }

    public void setCommentLang(String commentLang) {
        this.commentLang = commentLang;
    }

    public String getCommentGtype() {
        return commentGtype;
    }

    public void setCommentGtype(String commentGtype) {
        this.commentGtype = commentGtype;
    }

    public String getCommentNtype() {
        return commentNtype;
    }

    public void setCommentNtype(String commentNtype) {
        this.commentNtype = commentNtype;
    }

    public BigInteger getCommentNid() {
        return commentNid;
    }

    public void setCommentNid(BigInteger commentNid) {
        this.commentNid = commentNid;
    }

    public BigInteger getCommentId() {
        return commentId;
    }

    public void setCommentId(BigInteger commentId) {
        this.commentId = commentId;
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

    public String getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(String userRegister) {
        this.userRegister = userRegister;
    }

}
