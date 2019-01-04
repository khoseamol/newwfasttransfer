
package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.master_ifsc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MasterIFSCData{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("BankName")
    @Expose
    private String bankName;
    @SerializedName("IfscCode")
    @Expose
    private String ifscCode;

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
     *     The bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 
     * @param bankName
     *     The BankName
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * 
     * @return
     *     The ifscCode
     */
    public String getIfscCode() {
        return ifscCode;
    }

    /**
     * 
     * @param ifscCode
     *     The IfscCode
     */
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    @Override
    public String toString() {
        return bankName;
    }
}
