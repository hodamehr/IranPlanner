
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LodgingRoomPriceDetail implements Serializable {

    @SerializedName("lodging_room_min_price")
    @Expose
    private Integer lodgingRoomMinPrice;
    @SerializedName("lodging_room_max_price")
    @Expose
    private Integer lodgingRoomMaxPrice;
    @SerializedName("lodging_room_avg_price")
    @Expose
    private Double lodgingRoomAvgPrice;

    public Integer getLodgingRoomMinPrice() {
        return lodgingRoomMinPrice;
    }

    public void setLodgingRoomMinPrice(Integer lodgingRoomMinPrice) {
        this.lodgingRoomMinPrice = lodgingRoomMinPrice;
    }

    public Integer getLodgingRoomMaxPrice() {
        return lodgingRoomMaxPrice;
    }

    public void setLodgingRoomMaxPrice(Integer lodgingRoomMaxPrice) {
        this.lodgingRoomMaxPrice = lodgingRoomMaxPrice;
    }

    public Double getLodgingRoomAvgPrice() {
        return lodgingRoomAvgPrice;
    }

    public void setLodgingRoomAvgPrice(Double lodgingRoomAvgPrice) {
        this.lodgingRoomAvgPrice = lodgingRoomAvgPrice;
    }

}
