
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItineraryLodgingCity implements Serializable {

    @SerializedName("itinerary_id")
    @Expose
    private String itineraryId;
    @SerializedName("city_position_lat")
    @Expose
    private String cityPositionLat;
    @SerializedName("city_position_lon")
    @Expose
    private String cityPositionLon;
    @SerializedName("lodging_lenght")
    @Expose
    private String lodgingLenght;
    @SerializedName("city_title")
    @Expose
    private String cityTitle;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("city_province_name")
    @Expose
    private String cityProvinceName;
    @SerializedName("province_id")
    @Expose
    private String provinceId;

    @SerializedName("city_img_url")
    @Expose
    private String cityImgUrl;

    public String getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(String itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getCityPositionLat() {
        return cityPositionLat;
    }

    public void setCityPositionLat(String cityPositionLat) {
        this.cityPositionLat = cityPositionLat;
    }

    public String getCityPositionLon() {
        return cityPositionLon;
    }

    public void setCityPositionLon(String cityPositionLon) {
        this.cityPositionLon = cityPositionLon;
    }

    public String getLodgingLenght() {
        return lodgingLenght;
    }

    public void setLodgingLenght(String lodgingLenght) {
        this.lodgingLenght = lodgingLenght;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityProvinceName() {
        return cityProvinceName;
    }

    public void setCityProvinceName(String cityProvinceName) {
        this.cityProvinceName = cityProvinceName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
    public String getCityImgUrl() {
        return cityImgUrl;
    }

    public void setCityImgUrl(String cityImgUrl) {
        this.cityImgUrl = cityImgUrl;
    }
}
