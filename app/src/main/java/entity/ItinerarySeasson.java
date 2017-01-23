
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItinerarySeasson  implements Serializable {

    @SerializedName("month_name")
    @Expose
    private String monthName;

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

}
