package com.example.webplat.amoldesigning.moneytransfer_DMT;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.benadd.YesAddBenResponse;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.master_ifsc.MasterIFSCData;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.master_ifsc.MasterIfsc;
import com.example.webplat.amoldesigning.pojo.recharge_response.RechargeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by webplat2 on 21/6/18.
 */

public class DMT2_AddBeneficiary extends AppCompatActivity{

    EditText mEdBeneficiaryName;
    CountDownTimer timer;
    EditText mEdBeneficiaryNickName;

    EditText mEdBenMobile;

    EditText mEdBankAccountNumber;

    EditText mEdConfirmAccountNumber;

    EditText mEdIfscCode;

    Button mBtnAddBeneficary;

    AutoCompleteTextView mAutoCompleteBankBranch;


    AutoCompleteTextView mSpinnerBank;

    LinearLayout bankLayout;


    Spinner spinnerAccountTYpe;


    RadioGroup mRadioGroupIFSCSelector;

    AutoCompleteTextView mAutoCompleteMasterIFSC;

    RadioButton mRadioButtonIFSC;

    RadioButton mRadioSearchIFSC;

    RadioButton mRadioButtonMasterIFSC;

    Context mContext;
    Toolbar toolbar;
    PrefUtils prefs;
    int sec = 0;
    int timerCounter = 61;
    private List<MasterIFSCData> masterBankLIst = new ArrayList<MasterIFSCData>();
    String[] transferType = {"IMPS", "NEFT"};
    ServiceCallApiDMT2 yesbankservice;

    ServiceCallApi serviceCallApi;
    private Button mVerifyOtpBtn;
    private EditText mOtpET;
    private TextView mOtpVerificationTV;
    private Button mResendOtpBtn;
    private boolean isFirstTime = false;
    private boolean isViewShown = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beneficiary_dmt);
        BindView();
    }

    private void BindView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
        toolbar.setTitle("Add Beneficiary");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        mEdBeneficiaryName = (EditText)findViewById(R.id.edBeneficiaryName) ;

        mEdBeneficiaryNickName = (EditText)findViewById(R.id.edBeneficiaryNickName);

        mEdBenMobile= (EditText)findViewById(R.id.edBenMobile);

        mEdBankAccountNumber = (EditText)findViewById(R.id.edBankAccountNumber);
        mEdConfirmAccountNumber = (EditText) findViewById(R.id.edConfirmAccountNumber);

        mEdIfscCode = (EditText)findViewById(R.id.edIfscCode);
        mBtnAddBeneficary =(Button)findViewById(R.id.btnAddBeneficary);

         mAutoCompleteBankBranch = (AutoCompleteTextView)findViewById(R.id.autoCompleteBankBranch);


         mSpinnerBank = (AutoCompleteTextView)findViewById(R.id.autoCompleteBankName);

         bankLayout = (LinearLayout) findViewById(R.id.bankLayout);

        spinnerAccountTYpe = (Spinner) findViewById(R.id.spinnerAccountTYpe);


         mRadioGroupIFSCSelector = (RadioGroup)findViewById(R.id.radioGroupIFSCSelector);

         mAutoCompleteMasterIFSC = (AutoCompleteTextView) findViewById(R.id.autoCompleteMasterIFSC);

        mRadioButtonIFSC = (RadioButton)findViewById(R.id.radioButtonIFSC);

        mRadioSearchIFSC = (RadioButton)findViewById(R.id.radioSearchIFSC);
       mRadioButtonMasterIFSC = (RadioButton)findViewById(R.id.radioButtonMasterIFSC);

        mContext = DMT2_AddBeneficiary.this;
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, transferType);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAccountTYpe.setAdapter(adapter1);
        spinnerAccountTYpe.setVisibility(View.GONE);
        mEdBeneficiaryNickName.setVisibility(View.VISIBLE);
        mEdBeneficiaryNickName.setHint("Beneficiary Last Name");
        mEdBenMobile.setVisibility(View.GONE);
        mAutoCompleteMasterIFSC = (AutoCompleteTextView)findViewById(R.id.autoCompleteMasterIFSC);
        GetMasterIFSC();
        mRadioButtonIFSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRadioButtonIFSC.isChecked()) {

                    mEdIfscCode.setFocusableInTouchMode(true);
                    mEdIfscCode.setText("");
                    bankLayout.setVisibility(View.GONE);
                }
            }
        });


       mRadioSearchIFSC.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(mRadioSearchIFSC.isChecked()){
                   mEdIfscCode.setText("");
                   bankLayout.setVisibility(View.VISIBLE);
                   mAutoCompleteMasterIFSC.setVisibility(View.GONE);
               }
           }
       });
        mRadioButtonMasterIFSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEdIfscCode.setFocusableInTouchMode(false);
                if(mRadioButtonMasterIFSC.isChecked()){
                    mEdIfscCode.setText("");
                    bankLayout.setVisibility(View.GONE);
                    mAutoCompleteMasterIFSC.setVisibility(View.VISIBLE);
                }
            }
        });

