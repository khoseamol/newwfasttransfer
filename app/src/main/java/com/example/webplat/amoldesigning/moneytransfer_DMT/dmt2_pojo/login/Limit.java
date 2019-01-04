
package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Limit {

    @SerializedName("remaining")
    @Expose
    private String remaining;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("priority")
    @Expose
    private Object priority;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("used")
    @Expose
    private String used;
    @SerializedName("pipe")
    @Expose
    private Integer pipe;

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getPriority() {
        return priority;
    }

    public void setPriority(Object priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public Integer getPipe() {
        return pipe;
    }

    public void setPipe(Integer pipe) {
        this.pipe = pipe;
    }

}
