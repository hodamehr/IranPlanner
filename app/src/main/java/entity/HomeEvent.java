
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeEvent implements Serializable {

    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("event_title")
    @Expose
    private String eventTitle;
    @SerializedName("event_duration")
    @Expose
    private Object eventDuration;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Object getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(Object eventDuration) {
        this.eventDuration = eventDuration;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
