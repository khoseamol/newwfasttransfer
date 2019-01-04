
package com.example.webplat.amoldesigning.pojo.bbpssubcategory;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Datum implements Serializable {

    @SerializedName("Billercode")
    private String mBillercode;
    @SerializedName("Billerid")
    private int mBillerid;
    @SerializedName("Billername")
    private String mBillername;
    @SerializedName("CategoryId")
    private int mCategoryId;
    @SerializedName("Imageurl")
    private String mImageurl;
    @SerializedName("Partialallow")
    private Boolean mPartialallow;
    @SerializedName("Validateallow")
    private Boolean mValidateallow;

    public String getBillercode() {
        return mBillercode;
    }

    public void setBillercode(String billercode) {
        mBillercode = billercode;
    }

    public int getBillerid() {
        return mBillerid;
    }

    public void setBillerid(int billerid) {
        mBillerid = billerid;
    }

    public String getBillername() {
        return mBillername;
    }

    public void setBillername(String billername) {
        mBillername = billername;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }

    public String getImageurl() {
        return mImageurl;
    }

    public void setImageurl(String imageurl) {
        mImageurl = imageurl;
    }

    public Boolean getPartialallow() {
        return mPartialallow;
    }

    public void setPartialallow(Boolean partialallow) {
        mPartialallow = partialallow;
    }

    public Boolean getValidateallow() {
        return mValidateallow;
    }

    public void setValidateallow(Boolean validateallow) {
        mValidateallow = validateallow;
    }

    @Override
    public String toString() {
        return mBillername;
    }
}
