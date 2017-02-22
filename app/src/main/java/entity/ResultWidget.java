
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultWidget implements Serializable {
    @SerializedName("widget_bookmark_value")
    @Expose
    private Integer widgetBookmarkValue;
    @SerializedName("widget_wish_value")
    @Expose
    private Integer widgetWishValue;
    @SerializedName("widget_visited_value")
    @Expose
    private Integer widgetVisitedValue;
    @SerializedName("widget_like_value")
    @Expose
    private Integer widgetLikeValue;

    public Integer getWidgetBookmarkValue() {
        return widgetBookmarkValue;
    }

    public void setWidgetBookmarkValue(Integer widgetBookmarkValue) {
        this.widgetBookmarkValue = widgetBookmarkValue;
    }

    public Integer getWidgetWishValue() {
        return widgetWishValue;
    }

    public void setWidgetWishValue(Integer widgetWishValue) {
        this.widgetWishValue = widgetWishValue;
    }

    public Integer getWidgetVisitedValue() {
        return widgetVisitedValue;
    }

    public void setWidgetVisitedValue(Integer widgetVisitedValue) {
        this.widgetVisitedValue = widgetVisitedValue;
    }

    public Integer getWidgetLikeValue() {
        return widgetLikeValue;
    }

    public void setWidgetLikeValue(Integer widgetLikeValue) {
        this.widgetLikeValue = widgetLikeValue;
    }

//    @SerializedName("widget_bookmark_value")
//    @Expose
//    private Integer widgetBookmarkValue;
//    @SerializedName("widget_wish_value")
//    @Expose
//    private Integer widgetWishValue;
//    @SerializedName("widget_visited_value")
//    @Expose
//    private Integer widgetVisitedValue;
//    @SerializedName("widget_like_value")
//    @Expose
//    private Integer widgetLikeValue;
//
//    public Integer getWidgetBookmarkValue() {
//        return widgetBookmarkValue;
//    }
//
//    public void setWidgetBookmarkValue(Integer widgetBookmarkValue) {
//        this.widgetBookmarkValue = widgetBookmarkValue;
//    }
//
//    public Integer getWidgetWishValue() {
//        return widgetWishValue;
//    }
//
//    public void setWidgetWishValue(Integer widgetWishValue) {
//        this.widgetWishValue = widgetWishValue;
//    }
//
//    public Integer getWidgetVisitedValue() {
//        return widgetVisitedValue;
//    }
//
//    public void setWidgetVisitedValue(Integer widgetVisitedValue) {
//        this.widgetVisitedValue = widgetVisitedValue;
//    }
//
//    public Integer getWidgetLikeValue() {
//        return widgetLikeValue;
//    }
//
//    public void setWidgetLikeValue(Integer widgetLikeValue) {
//        this.widgetLikeValue = widgetLikeValue;
//    }

}