;


        mBtnAddBeneficary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEdBeneficiaryName.getText().toString().trim())) {
                    mEdBeneficiaryName.setError("Enter Beneficary Name");
                } else {
                    mEdBeneficiaryName.setError(null);

                    if (TextUtils.isEmpty(mEdBeneficiaryNickName.getText().toString().trim())) {
                        mEdBeneficiaryNickName.setError("Enter last name");
                    } else {
                        mEdBeneficiaryNickName.setError(null);
                        if (mEdBeneficiaryName.getText().toString().trim().length() > 2) {
                            mEdBeneficiaryName.setError(null);

                            if (TextUtils.isEmpty(mEdBankAccountNumber.getText().toString().trim())) {
                                mEdBankAccountNumber.setError("Enter Acount Number");
                            } else {
                                mEdBankAccountNumber.setError(null);
                                if (TextUtils.isEmpty(mEdConfirmAccountNumber.getText().toString().trim())) {
                                    mEdConfirmAccountNumber.setError("Enter Acount Number");
                                } else {
                                    mEdConfirmAccountNumber.setError(null);

                                    if (mEdBankAccountNumber.getText().toString().trim().equals(mEdConfirmAccountNumber.getText().toString().trim())) {
                                        mEdBankAccountNumber.setError(null);

                                        if (TextUtils.isEmpty(mEdIfscCode.getText().toString().trim())) {
                                            mEdIfscCode.setError("Enter IFSC Code");
                                        } else {
                                            mEdIfscCode.setError(null);

                                            addBeneficary(mEdBeneficiaryName.getText().toString().trim(), mEdBeneficiaryNickName.getText().toString().trim(),
                                                    mEdBankAccountNumber.getText().toString().trim(), mEdIfscCode.getText().toString().trim(),
                                                    spinnerAccountTYpe.getSelectedItem().toString().trim());


                                        }

                                    } else {
                                        mEdConfirmAccountNumber.setError("Acount Number not match");
                                    }
                                }
                            }
                        } else {
                            mEdBeneficiaryName.requestFocus();
                            mEdBeneficiaryName.setError("Name Should have  3 character");
                        }
                    }


                }
            }
        });
    }
    private void addBeneficary(final String benName, final String benlastname, final String acountNumber, final String ifscCode, String accountType) {
        ApplicationConstant.hideKeypad(DMT2_AddBeneficiary.this);
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(DMT2_AddBeneficiary.this);
        progressDialog.show();

        yesbankservice = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);
        Call<YesAddBenResponse> result = yesbankservice.dmt2_addBeneficary(prefs.getFromPrefs(mContext, "userid", ""), prefs.getFromPrefs(mContext, "password", ""), benName, benlastname, prefs.getFromPrefs(mContext, "DMT2senderMobile", ""), prefs.getFromPrefs(mContext, "senderId", ""), acountNumber, ifscCode);
        result.enqueue(new Callback<YesAddBenResponse>() {
            @Override
            public void onResponse(Call<YesAddBenResponse> call, Response<YesAddBenResponse> response) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                if (response != null) {
                    YesAddBenResponse rechargeResponse = response.body();
                    if (rechargeResponse != null) {
                        if (rechargeResponse.getResponseCode() == 1) {
                            if (rechargeResponse.getData() != null) {
                                DisplayOTPDialog(rechargeResponse.getData().getBeneid(), benName, benlastname, acountNumber, ifscCode);
                            } else {
                                ApplicationConstant.DisplayMessageDialog(DMT2_AddBeneficiary.this, Constant.Response.ERROR, rechargeResponse.getRemarks());
                            }
                        } else {
                            ApplicationConstant.DisplayMessageDialog(DMT2_AddBeneficiary.this, Constant.Response.ERROR, rechargeResponse.getRemarks());
                        }
                    }


                } else {
                    ApplicationConstant.DisplayMessageDialog(DMT2_AddBeneficiary.this, Constant.Response.ERROR, getString(R.string.error_message));
                }

            }

            @Override
            public void onFailure(Call<YesAddBenResponse> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }

        });
