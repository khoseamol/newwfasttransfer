package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.fundtransfer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class Dmt2transferData {
  @SerializedName("Operator")
  @Expose
  private String Operator;
  @SerializedName("Status")
  @Expose
  private String Status;
  @SerializedName("sendername")
  @Expose
  private String sendername;
  @SerializedName("benename")
  @Expose
  private String benename;
  @SerializedName("Amount")
  @Expose
  private Integer Amount;
  @SerializedName("ifsccode")
  @Expose
  private String ifsccode;
  @SerializedName("ServiceId")
  @Expose
  private Integer ServiceId;
  @SerializedName("OPTID")
  @Expose
  private String OPTID;
  @SerializedName("ReceiptId")
  @Expose
  private Integer ReceiptId;
  @SerializedName("ServiceCharge")
  @Expose
  private Integer ServiceCharge;
  @SerializedName("Number")
  @Expose
  private Long Number;
  @SerializedName("RechargeDate")
  @Expose
  private String RechargeDate;
  @SerializedName("accountno")
  @Expose
  private Long accountno;
  @SerializedName("Id")
  @Expose
  private Integer Id;
  @SerializedName("paytype")
  @Expose
  private String paytype;
  public void setOperator(String Operator){
   this.Operator=Operator;
  }
  public String getOperator(){
   return Operator;
  }
  public void setStatus(String Status){
   this.Status=Status;
  }
  public String getStatus(){
   return Status;
  }
  public void setSendername(String sendername){
   this.sendername=sendername;
  }
  public String getSendername(){
   return sendername;
  }
  public void setBenename(String benename){
   this.benename=benename;
  }
  public String getBenename(){
   return benename;
  }
  public void setAmount(Integer Amount){
   this.Amount=Amount;
  }
  public Integer getAmount(){
   return Amount;
  }
  public void setIfsccode(String ifsccode){
   this.ifsccode=ifsccode;
  }
  public String getIfsccode(){
   return ifsccode;
  }
  public void setServiceId(Integer ServiceId){
   this.ServiceId=ServiceId;
  }
  public Integer getServiceId(){
   return ServiceId;
  }
  public void setOPTID(String OPTID){
   this.OPTID=OPTID;
  }
  public String getOPTID(){
   return OPTID;
  }
  public void setReceiptId(Integer ReceiptId){
   this.ReceiptId=ReceiptId;
  }
  public Integer getReceiptId(){
   return ReceiptId;
  }
  public void setServiceCharge(Integer ServiceCharge){
   this.ServiceCharge=ServiceCharge;
  }
  public Integer getServiceCharge(){
   return ServiceCharge;
  }
  public void setNumber(Long Number){
   this.Number=Number;
  }
  public Long getNumber(){
   return Number;
  }
  public void setRechargeDate(String RechargeDate){
   this.RechargeDate=RechargeDate;
  }
  public String getRechargeDate(){
   return RechargeDate;
  }
  public void setAccountno(Long accountno){
   this.accountno=accountno;
  }
  public Long getAccountno(){
   return accountno;
  }
  public void setId(Integer Id){
   this.Id=Id;
  }
  public Integer getId(){
   return Id;
  }
  public void setPaytype(String paytype){
   this.paytype=paytype;
  }
  public String getPaytype(){
   return paytype;
  }
}