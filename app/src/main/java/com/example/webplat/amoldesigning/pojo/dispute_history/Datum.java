
package com.example.webplat.amoldesigning.pojo.dispute_history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Datum {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("UserType")
    @Expose
    private String userType;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("TransId")
    @Expose
    private String transId;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Response")
    @Expose
    private String response;
    @SerializedName("ResponseDate")
    @Expose
    private String responseDate;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     *     The UserId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return
     *     The userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 
     * @param userType
     *     The UserType
     */
    public void setUserType(String userType) {
        this.userType = userType;
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
     *     The Message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The transId
     */
    public String getTransId() {
        return transId;
    }

    /**
     * 
     * @param transId
     *     The TransId
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The Date
     */
    public void setDate(String date) {
        this.date = date;
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
     *     The response
     */
    public String getResponse() {
        return response;
    }

    /**
     * 
     * @param response
     *     The Response
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * 
     * @return
     *     The responseDate
     */
    public String getResponseDate() {
        return responseDate;
    }

    /**
     * 
     * @param responseDate
     *     The ResponseDate
     */
    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

}
