
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WidgetBookmark implements Serializable {

    @SerializedName("node_id")
    @Expose
    private Integer nodeId;
    @SerializedName("widget_type")
    @Expose
    private String widgetType;
    @SerializedName("widget_value")
    @Expose
    private Integer widgetValue;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    public Integer getWidgetValue() {
        return widgetValue;
    }

    public void setWidgetValue(Integer widgetValue) {
        this.widgetValue = widgetValue;
    }

}
