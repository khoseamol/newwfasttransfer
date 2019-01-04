
package com.example.webplat.amoldesigning.pojo.auto_selection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Circle")
    @Expose
    private String circle;
    @SerializedName("Operator")
    @Expose
    private String operator;
    @SerializedName("OurCode")
    @Expose
    private String ourCode;
    @SerializedName("Series")
    @Expose
    private String series;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The circle
     */
    public String getCircle() {
        return circle;
    }

    /**
     * 
     * @param circle
     *     The Circle
     */
    public void setCircle(String circle) {
        this.circle = circle;
    }

    /**
     * 
     * @return
     *     The operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 
     * @param operator
     *     The Operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 
     * @return
     *     The ourCode
     */
    public String getOurCode() {
        return ourCode;
    }

    /**
     * 
     * @param ourCode
     *     The OurCode
     */
    public void setOurCode(String ourCode) {
        this.ourCode = ourCode;
    }

    /**
     * 
     * @return
     *     The series
     */
    public String getSeries() {
        return series;
    }

    /**
     * 
     * @param series
     *     The Series
     */
    public void setSeries(String series) {
        this.series = series;
    }

}
