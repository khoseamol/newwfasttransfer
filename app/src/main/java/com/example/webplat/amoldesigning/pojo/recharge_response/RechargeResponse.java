
package com.example.webplat.amoldesigning.pojo.recharge_response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;


@Generated("org.jsonschema2pojo")
public class RechargeResponse {

    @SerializedName("ResponseStatus")
    @Expose
    private int responseStatus;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("ErrorCode")
    @Expose
    private String errorCode;
    @SerializedName("Data")
    @Expose
    private String data;

    /**
     *
     * @return
     * The responseStatus
     */
    public int getResponseStatus() {
        return responseStatus;
    }

    /**
     *
     * @param responseStatus
     * The ResponseStatus
     */
    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The Status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     *
     * @param remarks
     * The Remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     *
     * @return
     * The errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     *
     * @param errorCode
     * The ErrorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     *
     * @return
     * The data
     */
    public String getData() {
        return data;
    }

    /**
     *
     * @param data
     * The Data
     */
    public void setData(String data) {
        this.data = data;
    }

}