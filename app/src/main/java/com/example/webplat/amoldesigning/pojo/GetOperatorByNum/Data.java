
package com.example.webplat.amoldesigning.pojo.GetOperatorByNum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("circle")
    @Expose
    private String circle;
    @SerializedName("operators")
    @Expose
    private String operators;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("OurCode")
    @Expose
    private String ourCode;
    @SerializedName("series")
    @Expose
    private Object series;

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getOurCode() {
        return ourCode;
    }

    public void setOurCode(String ourCode) {
        this.ourCode = ourCode;
    }

    public Object getSeries() {
        return series;
    }

    public void setSeries(Object series) {
        this.series = series;
    }

}
