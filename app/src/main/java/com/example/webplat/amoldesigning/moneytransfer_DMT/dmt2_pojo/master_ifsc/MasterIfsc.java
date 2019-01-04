
package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.master_ifsc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MasterIfsc {

    @SerializedName("ResponseStatus")
    @Expose
    private String responseStatus;
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
    private List<MasterIFSCData> data = new ArrayList<MasterIFSCData>();

    /**
     * 
     * @return
     *     The responseStatus
     */
    public String getResponseStatus() {
        return responseStatus;
    }

    /**
     * 
     * @param responseStatus
     *     The ResponseStatus
     */
    public void setResponseStatus(String responseStatus) {
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
    public List<MasterIFSCData> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The DTHActivitionOpPojoData
     */
    public void setData(List<MasterIFSCData> data) {
        this.data = data;
    }

}
