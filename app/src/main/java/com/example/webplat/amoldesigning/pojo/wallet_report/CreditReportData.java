
package com.example.webplat.amoldesigning.pojo.wallet_report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditReportData {

    @SerializedName("Trxnid")
    @Expose
    private String trxnid;
    @SerializedName("CrUsername")
    @Expose
    private String crUsername;
    @SerializedName("DrUsername")
    @Expose
    private String drUsername;
    @SerializedName("OpeningBalance")
    @Expose
    private String openingBalance;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("ClosingBalance")
    @Expose
    private String closingBalance;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("Refid")
    @Expose
    private String refid;
    @SerializedName("Paymentmode")
    @Expose
    private String paymentmode;
    @SerializedName("PaymentDate")
    @Expose
    private String paymentDate;

    public String getTrxnid() {
        return trxnid;
    }

    public void setTrxnid(String trxnid) {
        this.trxnid = trxnid;
    }

    public String getCrUsername() {
        return crUsername;
    }

    public void setCrUsername(String crUsername) {
        this.crUsername = crUsername;
    }

    public String getDrUsername() {
        return drUsername;
    }

    public void setDrUsername(String drUsername) {
        this.drUsername = drUsername;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(String closingBalance) {
        this.closingBalance = closingBalance;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRefid() {
        return refid;
    }

    public void setRefid(String refid) {
        this.refid = refid;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

}
