
package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.benadd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YesAddBenResponse {

    @SerializedName("ResponseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("ErrorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("Data")
    @Expose
    private YesAddBenResponseData data;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public YesAddBenResponseData getData() {
        return data;
    }

    public void setData(YesAddBenResponseData data) {
        this.data = data;
    }

}
