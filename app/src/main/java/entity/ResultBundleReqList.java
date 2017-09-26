
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultBundleReqList {

    @SerializedName("Result_room")
    @Expose
    private ResultRoom resultRoom;

    public ResultRoom getResultRoom() {
        return resultRoom;
    }

    public void setResultRoom(ResultRoom resultRoom) {
        this.resultRoom = resultRoom;
    }

}
