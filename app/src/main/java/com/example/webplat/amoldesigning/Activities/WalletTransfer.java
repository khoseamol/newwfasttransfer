package com.example.webplat.amoldesigning.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.recharge_response.RechargeResponse;
import com.example.webplat.amoldesigning.pojo.user_profile.UserDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletTransfer extends MainActivity implements View.OnClickListener {
    private EditText mEditTextUserName;
    private EditText mEditOwnerName;
    private EditText mEdAmount;
    private EditText mEdRemarks;
    private Button mBtnTransfer;
    Context mContext;
    PrefUtils prefs;
    private RelativeLayout mTopLayout;
    Intent intent;
    ServiceCallApi serviceCallApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.wallet_transfer, frameLayout);
        bindView();
    }

    private void bindView() {
        mContext = WalletTransfer.this;
//        setTitle(prefs.getFromPrefs(mContext, "AppName", ""));
        mTopLayout = (RelativeLayout) findViewById(R.id.topLayout);
        mEditTextUserName = (EditText) findViewById(R.id.editTextUserName);
        mEdAmount = (EditText) findViewById(R.id.edAmount);
        mEdRemarks = (EditText) findViewById(R.id.edRemarks);
        mBtnTransfer = (Button) findViewById(R.id.btnTransfer);
        mEditOwnerName = (EditText) findViewById(R.id.editOwnerName);
        intent = getIntent();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        toolbar.setTitle("WALLET TRANSFER");

        mEditTextUserName.setText(intent.getStringExtra("viewUsername"));
        mEditOwnerName.setText(intent.getStringExtra("viewOwnername"));

        if (mEditTextUserName != null && mEditOwnerName != null) {
            mEdAmount.requestFocus();
        }
        mBtnTransfer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTransfer:
                if (TextUtils.isEmpty(mEditTextUserName.getText().toString().trim())) {
                    mEditTextUserName.setError("Enter Username");
                } else {
                    mEditTextUserName.setError(null);

                    if (TextUtils.isEmpty(mEdAmount.getText().toString().trim())) {
                        mEdAmount.setError("Enter amount");
                    } else {
                        mEdAmount.setError(null);
                        if (TextUtils.isEmpty(mEdRemarks.getText().toString().trim())) {
                            mEdRemarks.setError("Enter Remarks");
                        } else {
                            mEdRemarks.setError(null);
                            transferBalance(
                                    mEditTextUserName.getText().toString().trim(),
                                    mEdAmount.getText().toString().trim(),
                                    mEdRemarks.getText().toString().trim());

                        }
                    }
                    break;
                }
        }
    }

    private void transferBalance(String name, String amount, String remarks) {
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(WalletTransfer.this);
        progressDialog.show();

        ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
        serviceCallApi = apiServiceGenerator.createService(ServiceCallApi.class);
        final Call<RechargeResponse> objbanklist = serviceCallApi.transferBalace(
                prefs.getFromPrefs(mContext, "userid", ""),
                prefs.getFromPrefs(mContext, "password", ""),
                name,
                amount,
                remarks);
        objbanklist.enqueue(new Callback<RechargeResponse>() {
            @Override
            public void onResponse(Call<RechargeResponse> call, Response<RechargeResponse> response) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                RechargeResponse userDetails = response.body();
                if (userDetails.getStatus().equalsIgnoreCase("Success")) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    ApplicationConstant.DisplayMessageDialog(WalletTransfer.this, userDetails.getStatus(), userDetails.getRemarks());

                    mEditTextUserName.setText("");
                    mEditOwnerName.setText("");
                    mEdAmount.setText("");
                    mEdRemarks.setText("");

                } else {
                    ApplicationConstant.DisplayMessageDialog(WalletTransfer.this, userDetails.getStatus(), userDetails.getRemarks());
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
