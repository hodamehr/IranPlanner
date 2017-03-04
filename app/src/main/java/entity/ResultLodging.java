
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultLodging implements Serializable {

    @SerializedName("lodging_id")
    @Expose
    private Integer lodgingId;
    @SerializedName("lodging_name")
    @Expose
    private String lodgingName;
    @SerializedName("lodging_address")
    @Expose
    private String lodgingAddress;
    @SerializedName("lodging_pos_lat")
    @Expose
    private Double lodgingPosLat;
    @SerializedName("lodging_pos_long")
    @Expose
    private Double lodgingPosLong;
    @SerializedName("lodging_rate_title")
    @Expose
    private String lodgingRateTitle;
    @SerializedName("lodgin_type_id")
    @Expose
    private Integer lodginTypeId;
    @SerializedName("lodging_type_title")
    @Expose
    private String lodgingTypeTitle;
    @SerializedName("lodging_lang")
    @Expose
    private String lodgingLang;
    @SerializedName("lodging_img_url")
    @Expose
    private String lodgingImgUrl;
    @SerializedName("lodging_city_name")
    @Expose
    private String lodgingCityName;
    @SerializedName("lodging_city_id")
    @Expose
    private Integer lodgingCityId;
    @SerializedName("lodging_province_name")
    @Expose
    private String lodgingProvinceName;
    @SerializedName("lodging_province_id")
    @Expose
    private Integer lodgingProvinceId;
    @SerializedName("lodging_rate_int")
    @Expose
    private Integer lodgingRateInt;
    @SerializedName("lodging_facility")
    @Expose
    private List<LodgingFacility> lodgingFacility = null;
    @SerializedName("lodging_body")
    @Expose
    private Object lodgingBody;
    @SerializedName("lodging_room_price_detail")
    @Expose
    private List<LodgingRoomPriceDetail> lodgingRoomPriceDetail = null;
    @SerializedName("lodging_room_price_rule_detail")
    @Expose
    private LodgingRoomPriceRuleDetail lodgingRoomPriceRuleDetail;

    public Integer getLodgingId() {
        return lodgingId;
    }

    public void setLodgingId(Integer lodgingId) {
        this.lodgingId = lodgingId;
    }

    public String getLodgingName() {
        return lodgingName;
    }

    public void setLodgingName(String lodgingName) {
        this.lodgingName = lodgingName;
    }

    public String getLodgingAddress() {
        return lodgingAddress;
    }

    public void setLodgingAddress(String lodgingAddress) {
        this.lodgingAddress = lodgingAddress;
    }

    public Double getLodgingPosLat() {
        return lodgingPosLat;
    }

    public void setLodgingPosLat(Double lodgingPosLat) {
        this.lodgingPosLat = lodgingPosLat;
    }

    public Double getLodgingPosLong() {
        return lodgingPosLong;
    }

    public void setLodgingPosLong(Double lodgingPosLong) {
        this.lodgingPosLong = lodgingPosLong;
    }

    public String getLodgingRateTitle() {
        return lodgingRateTitle;
    }

    public void setLodgingRateTitle(String lodgingRateTitle) {
        this.lodgingRateTitle = lodgingRateTitle;
    }

    public Integer getLodginTypeId() {
        return lodginTypeId;
    }

    public void setLodginTypeId(Integer lodginTypeId) {
        this.lodginTypeId = lodginTypeId;
    }

    public String getLodgingTypeTitle() {
        return lodgingTypeTitle;
    }

    public void setLodgingTypeTitle(String lodgingTypeTitle) {
        this.lodgingTypeTitle = lodgingTypeTitle;
    }

    public String getLodgingLang() {
        return lodgingLang;
    }

    public void setLodgingLang(String lodgingLang) {
        this.lodgingLang = lodgingLang;
    }

    public String getLodgingImgUrl() {
        return lodgingImgUrl;
    }

    public void setLodgingImgUrl(String lodgingImgUrl) {
        this.lodgingImgUrl = lodgingImgUrl;
    }

    public String getLodgingCityName() {
        return lodgingCityName;
    }

    public void setLodgingCityName(String lodgingCityName) {
        this.lodgingCityName = lodgingCityName;
    }

    public Integer getLodgingCityId() {
        return lodgingCityId;
    }

    public void setLodgingCityId(Integer lodgingCityId) {
        this.lodgingCityId = lodgingCityId;
    }

    public String getLodgingProvinceName() {
        return lodgingProvinceName;
    }

    public void setLodgingProvinceName(String lodgingProvinceName) {
        this.lodgingProvinceName = lodgingProvinceName;
    }

    public Integer getLodgingProvinceId() {
        return lodgingProvinceId;
    }

    public void setLodgingProvinceId(Integer lodgingProvinceId) {
        this.lodgingProvinceId = lodgingProvinceId;
    }

    public Integer getLodgingRateInt() {
        return lodgingRateInt;
    }

    public void setLodgingRateInt(Integer lodgingRateInt) {
        this.lodgingRateInt = lodgingRateInt;
    }

    public List<LodgingFacility> getLodgingFacility() {
        return lodgingFacility;
    }

    public void setLodgingFacility(List<LodgingFacility> lodgingFacility) {
        this.lodgingFacility = lodgingFacility;
    }

    public Object getLodgingBody() {
        return lodgingBody;
    }

    public void setLodgingBody(Object lodgingBody) {
        this.lodgingBody = lodgingBody;
    }

    public List<LodgingRoomPriceDetail> getLodgingRoomPriceDetail() {
        return lodgingRoomPriceDetail;
    }

    public void setLodgingRoomPriceDetail(List<LodgingRoomPriceDetail> lodgingRoomPriceDetail) {
        this.lodgingRoomPriceDetail = lodgingRoomPriceDetail;
    }

    public LodgingRoomPriceRuleDetail getLodgingRoomPriceRuleDetail() {
        return lodgingRoomPriceRuleDetail;
    }

    public void setLodgingRoomPriceRuleDetail(LodgingRoomPriceRuleDetail lodgingRoomPriceRuleDetail) {
        this.lodgingRoomPriceRuleDetail = lodgingRoomPriceRuleDetail;
    }

}
