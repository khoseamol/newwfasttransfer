
package com.example.webplat.amoldesigning.pojo.balance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Data {

    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("MainBalance")
    @Expose
    private String mainBalance;

    /**
     * 
     * @return
     *     The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName
     *     The UserName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * @return
     *     The mainBalance
     */
    public String getMainBalance() {
        return mainBalance;
    }

    /**
     * 
     * @param mainBalance
     *     The MainBalance
     */
    public void setMainBalance(String mainBalance) {
        this.mainBalance = mainBalance;
    }

}
