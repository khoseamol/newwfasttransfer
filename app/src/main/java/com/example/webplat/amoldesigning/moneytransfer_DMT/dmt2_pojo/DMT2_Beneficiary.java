package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Awesome Pojo Generator
 * */
public class DMT2_Beneficiary implements Serializable {
  @SerializedName("bank")
  @Expose
  private String bank;
  @SerializedName("imps")
  @Expose
  private String imps;
  @SerializedName("last_success_imps")
  @Expose
  private String last_success_imps;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("mobile")
  @Expose
  private String mobile;
  @SerializedName("last_success_date")
  @Expose
  private String last_success_date;
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("ifsc")
  @Expose
  private String ifsc;
  @SerializedName("last_success_name")
  @Expose
  private String last_success_name;
  @SerializedName("account")
  @Expose
  private String account;
  @SerializedName("status")
  @Expose
  private String status;
  public void setBank(String bank){
   this.bank=bank;
  }
  public String getBank(){
   return bank;
  }
  public void setImps(String imps){
   this.imps=imps;
  }
  public String getImps(){
   return imps;
  }
  public void setLast_success_imps(String last_success_imps){
   this.last_success_imps=last_success_imps;
  }
  public String getLast_success_imps(){
   return last_success_imps;
  }
  public void setName(String name){
   this.name=name;
  }
  public String getName(){
   return name;
  }
  public void setMobile(String mobile){
   this.mobile=mobile;
  }
  public String getMobile(){
   return mobile;
  }
  public void setLast_success_date(String last_success_date){
   this.last_success_date=last_success_date;
  }
  public String getLast_success_date(){
   return last_success_date;
  }
  public void setId(String id){
   this.id=id;
  }
  public String getId(){
   return id;
  }
  public void setIfsc(String ifsc){
   this.ifsc=ifsc;
  }
  public String getIfsc(){
   return ifsc;
  }
  public void setLast_success_name(String last_success_name){
   this.last_success_name=last_success_name;
  }
  public String getLast_success_name(){
   return last_success_name;
  }
  public void setAccount(String account){
   this.account=account;
  }
  public String getAccount(){
   return account;
  }
  public void setStatus(String status){
   this.status=status;
  }
  public String getStatus(){
   return status;
  }
}