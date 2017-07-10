
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeItinerary implements Serializable {

    @SerializedName("itinerary_id")
    @Expose
    private String itineraryId;
    @SerializedName("itinerary_title")
    @Expose
    private String itineraryTitle;
    @SerializedName("itinerary_from_province_title")
    @Expose
    private String itineraryFromProvinceTitle;
    @SerializedName("itinerary_from_province_id")
    @Expose
    private String itineraryFromProvinceId;
    @SerializedName("itinerary_from_city_id")
    @Expose
    private String itineraryFromCityId;
    @SerializedName("itinerary_from_city_title")
    @Expose
    private String itineraryFromCityTitle;
    @SerializedName("itinerary_to_province_title")
    @Expose
    private String itineraryToProvinceTitle;
    @SerializedName("itinerary_to_province_id")
    @Expose
    private String itineraryToProvinceId;
    @SerializedName("itinerary_to_city_id")
    @Expose
    private String itineraryToCityId;
    @SerializedName("itinerary_city_to_title")
    @Expose
    private String itineraryCityToTitle;
    @SerializedName("itinerary_duration_id")
    @Expose
    private String itineraryDurationId;
    @SerializedName("itinerary_duration_title")
    @Expose
    private String itineraryDurationTitle;
    @SerializedName("itinerary_transportation_id")
    @Expose
    private String itineraryTransportationId;
    @SerializedName("itinerary_transportation_title")
    @Expose
    private String itineraryTransportationTitle;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;

    public String getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(String itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getItineraryTitle() {
        return itineraryTitle;
    }

    public void setItineraryTitle(String itineraryTitle) {
        this.itineraryTitle = itineraryTitle;
    }

    public String getItineraryFromProvinceTitle() {
        return itineraryFromProvinceTitle;
    }

    public void setItineraryFromProvinceTitle(String itineraryFromProvinceTitle) {
        this.itineraryFromProvinceTitle = itineraryFromProvinceTitle;
    }

    public String getItineraryFromProvinceId() {
        return itineraryFromProvinceId;
    }

    public void setItineraryFromProvinceId(String itineraryFromProvinceId) {
        this.itineraryFromProvinceId = itineraryFromProvinceId;
    }

    public String getItineraryFromCityId() {
        return itineraryFromCityId;
    }

    public void setItineraryFromCityId(String itineraryFromCityId) {
        this.itineraryFromCityId = itineraryFromCityId;
    }

    public String getItineraryFromCityTitle() {
        return itineraryFromCityTitle;
    }

    public void setItineraryFromCityTitle(String itineraryFromCityTitle) {
        this.itineraryFromCityTitle = itineraryFromCityTitle;
    }

    public String getItineraryToProvinceTitle() {
        return itineraryToProvinceTitle;
    }

    public void setItineraryToProvinceTitle(String itineraryToProvinceTitle) {
        this.itineraryToProvinceTitle = itineraryToProvinceTitle;
    }

    public String getItineraryToProvinceId() {
        return itineraryToProvinceId;
    }

    public void setItineraryToProvinceId(String itineraryToProvinceId) {
        this.itineraryToProvinceId = itineraryToProvinceId;
    }

    public String getItineraryToCityId() {
        return itineraryToCityId;
    }

    public void setItineraryToCityId(String itineraryToCityId) {
        this.itineraryToCityId = itineraryToCityId;
    }

    public String getItineraryCityToTitle() {
        return itineraryCityToTitle;
    }

    public void setItineraryCityToTitle(String itineraryCityToTitle) {
        this.itineraryCityToTitle = itineraryCityToTitle;
    }

    public String getItineraryDurationId() {
        return itineraryDurationId;
    }

    public void setItineraryDurationId(String itineraryDurationId) {
        this.itineraryDurationId = itineraryDurationId;
    }

    public String getItineraryDurationTitle() {
        return itineraryDurationTitle;
    }

    public void setItineraryDurationTitle(String itineraryDurationTitle) {
        this.itineraryDurationTitle = itineraryDurationTitle;
    }

    public String getItineraryTransportationId() {
        return itineraryTransportationId;
    }

    public void setItineraryTransportationId(String itineraryTransportationId) {
        this.itineraryTransportationId = itineraryTransportationId;
    }

    public String getItineraryTransportationTitle() {
        return itineraryTransportationTitle;
    }

    public void setItineraryTransportationTitle(String itineraryTransportationTitle) {
        this.itineraryTransportationTitle = itineraryTransportationTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
