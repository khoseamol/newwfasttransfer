
package com.example.webplat.amoldesigning.pojo.recghargeplan_pojo;

import com.example.webplat.amoldesigning.pojo.plans.PlansDatum;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

;

public class Result {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<PlansDatum> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PlansDatum> getData() {
        return data;
    }

    public void setData(List<PlansDatum> data) {
        this.data = data;
    }

}
