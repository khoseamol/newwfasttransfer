
package com.example.webplat.amoldesigning.pojo.checkuserbyuserne;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetailsFromCheckUserData {

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
    private Object imgurl;
    @SerializedName("LastSeen")
    @Expose
    private String lastSeen;
    @SerializedName("Status")
    @Expose
    private Boolean status;

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

    public Object getImgurl() {
        return imgurl;
    }

    public void setImgurl(Object imgurl) {
        this.imgurl = imgurl;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
