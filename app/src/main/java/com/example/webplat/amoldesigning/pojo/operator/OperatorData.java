
package com.example.webplat.amoldesigning.pojo.operator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OperatorData {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("OperatorName")
    @Expose
    private String operatorName;
    @SerializedName("ServiceName")
    @Expose
    private String serviceName;
    @SerializedName("OurCode")
    @Expose
    private String ourCode;
    @SerializedName("Image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getOurCode() {
        return ourCode;
    }

    public void setOurCode(String ourCode) {
        this.ourCode = ourCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
