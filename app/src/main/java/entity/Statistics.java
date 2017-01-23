
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Statistics implements Serializable {

    @SerializedName("Count Row")
    @Expose
    private Integer countRow;
    @SerializedName("Response Time")
    @Expose
    private String responseTime;

    public Integer getCountRow() {
        return countRow;
    }

    public void setCountRow(Integer countRow) {
        this.countRow = countRow;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

}
