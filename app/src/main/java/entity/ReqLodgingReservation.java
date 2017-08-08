
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReqLodgingReservation implements Serializable{

    @SerializedName("req_room_no")
    @Expose
    private String reqRoomNo;
    @SerializedName("req_room_req_id")
    @Expose
    private String reqRoomReqId;
    @SerializedName("req_room_id")
    @Expose
    private String reqRoomId;
    @SerializedName("req_room_lodging_id")
    @Expose
    private String reqRoomLodgingId;
    @SerializedName("req_room_status")
    @Expose
    private String reqRoomStatus;
    @SerializedName("req_room_bundle_id")
    @Expose
    private String reqRoomBundleId;
    @SerializedName("req_room_name_first")
    @Expose
    private String reqRoomNameFirst;
    @SerializedName("req_room_name_last")
    @Expose
    private String reqRoomNameLast;
    @SerializedName("req_room_nation")
    @Expose
    private String reqRoomNation;
    @SerializedName("req_room_extra_count")
    @Expose
    private String reqRoomExtraCount;
    @SerializedName("req_room_desciption")
    @Expose
    private String reqRoomDesciption;
    @SerializedName("req_room_price_net")
    @Expose
    private String reqRoomPriceNet;
    @SerializedName("req_room_price_person")
    @Expose
    private String reqRoomPricePerson;
    @SerializedName("req_room_price_person_sum")
    @Expose
    private String reqRoomPricePersonSum;
    @SerializedName("req_room_price_half_in")
    @Expose
    private String reqRoomPriceHalfIn;
    @SerializedName("req_room_price_half_out")
    @Expose
    private String reqRoomPriceHalfOut;
    @SerializedName("req_room_price_discount")
    @Expose
    private String reqRoomPriceDiscount;
    @SerializedName("req_room_price_calc")
    @Expose
    private String reqRoomPriceCalc;
    @SerializedName("req_room_price_final")
    @Expose
    private String reqRoomPriceFinal;

    public String getReqRoomNo() {
        return reqRoomNo;
    }

    public void setReqRoomNo(String reqRoomNo) {
        this.reqRoomNo = reqRoomNo;
    }

    public String getReqRoomReqId() {
        return reqRoomReqId;
    }

    public void setReqRoomReqId(String reqRoomReqId) {
        this.reqRoomReqId = reqRoomReqId;
    }

    public String getReqRoomId() {
        return reqRoomId;
    }

    public void setReqRoomId(String reqRoomId) {
        this.reqRoomId = reqRoomId;
    }

    public String getReqRoomLodgingId() {
        return reqRoomLodgingId;
    }

    public void setReqRoomLodgingId(String reqRoomLodgingId) {
        this.reqRoomLodgingId = reqRoomLodgingId;
    }

    public String getReqRoomStatus() {
        return reqRoomStatus;
    }

    public void setReqRoomStatus(String reqRoomStatus) {
        this.reqRoomStatus = reqRoomStatus;
    }

    public String getReqRoomBundleId() {
        return reqRoomBundleId;
    }

    public void setReqRoomBundleId(String reqRoomBundleId) {
        this.reqRoomBundleId = reqRoomBundleId;
    }

    public String getReqRoomNameFirst() {
        return reqRoomNameFirst;
    }

    public void setReqRoomNameFirst(String reqRoomNameFirst) {
        this.reqRoomNameFirst = reqRoomNameFirst;
    }

    public String getReqRoomNameLast() {
        return reqRoomNameLast;
    }

    public void setReqRoomNameLast(String reqRoomNameLast) {
        this.reqRoomNameLast = reqRoomNameLast;
    }

    public String getReqRoomNation() {
        return reqRoomNation;
    }

    public void setReqRoomNation(String reqRoomNation) {
        this.reqRoomNation = reqRoomNation;
    }

    public String getReqRoomExtraCount() {
        return reqRoomExtraCount;
    }

    public void setReqRoomExtraCount(String reqRoomExtraCount) {
        this.reqRoomExtraCount = reqRoomExtraCount;
    }

    public String getReqRoomDesciption() {
        return reqRoomDesciption;
    }

    public void setReqRoomDesciption(String reqRoomDesciption) {
        this.reqRoomDesciption = reqRoomDesciption;
    }

    public String getReqRoomPriceNet() {
        return reqRoomPriceNet;
    }

    public void setReqRoomPriceNet(String reqRoomPriceNet) {
        this.reqRoomPriceNet = reqRoomPriceNet;
    }

    public String getReqRoomPricePerson() {
        return reqRoomPricePerson;
    }

    public void setReqRoomPricePerson(String reqRoomPricePerson) {
        this.reqRoomPricePerson = reqRoomPricePerson;
    }

    public String getReqRoomPricePersonSum() {
        return reqRoomPricePersonSum;
    }

    public void setReqRoomPricePersonSum(String reqRoomPricePersonSum) {
        this.reqRoomPricePersonSum = reqRoomPricePersonSum;
    }

    public String getReqRoomPriceHalfIn() {
        return reqRoomPriceHalfIn;
    }

    public void setReqRoomPriceHalfIn(String reqRoomPriceHalfIn) {
        this.reqRoomPriceHalfIn = reqRoomPriceHalfIn;
    }

    public String getReqRoomPriceHalfOut() {
        return reqRoomPriceHalfOut;
    }

    public void setReqRoomPriceHalfOut(String reqRoomPriceHalfOut) {
        this.reqRoomPriceHalfOut = reqRoomPriceHalfOut;
    }

    public String getReqRoomPriceDiscount() {
        return reqRoomPriceDiscount;
    }

    public void setReqRoomPriceDiscount(String reqRoomPriceDiscount) {
        this.reqRoomPriceDiscount = reqRoomPriceDiscount;
    }

    public String getReqRoomPriceCalc() {
        return reqRoomPriceCalc;
    }

    public void setReqRoomPriceCalc(String reqRoomPriceCalc) {
        this.reqRoomPriceCalc = reqRoomPriceCalc;
    }

    public String getReqRoomPriceFinal() {
        return reqRoomPriceFinal;
    }

    public void setReqRoomPriceFinal(String reqRoomPriceFinal) {
        this.reqRoomPriceFinal = reqRoomPriceFinal;
    }

}
