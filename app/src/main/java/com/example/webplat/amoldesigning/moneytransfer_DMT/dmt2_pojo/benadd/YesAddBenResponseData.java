
package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.benadd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YesAddBenResponseData {

    @SerializedName("RGCode")
    @Expose
    private String rGCode;
    @SerializedName("Beneid")
    @Expose
    private String beneid;

    public String getRGCode() {
        return rGCode;
    }

    public void setRGCode(String rGCode) {
        this.rGCode = rGCode;
    }

    public String getBeneid() {
        return beneid;
    }

    public void setBeneid(String beneid) {
        this.beneid = beneid;
    }

}
