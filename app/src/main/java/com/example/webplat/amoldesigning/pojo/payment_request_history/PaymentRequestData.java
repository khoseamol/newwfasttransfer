package com.example.webplat.amoldesigning.pojo.payment_request_history;

/**
 * Created by Administrator on 06/06/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentRequestData {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Amount")
    @Expose
    private Integer amount;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("UserDetails")
    @Expose
    private String userDetails;
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("PaymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("ChequeNo")
    @Expose
    private Object chequeNo;
    @SerializedName("ChequeDate")
    @Expose
    private Object chequeDate;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("RefNo")
    @Expose
    private String refNo;
    @SerializedName("DepositBank")
    @Expose
    private String depositBank;
    @SerializedName("Approved")
    @Expose
    private String approved;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     * The Amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     * The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     * The UserName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     * The userDetails
     */
    public String getUserDetails() {
        return userDetails;
    }

    /**
     *
     * @param userDetails
     * The UserDetails
     */
    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    /**
     *
     * @return
     * The requestDate
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     *
     * @param requestDate
     * The RequestDate
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /**
     *
     * @return
     * The paymentMode
     */
    public String getPaymentMode() {
        return paymentMode;
    }

    /**
     *
     * @param paymentMode
     * The PaymentMode
     */
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    /**
     *
     * @return
     * The chequeNo
     */
    public Object getChequeNo() {
        return chequeNo;
    }

    /**
     *
     * @param chequeNo
     * The ChequeNo
     */
    public void setChequeNo(Object chequeNo) {
        this.chequeNo = chequeNo;
    }

    /**
     *
     * @return
     * The chequeDate
     */
    public Object getChequeDate() {
        return chequeDate;
    }

    /**
     *
     * @param chequeDate
     * The ChequeDate
     */
    public void setChequeDate(Object chequeDate) {
        this.chequeDate = chequeDate;
    }

    /**
     *
     * @return
     * The remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     *
     * @param remarks
     * The Remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     *
     * @return
     * The refNo
     */
    public String getRefNo() {
        return refNo;
    }

    /**
     *
     * @param refNo
     * The RefNo
     */
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    /**
     *
     * @return
     * The depositBank
     */
    public String getDepositBank() {
        return depositBank;
    }

    /**
     *
     * @param depositBank
     * The DepositBank
     */
    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    /**
     *
     * @return
     * The approved
     */
    public String getApproved() {
        return approved;
    }

    /**
     *
     * @param approved
     * The Approved
     */
    public void setApproved(String approved) {
        this.approved = approved;
    }

}