//
    }
    private void DisplayOTPDialog(final String beneid, final String benName, final String benlastname, final String acountNumber, final String ifsc) {
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.otp_confirm_dialog, null);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DMT2_AddBeneficiary.this);
        builder.setCancelable(false).setView(dialoglayout);
        mVerifyOtpBtn = (Button) dialoglayout.findViewById(R.id.verifyOtpBtn);
        mOtpET = (EditText) dialoglayout.findViewById(R.id.otpET);
        mResendOtpBtn = (Button) dialoglayout.findViewById(R.id.resendOtpBtn);
        ImageView imageCancel = (ImageView) dialoglayout.findViewById(R.id.imageCancel);
        mOtpVerificationTV = (TextView) dialoglayout.findViewById(R.id.otpVerificationTV);


        final android.app.AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //change input type to text
        mOtpET.setInputType(InputType.TYPE_CLASS_NUMBER);
        imageCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });

        //change input type to text
        mOtpET.setInputType(InputType.TYPE_CLASS_NUMBER);


        final Timer t = new Timer();
        mResendOtpBtn.setClickable(false);
        mResendOtpBtn.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.darker_gray));
        mResendOtpBtn.setEnabled(false);
        timer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mOtpVerificationTV.setText("0:"+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                mOtpVerificationTV.setText("1:00");
                mResendOtpBtn.setClickable(true);
                mResendOtpBtn.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                mResendOtpBtn.setEnabled(true);
            }
        };
        timer.start();

        mResendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
                ApplicationConstant.hideKeypad(DMT2_AddBeneficiary.this);
                final ProgressDialog progressDialog = CustomProgressDialog.ctor(DMT2_AddBeneficiary.this);
                progressDialog.show();
                yesbankservice = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);

                Call<YesAddBenResponse> result = yesbankservice.dmt2_recipentadd_resendOTP(prefs.getFromPrefs(mContext, "userid", ""), prefs.getFromPrefs(mContext, "password", ""), prefs.getFromPrefs(mContext, "DMT2senderMobile", ""), prefs.getFromPrefs(mContext, "senderId", ""), beneid, benName, benlastname, acountNumber, ifsc);
                result.enqueue(new Callback<YesAddBenResponse>() {
                    @Override
                    public void onResponse(Call<YesAddBenResponse> call, Response<YesAddBenResponse> response) {
                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();
                        if (response != null) {
                            YesAddBenResponse rechargeResponse = response.body();
                            if (rechargeResponse.getStatus().equals("Success")) {
                                Toast.makeText(mContext, "otp resend successful.", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(mContext, rechargeResponse.getRemarks(), Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<YesAddBenResponse> call, Throwable t) {
                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();
                    }

                });


            }
        });
        mVerifyOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationConstant.hideKeypad(DMT2_AddBeneficiary.this);
                if (TextUtils.isEmpty(mOtpET.getText().toString().trim())) {
                    mOtpET.setError("Enter OTP");
                } else {
                    ApplicationConstant.hideKeypad(DMT2_AddBeneficiary.this);
                    final ProgressDialog progressDialog = CustomProgressDialog.ctor(DMT2_AddBeneficiary.this);
                    progressDialog.show();
                    yesbankservice = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);

                    Call<RechargeResponse> result = yesbankservice.dmt2_verifyOTP(prefs.getFromPrefs(mContext, "userid", ""), prefs.getFromPrefs(mContext, "password", ""), prefs.getFromPrefs(mContext, "DMT2senderMobile", ""), prefs.getFromPrefs(mContext, "senderId", ""), mOtpET.getText().toString().trim(), beneid);
                    result.enqueue(new Callback<RechargeResponse>() {
                        @Override
                        public void onResponse(Call<RechargeResponse> call, Response<RechargeResponse> response) {
                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();
                            if (response != null) {
                                RechargeResponse rechargeResponse = response.body();
                                if (rechargeResponse.getStatus().equals("Success")) {
                                    Toast.makeText(mContext, "Beneficiary Registered successfully", Toast.LENGTH_SHORT).show();
                                    alert.dismiss();
                                    mEdBeneficiaryName.setText("");
                                    mEdBeneficiaryNickName.setText("");
                                    mEdBankAccountNumber.setText("");
                                    mEdConfirmAccountNumber.setText("");
                                    mEdIfscCode.setText("");
                                    mSpinnerBank.setText("");
                                    mRadioButtonIFSC.isChecked();
                                    mAutoCompleteMasterIFSC.setText("");

                                    Intent intent = new Intent(DMT2_AddBeneficiary.this, DMT2DashBoardActivity.class);
                                    startActivity(intent);
                                    finish();


                                } else {
                                    ApplicationConstant.DisplayMessageDialog(DMT2_AddBeneficiary.this, Constant.Response.ERROR, "Please enter valid OTP");
                                }

                            } else {
                                Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<RechargeResponse> call, Throwable t) {
                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    });
                }

            }
        });

        alert.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    alert.dismiss();
                    return true;
                }
                return false;
            }
        });

        alert.show();

    }

    private void GetMasterIFSC() {getMasterIFscFromService();
    }
    private void getMasterIFscFromService() {
        ApplicationConstant.hideKeypad(DMT2_AddBeneficiary.this);
        final ProgressDialog pDialog = CustomProgressDialog.ctor(DMT2_AddBeneficiary.this);
        pDialog.show();
        ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
        yesbankservice = apiServiceGenerator.createService(ServiceCallApiDMT2.class);
        final Call<MasterIfsc> objbanklist = yesbankservice.GetMasterIfscEzPay(
                prefs.getFromPrefs(mContext, "userid", ""),
                prefs.getFromPrefs(mContext, "password", ""));
        objbanklist.enqueue(new Callback<MasterIfsc>() {
            @Override
            public void onResponse(Call<MasterIfsc> call, Response<MasterIfsc> response) {
                if (pDialog.isShowing())
                    pDialog.dismiss();
                MasterIfsc masterIfsc = response.body();
                if(masterIfsc.getData().size()>0){
                    for(int i=0;i<masterIfsc.getData().size();i++){
                        MasterIFSCData data = masterIfsc.getData().get(i);
                        masterBankLIst.add(data);
                    }



                    ArrayAdapter<MasterIFSCData> masterIFSCAdapter = new ArrayAdapter<MasterIFSCData>(
                            mContext, R.layout.simple_spiner_item, masterBankLIst);
                    masterIFSCAdapter.setDropDownViewResource(R.layout.simple_spiner_item);
//                            mAutoCompleteMasterIFSC.setThreshold(2);
                    mAutoCompleteMasterIFSC.setAdapter(masterIFSCAdapter);
                    mAutoCompleteMasterIFSC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            if (masterBankLIst.size() > 0) {
                                MasterIFSCData bank = (MasterIFSCData) adapterView.getItemAtPosition(position);
                                mEdIfscCode.setText(bank.getIfscCode());
                            }
                        }
                    });
                }
                 else {
                    ApplicationConstant.DisplayMessageDialog(DMT2_AddBeneficiary.this, "Failed", "No Master IFSC Found");
                }
            }

            @Override
            public void onFailure(Call<MasterIfsc> call, Throwable t) {
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }
        });
    }


}
