
package com.example.webplat.amoldesigning.pojo.commission_history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommissionData {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Operators")
    @Expose
    private String operators;
    @SerializedName("Percentage")
    @Expose
    private String percentage;
    @SerializedName("Charge")
    @Expose
    private String charge;
    @SerializedName("SId")
    @Expose
    private String sId;
    @SerializedName("ServiceName")
    @Expose
    private String serviceName;
    @SerializedName("Image")
    @Expose
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getSId() {
        return sId;
    }

    public void setSId(String sId) {
        this.sId = sId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
