
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultSouvenir implements Serializable {

    @SerializedName("souvenir_city_id")
    @Expose
    private Integer souvenirCityId;
    @SerializedName("souvenir_province_id")
    @Expose
    private Integer souvenirProvinceId;
    @SerializedName("souvenir_province_name")
    @Expose
    private String souvenirProvinceName;
    @SerializedName("souvenir_id")
    @Expose
    private Integer souvenirId;
    @SerializedName("souvenir_name")
    @Expose
    private String souvenirName;
    @SerializedName("souvenir_img_url")
    @Expose
    private String souvenirImgUrl;
    @SerializedName("souvenir_url")
    @Expose
    private String souvenirUrl;
    @SerializedName("souvenir_body")
    @Expose
    private String souvenirBody;

    public Integer getSouvenirCityId() {
        return souvenirCityId;
    }

    public void setSouvenirCityId(Integer souvenirCityId) {
        this.souvenirCityId = souvenirCityId;
    }

    public Integer getSouvenirProvinceId() {
        return souvenirProvinceId;
    }

    public void setSouvenirProvinceId(Integer souvenirProvinceId) {
        this.souvenirProvinceId = souvenirProvinceId;
    }

    public String getSouvenirProvinceName() {
        return souvenirProvinceName;
    }

    public void setSouvenirProvinceName(String souvenirProvinceName) {
        this.souvenirProvinceName = souvenirProvinceName;
    }

    public Integer getSouvenirId() {
        return souvenirId;
    }

    public void setSouvenirId(Integer souvenirId) {
        this.souvenirId = souvenirId;
    }

    public String getSouvenirName() {
        return souvenirName;
    }

    public void setSouvenirName(String souvenirName) {
        this.souvenirName = souvenirName;
    }

    public String getSouvenirImgUrl() {
        return souvenirImgUrl;
    }

    public void setSouvenirImgUrl(String souvenirImgUrl) {
        this.souvenirImgUrl = souvenirImgUrl;
    }

    public String getSouvenirUrl() {
        return souvenirUrl;
    }

    public void setSouvenirUrl(String souvenirUrl) {
        this.souvenirUrl = souvenirUrl;
    }

    public String getSouvenirBody() {
        return souvenirBody;
    }

    public void setSouvenirBody(String souvenirBody) {
        this.souvenirBody = souvenirBody;
    }

}
