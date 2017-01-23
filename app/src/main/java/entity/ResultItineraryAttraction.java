
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultItineraryAttraction  implements Serializable {

    @SerializedName("itinerary_id")
    @Expose
    private String itineraryId;
    @SerializedName("attraction_id")
    @Expose
    private String attractionId;
    @SerializedName("itinerary_dayplan_name")
    @Expose
    private String itineraryDayplanName;
    @SerializedName("itinerary_daylight_name")
    @Expose
    private String itineraryDaylightName;
    @SerializedName("itinerary_daylight_weight")
    @Expose
    private String itineraryDaylightWeight;
    @SerializedName("itinerary_img_url")
    @Expose
    private String itineraryImgUrl;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("city_title")
    @Expose
    private String cityTitle;
    @SerializedName("province_title")
    @Expose
    private String provinceTitle;
    @SerializedName("province_id")
    @Expose
    private String provinceId;
    @SerializedName("attraction_title")
    @Expose
    private String attractionTitle;
    @SerializedName("attraction_difficulty")
    @Expose
    private String attractionDifficulty;
    @SerializedName("attraction_price")
    @Expose
    private Object attractionPrice;
    @SerializedName("attraction_price_foreign")
    @Expose
    private Object attractionPriceForeign;
    @SerializedName("attarction_itinerary_type_title")
    @Expose
    private String attarctionItineraryTypeTitle;
    @SerializedName("attraction_address")
    @Expose
    private String attractionAddress;
    @SerializedName("attraction_position_lat")
    @Expose
    private String attractionPositionLat;
    @SerializedName("attraction_position_on")
    @Expose
    private String attractionPositionOn;
    @SerializedName("attarction_itinerary_type_id")
    @Expose
    private String attarctionItineraryTypeId;
    @SerializedName("attarction_4sq_id")
    @Expose
    private String attarction4sqId;
    @SerializedName("attarction_estimated_time")
    @Expose
    private String attarctionEstimatedTime;
    @SerializedName("attarction_body")
    @Expose
    private String attarctionBody;

    public String getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(String itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(String attractionId) {
        this.attractionId = attractionId;
    }

    public String getItineraryDayplanName() {
        return itineraryDayplanName;
    }

    public void setItineraryDayplanName(String itineraryDayplanName) {
        this.itineraryDayplanName = itineraryDayplanName;
    }

    public String getItineraryDaylightName() {
        return itineraryDaylightName;
    }

    public void setItineraryDaylightName(String itineraryDaylightName) {
        this.itineraryDaylightName = itineraryDaylightName;
    }

    public String getItineraryDaylightWeight() {
        return itineraryDaylightWeight;
    }

    public void setItineraryDaylightWeight(String itineraryDaylightWeight) {
        this.itineraryDaylightWeight = itineraryDaylightWeight;
    }

    public String getItineraryImgUrl() {
        return itineraryImgUrl;
    }

    public void setItineraryImgUrl(String itineraryImgUrl) {
        this.itineraryImgUrl = itineraryImgUrl;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public String getProvinceTitle() {
        return provinceTitle;
    }

    public void setProvinceTitle(String provinceTitle) {
        this.provinceTitle = provinceTitle;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getAttractionTitle() {
        return attractionTitle;
    }

    public void setAttractionTitle(String attractionTitle) {
        this.attractionTitle = attractionTitle;
    }

    public String getAttractionDifficulty() {
        return attractionDifficulty;
    }

    public void setAttractionDifficulty(String attractionDifficulty) {
        this.attractionDifficulty = attractionDifficulty;
    }

    public Object getAttractionPrice() {
        return attractionPrice;
    }

    public void setAttractionPrice(Object attractionPrice) {
        this.attractionPrice = attractionPrice;
    }

    public Object getAttractionPriceForeign() {
        return attractionPriceForeign;
    }

    public void setAttractionPriceForeign(Object attractionPriceForeign) {
        this.attractionPriceForeign = attractionPriceForeign;
    }

    public String getAttarctionItineraryTypeTitle() {
        return attarctionItineraryTypeTitle;
    }

    public void setAttarctionItineraryTypeTitle(String attarctionItineraryTypeTitle) {
        this.attarctionItineraryTypeTitle = attarctionItineraryTypeTitle;
    }

    public String getAttractionAddress() {
        return attractionAddress;
    }

    public void setAttractionAddress(String attractionAddress) {
        this.attractionAddress = attractionAddress;
    }

    public String getAttractionPositionLat() {
        return attractionPositionLat;
    }

    public void setAttractionPositionLat(String attractionPositionLat) {
        this.attractionPositionLat = attractionPositionLat;
    }

    public String getAttractionPositionOn() {
        return attractionPositionOn;
    }

    public void setAttractionPositionOn(String attractionPositionOn) {
        this.attractionPositionOn = attractionPositionOn;
    }

    public String getAttarctionItineraryTypeId() {
        return attarctionItineraryTypeId;
    }

    public void setAttarctionItineraryTypeId(String attarctionItineraryTypeId) {
        this.attarctionItineraryTypeId = attarctionItineraryTypeId;
    }

    public String getAttarction4sqId() {
        return attarction4sqId;
    }

    public void setAttarction4sqId(String attarction4sqId) {
        this.attarction4sqId = attarction4sqId;
    }

    public String getAttarctionEstimatedTime() {
        return attarctionEstimatedTime;
    }

    public void setAttarctionEstimatedTime(String attarctionEstimatedTime) {
        this.attarctionEstimatedTime = attarctionEstimatedTime;
    }

    public String getAttarctionBody() {
        return attarctionBody;
    }

    public void setAttarctionBody(String attarctionBody) {
        this.attarctionBody = attarctionBody;
    }

}
