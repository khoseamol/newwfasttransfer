
package com.example.webplat.amoldesigning.pojo.billing_history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BillingHistoryData {

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
    private String amount;
    @SerializedName("Cr_Dr_Type")
    @Expose
    private String crDrType;
    @SerializedName("Ref_No")
    @Expose
    private String refNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(String oldBalance) {
        this.oldBalance = oldBalance;
    }

    public String getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(String newBalance) {
        this.newBalance = newBalance;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCrDrType() {
        return crDrType;
    }

    public void setCrDrType(String crDrType) {
        this.crDrType = crDrType;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

}
