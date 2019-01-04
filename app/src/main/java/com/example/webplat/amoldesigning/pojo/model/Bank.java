package com.example.webplat.amoldesigning.pojo.model;

/**
 * Created by Administrator on 28/09/2016.
 */
public class Bank {
    String bankId;

    public String getIfscCode() {
        return IfscCode;
    }

    public void setIfscCode(String ifscCode) {
        IfscCode = ifscCode;
    }

    String IfscCode;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    String bankName;

    @Override
    public String toString() {
        return bankName;
    }
}
