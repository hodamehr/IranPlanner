
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeNeighborCity  implements Serializable{
    @SerializedName("img_url")
    @Expose
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @SerializedName("item_id")

    @Expose
    private String itemId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("field_country_capital")
    @Expose
    private String fieldCountryCapital;
    @SerializedName("field_province_capital")
    @Expose
    private String fieldProvinceCapital;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("position_lat")
    @Expose
    private String positionLat;
    @SerializedName("position_lon")
    @Expose
    private String positionLon;
    @SerializedName("distance")
    @Expose
    private String distance;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldCountryCapital() {
        return fieldCountryCapital;
    }

    public void setFieldCountryCapital(String fieldCountryCapital) {
        this.fieldCountryCapital = fieldCountryCapital;
    }

    public String getFieldProvinceCapital() {
        return fieldProvinceCapital;
    }

    public void setFieldProvinceCapital(String fieldProvinceCapital) {
        this.fieldProvinceCapital = fieldProvinceCapital;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPositionLat() {
        return positionLat;
    }

    public void setPositionLat(String positionLat) {
        this.positionLat = positionLat;
    }

    public String getPositionLon() {
        return positionLon;
    }

    public void setPositionLon(String positionLon) {
        this.positionLon = positionLon;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

}
