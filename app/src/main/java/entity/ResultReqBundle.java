
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultReqBundle implements Serializable{

    @SerializedName("bundle_request")
    @Expose
    private BundleRequest bundleRequest;

    public BundleRequest getBundleRequest() {
        return bundleRequest;
    }

    public void setBundleRequest(BundleRequest bundleRequest) {
        this.bundleRequest = bundleRequest;
    }

}
