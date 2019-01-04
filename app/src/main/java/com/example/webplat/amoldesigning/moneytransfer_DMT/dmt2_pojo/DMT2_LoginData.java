package com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Awesome Pojo Generator
 * */
public class DMT2_LoginData {
  @SerializedName("beneficiary")
  @Expose
  private List<DMT2_Beneficiary> beneficiary;
  @SerializedName("remitter")
  @Expose
  private Remitter remitter;
  public void setBeneficiary(List<DMT2_Beneficiary> beneficiary){
   this.beneficiary=beneficiary;
  }
  public List<DMT2_Beneficiary> getBeneficiary(){
   return beneficiary;
  }
  public void setRemitter(Remitter remitter){
   this.remitter=remitter;
  }
  public Remitter getRemitter(){
   return remitter;
  }
}