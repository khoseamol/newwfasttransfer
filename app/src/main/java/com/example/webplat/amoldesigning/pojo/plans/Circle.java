
package com.example.webplat.amoldesigning.pojo.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Circle {

    @SerializedName("circleid")
    @Expose
    private String circleid;

    /**
     * 
     * @return
     *     The circleid
     */
    public String getCircleid() {
        return circleid;
    }

    /**
     * 
     * @param circleid
     *     The circleid
     */
    public void setCircleid(String circleid) {
        this.circleid = circleid;
    }

}
