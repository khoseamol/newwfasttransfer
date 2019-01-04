
package com.example.webplat.amoldesigning.pojo.checkuserbyuserne;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDetailsFromCheckUser {

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
    private List<UserDetailsFromCheckUserData> data = null;

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

    public List<UserDetailsFromCheckUserData> getData() {
        return data;
    }

    public void setData(List<UserDetailsFromCheckUserData> data) {
        this.data = data;
    }

}
