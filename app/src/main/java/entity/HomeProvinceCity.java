
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeProvinceCity implements Serializable {

    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("city_title")
    @Expose
    private String cityTitle;
    @SerializedName("city_country_capital")
    @Expose
    private String cityCountryCapital;
    @SerializedName("city_province_capital")
    @Expose
    private String cityProvinceCapital;
    @SerializedName("province_title")
    @Expose
    private String provinceTitle;
    @SerializedName("province_id")
    @Expose
    private String provinceId;

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

    public String getCityCountryCapital() {
        return cityCountryCapital;
    }

    public void setCityCountryCapital(String cityCountryCapital) {
        this.cityCountryCapital = cityCountryCapital;
    }

    public String getCityProvinceCapital() {
        return cityProvinceCapital;
    }

    public void setCityProvinceCapital(String cityProvinceCapital) {
        this.cityProvinceCapital = cityProvinceCapital;
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

}
