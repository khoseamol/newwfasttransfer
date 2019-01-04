
package com.example.webplat.amoldesigning.pojo.recghargeplan_pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

;

public class RechargePlan {

    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
