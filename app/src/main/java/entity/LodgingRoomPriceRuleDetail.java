
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LodgingRoomPriceRuleDetail implements Serializable {

    @SerializedName("lodging_room_price_detail_value")
    @Expose
    private String lodgingRoomPriceDetailValue;
    @SerializedName("lodging_room_price_detail_type")
    @Expose
    private String lodgingRoomPriceDetailType;

    public String getLodgingRoomPriceDetailValue() {
        return lodgingRoomPriceDetailValue;
    }

    public void setLodgingRoomPriceDetailValue(String lodgingRoomPriceDetailValue) {
        this.lodgingRoomPriceDetailValue = lodgingRoomPriceDetailValue;
    }

    public String getLodgingRoomPriceDetailType() {
        return lodgingRoomPriceDetailType;
    }

    public void setLodgingRoomPriceDetailType(String lodgingRoomPriceDetailType) {
        this.lodgingRoomPriceDetailType = lodgingRoomPriceDetailType;
    }

}
