
package com.example.webplat.amoldesigning.pojo.bbpscategory;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class BBPSCategory {

    @SerializedName("Data")
    private List<Datum> mData;
    @SerializedName("ErrorCode")
    private String mErrorCode;
    @SerializedName("Remarks")
    private String mRemarks;
    @SerializedName("ResponseStatus")
    private String mResponseStatus;
    @SerializedName("Status")
    private String mStatus;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public String getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(String errorCode) {
        mErrorCode = errorCode;
    }

    public String getRemarks() {
        return mRemarks;
    }

    public void setRemarks(String remarks) {
        mRemarks = remarks;
    }

    public String getResponseStatus() {
        return mResponseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        mResponseStatus = responseStatus;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
