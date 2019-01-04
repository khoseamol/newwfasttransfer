package com.example.webplat.amoldesigning.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.change_password.Changpassword;
import com.example.webplat.amoldesigning.pojo.recharge_response.RechargeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressLint("NewApi")
public class ChangePasswordActivity extends MainActivity implements OnClickListener {
    Context mContext;
    PrefUtils prefs;
    private EditText mOldPassword;
    private EditText mNewPassword;
    private EditText mConfirmPassword;
    private android.widget.Button mBtnChangePassword;
    private android.support.design.widget.CoordinatorLayout mCoordinatorLayout;
    private LinearLayout mTopLayout;
    ServiceCallApi serviceCallApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.change_password, frameLayout);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        bindViews();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

    }

    public void bindViews() {
        mContext = ChangePasswordActivity.this;
        mOldPassword = (EditText) findViewById(R.id.oldPassword);
        mNewPassword = (EditText) findViewById(R.id.newPassword);
        mConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        mBtnChangePassword = (android.widget.Button) findViewById(R.id.btnChangePassword);
        mCoordinatorLayout = (android.support.design.widget.CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mTopLayout = (LinearLayout) findViewById(R.id.topLayout);
        mBtnChangePassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChangePassword:
                ApplicationConstant.hideKeypad(ChangePasswordActivity.this);
                if (TextUtils.isEmpty(mOldPassword.getText().toString().trim())) {
                    mOldPassword.setError("Enter Password");
                } else {
                    mOldPassword.setError(null);
                    if (TextUtils.isEmpty(mNewPassword.getText().toString().trim())) {
                        mNewPassword.setError("Enter Password");
                    } else {
                        mNewPassword.setError(null);
                        if (TextUtils.isEmpty(mConfirmPassword.getText().toString().trim())) {
                            mConfirmPassword.setError("Enter Password");
                        } else {
                            mConfirmPassword.setError(null);
                            if (mNewPassword.getText().toString().equals(mConfirmPassword.getText().toString())) {
                                ApplicationConstant.hideKeypad(ChangePasswordActivity.this);
                                final ProgressDialog progressDialog = CustomProgressDialog.ctor(ChangePasswordActivity.this);
                                progressDialog.show();
                                ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
                                serviceCallApi = apiServiceGenerator.createService(ServiceCallApi.class);
                                final Call<Changpassword> objbanklist = serviceCallApi.changePassword(
                                        prefs.getFromPrefs(mContext, "userid", ""),
                                        mOldPassword.getText().toString().trim(),
                                        mNewPassword.getText().toString().trim());
                                objbanklist.enqueue(new Callback<Changpassword>() {
                                    @Override
                                    public void onResponse(Call<Changpassword> call, Response<Changpassword> response) {
                                        if (progressDialog != null && progressDialog.isShowing())
                                            progressDialog.dismiss();

                                        Changpassword changpassword = response.body();
                                        if (changpassword.getStatus().equalsIgnoreCase("Success")) {
                                            if (progressDialog.isShowing())
                                                progressDialog.dismiss();
                                            mConfirmPassword.setText("");
                                            mNewPassword.setText("");
                                            mOldPassword.setText("");

                                            ApplicationConstant.DisplayMessageDialog(ChangePasswordActivity.this, changpassword.getStatus(), changpassword.getRemarks());

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Changpassword> call, Throwable t) {
                                        if (progressDialog != null && progressDialog.isShowing())
                                            progressDialog.dismiss();
                                    }
                                });


                            } else {
                                Toast.makeText(mContext, "Password Not Match..!", Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                }
                if (mOldPassword.getText().toString().length() > 0 && mNewPassword.getText().toString().length() > 0
                        && mConfirmPassword.getText().toString().length() > 0) {

                } else {
                    DisplaySnakeBar("Enter missing values");
                }
                break;
        }
    }

    private void DisplaySnakeBar(String message) {
        ApplicationConstant.hideKeypad(ChangePasswordActivity.this);
        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean yesDarkTheme = preferences.getBoolean("theme_switch", false);
        if (yesDarkTheme == true) {
            // Do something
            tv.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            tv.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        }
        snackbar.show();
    }
}
