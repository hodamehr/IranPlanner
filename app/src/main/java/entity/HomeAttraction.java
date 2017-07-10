
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeAttraction implements Serializable {

    @SerializedName("attraction_id")
    @Expose
    private String attractionId;
    @SerializedName("attraction_title")
    @Expose
    private String attractionTitle;
    @SerializedName("attraction_city_id")
    @Expose
    private String attractionCityId;
    @SerializedName("attraction_province_id")
    @Expose
    private Integer attractionProvinceId;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("distance")
    @Expose
    private String distance;

    public String getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(String attractionId) {
        this.attractionId = attractionId;
    }

    public String getAttractionTitle() {
        return attractionTitle;
    }

    public void setAttractionTitle(String attractionTitle) {
        this.attractionTitle = attractionTitle;
    }

    public String getAttractionCityId() {
        return attractionCityId;
    }

    public void setAttractionCityId(String attractionCityId) {
        this.attractionCityId = attractionCityId;
    }

    public Integer getAttractionProvinceId() {
        return attractionProvinceId;
    }

    public void setAttractionProvinceId(Integer attractionProvinceId) {
        this.attractionProvinceId = attractionProvinceId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

}
