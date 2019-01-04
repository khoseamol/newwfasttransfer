
package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Data {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("response_type_id")
    @Expose
    private Integer responseTypeId;
    @SerializedName("response_status_id")
    @Expose
    private Integer responseStatusId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Data_Easy_Pay data;

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

    /**
     *
     * @return
     *     The responseTypeId
     */
    public Integer getResponseTypeId() {
        return responseTypeId;
    }

    /**
     *
     * @param responseTypeId
     *     The response_type_id
     */
    public void setResponseTypeId(Integer responseTypeId) {
        this.responseTypeId = responseTypeId;
    }

    /**
     *
     * @return
     *     The responseStatusId
     */
    public Integer getResponseStatusId() {
        return responseStatusId;
    }

    /**
     *
     * @param responseStatusId
     *     The response_status_id
     */
    public void setResponseStatusId(Integer responseStatusId) {
        this.responseStatusId = responseStatusId;
    }

    /**
     *
     * @return
     *     The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *     The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @return
     *     The data
     */
    public Data_Easy_Pay getData() {
        return data;
    }

    /**
     *
     * @param data
     *     The data
     */
    public void setData(Data_Easy_Pay data) {
        this.data = data;
    }

}
