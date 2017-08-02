
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultRoom implements Serializable {

    @SerializedName("room_title")
    @Expose
    private String roomTitle;
    @SerializedName("room_id")
    @Expose
    private String roomId;
    @SerializedName("room_language")
    @Expose
    private String roomLanguage;
    @SerializedName("room_lodging_id")
    @Expose
    private String roomLodgingId;
    @SerializedName("room_area")
    @Expose
    private String roomArea;
    @SerializedName("room_capacity_adult")
    @Expose
    private String roomCapacityAdult;
    @SerializedName("room_capacity_children")
    @Expose
    private String roomCapacityChildren;
    @SerializedName("room_capacity_extra")
    @Expose
    private String roomCapacityExtra;
    @SerializedName("room_type_id")
    @Expose
    private String roomTypeId;
    @SerializedName("room_type_name")
    @Expose
    private String roomTypeName;
    @SerializedName("room_price")
    @Expose
    private String roomPrice;
    @SerializedName("room_price_promotion")
    @Expose
    private String roomPricePromotion;
    @SerializedName("room_price_foreign")
    @Expose
    private String roomPriceForeign;
    @SerializedName("room_price_ad_people")
    @Expose
    private String roomPriceAdPeople;
    @SerializedName("room_price_difference")
    @Expose
    private String roomPriceDifference;
    @SerializedName("room_price_difference_percent")
    @Expose
    private String roomPriceDifferencePercent;
    @SerializedName("room_price_halfboard_in")
    @Expose
    private String roomPriceHalfboardIn;
    @SerializedName("room_price_halfboard_out")
    @Expose
    private String roomPriceHalfboardOut;
    @SerializedName("room_price_quantity")
    @Expose
    private String roomPriceQuantity;
    @SerializedName("lodging_room_bed")
    @Expose
    private List<LodgingRoomBed> lodgingRoomBed = null;
    @SerializedName("lodging_room_facility")
    @Expose
    private List<LodgingRoomFacility> lodgingRoomFacility = null;
    @SerializedName("lodging_room_availability")
    @Expose
    private List<LodgingRoomAvailability> lodgingRoomAvailability = null;
    @SerializedName("lodging_room_price")
    @Expose
    private String lodgingRoomPrice;

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomLanguage() {
        return roomLanguage;
    }

    public void setRoomLanguage(String roomLanguage) {
        this.roomLanguage = roomLanguage;
    }

    public String getRoomLodgingId() {
        return roomLodgingId;
    }

    public void setRoomLodgingId(String roomLodgingId) {
        this.roomLodgingId = roomLodgingId;
    }

    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }

    public String getRoomCapacityAdult() {
        return roomCapacityAdult;
    }

    public void setRoomCapacityAdult(String roomCapacityAdult) {
        this.roomCapacityAdult = roomCapacityAdult;
    }

    public String getRoomCapacityChildren() {
        return roomCapacityChildren;
    }

    public void setRoomCapacityChildren(String roomCapacityChildren) {
        this.roomCapacityChildren = roomCapacityChildren;
    }

    public String getRoomCapacityExtra() {
        return roomCapacityExtra;
    }

    public void setRoomCapacityExtra(String roomCapacityExtra) {
        this.roomCapacityExtra = roomCapacityExtra;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomPricePromotion() {
        return roomPricePromotion;
    }

    public void setRoomPricePromotion(String roomPricePromotion) {
        this.roomPricePromotion = roomPricePromotion;
    }

    public String getRoomPriceForeign() {
        return roomPriceForeign;
    }

    public void setRoomPriceForeign(String roomPriceForeign) {
        this.roomPriceForeign = roomPriceForeign;
    }

    public String getRoomPriceAdPeople() {
        return roomPriceAdPeople;
    }

    public void setRoomPriceAdPeople(String roomPriceAdPeople) {
        this.roomPriceAdPeople = roomPriceAdPeople;
    }

    public String getRoomPriceDifference() {
        return roomPriceDifference;
    }

    public void setRoomPriceDifference(String roomPriceDifference) {
        this.roomPriceDifference = roomPriceDifference;
    }

    public String getRoomPriceDifferencePercent() {
        return roomPriceDifferencePercent;
    }

    public void setRoomPriceDifferencePercent(String roomPriceDifferencePercent) {
        this.roomPriceDifferencePercent = roomPriceDifferencePercent;
    }

    public String getRoomPriceHalfboardIn() {
        return roomPriceHalfboardIn;
    }

    public void setRoomPriceHalfboardIn(String roomPriceHalfboardIn) {
        this.roomPriceHalfboardIn = roomPriceHalfboardIn;
    }

    public String getRoomPriceHalfboardOut() {
        return roomPriceHalfboardOut;
    }

    public void setRoomPriceHalfboardOut(String roomPriceHalfboardOut) {
        this.roomPriceHalfboardOut = roomPriceHalfboardOut;
    }

    public String getRoomPriceQuantity() {
        return roomPriceQuantity;
    }

    public void setRoomPriceQuantity(String roomPriceQuantity) {
        this.roomPriceQuantity = roomPriceQuantity;
    }

    public List<LodgingRoomBed> getLodgingRoomBed() {
        return lodgingRoomBed;
    }

    public void setLodgingRoomBed(List<LodgingRoomBed> lodgingRoomBed) {
        this.lodgingRoomBed = lodgingRoomBed;
    }

    public List<LodgingRoomFacility> getLodgingRoomFacility() {
        return lodgingRoomFacility;
    }

    public void setLodgingRoomFacility(List<LodgingRoomFacility> lodgingRoomFacility) {
        this.lodgingRoomFacility = lodgingRoomFacility;
    }

    public List<LodgingRoomAvailability> getLodgingRoomAvailability() {
        return lodgingRoomAvailability;
    }

    public void setLodgingRoomAvailability(List<LodgingRoomAvailability> lodgingRoomAvailability) {
        this.lodgingRoomAvailability = lodgingRoomAvailability;
    }

    public String getLodgingRoomPrice() {
        return lodgingRoomPrice;
    }

    public void setLodgingRoomPrice(String lodgingRoomPrice) {
        this.lodgingRoomPrice = lodgingRoomPrice;
    }

}
