package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.add_beneficary;

/**
 * Created by Administrator on 02/06/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Data {

    @SerializedName("OTCRefCode")
    @Expose
    private String oTCRefCode;

    @SerializedName("OTCRef")
    @Expose
    private String OTCRef;

    /**
     *
     * @return
     * The oTCRefCode
     */
    public String getOTCRefCode() {
        return oTCRefCode;
    }

    /**
     *
     * @param oTCRefCode
     * The OTCRefCode
     */
    public void setOTCRefCode(String oTCRefCode) {
        this.oTCRefCode = oTCRefCode;
    }

    /**
     *
     * @return
     * The oTCRefCode
     */
    public String getOTCRef() {
        return OTCRef;
    }

    /**
     *
     * @param oTCRefCode
     * The OTCRefCode
     */
    public void setOTCRef(String oTCRefCode) {
        this.OTCRef = oTCRefCode;
    }

}