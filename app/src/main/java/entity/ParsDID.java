
package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ParsDID  implements Serializable {

    @SerializedName("Copyright")
    @Expose
    private String copyright;
    @SerializedName("Documentation")
    @Expose
    private String documentation;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

}
