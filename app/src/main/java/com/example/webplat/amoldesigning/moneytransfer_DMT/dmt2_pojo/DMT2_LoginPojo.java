package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class DMT2_LoginPojo{
  @SerializedName("Status")
  @Expose
  private String Status;
  @SerializedName("ResponseCode")
  @Expose
  private Integer ResponseCode;
  @SerializedName("Remarks")
  @Expose
  private String Remarks;
  @SerializedName("Data")
  @Expose
  private DMT2_LoginData Data;
  @SerializedName("ErrorCode")
  @Expose
  private Integer ErrorCode;
  public void setStatus(String Status){
   this.Status=Status;
  }
  public String getStatus(){
   return Status;
  }
  public void setResponseCode(Integer ResponseCode){
   this.ResponseCode=ResponseCode;
  }
  public Integer getResponseCode(){
   return ResponseCode;
  }
  public void setRemarks(String Remarks){
   this.Remarks=Remarks;
  }
  public String getRemarks(){
   return Remarks;
  }
  public void setData(DMT2_LoginData Data){
   this.Data=Data;
  }
  public DMT2_LoginData getData(){
   return Data;
  }
  public void setErrorCode(Integer ErrorCode){
   this.ErrorCode=ErrorCode;
  }
  public Integer getErrorCode(){
   return ErrorCode;
  }
}