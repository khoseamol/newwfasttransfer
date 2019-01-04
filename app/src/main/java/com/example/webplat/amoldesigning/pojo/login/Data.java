
package com.example.webplat.amoldesigning.pojo.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Data {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("OwnerName")
    @Expose
    private String ownerName;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("UserType")
    @Expose
    private String userType;
    @SerializedName("MainBalance")
    @Expose
    private Double mainBalance;
    @SerializedName("Imgurl")
    @Expose
    private String imgurl;
    @SerializedName("LastSeen")
    @Expose
    private String lastSeen;
    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("SupportNo")
    @Expose
    private String supportNo;
    @SerializedName("FirmName")
    @Expose
    private String firmName;
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
     *     The ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 
     * @param ownerName
     *     The OwnerName
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

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
     *     The userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 
     * @param userType
     *     The UserType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 
     * @return
     *     The mainBalance
     */
    public Double getMainBalance() {
        return mainBalance;
    }

    /**
     * 
     * @param mainBalance
     *     The MainBalance
     */
    public void setMainBalance(Double mainBalance) {
        this.mainBalance = mainBalance;
    }

    /**
     * 
     * @return
     *     The imgurl
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * 
     * @param imgurl
     *     The Imgurl
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * 
     * @return
     *     The lastSeen
     */
    public String getLastSeen() {
        return lastSeen;
    }

    /**
     * 
     * @param lastSeen
     *     The LastSeen
     */
    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The Status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The supportNo
     */
    public String getSupportNo() {
        return supportNo;
    }

    /**
     * 
     * @param supportNo
     *     The SupportNo
     */
    public void setSupportNo(String supportNo) {
        this.supportNo = supportNo;
    }
    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }


    @SerializedName("ApiKey")
    @Expose
    private String apiKey;
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
