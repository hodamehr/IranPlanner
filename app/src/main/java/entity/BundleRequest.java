
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BundleRequest implements Serializable{

    @SerializedName("bundle_id")
    @Expose
    private String bundleId;
    @SerializedName("bundle_lodging_id")
    @Expose
    private String bundleLodgingId;
    @SerializedName("bundle_lodging_title")
    @Expose
    private String bundleLodgingTitle;
    @SerializedName("lodging_img_url")
    @Expose
    private String lodgingImgUrl;
    @SerializedName("bundle_date_request")
    @Expose
    private String bundleDateRequest;
    @SerializedName("bundle_date_from")
    @Expose
    private String bundleDateFrom;
    @SerializedName("bundle_date_to")
    @Expose
    private String bundleDateTo;
    @SerializedName("bundle_date_count")
    @Expose
    private String bundleDateCount;
    @SerializedName("request_history")
    @Expose
    private String requestHistory;

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getBundleLodgingId() {
        return bundleLodgingId;
    }

    public void setBundleLodgingId(String bundleLodgingId) {
        this.bundleLodgingId = bundleLodgingId;
    }

    public String getBundleLodgingTitle() {
        return bundleLodgingTitle;
    }

    public void setBundleLodgingTitle(String bundleLodgingTitle) {
        this.bundleLodgingTitle = bundleLodgingTitle;
    }

    public String getLodgingImgUrl() {
        return lodgingImgUrl;
    }

    public void setLodgingImgUrl(String lodgingImgUrl) {
        this.lodgingImgUrl = lodgingImgUrl;
    }

    public String getBundleDateRequest() {
        return bundleDateRequest;
    }

    public void setBundleDateRequest(String bundleDateRequest) {
        this.bundleDateRequest = bundleDateRequest;
    }

    public String getBundleDateFrom() {
        return bundleDateFrom;
    }

    public void setBundleDateFrom(String bundleDateFrom) {
        this.bundleDateFrom = bundleDateFrom;
    }

    public String getBundleDateTo() {
        return bundleDateTo;
    }

    public void setBundleDateTo(String bundleDateTo) {
        this.bundleDateTo = bundleDateTo;
    }

    public String getBundleDateCount() {
        return bundleDateCount;
    }

    public void setBundleDateCount(String bundleDateCount) {
        this.bundleDateCount = bundleDateCount;
    }

    public String getRequestHistory() {
        return requestHistory;
    }

    public void setRequestHistory(String requestHistory) {
        this.requestHistory = requestHistory;
    }

}
