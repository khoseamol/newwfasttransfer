
package com.example.webplat.amoldesigning.pojo.bank_list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BabkList {

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
    private List<Datum> data = null;

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
