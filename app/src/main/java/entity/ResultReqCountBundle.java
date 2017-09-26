
package entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultReqCountBundle implements Serializable {

    @SerializedName("Result_req_count")
    @Expose
    private List<ResultReqCount> resultReqCount = null;
    @SerializedName("Result_req_bundle")
    @Expose
    private List<ResultReqBundle> resultReqBundle = null;

    public List<ResultReqCount> getResultReqCount() {
        return resultReqCount;
    }

    public void setResultReqCount(List<ResultReqCount> resultReqCount) {
        this.resultReqCount = resultReqCount;
    }

    public List<ResultReqBundle> getResultReqBundle() {
        return resultReqBundle;
    }

    public void setResultReqBundle(List<ResultReqBundle> resultReqBundle) {
        this.resultReqBundle = resultReqBundle;
    }

}
