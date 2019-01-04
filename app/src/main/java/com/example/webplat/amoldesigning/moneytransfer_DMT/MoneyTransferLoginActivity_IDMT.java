package com.example.webplat.amoldesigning.moneytransfer_DMT;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.webplat.amoldesigning.Activities.MainActivity;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.DMT2_LoginPojo;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.login.Limit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by webplat2 on 20/6/18.
 */

@SuppressLint("NewApi")
public class MoneyTransferLoginActivity_IDMT extends MainActivity implements View.OnClickListener {
    public List<Limit> LimitdataStaus = new ArrayList<Limit>();
    Context mContext;
    PrefUtils prefUtils;
    String avaBal = "";
    String namefetch="";
    int sec = 0;

    int timerCounter = 61;
    private RelativeLayout mTopLayout;
    private Button mVerifyOtpBtn;
    private EditText mOtpET;
    private TextView mOtpVerificationTV;
    private Button mResendOtpBtn;
    private EditText mOtpETDialog;
    private List<Limit> Limitdata = new ArrayList<Limit>();
    ServiceCallApi serviceCallApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.money_login, frameLayout);
        bindViews();
    }



    private void bindViews() {
        mContext = MoneyTransferLoginActivity_IDMT.this;
        mVerifyOtpBtn = (Button) findViewById(R.id.verifyOtpBtn);
        mOtpET = (EditText) findViewById(R.id.otpET);

        mResendOtpBtn = (Button) findViewById(R.id.resendOtpBtn);
        mVerifyOtpBtn.setText("Search ");
        mResendOtpBtn.setVisibility(View.GONE);
        mVerifyOtpBtn.setOnClickListener(this);
        mOtpET.setHint("Mobile Number");
        mVerifyOtpBtn.setVisibility(View.VISIBLE);
        mOtpVerificationTV = (TextView) findViewById(R.id.otpVerificationTV) ;
        mOtpVerificationTV.setText("Money Transfer DMT2");
        mOtpET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()==10){
                   // callService(mOtpET.getText().toString().trim());
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.verifyOtpBtn){
            if(mOtpET.getText().toString().trim().length()==10){
                callService(mOtpET.getText().toString().trim());
            }
        }
    }



    private void callService(final String mobilenumber) {
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(MoneyTransferLoginActivity_IDMT.this);
        progressDialog.show();
        ServiceCallApiDMT2 serviceCallApiDMT2 = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);
        Call<DMT2_LoginPojo> resulte = serviceCallApiDMT2.dmtLogin(prefUtils.getFromPrefs(mContext, "userid", ""), prefUtils.getFromPrefs(mContext, "password", ""), mobilenumber);
        resulte.enqueue(new Callback<DMT2_LoginPojo>() {
            @Override
            public void onResponse(Call<DMT2_LoginPojo> call, Response<DMT2_LoginPojo> response) {
                DMT2_LoginPojo moneyLogin = response.body();
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (moneyLogin != null) {
                    if (moneyLogin != null) {
                        System.out.println("Sender Validate : " + moneyLogin.toString());
                        if (moneyLogin.getRemarks().equals("Customer not verified")) {

                        } else {
                            if (moneyLogin.getResponseCode() == 2) {
                                mOtpET.setText("");

                                Intent intent = new Intent(mContext, DMT2_MoneyRegister.class);
                                intent.putExtra("mobileNumber", mobilenumber);
                                startActivity(intent);
                            } else if (moneyLogin.getResponseCode() == 1 && moneyLogin.getStatus().equals("Success")) {
                                mOtpET.setText("");
                                try {  prefUtils.saveToPrefs(mContext, "DMT2senderMobile", mobilenumber);
                                    prefUtils.saveToPrefs(mContext, "senderFirstName", moneyLogin.getData().getRemitter().getName());
                                    prefUtils.saveToPrefs(mContext, "senderId", moneyLogin.getData().getRemitter().getId());
                                    prefUtils.saveToPrefs(mContext, "senderLastName", moneyLogin.getData().getRemitter().getName());
                                    Intent intent = new Intent(mContext, DMT2DashBoardActivity.class);
                                    intent.putExtra("tabPosition", 0);
                                    startActivity(intent);

                                }catch (Exception e){
                                    Toast.makeText(mContext,"Error occure while retriving data",Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                ApplicationConstant.DisplayMessageForDialog(MoneyTransferLoginActivity_IDMT.this, Constant.Response.ERROR,moneyLogin.getRemarks());
                            }
                        }


                    } else {
                        Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<DMT2_LoginPojo> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }
        });
    }
}
