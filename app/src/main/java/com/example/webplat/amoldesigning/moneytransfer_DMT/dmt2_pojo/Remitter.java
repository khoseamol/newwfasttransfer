package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class Remitter{
  @SerializedName("pincode")
  @Expose
  private String pincode;
  @SerializedName("remaininglimit")
  @Expose
  private String remaininglimit;
  @SerializedName("address")
  @Expose
  private String address;
  @SerializedName("perm_txn_limit")
  @Expose
  private String perm_txn_limit;
  @SerializedName("city")
  @Expose
  private String city;
  @SerializedName("kycdocs")
  @Expose
  private String kycdocs;
  @SerializedName("kycstatus")
  @Expose
  private String kycstatus;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("mobile")
  @Expose
  private Long mobile;
  @SerializedName("consumedlimit")
  @Expose
  private String consumedlimit;
  @SerializedName("state")
  @Expose
  private String state;
  @SerializedName("id")
  @Expose
  private String id;
  public void setPincode(String pincode){
   this.pincode=pincode;
  }
  public String getPincode(){
   return pincode;
  }
  public void setRemaininglimit(String remaininglimit){
   this.remaininglimit=remaininglimit;
  }
  public String getRemaininglimit(){
   return remaininglimit;
  }
  public void setAddress(String address){
   this.address=address;
  }
  public String getAddress(){
   return address;
  }
  public void setPerm_txn_limit(String perm_txn_limit){
   this.perm_txn_limit=perm_txn_limit;
  }
  public String getPerm_txn_limit(){
   return perm_txn_limit;
  }
  public void setCity(String city){
   this.city=city;
  }
  public String getCity(){
   return city;
  }
  public void setKycdocs(String kycdocs){
   this.kycdocs=kycdocs;
  }
  public String getKycdocs(){
   return kycdocs;
  }
  public void setKycstatus(String kycstatus){
   this.kycstatus=kycstatus;
  }
  public String getKycstatus(){
   return kycstatus;
  }
  public void setName(String name){
   this.name=name;
  }
  public String getName(){
   return name;
  }
  public void setMobile(Long mobile){
   this.mobile=mobile;
  }
  public Long getMobile(){
   return mobile;
  }
  public void setConsumedlimit(String consumedlimit){
   this.consumedlimit=consumedlimit;
  }
  public String getConsumedlimit(){
   return consumedlimit;
  }
  public void setState(String state){
   this.state=state;
  }
  public String getState(){
   return state;
  }
  public void setId(String id){
   this.id=id;
  }
  public String getId(){
   return id;
  }
}