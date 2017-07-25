
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeSouvenir implements Serializable {

    @SerializedName("souvenirs_id")
    @Expose
    private String souvenirsId;
    @SerializedName("souvenirs_title")
    @Expose
    private String souvenirsTitle;
    @SerializedName("souvenirs_province_id")
    @Expose
    private String souvenirsProvinceId;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;

    public String getSouvenirsId() {
        return souvenirsId;
    }

    public void setSouvenirsId(String souvenirsId) {
        this.souvenirsId = souvenirsId;
    }

    public String getSouvenirsTitle() {
        return souvenirsTitle;
    }

    public void setSouvenirsTitle(String souvenirsTitle) {
        this.souvenirsTitle = souvenirsTitle;
    }

    public String getSouvenirsProvinceId() {
        return souvenirsProvinceId;
    }

    public void setSouvenirsProvinceId(String souvenirsProvinceId) {
        this.souvenirsProvinceId = souvenirsProvinceId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
