
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItineraryDifficulty  implements Serializable {

    @SerializedName("itinerary_difficulty")
    @Expose
    private String itineraryDifficulty;
    @SerializedName("itinerary_difficulty_color")
    @Expose
    private String itineraryDifficultyColor;
    @SerializedName("itinerary_difficulty_group")
    @Expose
    private String itineraryDifficultyGroup;

    public String getItineraryDifficulty() {
        return itineraryDifficulty;
    }

    public void setItineraryDifficulty(String itineraryDifficulty) {
        this.itineraryDifficulty = itineraryDifficulty;
    }

    public String getItineraryDifficultyColor() {
        return itineraryDifficultyColor;
    }

    public void setItineraryDifficultyColor(String itineraryDifficultyColor) {
        this.itineraryDifficultyColor = itineraryDifficultyColor;
    }

    public String getItineraryDifficultyGroup() {
        return itineraryDifficultyGroup;
    }

    public void setItineraryDifficultyGroup(String itineraryDifficultyGroup) {
        this.itineraryDifficultyGroup = itineraryDifficultyGroup;
    }

}
