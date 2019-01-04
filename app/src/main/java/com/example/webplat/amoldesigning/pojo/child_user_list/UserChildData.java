
package com.example.webplat.amoldesigning.pojo.child_user_list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserChildData {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("OwnerName")
    @Expose
    private String ownerName;
    @SerializedName("UserType")
    @Expose
    private String userType;
    @SerializedName("Balance")
    @Expose
    private String balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return userName;
    }

}
