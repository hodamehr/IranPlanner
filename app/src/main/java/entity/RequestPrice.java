
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RequestPrice implements Serializable{

    @SerializedName("price_room_b2c_calc")
    @Expose
    private String priceRoomB2cCalc;
    @SerializedName("price_room_promotion_calc")
    @Expose
    private String priceRoomPromotionCalc;
    @SerializedName("price_room_discount_calc")
    @Expose
    private String priceRoomDiscountCalc;
    @SerializedName("price_room_calc")
    @Expose
    private String priceRoomCalc;
    @SerializedName("price_half_in_calc")
    @Expose
    private String priceHalfInCalc;
    @SerializedName("price_half_out_calc")
    @Expose
    private String priceHalfOutCalc;
    @SerializedName("price_add_adult_calc")
    @Expose
    private String priceAddAdultCalc;
    @SerializedName("price_pay")
    @Expose
    private String pricePay;

    public String getPriceRoomB2cCalc() {
        return priceRoomB2cCalc;
    }

    public void setPriceRoomB2cCalc(String priceRoomB2cCalc) {
        this.priceRoomB2cCalc = priceRoomB2cCalc;
    }

    public String getPriceRoomPromotionCalc() {
        return priceRoomPromotionCalc;
    }

    public void setPriceRoomPromotionCalc(String priceRoomPromotionCalc) {
        this.priceRoomPromotionCalc = priceRoomPromotionCalc;
    }

    public String getPriceRoomDiscountCalc() {
        return priceRoomDiscountCalc;
    }

    public void setPriceRoomDiscountCalc(String priceRoomDiscountCalc) {
        this.priceRoomDiscountCalc = priceRoomDiscountCalc;
    }

    public String getPriceRoomCalc() {
        return priceRoomCalc;
    }

    public void setPriceRoomCalc(String priceRoomCalc) {
        this.priceRoomCalc = priceRoomCalc;
    }

    public String getPriceHalfInCalc() {
        return priceHalfInCalc;
    }

    public void setPriceHalfInCalc(String priceHalfInCalc) {
        this.priceHalfInCalc = priceHalfInCalc;
    }

    public String getPriceHalfOutCalc() {
        return priceHalfOutCalc;
    }

    public void setPriceHalfOutCalc(String priceHalfOutCalc) {
        this.priceHalfOutCalc = priceHalfOutCalc;
    }

    public String getPriceAddAdultCalc() {
        return priceAddAdultCalc;
    }

    public void setPriceAddAdultCalc(String priceAddAdultCalc) {
        this.priceAddAdultCalc = priceAddAdultCalc;
    }

    public String getPricePay() {
        return pricePay;
    }

    public void setPricePay(String pricePay) {
        this.pricePay = pricePay;
    }

}
