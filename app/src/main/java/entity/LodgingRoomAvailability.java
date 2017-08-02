
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LodgingRoomAvailability implements Serializable {

    @SerializedName("room_date_no")
    @Expose
    private String roomDateNo;
    @SerializedName("room_date_unix")
    @Expose
    private String roomDateUnix;
    @SerializedName("room_date_date")
    @Expose
    private String roomDateDate;
    @SerializedName("room_date_name")
    @Expose
    private String roomDateName;
    @SerializedName("room_date_price")
    @Expose
    private String roomDatePrice;
    @SerializedName("room_date_status")
    @Expose
    private String roomDateStatus;

    public String getRoomDateNo() {
        return roomDateNo;
    }

    public void setRoomDateNo(String roomDateNo) {
        this.roomDateNo = roomDateNo;
    }

    public String getRoomDateUnix() {
        return roomDateUnix;
    }

    public void setRoomDateUnix(String roomDateUnix) {
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

    public String getRoomDatePrice() {
        return roomDatePrice;
    }

    public void setRoomDatePrice(String roomDatePrice) {
        this.roomDatePrice = roomDatePrice;
    }

    public String getRoomDateStatus() {
        return roomDateStatus;
    }

    public void setRoomDateStatus(String roomDateStatus) {
        this.roomDateStatus = roomDateStatus;
    }

}
