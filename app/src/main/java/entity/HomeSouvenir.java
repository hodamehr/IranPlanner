
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
    private Integer souvenirsProvinceId;
    @SerializedName("img_url")
    @Expose
    private Object imgUrl;

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

    public Integer getSouvenirsProvinceId() {
        return souvenirsProvinceId;
    }

    public void setSouvenirsProvinceId(Integer souvenirsProvinceId) {
        this.souvenirsProvinceId = souvenirsProvinceId;
    }

    public Object getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Object imgUrl) {
        this.imgUrl = imgUrl;
    }

}
