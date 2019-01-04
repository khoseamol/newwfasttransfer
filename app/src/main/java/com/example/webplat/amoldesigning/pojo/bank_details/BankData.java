package com.example.webplat.amoldesigning.pojo.bank_details;

/**
 * Created by Administrator on 06/06/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class BankData {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("AccountName")
    @Expose
    private String accountName;
    @SerializedName("AccountNo")
    @Expose
    private String accountNo;
    @SerializedName("IFSC")
    @Expose
    private String iFSC;
    @SerializedName("Branch")
    @Expose
    private String branch;

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
     * The accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     *
     * @param accountName
     * The AccountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     *
     * @return
     * The accountNo
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     *
     * @param accountNo
     * The AccountNo
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     *
     * @return
     * The iFSC
     */
    public String getIFSC() {
        return iFSC;
    }

    /**
     *
     * @param iFSC
     * The IFSC
     */
    public void setIFSC(String iFSC) {
        this.iFSC = iFSC;
    }

    /**
     *
     * @return
     * The branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     *
     * @param branch
     * The Branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return accountName+" ( "+branch+ " )";
    }
}
