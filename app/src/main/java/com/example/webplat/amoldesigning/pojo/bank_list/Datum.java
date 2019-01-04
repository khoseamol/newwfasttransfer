
package com.example.webplat.amoldesigning.pojo.bank_list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIFSC() {
        return iFSC;
    }

    public void setIFSC(String iFSC) {
        this.iFSC = iFSC;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;




    }

    @Override
    public String toString() {
        return accountName;
    }
}
