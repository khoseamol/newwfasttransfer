
package com.example.webplat.amoldesigning.pojo.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RechargePlans {

    @SerializedName("ResponseStatus")
    @Expose
    private Integer responseStatus;
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
    private List<PlansDatum> data = null;

    /**
     * 
     * @return
     *     The responseStatus
     */
    public Integer getResponseStatus() {
        return responseStatus;
    }

    /**
     * 
     * @param responseStatus
     *     The ResponseStatus
     */
    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The Status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 
     * @param remarks
     *     The Remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 
     * @return
     *     The errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 
     * @param errorCode
     *     The ErrorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 
     * @return
     *     The data
     */
    public List<PlansDatum> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The Data
     */
    public void setData(List<PlansDatum> data) {
        this.data = data;
    }

}
