
package com.example.webplat.amoldesigning.pojo.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class RechargePlansByOperatorResponse {

    @SerializedName("result")
    @Expose
    private RechargePlansbyNumberResult result;

    /**
     * 
     * @return
     *     The result
     */
    public RechargePlansbyNumberResult getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(RechargePlansbyNumberResult result) {
        this.result = result;
    }

    public RechargePlansByOperatorResponse withResult(RechargePlansbyNumberResult result) {
        this.result = result;
        return this;
    }

}
