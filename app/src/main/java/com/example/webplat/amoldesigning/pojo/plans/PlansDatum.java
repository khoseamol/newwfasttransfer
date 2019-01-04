
package com.example.webplat.amoldesigning.pojo.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlansDatum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("planid")
    @Expose
    private String planid;
    @SerializedName("operatorid")
    @Expose
    private String operatorid;
    @SerializedName("circleid")
    @Expose
    private String circleid;
    @SerializedName("recharge_amount")
    @Expose
    private String rechargeAmount;
    @SerializedName("recharge_validity")
    @Expose
    private String rechargeValidity;
    @SerializedName("recharge_shortdesc")
    @Expose
    private String rechargeShortdesc;
    @SerializedName("recharge_longdesc")
    @Expose
    private String rechargeLongdesc;
    @SerializedName("recharge_type")
    @Expose
    private String rechargeType;

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
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The planid
     */
    public String getPlanid() {
        return planid;
    }

    /**
     * 
     * @param planid
     *     The planid
     */
    public void setPlanid(String planid) {
        this.planid = planid;
    }

    /**
     * 
     * @return
     *     The operatorid
     */
    public String getOperatorid() {
        return operatorid;
    }

    /**
     * 
     * @param operatorid
     *     The operatorid
     */
    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid;
    }

    /**
     * 
     * @return
     *     The circleid
     */
    public String getCircleid() {
        return circleid;
    }

    /**
     * 
     * @param circleid
     *     The circleid
     */
    public void setCircleid(String circleid) {
        this.circleid = circleid;
    }

    /**
     * 
     * @return
     *     The rechargeAmount
     */
    public String getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     * 
     * @param rechargeAmount
     *     The recharge_amount
     */
    public void setRechargeAmount(String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    /**
     * 
     * @return
     *     The rechargeValidity
     */
    public String getRechargeValidity() {
        return rechargeValidity;
    }

    /**
     * 
     * @param rechargeValidity
     *     The recharge_validity
     */
    public void setRechargeValidity(String rechargeValidity) {
        this.rechargeValidity = rechargeValidity;
    }

    /**
     * 
     * @return
     *     The rechargeShortdesc
     */
    public String getRechargeShortdesc() {
        return rechargeShortdesc;
    }

    /**
     * 
     * @param rechargeShortdesc
     *     The recharge_shortdesc
     */
    public void setRechargeShortdesc(String rechargeShortdesc) {
        this.rechargeShortdesc = rechargeShortdesc;
    }

    /**
     * 
     * @return
     *     The rechargeLongdesc
     */
    public String getRechargeLongdesc() {
        return rechargeLongdesc;
    }

    /**
     * 
     * @param rechargeLongdesc
     *     The recharge_longdesc
     */
    public void setRechargeLongdesc(String rechargeLongdesc) {
        this.rechargeLongdesc = rechargeLongdesc;
    }

    /**
     * 
     * @return
     *     The rechargeType
     */
    public String getRechargeType() {
        return rechargeType;
    }

    /**
     * 
     * @param rechargeType
     *     The recharge_type
     */
    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

}
