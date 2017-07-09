
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeLodging implements Serializable {

    @SerializedName("lodging_id")
    @Expose
    private String lodgingId;
    @SerializedName("lodging_title")
    @Expose
    private String lodgingTitle;
    @SerializedName("lodging_type_id")
    @Expose
    private String lodgingTypeId;
    @SerializedName("lodging_type_name")
    @Expose
    private String lodgingTypeName;
    @SerializedName("lodging_rate_id")
    @Expose
    private Integer lodgingRateId;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;

    public String getLodgingId() {
        return lodgingId;
    }

    public void setLodgingId(String lodgingId) {
        this.lodgingId = lodgingId;
    }

    public String getLodgingTitle() {
        return lodgingTitle;
    }

    public void setLodgingTitle(String lodgingTitle) {
        this.lodgingTitle = lodgingTitle;
    }

    public String getLodgingTypeId() {
        return lodgingTypeId;
    }

    public void setLodgingTypeId(String lodgingTypeId) {
        this.lodgingTypeId = lodgingTypeId;
    }

    public String getLodgingTypeName() {
        return lodgingTypeName;
    }

    public void setLodgingTypeName(String lodgingTypeName) {
        this.lodgingTypeName = lodgingTypeName;
    }

    public Integer getLodgingRateId() {
        return lodgingRateId;
    }

    public void setLodgingRateId(Integer lodgingRateId) {
        this.lodgingRateId = lodgingRateId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
