
package com.example.webplat.amoldesigning.pojo.recghargeplan_pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

;

public class PlanDataList {

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanid() {
        return planid;
    }

    public void setPlanid(String planid) {
        this.planid = planid;
    }

    public String getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid;
    }

    public String getCircleid() {
        return circleid;
    }

    public void setCircleid(String circleid) {
        this.circleid = circleid;
    }

    public String getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public String getRechargeValidity() {
        return rechargeValidity;
    }

    public void setRechargeValidity(String rechargeValidity) {
        this.rechargeValidity = rechargeValidity;
    }

    public String getRechargeShortdesc() {
        return rechargeShortdesc;
    }

    public void setRechargeShortdesc(String rechargeShortdesc) {
        this.rechargeShortdesc = rechargeShortdesc;
    }

    public String getRechargeLongdesc() {
        return rechargeLongdesc;
    }

    public void setRechargeLongdesc(String rechargeLongdesc) {
        this.rechargeLongdesc = rechargeLongdesc;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

}
