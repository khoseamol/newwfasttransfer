
package com.example.webplat.amoldesigning.pojo.credit_summary_report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditReportDataaa {

    @SerializedName("Trxnid")
    @Expose
    private String trxnid;
    @SerializedName("PaymentType")
    @Expose
    private String paymentType;
    @SerializedName("RTCRUsername")
    @Expose
    private String rTCRUsername;
    @SerializedName("RTDRUsername")
    @Expose
    private String rTDRUsername;
    @SerializedName("RechDate")
    @Expose
    private String rechDate;
    @SerializedName("RechTime")
    @Expose
    private String rechTime;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("old_bal")
    @Expose
    private String oldBal;
    @SerializedName("new_bal")
    @Expose
    private String newBal;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("balance_id")
    @Expose
    private String balanceId;

    public String getTrxnid() {
        return trxnid;
    }

    public void setTrxnid(String trxnid) {
        this.trxnid = trxnid;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getRTCRUsername() {
        return rTCRUsername;
    }

    public void setRTCRUsername(String rTCRUsername) {
        this.rTCRUsername = rTCRUsername;
    }

    public String getRTDRUsername() {
        return rTDRUsername;
    }

    public void setRTDRUsername(String rTDRUsername) {
        this.rTDRUsername = rTDRUsername;
    }

    public String getRechDate() {
        return rechDate;
    }

    public void setRechDate(String rechDate) {
        this.rechDate = rechDate;
    }

    public String getRechTime() {
        return rechTime;
    }

    public void setRechTime(String rechTime) {
        this.rechTime = rechTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOldBal() {
        return oldBal;
    }

    public void setOldBal(String oldBal) {
        this.oldBal = oldBal;
    }

    public String getNewBal() {
        return newBal;
    }

    public void setNewBal(String newBal) {
        this.newBal = newBal;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

}
