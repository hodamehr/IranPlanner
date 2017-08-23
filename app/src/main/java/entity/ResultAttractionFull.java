
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultAttractionFull implements Serializable {

    @SerializedName("Resul_Attraction")
    @Expose
    private ResulAttraction resulAttraction;
    @SerializedName("Result_attraction_list")
    @Expose
    private List<ResultAttractionList> resultAttractionList = null;

    public ResulAttraction getResulAttraction() {
        return resulAttraction;
    }

    public void setResulAttraction(ResulAttraction resulAttraction) {
        this.resulAttraction = resulAttraction;
    }

    public List<ResultAttractionList> getResultAttractionList() {
        return resultAttractionList;
    }

    public void setResultAttractionList(List<ResultAttractionList> resultAttractionList) {
        this.resultAttractionList = resultAttractionList;
    }

}
