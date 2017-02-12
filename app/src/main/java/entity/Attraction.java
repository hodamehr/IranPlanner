
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Attraction implements Serializable {

    @SerializedName("attraction_id")
    @Expose
    private String attractionId;
    @SerializedName("attraction_name")
    @Expose
    private String attractionName;
    @SerializedName("attraction_province_name")
    @Expose
    private String attractionProvinceName;
    @SerializedName("attraction_city_name")
    @Expose
    private String attractionCityName;
    @SerializedName("attraction_city_id")
    @Expose
    private String attractionCityId;
    @SerializedName("attraction_province_id")
    @Expose
    private String attractionProvinceId;
    @SerializedName("slogan")
    @Expose
    private Object slogan;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("img_url")
    @Expose
    private Object imgUrl;

    public String getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(String attractionId) {
        this.attractionId = attractionId;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public String getAttractionProvinceName() {
        return attractionProvinceName;
    }

    public void setAttractionProvinceName(String attractionProvinceName) {
        this.attractionProvinceName = attractionProvinceName;
    }

    public String getAttractionCityName() {
        return attractionCityName;
    }

    public void setAttractionCityName(String attractionCityName) {
        this.attractionCityName = attractionCityName;
    }

    public String getAttractionCityId() {
        return attractionCityId;
    }

    public void setAttractionCityId(String attractionCityId) {
        this.attractionCityId = attractionCityId;
    }

    public String getAttractionProvinceId() {
        return attractionProvinceId;
    }

    public void setAttractionProvinceId(String attractionProvinceId) {
        this.attractionProvinceId = attractionProvinceId;
    }

    public Object getSlogan() {
        return slogan;
    }

    public void setSlogan(Object slogan) {
        this.slogan = slogan;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Object getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Object imgUrl) {
        this.imgUrl = imgUrl;
    }

}
