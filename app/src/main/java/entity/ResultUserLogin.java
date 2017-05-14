
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class ResultUserLogin {

//    @SerializedName("status")
//    @Expose
//    private String status;
//    @SerializedName("statuscode")
//    @Expose
//    private Integer statuscode;
//    @SerializedName("user_email")
//    @Expose
//    private String userEmail;
//    @SerializedName("user_fname")
//    @Expose
//    private String userFname;
//    @SerializedName("user_lname")
//    @Expose
//    private String userLname;
//    @SerializedName("user_gender")
//    @Expose
//    private Integer userGender;
//    @SerializedName("user_uid")
//    @Expose
//    private BigInteger userUid;
//    @SerializedName("user_cid")
//    @Expose
//    private String userCid;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public Integer getStatuscode() {
//        return statuscode;
//    }
//
//    public void setStatuscode(Integer statuscode) {
//        this.statuscode = statuscode;
//    }
//
//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }
//
//    public String getUserFname() {
//        return userFname;
//    }
//
//    public void setUserFname(String userFname) {
//        this.userFname = userFname;
//    }
//
//    public String getUserLname() {
//        return userLname;
//    }
//
//    public void setUserLname(String userLname) {
//        this.userLname = userLname;
//    }
//
//    public Integer getUserGender() {
//        return userGender;
//    }
//
//    public void setUserGender(Integer userGender) {
//        this.userGender = userGender;
//    }
//
//    public BigInteger getUserUid() {
//        return userUid;
//    }
//
//    public void setUserUid(BigInteger userUid) {
//        this.userUid = userUid;
//    }
//
//    public String getUserCid() {
//        return userCid;
//    }
//
//    public void setUserCid(String userCid) {
//        this.userCid = userCid;
//    }
//


        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("statuscode")
        @Expose
        private Integer statuscode;
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
        private Integer userGender;
        @SerializedName("user_uid")
        @Expose
        private BigInteger userUid;
        @SerializedName("user_cid")
        @Expose
        private Integer userCid;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getStatuscode() {
            return statuscode;
        }

        public void setStatuscode(Integer statuscode) {
            this.statuscode = statuscode;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
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

        public Integer getUserGender() {
            return userGender;
        }

        public void setUserGender(Integer userGender) {
            this.userGender = userGender;
        }

        public BigInteger getUserUid() {
            return userUid;
        }

        public void setUserUid(BigInteger userUid) {
            this.userUid = userUid;
        }

        public Integer getUserCid() {
            return userCid;
        }

        public void setUserCid(Integer userCid) {
            this.userCid = userCid;
        }



}
