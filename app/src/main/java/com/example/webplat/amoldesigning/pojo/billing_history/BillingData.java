
package com.example.webplat.amoldesigning.pojo.billing_history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class BillingData {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("Old_Balance")
    @Expose
    private String oldBalance;
    @SerializedName("New_Balance")
    @Expose
    private String newBalance;
    @SerializedName("Trans_Type")
    @Expose
    private String transType;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("Amount")
    @Expose
    private Double amount;
    @SerializedName("Cr_Dr_Type")
    @Expose
    private String crDrType;
    @SerializedName("Ref_No")
    @Expose
    private Integer refNo;

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
     *     The oldBalance
     */
    public String getOldBalance() {
        return oldBalance;
    }

    /**
     * 
     * @param oldBalance
     *     The Old_Balance
     */
    public void setOldBalance(String oldBalance) {
        this.oldBalance = oldBalance;
    }

    /**
     * 
     * @return
     *     The newBalance
     */
    public String getNewBalance() {
        return newBalance;
    }

    /**
     * 
     * @param newBalance
     *     The New_Balance
     */
    public void setNewBalance(String newBalance) {
        this.newBalance = newBalance;
    }

    /**
     * 
     * @return
     *     The transType
     */
    public String getTransType() {
        return transType;
    }

    /**
     * 
     * @param transType
     *     The Trans_Type
     */
    public void setTransType(String transType) {
        this.transType = transType;
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
     *     The amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The Amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return
     *     The crDrType
     */
    public String getCrDrType() {
        return crDrType;
    }

    /**
     * 
     * @param crDrType
     *     The Cr_Dr_Type
     */
    public void setCrDrType(String crDrType) {
        this.crDrType = crDrType;
    }

    /**
     * 
     * @return
     *     The refNo
     */
    public Integer getRefNo() {
        return refNo;
    }

    /**
     * 
     * @param refNo
     *     The Ref_No
     */
    public void setRefNo(Integer refNo) {
        this.refNo = refNo;
    }

}
