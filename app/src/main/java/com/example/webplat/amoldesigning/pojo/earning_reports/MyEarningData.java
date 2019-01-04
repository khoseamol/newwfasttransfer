
package com.example.webplat.amoldesigning.pojo.earning_reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MyEarningData{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("UniqueCode")
    @Expose
    private String uniqueCode;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("Number")
    @Expose
    private String number;
    @SerializedName("Operator")
    @Expose
    private String operator;
    @SerializedName("Commission")
    @Expose
    private String commission;
    @SerializedName("RechargeDate")
    @Expose
    private String rechargeDate;
    @SerializedName("new_bal")
    @Expose
    private String newBal;

    @SerializedName("Image")
    @Expose
    private String image;


    @SerializedName("ServiceType")
    @Expose
    private String serviceType;
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
     *     The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *     The Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return
     *     The uniqueCode
     */
    public String getUniqueCode() {
        return uniqueCode;
    }

    /**
     * 
     * @param uniqueCode
     *     The UniqueCode
     */
    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
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
     *     The operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 
     * @param operator
     *     The Operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 
     * @return
     *     The commission
     */
    public String getCommission() {
        return commission;
    }

    /**
     * 
     * @param commission
     *     The Commission
     */
    public void setCommission(String commission) {
        this.commission = commission;
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
     *     The newBal
     */
    public String getNewBal() {
        return newBal;
    }

    /**
     * 
     * @param newBal
     *     The new_bal
     */
    public void setNewBal(String newBal) {
        this.newBal = newBal;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The Image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     *
     * @param serviceType
     * The ServiceType
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

}
