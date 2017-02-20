
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultWidget {

    @SerializedName("widget_bookmark_value")
    @Expose
    private Integer widgetBookmarkValue;

    public Integer getWidgetBookmarkValue() {
        return widgetBookmarkValue;
    }

    public void setWidgetBookmarkValue(Integer widgetBookmarkValue) {
        this.widgetBookmarkValue = widgetBookmarkValue;
    }

}
