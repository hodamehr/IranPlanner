
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Request implements Serializable{

    @SerializedName("req_bundle_order")
    @Expose
    private String reqBundleOrder;
    @SerializedName("req_uid")
    @Expose
    private String reqUid;
    @SerializedName("req_id")
    @Expose
    private String reqId;
    @SerializedName("req_status")
    @Expose
    private String reqStatus;
    @SerializedName("req_bundle_id")
    @Expose
    private String reqBundleId;
    @SerializedName("req_room_id")
    @Expose
    private String reqRoomId;
    @SerializedName("req_room_title")
    @Expose
    private String reqRoomTitle;
    @SerializedName("req_lodging_id")
    @Expose
    private String reqLodgingId;
    @SerializedName("req_lodging_title")
    @Expose
    private String reqLodgingTitle;
    @SerializedName("req_date_from")
    @Expose
    private String reqDateFrom;
    @SerializedName("req_date_to")
    @Expose
    private String reqDateTo;
    @SerializedName("req_timestamp")
    @Expose
    private String reqTimestamp;
    @SerializedName("status_timestamp")
    @Expose
    private String statusTimestamp;
    @SerializedName("status_expire")
    @Expose
    private String statusExpire;
    @SerializedName("req_room_name_first")
    @Expose
    private String reqRoomNameFirst;
    @SerializedName("req_room_name_last")
    @Expose
    private String reqRoomNameLast;
    @SerializedName("req_room_extra_count")
    @Expose
    private String reqRoomExtraCount;
    @SerializedName("req_price_person")
    @Expose
    private String reqPricePerson;
    @SerializedName("req_price_half_in")
    @Expose
    private String reqPriceHalfIn;
    @SerializedName("req_price_half_out")
    @Expose
    private String reqPriceHalfOut;
    @SerializedName("req_price_room")
    @Expose
    private String reqPriceRoom;
    @SerializedName("req_price_final")
    @Expose
    private String reqPriceFinal;
    @SerializedName("req_price_sum")
    @Expose
    private String reqPriceSum;
    @SerializedName("request_price")
    @Expose
    private RequestPrice requestPrice;

    public String getReqBundleOrder() {
        return reqBundleOrder;
    }

    public void setReqBundleOrder(String reqBundleOrder) {
        this.reqBundleOrder = reqBundleOrder;
    }

    public String getReqUid() {
        return reqUid;
    }

    public void setReqUid(String reqUid) {
        this.reqUid = reqUid;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public String getReqBundleId() {
        return reqBundleId;
    }

    public void setReqBundleId(String reqBundleId) {
        this.reqBundleId = reqBundleId;
    }

    public String getReqRoomId() {
        return reqRoomId;
    }

    public void setReqRoomId(String reqRoomId) {
        this.reqRoomId = reqRoomId;
    }

    public String getReqRoomTitle() {
        return reqRoomTitle;
    }

    public void setReqRoomTitle(String reqRoomTitle) {
        this.reqRoomTitle = reqRoomTitle;
    }

    public String getReqLodgingId() {
        return reqLodgingId;
    }

    public void setReqLodgingId(String reqLodgingId) {
        this.reqLodgingId = reqLodgingId;
    }

    public String getReqLodgingTitle() {
        return reqLodgingTitle;
    }

    public void setReqLodgingTitle(String reqLodgingTitle) {
        this.reqLodgingTitle = reqLodgingTitle;
    }

    public String getReqDateFrom() {
        return reqDateFrom;
    }

    public void setReqDateFrom(String reqDateFrom) {
        this.reqDateFrom = reqDateFrom;
    }

    public String getReqDateTo() {
        return reqDateTo;
    }

    public void setReqDateTo(String reqDateTo) {
        this.reqDateTo = reqDateTo;
    }

    public String getReqTimestamp() {
        return reqTimestamp;
    }

    public void setReqTimestamp(String reqTimestamp) {
        this.reqTimestamp = reqTimestamp;
    }

    public String getStatusTimestamp() {
        return statusTimestamp;
    }

    public void setStatusTimestamp(String statusTimestamp) {
        this.statusTimestamp = statusTimestamp;
    }

    public String getStatusExpire() {
        return statusExpire;
    }

    public void setStatusExpire(String statusExpire) {
        this.statusExpire = statusExpire;
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

    public String getReqRoomExtraCount() {
        return reqRoomExtraCount;
    }

    public void setReqRoomExtraCount(String reqRoomExtraCount) {
        this.reqRoomExtraCount = reqRoomExtraCount;
    }

    public String getReqPricePerson() {
        return reqPricePerson;
    }

    public void setReqPricePerson(String reqPricePerson) {
        this.reqPricePerson = reqPricePerson;
    }

    public String getReqPriceHalfIn() {
        return reqPriceHalfIn;
    }

    public void setReqPriceHalfIn(String reqPriceHalfIn) {
        this.reqPriceHalfIn = reqPriceHalfIn;
    }

    public String getReqPriceHalfOut() {
        return reqPriceHalfOut;
    }

    public void setReqPriceHalfOut(String reqPriceHalfOut) {
        this.reqPriceHalfOut = reqPriceHalfOut;
    }

    public String getReqPriceRoom() {
        return reqPriceRoom;
    }

    public void setReqPriceRoom(String reqPriceRoom) {
        this.reqPriceRoom = reqPriceRoom;
    }

    public String getReqPriceFinal() {
        return reqPriceFinal;
    }

    public void setReqPriceFinal(String reqPriceFinal) {
        this.reqPriceFinal = reqPriceFinal;
    }

    public String getReqPriceSum() {
        return reqPriceSum;
    }

    public void setReqPriceSum(String reqPriceSum) {
        this.reqPriceSum = reqPriceSum;
    }

    public RequestPrice getRequestPrice() {
        return requestPrice;
    }

    public void setRequestPrice(RequestPrice requestPrice) {
        this.requestPrice = requestPrice;
    }

}
