
package com.example.webplat.amoldesigning.pojo.wallet_history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class WalletHistoryData{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("DrUserName")
    @Expose
    private String drUserName;
    @SerializedName("CrUserName")
    @Expose
    private String crUserName;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("PaymentDate")
    @Expose
    private String paymentDate;
    @SerializedName("PaymentType")
    @Expose
    private String paymentType;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("Balance")
    @Expose
    private String balance;

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
     *     The drUserName
     */
    public String getDrUserName() {
        return drUserName;
    }

    /**
     * 
     * @param drUserName
     *     The DrUserName
     */
    public void setDrUserName(String drUserName) {
        this.drUserName = drUserName;
    }

    /**
     * 
     * @return
     *     The crUserName
     */
    public String getCrUserName() {
        return crUserName;
    }

    /**
     * 
     * @param crUserName
     *     The CrUserName
     */
    public void setCrUserName(String crUserName) {
        this.crUserName = crUserName;
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
     *     The paymentDate
     */
    public String getPaymentDate() {
        return paymentDate;
    }

    /**
     * 
     * @param paymentDate
     *     The PaymentDate
     */
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * 
     * @return
     *     The paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * 
     * @param paymentType
     *     The PaymentType
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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
     *     The balance
     */
    public String getBalance() {
        return balance;
    }

    /**
     * 
     * @param balance
     *     The Balance
     */
    public void setBalance(String balance) {
        this.balance = balance;
    }

}
