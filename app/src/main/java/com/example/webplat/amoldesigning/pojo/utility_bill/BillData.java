package com.example.webplat.amoldesigning.pojo.utility_bill;

/**
 * Created by Administrator on 07/07/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class BillData {

    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("DueDate")
    @Expose
    private String dueDate;
    @SerializedName("Message")
    @Expose
    private String message;

    /**
     *
     * @return
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The Price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The dueDate
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     *
     * @param dueDate
     * The DueDate
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The Message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
