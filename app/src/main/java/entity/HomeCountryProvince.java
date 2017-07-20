
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeCountryProvince implements Serializable{

    @SerializedName("province_id")
    @Expose
    private String provinceId;
    @SerializedName("province_title")
    @Expose
    private String provinceTitle;
    @SerializedName("city_country_capital")
    @Expose
    private String cityCountryCapital;
    @SerializedName("city_province_capital")
    @Expose
    private String cityProvinceCapital;
    @SerializedName("country_title")
    @Expose
    private String countryTitle;
    @SerializedName("country_id")
    @Expose
    private String countryId;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceTitle() {
        return provinceTitle;
    }

    public void setProvinceTitle(String provinceTitle) {
        this.provinceTitle = provinceTitle;
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

    public String getCountryTitle() {
        return countryTitle;
    }

    public void setCountryTitle(String countryTitle) {
        this.countryTitle = countryTitle;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

}
