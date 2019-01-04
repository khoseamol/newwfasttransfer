
package com.example.webplat.amoldesigning.pojo.utility_bbps.utility_bill;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Data {

    @SerializedName("Billdate")
    private String mBilldate;
    @SerializedName("Billernumber")
    private String mBillernumber;
    @SerializedName("Consumername")
    private String mConsumername;
    @SerializedName("Duedate")
    private String mDuedate;
    @SerializedName("Partial")
    private String mPartial;
    @SerializedName("Price")
    private Double mPrice;
    @SerializedName("Remarks")
    private String mRemarks;

    public String getBilldate() {
        return mBilldate;
    }

    public void setBilldate(String billdate) {
        mBilldate = billdate;
    }

    public String getBillernumber() {
        return mBillernumber;
    }

    public void setBillernumber(String billernumber) {
        mBillernumber = billernumber;
    }

    public String getConsumername() {
        return mConsumername;
    }

    public void setConsumername(String consumername) {
        mConsumername = consumername;
    }

    public String getDuedate() {
        return mDuedate;
    }

    public void setDuedate(String duedate) {
        mDuedate = duedate;
    }

    public String getPartial() {
        return mPartial;
    }

    public void setPartial(String partial) {
        mPartial = partial;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        mPrice = price;
    }

    public String getRemarks() {
        return mRemarks;
    }

    public void setRemarks(String remarks) {
        mRemarks = remarks;
    }

}
