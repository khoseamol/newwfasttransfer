
package com.example.webplat.amoldesigning.pojo.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class RechargePlansbyNumberResult {

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
    private OperatorByNumberData data;

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
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public RechargePlansbyNumberResult withStatus(String status) {
        this.status = status;
        return this;
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
     *     The error_code
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public RechargePlansbyNumberResult withErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public RechargePlansbyNumberResult withMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 
     * @return
     *     The data
     */
    public OperatorByNumberData getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(OperatorByNumberData data) {
        this.data = data;
    }

    public RechargePlansbyNumberResult withData(OperatorByNumberData data) {
        this.data = data;
        return this;
    }

}
