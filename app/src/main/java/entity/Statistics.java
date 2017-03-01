
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Statistics implements Serializable {

    @SerializedName("offset_last")
    @Expose
    private Integer offsetLast;
    @SerializedName("offset_next")
    @Expose
    private Integer offsetNext;
    @SerializedName("DurationArray")
    @Expose
    private List<Integer> durationArray = null;
    @SerializedName("Count_All")
    @Expose
    private Integer countAll;
    @SerializedName("Count_Row")
    @Expose
    private Integer countRow;
    @SerializedName("Response_Time")
    @Expose
    private Double responseTime;
    @SerializedName("Size")
    @Expose
    private Integer size;

    @SerializedName("Date_Now")
    @Expose
    private Integer dateNow;


    public Integer getOffsetLast() {
        return offsetLast;
    }

    public void setOffsetLast(Integer offsetLast) {
        this.offsetLast = offsetLast;
    }

    public Integer getOffsetNext() {
        return offsetNext;
    }

    public void setOffsetNext(Integer offsetNext) {
        this.offsetNext = offsetNext;
    }

    public List<Integer> getDurationArray() {
        return durationArray;
    }

    public void setDurationArray(List<Integer> durationArray) {
        this.durationArray = durationArray;
    }

    public Integer getCountAll() {
        return countAll;
    }

    public void setCountAll(Integer countAll) {
        this.countAll = countAll;
    }

    public Integer getCountRow() {
        return countRow;
    }

    public void setCountRow(Integer countRow) {
        this.countRow = countRow;
    }

    public Double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Double responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
    public Integer getDateNow() {
        return dateNow;
    }

    public void setDateNow(Integer dateNow) {
        this.dateNow = dateNow;
    }

}
