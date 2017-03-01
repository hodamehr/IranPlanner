
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LodgingRoomAvailability implements Serializable {

    @SerializedName("room_date_no")
    @Expose
    private Integer roomDateNo;
    @SerializedName("room_date_unix")
    @Expose
    private Integer roomDateUnix;
    @SerializedName("room_date_date")
    @Expose
    private String roomDateDate;
    @SerializedName("room_date_name")
    @Expose
    private String roomDateName;
    @SerializedName("room_date_price")
    @Expose
    private Integer roomDatePrice;
    @SerializedName("room_date_status")
    @Expose
    private String roomDateStatus;

    public Integer getRoomDateNo() {
        return roomDateNo;
    }

    public void setRoomDateNo(Integer roomDateNo) {
        this.roomDateNo = roomDateNo;
    }

    public Integer getRoomDateUnix() {
        return roomDateUnix;
    }

    public void setRoomDateUnix(Integer roomDateUnix) {
        this.roomDateUnix = roomDateUnix;
    }

    public String getRoomDateDate() {
        return roomDateDate;
    }

    public void setRoomDateDate(String roomDateDate) {
        this.roomDateDate = roomDateDate;
    }

    public String getRoomDateName() {
        return roomDateName;
    }

    public void setRoomDateName(String roomDateName) {
        this.roomDateName = roomDateName;
    }

    public Integer getRoomDatePrice() {
        return roomDatePrice;
    }

    public void setRoomDatePrice(Integer roomDatePrice) {
        this.roomDatePrice = roomDatePrice;
    }

    public String getRoomDateStatus() {
        return roomDateStatus;
    }

    public void setRoomDateStatus(String roomDateStatus) {
        this.roomDateStatus = roomDateStatus;
    }

}
