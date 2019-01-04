
package com.example.webplat.amoldesigning.pojo.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {

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
    private String mainBalance;
    @SerializedName("Imgurl")
    @Expose
    private String imgurl;
    @SerializedName("LastSeen")
    @Expose
    private String lastSeen;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMainBalance() {
        return mainBalance;
    }

    public void setMainBalance(String mainBalance) {
        this.mainBalance = mainBalance;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
