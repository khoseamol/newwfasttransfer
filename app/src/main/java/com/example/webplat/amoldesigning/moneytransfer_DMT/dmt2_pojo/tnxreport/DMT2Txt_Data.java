package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.tnxreport;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class DMT2Txt_Data {
  @SerializedName("Status")
  @Expose
  private String Status;
  @SerializedName("OperatorName")
  @Expose
  private String OperatorName;
  @SerializedName("OptID")
  @Expose
  private String OptID;
  @SerializedName("Accountno")
  @Expose
  private String Accountno;
  @SerializedName("Amount")
  @Expose
  private String Amount;
  @SerializedName("Trxndate")
  @Expose
  private String Trxndate;
  @SerializedName("Accountname")
  @Expose
  private String Accountname;
  @SerializedName("Reason")
  @Expose
  private String Reason;
  @SerializedName("IFSC")
  @Expose
  private String IFSC;
  @SerializedName("Bankname")
  @Expose
  private String Bankname;
  @SerializedName("Sendername")
  @Expose
  private String Sendername;
  @SerializedName("Number")
  @Expose
  private String Number;
  @SerializedName("Trxnid")
  @Expose
  private String Trxnid;
  @SerializedName("Trxntype")
  @Expose
  private String Trxntype;
  public void setStatus(String Status){
   this.Status=Status;
  }
  public String getStatus(){
   return Status;
  }
  public void setOperatorName(String OperatorName){
   this.OperatorName=OperatorName;
  }
  public String getOperatorName(){
   return OperatorName;
  }
  public void setOptID(String OptID){
   this.OptID=OptID;
  }
  public String getOptID(){
   return OptID;
  }
  public void setAccountno(String Accountno){
   this.Accountno=Accountno;
  }
  public String getAccountno(){
   return Accountno;
  }
  public void setAmount(String Amount){
   this.Amount=Amount;
  }
  public String getAmount(){
   return Amount;
  }
  public void setTrxndate(String Trxndate){
   this.Trxndate=Trxndate;
  }
  public String getTrxndate(){
   return Trxndate;
  }
  public void setAccountname(String Accountname){
   this.Accountname=Accountname;
  }
  public String getAccountname(){
   return Accountname;
  }
  public void setReason(String Reason){
   this.Reason=Reason;
  }
  public String getReason(){
   return Reason;
  }
  public void setIFSC(String IFSC){
   this.IFSC=IFSC;
  }
  public String getIFSC(){
   return IFSC;
  }
  public void setBankname(String Bankname){
   this.Bankname=Bankname;
  }
  public String getBankname(){
   return Bankname;
  }
  public void setSendername(String Sendername){
   this.Sendername=Sendername;
  }
  public String getSendername(){
   return Sendername;
  }
  public void setNumber(String Number){
   this.Number=Number;
  }
  public String getNumber(){
   return Number;
  }
  public void setTrxnid(String Trxnid){
   this.Trxnid=Trxnid;
  }
  public String getTrxnid(){
   return Trxnid;
  }
  public void setTrxntype(String Trxntype){
   this.Trxntype=Trxntype;
  }
  public String getTrxntype(){
   return Trxntype;
  }
}