
package com.example.webplat.amoldesigning.pojo.recharge_history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class TransHistoryData {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("OperatorName")
    @Expose
    private String operatorName;
    @SerializedName("Number")
    @Expose
    private String number;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("RechargeDate")
    @Expose
    private String rechargeDate;
    @SerializedName("OptID")

    @Expose
    private String optID;
    @SerializedName("Image")

    @Expose
    private String image;
    @SerializedName("Status")
    @Expose
    private String status;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The operatorName
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 
     * @param operatorName
     *     The OperatorName
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * 
     * @return
     *     The number
     */
    public String getNumber() {
        return number;
    }

    /**
     * 
     * @param number
     *     The Number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 
     * @return
     *     The amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The Amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return
     *     The rechargeDate
     */
    public String getRechargeDate() {
        return rechargeDate;
    }

    /**
     * 
     * @param rechargeDate
     *     The RechargeDate
     */
    public void setRechargeDate(String rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    /**
     * 
     * @return
     *     The optID
     */
    public String getOptID() {
        return optID;
    }

    /**
     * 
     * @param optID
     *     The OptID
     */
    public void setOptID(String optID) {
        this.optID = optID;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The Image
     */
    public void setImage(String image) {
        this.image = image;
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

}
