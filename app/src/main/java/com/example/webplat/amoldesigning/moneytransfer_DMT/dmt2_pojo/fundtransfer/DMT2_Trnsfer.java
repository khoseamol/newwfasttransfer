package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.fundtransfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Awesome Pojo Generator
 * */
public class DMT2_Trnsfer{
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
  private List<Dmt2transferData> Data;
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
  public void setData(List<Dmt2transferData> Data){
   this.Data=Data;
  }
  public List<Dmt2transferData> getData(){
   return Data;
  }
  public void setErrorCode(Integer ErrorCode){
   this.ErrorCode=ErrorCode;
  }
  public Integer getErrorCode(){
   return ErrorCode;
  }
}