package com.example.webplat.amoldesigning.moneytransfer_DMT;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.DMT2_LoginPojo;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.add_beneficary.AddBeneficaryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 02/06/2016.
 */
public class DMT2_MoneyRegister extends AppCompatActivity implements View.OnClickListener {
  Context mContext;
  PrefUtils prefs;
  int sec = 0;
  int timerCounter = 61;
  ServiceCallApiDMT2 service;
  private EditText mEditNumber;
  private RadioGroup mRadioButtonSex;
  private RadioButton mRadioButtonMale;
  private RadioButton mRadioButtonFeMale;
  private EditText mEditFirstName;
  private EditText mEditLastName;
  private EditText mEditMotherMiddleName;
  private EditText mEditNationality;
  private EditText mEditDob;
  private Button mBtnRegister;
  private EditText mEdAddress;
  private EditText mEdPinCode;
  private Spinner mSpinnerState;
  private ScrollView mTopLayout;
  private Button mVerifyOtpBtn;
  private EditText mOtpET;
  private TextView mOtpVerificationTV;
  private Button mResendOtpBtn;
PrefUtils prefUtils;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.money_register);
    bindViews();
  }

  private void bindViews() {
    mContext = DMT2_MoneyRegister.this;

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setVisibility(View.VISIBLE);
    toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
    toolbar.setTitle("Register");
    if (toolbar != null) {

      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          finish();

        }
      });
    }

    mEditNumber = (EditText) findViewById(R.id.editNumber);
    mRadioButtonSex = (RadioGroup) findViewById(R.id.radioButtonSex);
    mRadioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
    mRadioButtonFeMale = (RadioButton) findViewById(R.id.radioButtonFeMale);
    mEditFirstName = (EditText) findViewById(R.id.editFirstName);
    mEditLastName = (EditText) findViewById(R.id.editLastName);
    mEditMotherMiddleName = (EditText) findViewById(R.id.editMotherMiddleName);
    mEditNationality = (EditText) findViewById(R.id.editNationality);
    mEditDob = (EditText) findViewById(R.id.editDob);
    mEdAddress = (EditText) findViewById(R.id.edAddress);
    mEdPinCode = (EditText) findViewById(R.id.edPinCode);
    mSpinnerState = (Spinner) findViewById(R.id.spinnerState);
    mBtnRegister = (Button) findViewById(R.id.btnRegister);
    mBtnRegister.setOnClickListener(this);

    mRadioButtonMale.setVisibility(View.GONE);
    mRadioButtonFeMale.setVisibility(View.GONE);

    mEditMotherMiddleName.setVisibility(View.GONE);
    mEditNationality.setVisibility(View.GONE);
    mEditDob.setVisibility(View.GONE);
    mEdAddress.setVisibility(View.GONE);
    mEdPinCode.setVisibility(View.GONE);
    mEditNumber.setText(getIntent().getStringExtra("mobileNumber"));
    mSpinnerState.setVisibility(View.GONE);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btnRegister:

        if (TextUtils.isEmpty(mEditNumber.getText().toString().trim())) {
          mEditNumber.setError("Enter Number");
        } else {
          mEditNumber.setError(null);
          if (TextUtils.isEmpty(mEditFirstName.getText().toString().trim())) {
            mEditFirstName.setError("Enter First Name");
          } else {
            mEditFirstName.setError(null);
            if (TextUtils.isEmpty(mEditLastName.getText().toString().trim())) {
              mEditLastName.setError("Enter Last Name");
            } else {
              mEditLastName.setError(null);

              registerUser(mEditNumber.getText().toString().trim(), mEditFirstName.getText().toString().trim(), mEditLastName.getText().toString().trim());

            }
          }
        }
        break;
    }
  }


  private void registerUser(final String registerNumber, String firstName, String lastName) {
    ApplicationConstant.hideKeypad(DMT2_MoneyRegister.this);
    final ProgressDialog progressDialog = CustomProgressDialog.ctor(DMT2_MoneyRegister.this);
    progressDialog.show();
    service = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);
    Call<AddBeneficaryResponse> result = service.registerUserForMoneyTransfer(prefs.getFromPrefs(mContext, "userid", ""), prefs.getFromPrefs(mContext, "password", ""), firstName, lastName, registerNumber);
    result.enqueue(new Callback<AddBeneficaryResponse>() {
      @Override
      public void onResponse(Call<AddBeneficaryResponse> call, Response<AddBeneficaryResponse> response) {
        if (progressDialog != null && progressDialog.isShowing())
          progressDialog.dismiss();
        if (response != null) {
          AddBeneficaryResponse rechargeResponse = response.body();
          if (rechargeResponse != null) {
            if (rechargeResponse.getStatus().equals("Success")) {
              callService(registerNumber);
              Toast.makeText(mContext,"Sender Registered successfully...!!",Toast.LENGTH_LONG).show();
            } else {
              ApplicationConstant.DisplayMessageDialog(DMT2_MoneyRegister.this, rechargeResponse.getStatus(), rechargeResponse.getRemarks());
            }
          } else {
            Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
          }

        } else {
          Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
        }

      }

      @Override
      public void onFailure(Call<AddBeneficaryResponse> call, Throwable t) {
        if (progressDialog != null && progressDialog.isShowing())
          progressDialog.dismiss();
      }

    });

  }

  private void callService(final String mobilenumber) {
    final ProgressDialog progressDialog = CustomProgressDialog.ctor(DMT2_MoneyRegister.this);
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
                Intent intent = new Intent(mContext, DMT2_MoneyRegister.class);
                intent.putExtra("mobileNumber", mobilenumber);
                startActivity(intent);
              } else if (moneyLogin.getResponseCode() == 1 && moneyLogin.getStatus().equals("Success")) {
                prefUtils.saveToPrefs(mContext, "DMT2senderMobile", mobilenumber);
                prefUtils.saveToPrefs(mContext, "senderId", moneyLogin.getData().getRemitter().getId());
                prefUtils.saveToPrefs(mContext, "senderFirstName", moneyLogin.getData().getRemitter().getName());
                prefUtils.saveToPrefs(mContext, "senderLastName", moneyLogin.getData().getRemitter().getName());
                Intent intent = new Intent(mContext, DMT2DashBoardActivity.class);
                intent.putExtra("tabPosition", 2);
                startActivity(intent);
                finish();
              } else {
                ApplicationConstant.DisplayMessageForDialog(DMT2_MoneyRegister.this,"Error", moneyLogin.getRemarks());
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

      }
    });
  }
}
