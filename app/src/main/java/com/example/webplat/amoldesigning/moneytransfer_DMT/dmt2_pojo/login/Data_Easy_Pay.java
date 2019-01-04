
package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Data_Easy_Pay {

    @SerializedName("limit")
    @Expose
    private List<Limit> limit = new ArrayList<Limit>();
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("customer_id_type")
    @Expose
    private String customerIdType;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("state_desc")
    @Expose
    private String stateDesc;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    /**
     *
     * @return
     *     The limit
     */
    public List<Limit> getLimit() {
        return limit;
    }

    /**
     *
     * @param limit
     *     The limit
     */
    public void setLimit(List<Limit> limit) {
        this.limit = limit;
    }

    /**
     * 
     * @return
     *     The balance
     */
    public String getBalance() {
        return balance;
    }

    /**
     * 
     * @param balance
     *     The balance
     */
    public void setBalance(String balance) {
        this.balance = balance;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The customerIdType
     */
    public String getCustomerIdType() {
        return customerIdType;
    }

    /**
     * 
     * @param customerIdType
     *     The customer_id_type
     */
    public void setCustomerIdType(String customerIdType) {
        this.customerIdType = customerIdType;
    }

    /**
     * 
     * @return
     *     The customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 
     * @param customerId
     *     The customer_id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * 
     * @return
     *     The stateDesc
     */
    public String getStateDesc() {
        return stateDesc;
    }

    /**
     * 
     * @param stateDesc
     *     The state_desc
     */
    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    /**
     * 
     * @return
     *     The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 
     * @param currency
     *     The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 
     * @return
     *     The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 
     * @param mobile
     *     The mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
