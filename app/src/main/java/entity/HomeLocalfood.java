
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeLocalfood implements Serializable{

    @SerializedName("localfood_id")
    @Expose
    private String localfoodId;
    @SerializedName("localfood_title")
    @Expose
    private String localfoodTitle;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;

    public String getLocalfoodId() {
        return localfoodId;
    }

    public void setLocalfoodId(String localfoodId) {
        this.localfoodId = localfoodId;
    }

    public String getLocalfoodTitle() {
        return localfoodTitle;
    }

    public void setLocalfoodTitle(String localfoodTitle) {
        this.localfoodTitle = localfoodTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
