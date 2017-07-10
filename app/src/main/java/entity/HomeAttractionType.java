
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeAttractionType implements Serializable {

    @SerializedName("attraction_type_count")
    @Expose
    private String attractionTypeCount;
    @SerializedName("attraction_type_id")
    @Expose
    private String attractionTypeId;
    @SerializedName("attraction_type_title")
    @Expose
    private String attractionTypeTitle;

    public String getAttractionTypeCount() {
        return attractionTypeCount;
    }

    public void setAttractionTypeCount(String attractionTypeCount) {
        this.attractionTypeCount = attractionTypeCount;
    }

    public String getAttractionTypeId() {
        return attractionTypeId;
    }

    public void setAttractionTypeId(String attractionTypeId) {
        this.attractionTypeId = attractionTypeId;
    }

    public String getAttractionTypeTitle() {
        return attractionTypeTitle;
    }

    public void setAttractionTypeTitle(String attractionTypeTitle) {
        this.attractionTypeTitle = attractionTypeTitle;
    }

}
