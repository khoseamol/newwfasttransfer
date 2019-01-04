package com.example.webplat.amoldesigning.moneytransfer_DMT;





import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.DMT2_LoginPojo;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.add_beneficary.AddBeneficaryResponse;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.benadd.YesAddBenResponse;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.fundtransfer.DMT2_Trnsfer;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.master_ifsc.MasterIfsc;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.tnxreport.MOneyReport_Dmt2_pojo;
import com.example.webplat.amoldesigning.pojo.recharge_response.RechargeResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceCallApiDMT2 {
  @FormUrlEncoded
  @POST("Moneyinstpservice/resentrecipientotp")
  Call<YesAddBenResponse> dmt2_recipentadd_resendOTP(@Field("UserName") String username, @Field("Password") String password, @Field("MobileNo") String MobileNo, @Field("Senderid") String Senderid, @Field("Beneid") String Beneid, @Field("FName") String FName, @Field("LName") String LName, @Field("BeneAccountno") String BeneAccountno, @Field("IFSC") String IFSC);


  @FormUrlEncoded
  @POST("MoneyEzService/GetMasteBankNameList")
  Call<MasterIfsc> GetMasterIfscEzPay(@Field("UserName") String userName,
                                      @Field("Password") String password);

  @FormUrlEncoded
  @POST("Moneyinstpservice/recipientremove")
  Call<YesAddBenResponse> dmt2_recipentdelete_resendOTP(@Field("UserName") String username, @Field("Password") String password, @Field("MobileNo") String MobileNo, @Field("Beneid") String Beneid, @Field("Senderid") String Senderid);
  @FormUrlEncoded
  @POST("Moneyinstpservice/sendervalidate")
  Call<DMT2_LoginPojo> dmtLogin(@Field("UserName") String username, @Field("Password") String password, @Field("MobileNo") String mobilenumber);

  @FormUrlEncoded
  @POST("Moneyinstpservice/recipientregistration")
  Call<YesAddBenResponse> dmt2_addBeneficary(@Field("UserName") String username, @Field("Password") String password, @Field("FName") String FName, @Field("LName") String LName, @Field("MobileNo") String MobileNo, @Field("Senderid") String Senderid, @Field("BeneAccountno") String BeneAccountno, @Field("IFSC") String IFSC);


  @FormUrlEncoded
  @POST("Moneyinstpservice/senderregistration")
  Call<AddBeneficaryResponse> registerUserForMoneyTransfer(@Field("UserName") String username, @Field("Password") String password, @Field("FName") String fname, @Field("LName") String lname, @Field("MobileNo") String MobileNo);


  @FormUrlEncoded
  @POST("Moneyinstpservice/recipientconfirmation")
  Call<RechargeResponse> dmt2_verifyOTP(@Field("UserName") String username, @Field("Password") String password, @Field("MobileNo") String MobileNo, @Field("Senderid") String Senderid, @Field("OTP") String OTP, @Field("Beneid") String Beneid);





  @FormUrlEncoded
  @POST("Moneyinstpservice/moneytransfer")
  Call<DMT2_Trnsfer> dmt2_transferMoney(@Field("UserName") String username, @Field("Password") String password, @Field("MobileNo") String mobileno, @Field("Sendername") String Sendername, @Field("BeneBankname") String BeneBankname, @Field("BeneId") String BeneId, @Field("BeneName") String BeneName, @Field("BeneAccountno") String BeneAccountno, @Field("IFSC") String IFSC, @Field("Paymenttype") String Paymenttype, @Field("Amount") String Amount);



  @FormUrlEncoded
  @POST("Moneyinstpservice/recipientverification")
  Call<AddBeneficaryResponse> dmt2_benvalidateBeneficary(@Field("UserName") String username, @Field("Password") String Password, @Field("BeneBankname") String BeneBankname, @Field("BeneId") String BeneId, @Field("MobileNo") String MobileNo, @Field("BeneName") String usBeneNameername, @Field("BeneAccountno") String BeneAccountno, @Field("IFSC") String IFSC, @Field("Sendername") String Sendername);

  @FormUrlEncoded
  @POST("Moneyinstpservice/transactionreport")
  Call<MOneyReport_Dmt2_pojo> dmt2_getyesbankMoneyHistory(@Field("UserName") String username, @Field("Password") String password, @Field("MobileNo") String MobileNo, @Field("Fromdate") String fromDate, @Field("Todate") String toDate);
  @FormUrlEncoded
  @POST("Moneyinstpservice/recipientremove")
  Call<YesAddBenResponse> dmt2_deleteBeneficary(@Field("UserName") String username, @Field("Password") String password, @Field("MobileNo") String MobileNo, @Field("Beneid") String Beneid, @Field("Senderid") String Senderid);

  @FormUrlEncoded
  @POST("Moneyinstpservice/recipientremoveconfirm")
  Call<YesAddBenResponse> dmt2_benedelet_verifyOTP(@Field("UserName") String username, @Field("Password") String password, @Field("MobileNo") String MobileNo, @Field("OTP") String OTP, @Field("Senderid") String Senderid, @Field("Beneid") String Beneid);
}
