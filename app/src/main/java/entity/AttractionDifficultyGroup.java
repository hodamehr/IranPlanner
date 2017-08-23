
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AttractionDifficultyGroup implements Serializable {

    @SerializedName("attraction_difficulty")
    @Expose
    private String attractionDifficulty;
    @SerializedName("attraction_difficulty_color")
    @Expose
    private String attractionDifficultyColor;
    @SerializedName("attraction_difficulty_group")
    @Expose
    private String attractionDifficultyGroup;

    public String getAttractionDifficulty() {
        return attractionDifficulty;
    }

    public void setAttractionDifficulty(String attractionDifficulty) {
        this.attractionDifficulty = attractionDifficulty;
    }

    public String getAttractionDifficultyColor() {
        return attractionDifficultyColor;
    }

    public void setAttractionDifficultyColor(String attractionDifficultyColor) {
        this.attractionDifficultyColor = attractionDifficultyColor;
    }

    public String getAttractionDifficultyGroup() {
        return attractionDifficultyGroup;
    }

    public void setAttractionDifficultyGroup(String attractionDifficultyGroup) {
        this.attractionDifficultyGroup = attractionDifficultyGroup;
    }

}
