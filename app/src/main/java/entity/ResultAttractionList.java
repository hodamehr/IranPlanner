
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultAttractionList implements Serializable{

    @SerializedName("Resul_Attraction")
    @Expose
    private ResulAttraction resulAttraction;

    public ResulAttraction getResulAttraction() {
        return resulAttraction;
    }

    public void setResulAttraction(ResulAttraction resulAttraction) {
        this.resulAttraction = resulAttraction;
    }

}
