
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultRoom implements Serializable{

    @SerializedName("room_title")
    @Expose
    private String roomTitle;
    @SerializedName("room_id")
    @Expose
    private Integer roomId;
    @SerializedName("room_language")
    @Expose
    private String roomLanguage;
    @SerializedName("room_lodging_id")
    @Expose
    private Integer roomLodgingId;
    @SerializedName("room_area")
    @Expose
    private Object roomArea;
    @SerializedName("room_capacity_adult")
    @Expose
    private Integer roomCapacityAdult;
    @SerializedName("room_capacity_children")
    @Expose
    private Object roomCapacityChildren;
    @SerializedName("room_capacity_extra")
    @Expose
    private Object roomCapacityExtra;
    @SerializedName("room_type_id")
    @Expose
    private Integer roomTypeId;
    @SerializedName("room_type_name")
    @Expose
    private String roomTypeName;
    @SerializedName("room_price")
    @Expose
    private Integer roomPrice;
    @SerializedName("room_price_foreign")
    @Expose
    private Integer roomPriceForeign;
    @SerializedName("room_price_quantity")
    @Expose
    private Integer roomPriceQuantity;
    @SerializedName("lodging_room_bed")
    @Expose
    private List<LodgingRoomBed> lodgingRoomBed = null;
    @SerializedName("lodging_room_facility")
    @Expose
    private List<LodgingRoomFacility> lodgingRoomFacility = null;
    @SerializedName("lodging_room_availability")
    @Expose
    private List<Object> lodgingRoomAvailability = null;
    @SerializedName("lodging_room_price")
    @Expose
    private String lodgingRoomPrice;

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomLanguage() {
        return roomLanguage;
    }

    public void setRoomLanguage(String roomLanguage) {
        this.roomLanguage = roomLanguage;
    }

    public Integer getRoomLodgingId() {
        return roomLodgingId;
    }

    public void setRoomLodgingId(Integer roomLodgingId) {
        this.roomLodgingId = roomLodgingId;
    }

    public Object getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(Object roomArea) {
        this.roomArea = roomArea;
    }

    public Integer getRoomCapacityAdult() {
        return roomCapacityAdult;
    }

    public void setRoomCapacityAdult(Integer roomCapacityAdult) {
        this.roomCapacityAdult = roomCapacityAdult;
    }

    public Object getRoomCapacityChildren() {
        return roomCapacityChildren;
    }

    public void setRoomCapacityChildren(Object roomCapacityChildren) {
        this.roomCapacityChildren = roomCapacityChildren;
    }

    public Object getRoomCapacityExtra() {
        return roomCapacityExtra;
    }

    public void setRoomCapacityExtra(Object roomCapacityExtra) {
        this.roomCapacityExtra = roomCapacityExtra;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getRoomPriceForeign() {
        return roomPriceForeign;
    }

    public void setRoomPriceForeign(Integer roomPriceForeign) {
        this.roomPriceForeign = roomPriceForeign;
    }

    public Integer getRoomPriceQuantity() {
        return roomPriceQuantity;
    }

    public void setRoomPriceQuantity(Integer roomPriceQuantity) {
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

    public List<Object> getLodgingRoomAvailability() {
        return lodgingRoomAvailability;
    }

    public void setLodgingRoomAvailability(List<Object> lodgingRoomAvailability) {
        this.lodgingRoomAvailability = lodgingRoomAvailability;
    }

    public String getLodgingRoomPrice() {
        return lodgingRoomPrice;
    }

    public void setLodgingRoomPrice(String lodgingRoomPrice) {
        this.lodgingRoomPrice = lodgingRoomPrice;
    }

}
