package com.example.webplat.amoldesigning.helper;

import com.example.webplat.amoldesigning.pojo.GetOperatorByNum.GetOperatorByNum;
import com.example.webplat.amoldesigning.pojo.balance.GetBalance;
import com.example.webplat.amoldesigning.pojo.bank_details.BankDetails;
import com.example.webplat.amoldesigning.pojo.bank_list.BabkList;
import com.example.webplat.amoldesigning.pojo.bbpsbilling.BBPSBilling;
import com.example.webplat.amoldesigning.pojo.bbpscategory.BBPSCategory;
import com.example.webplat.amoldesigning.pojo.bbpshistory.BBPSBillingHistory;
import com.example.webplat.amoldesigning.pojo.bbpssubcategory.BBPSSubcategory;
import com.example.webplat.amoldesigning.pojo.billing_history.BillingHistorySummary;
import com.example.webplat.amoldesigning.pojo.billing_history.BillingSummary;
import com.example.webplat.amoldesigning.pojo.change_password.Changpassword;
import com.example.webplat.amoldesigning.pojo.checkuserbyuserne.UserDetailsFromCheckUser;
import com.example.webplat.amoldesigning.pojo.child_user_list.GetUserChild;
import com.example.webplat.amoldesigning.pojo.commission_history.Commision;
import com.example.webplat.amoldesigning.pojo.credit_summary_report.CreditReporttt;
import com.example.webplat.amoldesigning.pojo.earning_reports.MyEarning;
import com.example.webplat.amoldesigning.pojo.login.LoginCheck;
import com.example.webplat.amoldesigning.pojo.login.LoginOTPVerifuy;
import com.example.webplat.amoldesigning.pojo.operator.OperatorListServer;
import com.example.webplat.amoldesigning.pojo.payment_request_history.PaymentRequestHistory;
import com.example.webplat.amoldesigning.pojo.recghargeplan_pojo.RechargePlan;
import com.example.webplat.amoldesigning.pojo.recharge_history.RechargeHistory;
import com.example.webplat.amoldesigning.pojo.recharge_response.RechargeResponse;
import com.example.webplat.amoldesigning.pojo.scheme_add_user.Schemes;
import com.example.webplat.amoldesigning.pojo.user_profile.UserDetails;
import com.example.webplat.amoldesigning.pojo.userchildelist.UserChild;
import com.example.webplat.amoldesigning.pojo.utility_bill.CheckUtilityBIll;
import com.example.webplat.amoldesigning.pojo.wallet_history.WalletSummary;
import com.example.webplat.amoldesigning.pojo.wallet_report.CreditReport;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceCallApi {

    @FormUrlEncoded
    @POST("RechargeplanService/GetCircleOperatorByNum")
    Call<GetOperatorByNum> getOperatorByNumber(@Field("number") String number);


    @FormUrlEncoded
    @POST("UserService/GetUserAllChild")
    Call<UserChild> userchildList(@Field("UserName") String userName, @Field("Password") String password);


    @FormUrlEncoded
    @POST("RechargeplanService/GetRechargePlans")
    Call<RechargePlan>getRechargePlans(@Field("circle") String circle, @Field("operator") String operator);

    @FormUrlEncoded
    @POST("RGUSER/GetLoginOTPVerify")
    Call<LoginOTPVerifuy> verifyLoginOTP(@Field("UserName") String userName,
                                         @Field("Password") String passWord,
                                         @Field("IMEINO") String imeiNo,
                                         @Field("OTP") String otp);


    @FormUrlEncoded
    @POST("RGUSER/GetLoginDetails")
    Call<LoginCheck> getLoginInfo(@Field("UserName") String userName, @Field("Password") String password, @Field("IMEINO") String picDeviceID);

    @POST("operatorServices/GetOperatorName")
    Call<OperatorListServer> getoperatorlist();

    @FormUrlEncoded
    @POST("Recharge/GetAllRecharge")
    Call<RechargeResponse> doRecharge(@Field("UserName") String userId,
                                      @Field("Password") String password,
                                      @Field("Amount") String amount,
                                      @Field("MobileNumber") String number,
                                      @Field("OPTID") String opcode,
                                      @Field("Circle") String circle);
    @FormUrlEncoded
    @POST("RGUSER/GetUtilityBillPayValidate")
    Call<CheckUtilityBIll> validateUtilityBill(@Field("UserName") String userId,
                                               @Field("Password") String password,
                                               @Field("OperatorCode") String opcode,
                                               @Field("Number") String subScriberId,
                                               @Field("BillUnit") String billingUnit,
                                               @Field("CycleNo") String cycleNumber);
  @FormUrlEncoded
    @POST("RGUSER/GetUtilityBillPayValidate")
    Call<com.example.webplat.amoldesigning.pojo.utility_bbps.utility_bill.CheckUtilityBIll> validateBill(@Field("UserName") String userId,
                                                                                                                @Field("Password") String password,
                                                                                                                @Field("OperatorCode") String opcode,
                                                                                                                @Field("Number") String subScriberId,
                                                                                                                @Field("BillUnit") String billingUnit,
                                                                                                                @Field("CycleNo") String cycleNumber);

    @FormUrlEncoded
    @POST("RGUSER/GetUserBalance")
    Call<GetBalance> getUserBalance(@Field("UserName") String userName, @Field("Password") String password);

    @FormUrlEncoded
    @POST("RGUSER/GetLastTransaction")
    Call<RechargeHistory> getRechargeHistory(@Field("UserName") String hpUserId, @Field("Password") String password,
                                             @Field("FromDate") String fromDate, @Field("ToDate") String toDate);


    @FormUrlEncoded
    @POST("UserService/GetCommissionEarnReport")
    Call<MyEarning> getMyEarnings(@Field("UserName") String hpUserId, @Field("Password") String password, @Field("FromDate") String fromDate, @Field("ToDate") String toDate);


    @FormUrlEncoded
    @POST("UserService/GetCommissionReport")
    Call<Commision> getCommissionHistory(@Field("UserName") String hpUserId, @Field("Password") String password, @Field("FromDate") String fromDate, @Field("ToDate") String toDate);

    @FormUrlEncoded
    @POST("RGUSER/GetTransactionByMobileNo")
    Call<RechargeHistory> getOrderHistoryById(@Field("UserName") String userName, @Field("Password") String password, @Field("MobileNo") String transId);

    @FormUrlEncoded
    @POST("UserService/GetBanksList")
    Call<BankDetails> getbankList(@Field("UserName") String userName,
                                  @Field("Password") String password);

    @FormUrlEncoded
    @POST("UserService/GetPaymentRequest")
    Call<RechargeResponse> getPaymentRequest(@Field("UserName") String userName, @Field("Password") String password,
                                             @Field("DepositbankId") String strBankCode,
                                             @Field("Amount") String amount, @Field("PaymentMode") String paymentTypeCode,
                                             @Field("RefNo") String refId, @Field("PaymentDate") String paymentDate,
                                             @Field("ChequeNo") String chequeNumber, @Field("ChequeDate") String chequeDate,
                                             @Field("Remarks") String remarks);

    @FormUrlEncoded
    @POST("RGUSER/GetChangePassword")
    Call<Changpassword> changePassword(@Field("UserName") String hpUserId,
                                       @Field("Password") String oldPw,
                                       @Field("NewPassword") String newPw);


    @FormUrlEncoded
    @POST("RechargeplanService/GetRechargePlans")
    Call<RechargePlan> getplan(@Field("circle") String circle, @Field("operator") String operator);


    @FormUrlEncoded
    @POST("UserService/AddNewUser")
    Call<RechargeResponse> addUser(@Field("UserName") String userName,
                                   @Field("Password") String password,
                                   @Field("OwnerName") String parentName,
                                   @Field("CurrentUserName") String name,
                                   @Field("CurrentUserId") String currentUserId,
                                   @Field("MobileNo") String mobileNumber,
                                   @Field("PanCard") String pan,
                                   @Field("FirmName") String farmName,
                                   @Field("Area") String area,
                                   @Field("Address") String address,
                                   @Field("SchemeId") String schemeId,
                                   @Field("SchemeAmount") String schemeAmount,
                                   @Field("UserType") String schemeType);

    @FormUrlEncoded
    @POST("UserService/GetUserSchemes")
    Call<Schemes> getUserScheme(@Field("UserName") String userName, @Field("Password") String password);

    @FormUrlEncoded
    @POST("UserService/GetUserAllChild")
    Call<GetUserChild> getUserChildList(@Field("UserName") String uswerName, @Field("Password") String password);

    @FormUrlEncoded
    @POST("UserService/GetWallettowalletcr")
    Call<RechargeResponse> transferBalace(@Field("UserName") String userName,
                                          @Field("Password") String password,
                                          @Field("CrUserName") String name,
                                          @Field("Amount") String amount,
                                          @Field("Remarks") String remarks);

    @FormUrlEncoded
    @POST("RGUSER/GetUserDetailsByUserId")
    Call<UserDetailsFromCheckUser> userCheckbyUserName(@Field("UserName") String userName);


    @FormUrlEncoded
    @POST("UserService/GetPaymentReport")
    Call<CreditReport>  getWalletReport(@Field("UserName") String userName, @Field("Password") String password, @Field("FromDate") String fromDate, @Field("ToDate") String toDate);


    @FormUrlEncoded
    @POST("RGUSER/GetLastTransaction")
    Call<RechargeHistory> getOrderHistory(@Field("UserName") String hpUserId, @Field("Password") String password,
                                          @Field("FromDate") String fromDate, @Field("ToDate") String toDate);


    @FormUrlEncoded
    @POST("UserService/GetBillingReport")
    Call<BillingHistorySummary> getLedgerHistory(@Field("UserName") String hpUserId,
                                                 @Field("Password") String password,
                                                 @Field("FromDate") String fromDate,
                                                 @Field("ToDate") String toDate);



    @FormUrlEncoded
    @POST("UserService/GetPaymentsHistory")
    Call<WalletSummary> getWalletHistory(@Field("UserName") String hpUserId,
                                         @Field("Password") String password,
                                         @Field("FromDate") String fromDate,
                                         @Field("ToDate") String toDate);

    @FormUrlEncoded
    @POST("UserService/GetPayRequestHistory")
    Call<PaymentRequestHistory> getpaymentRequestHistory(@Field("UserName") String hpUserId,
                                         @Field("Password") String password,
                                         @Field("From") String fromDate,
                                         @Field("To") String toDate);

    @FormUrlEncoded
    @POST("RGUSER/GetForgotPassword")
    Call<RechargeResponse> forgetPassword(@Field("UserName") String username);


    @FormUrlEncoded
    @POST("UserService/GetUserProfileDetails")
    Call<UserDetails> getUserInfo(@Field("UserName") String userName,
                                       @Field("Password") String password);

    @FormUrlEncoded
    @POST("ReportsServices/GetPaymentCreditReport")
    Call<CreditReporttt> getCreditHistory(@Field("UserName") String hpUserId,
                                          @Field("Password") String password,
                                          @Field("FromDate") String fromDate,
                                          @Field("ToDate") String toDate);


    @POST("OperatorServices/Categories")
    Call<BBPSCategory> getBBPSCatagaryURL();
//
//    @POST("OperatorServices/bbpsOperatorName")
//    Call<BBPSOperator> getBBPSOperator(@Field("CategoryId") Integer id);

    @FormUrlEncoded
    @POST("Recharge/bbpsbillpay")
    Call<com.example.webplat.amoldesigning.pojo.utility_bbps.utility_bill.CheckUtilityBIll>payBBPSBillURL(@Field("UserName") String userName,
                                                                                                          @Field("Password") String password,
                                                                                                          @Field("Number") String subscriberID,
                                                                                                          @Field("Amount") String amount,
                                                                                                          @Field("Mode") String mMode,
                                                                                                          @Field("Param1") String param1,
                                                                                                          @Field("Param2") String params2,
                                                                                                          @Field("Param3") String param3,
                                                                                                          @Field("CustomerMobileNo") String customerMobile,
                                                                                                          @Field("OperatorCode") String mOurCode,
                                                                                                          @Field("CircleCode") String circleCode,
                                                                                                          @Field("Duedate")String duedate,
                                                                                                          @Field("Billdate")String billdate,
                                                                                                          @Field("ConsumerName")String name,
                                                                                                          @Field("Billernumber")String billernumber);


    @POST("OperatorServices/SubCategories")
    Call<BBPSSubcategory> getBBPSServiceURL(@Query("category") Integer CategoryId);


    @POST("OperatorServices/GetBillerparams")
    Call<BBPSBilling> getBBPSUnit(@Query("billerid") Integer CategoryId);

    @FormUrlEncoded
    @POST("ReportsServices/GetBBPSReport")
    Call<BBPSBillingHistory> bbpshistory(@Field("UserName") String userName, @Field("Password") String password, @Field("FromDate") String fromDate, @Field("ToDate") String toDate);

}