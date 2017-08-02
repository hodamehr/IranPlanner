
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LodgingRoomBed implements Serializable {

    @SerializedName("room_bed_id")
    @Expose
    private String roomBedId;
    @SerializedName("room_bed_name")
    @Expose
    private String roomBedName;
    @SerializedName("room_bed_count")
    @Expose
    private String roomBedCount;
    @SerializedName("room_id")
    @Expose
    private String roomId;

    public String getRoomBedId() {
        return roomBedId;
    }

    public void setRoomBedId(String roomBedId) {
        this.roomBedId = roomBedId;
    }

    public String getRoomBedName() {
        return roomBedName;
    }

    public void setRoomBedName(String roomBedName) {
        this.roomBedName = roomBedName;
    }

    public String getRoomBedCount() {
        return roomBedCount;
    }

    public void setRoomBedCount(String roomBedCount) {
        this.roomBedCount = roomBedCount;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

}
