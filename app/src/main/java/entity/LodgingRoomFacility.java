
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LodgingRoomFacility implements Serializable {

    @SerializedName("room_facility_name")
    @Expose
    private String roomFacilityName;
    @SerializedName("room_facility_id")
    @Expose
    private String roomFacilityId;
    @SerializedName("room_id")
    @Expose
    private String roomId;

    public String getRoomFacilityName() {
        return roomFacilityName;
    }

    public void setRoomFacilityName(String roomFacilityName) {
        this.roomFacilityName = roomFacilityName;
    }

    public String getRoomFacilityId() {
        return roomFacilityId;
    }

    public void setRoomFacilityId(String roomFacilityId) {
        this.roomFacilityId = roomFacilityId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

}
