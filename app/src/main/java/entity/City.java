
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City  implements Serializable {

    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("city_name")
    @Expose
    private String cityName;
//    @SerializedName("city_province")
//    @Expose
//    private String cityProvince;
//    @SerializedName("latitude")
//    @Expose
//    private String latitude;
//    @SerializedName("longitude")
//    @Expose
//    private String longitude;
//    @SerializedName("lang")
//    @Expose
//    private String lang;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

//    public String getCityProvince() {
//        return cityProvince;
//    }
//
//    public void setCityProvince(String cityProvince) {
//        this.cityProvince = cityProvince;
//    }
//
//    public String getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(String latitude) {
//        this.latitude = latitude;
//    }
//
//    public String getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(String longitude) {
//        this.longitude = longitude;
//    }
//
//    public String getLang() {
//        return lang;
//    }
//
//    public void setLang(String lang) {
//        this.lang = lang;
//    }

}
