
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Statistics implements Serializable {

    @SerializedName("offset_last")
    @Expose
    private String offsetLast;
    @SerializedName("offset_next")
    @Expose
    private String offsetNext;
    @SerializedName("DurationArray")
    @Expose
    private List<String> durationArray = null;
    @SerializedName("Count_All")
    @Expose
    private String countAll;
    @SerializedName("Count_Row")
    @Expose
    private String countRow;
    @SerializedName("Response_Time")
    @Expose
    private Double responseTime;
    @SerializedName("Size")
    @Expose
    private String size;

    @SerializedName("Date_Now")
    @Expose
    private String dateNow;


    public String getOffsetLast() {
        return offsetLast;
    }

    public void setOffsetLast(String offsetLast) {
        this.offsetLast = offsetLast;
    }

    public String getOffsetNext() {
        return offsetNext;
    }

    public void setOffsetNext(String offsetNext) {
        this.offsetNext = offsetNext;
    }

    public List<String> getDurationArray() {
        return durationArray;
    }

    public void setDurationArray(List<String> durationArray) {
        this.durationArray = durationArray;
    }

    public String getCountAll() {
        return countAll;
    }

    public void setCountAll(String countAll) {
        this.countAll = countAll;
    }

    public String getCountRow() {
        return countRow;
    }

    public void setCountRow(String countRow) {
        this.countRow = countRow;
    }

    public Double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Double responseTime) {
        this.responseTime = responseTime;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public String getDateNow() {
        return dateNow;
    }

    public void setDateNow(String dateNow) {
        this.dateNow = dateNow;
    }

}
