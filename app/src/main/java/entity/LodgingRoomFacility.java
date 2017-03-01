
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
    private Integer roomFacilityId;
    @SerializedName("room_id")
    @Expose
    private Integer roomId;

    public String getRoomFacilityName() {
        return roomFacilityName;
    }

    public void setRoomFacilityName(String roomFacilityName) {
        this.roomFacilityName = roomFacilityName;
    }

    public Integer getRoomFacilityId() {
        return roomFacilityId;
    }

    public void setRoomFacilityId(Integer roomFacilityId) {
        this.roomFacilityId = roomFacilityId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

}
