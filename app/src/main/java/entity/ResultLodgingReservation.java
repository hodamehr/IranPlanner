
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultLodgingReservation implements Serializable {

    @SerializedName("req_bundle_id")
    @Expose
    private String reqBundleId;
    @SerializedName("req_bundle_status")
    @Expose
    private String reqBundleStatus;
    @SerializedName("req_uid")
    @Expose
    private String reqUid;
    @SerializedName("req_cid")
    @Expose
    private String reqCid;
    @SerializedName("req_lodging_id")
    @Expose
    private String reqLodgingId;
    @SerializedName("req_lodging_type_id")
    @Expose
    private String reqLodgingTypeId;
    @SerializedName("req_date")
    @Expose
    private String reqDate;
    @SerializedName("req_lodging_date_from")
    @Expose
    private String reqLodgingDateFrom;
    @SerializedName("req_lodging_date_to")
    @Expose
    private String reqLodgingDateTo;
    @SerializedName("req_lodging_date_count")
    @Expose
    private String reqLodgingDateCount;
    @SerializedName("req_head_name_first")
    @Expose
    private String reqHeadNameFirst;
    @SerializedName("req_head_name_last")
    @Expose
    private String reqHeadNameLast;
    @SerializedName("req_head_mobile")
    @Expose
    private String reqHeadMobile;
    @SerializedName("req_head_email")
    @Expose
    private String reqHeadEmail;
    @SerializedName("req_head_int_bus")
    @Expose
    private Object reqHeadIntBus;
    @SerializedName("req_head_int_ply")
    @Expose
    private Object reqHeadIntPly;
    @SerializedName("req_price_final_net")
    @Expose
    private String reqPriceFinalNet;
    @SerializedName("req_price_final_discount")
    @Expose
    private String reqPriceFinalDiscount;
    @SerializedName("req_price_final_percent")
    @Expose
    private String reqPriceFinalPercent;
    @SerializedName("req_price_final_pay")
    @Expose
    private String reqPriceFinalPay;
    @SerializedName("req_lodging_reservation")
    @Expose
    private List<ReqLodgingReservation> reqLodgingReservation = null;

    public String getReqBundleId() {
        return reqBundleId;
    }

    public void setReqBundleId(String reqBundleId) {
        this.reqBundleId = reqBundleId;
    }

    public String getReqBundleStatus() {
        return reqBundleStatus;
    }

    public void setReqBundleStatus(String reqBundleStatus) {
        this.reqBundleStatus = reqBundleStatus;
    }

    public String getReqUid() {
        return reqUid;
    }

    public void setReqUid(String reqUid) {
        this.reqUid = reqUid;
    }

    public String getReqCid() {
        return reqCid;
    }

    public void setReqCid(String reqCid) {
        this.reqCid = reqCid;
    }

    public String getReqLodgingId() {
        return reqLodgingId;
    }

    public void setReqLodgingId(String reqLodgingId) {
        this.reqLodgingId = reqLodgingId;
    }

    public String getReqLodgingTypeId() {
        return reqLodgingTypeId;
    }

    public void setReqLodgingTypeId(String reqLodgingTypeId) {
        this.reqLodgingTypeId = reqLodgingTypeId;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getReqLodgingDateFrom() {
        return reqLodgingDateFrom;
    }

    public void setReqLodgingDateFrom(String reqLodgingDateFrom) {
        this.reqLodgingDateFrom = reqLodgingDateFrom;
    }

    public String getReqLodgingDateTo() {
        return reqLodgingDateTo;
    }

    public void setReqLodgingDateTo(String reqLodgingDateTo) {
        this.reqLodgingDateTo = reqLodgingDateTo;
    }

    public String getReqLodgingDateCount() {
        return reqLodgingDateCount;
    }

    public void setReqLodgingDateCount(String reqLodgingDateCount) {
        this.reqLodgingDateCount = reqLodgingDateCount;
    }

    public String getReqHeadNameFirst() {
        return reqHeadNameFirst;
    }

    public void setReqHeadNameFirst(String reqHeadNameFirst) {
        this.reqHeadNameFirst = reqHeadNameFirst;
    }

    public String getReqHeadNameLast() {
        return reqHeadNameLast;
    }

    public void setReqHeadNameLast(String reqHeadNameLast) {
        this.reqHeadNameLast = reqHeadNameLast;
    }

    public String getReqHeadMobile() {
        return reqHeadMobile;
    }

    public void setReqHeadMobile(String reqHeadMobile) {
        this.reqHeadMobile = reqHeadMobile;
    }

    public String getReqHeadEmail() {
        return reqHeadEmail;
    }

    public void setReqHeadEmail(String reqHeadEmail) {
        this.reqHeadEmail = reqHeadEmail;
    }

    public Object getReqHeadIntBus() {
        return reqHeadIntBus;
    }

    public void setReqHeadIntBus(Object reqHeadIntBus) {
        this.reqHeadIntBus = reqHeadIntBus;
    }

    public Object getReqHeadIntPly() {
        return reqHeadIntPly;
    }

    public void setReqHeadIntPly(Object reqHeadIntPly) {
        this.reqHeadIntPly = reqHeadIntPly;
    }

    public String getReqPriceFinalNet() {
        return reqPriceFinalNet;
    }

    public void setReqPriceFinalNet(String reqPriceFinalNet) {
        this.reqPriceFinalNet = reqPriceFinalNet;
    }

    public String getReqPriceFinalDiscount() {
        return reqPriceFinalDiscount;
    }

    public void setReqPriceFinalDiscount(String reqPriceFinalDiscount) {
        this.reqPriceFinalDiscount = reqPriceFinalDiscount;
    }

    public String getReqPriceFinalPercent() {
        return reqPriceFinalPercent;
    }

    public void setReqPriceFinalPercent(String reqPriceFinalPercent) {
        this.reqPriceFinalPercent = reqPriceFinalPercent;
    }

    public String getReqPriceFinalPay() {
        return reqPriceFinalPay;
    }

    public void setReqPriceFinalPay(String reqPriceFinalPay) {
        this.reqPriceFinalPay = reqPriceFinalPay;
    }

    public List<ReqLodgingReservation> getReqLodgingReservation() {
        return reqLodgingReservation;
    }

    public void setReqLodgingReservation(List<ReqLodgingReservation> reqLodgingReservation) {
        this.reqLodgingReservation = reqLodgingReservation;
    }

}
