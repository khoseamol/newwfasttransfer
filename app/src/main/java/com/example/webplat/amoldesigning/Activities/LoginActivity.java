package com.example.webplat.amoldesigning.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.DBHelper;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.login.LoginCheck;
import com.example.webplat.amoldesigning.pojo.login.LoginOTPVerifuy;
import com.example.webplat.amoldesigning.pojo.operator.OperatorData;
import com.example.webplat.amoldesigning.pojo.operator.OperatorListServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1000;
    private LinearLayout mTopLayout;
    private ImageView mAppImageView;
    private EditText mEdittextUserName;
    private EditText mEdittextPassword;
    private CheckBox mCheck_show_pass;
    private ImageView mTxt_forgot_password;
    private TextView mTxtSetupDomain;
    private Button mBtn_Login;
    private LinearLayout mSignup_text;
    ServiceCallApi service;
    ProgressDialog progressDialog;
    Context mContext;
    PrefUtils prefs;
    private CheckBox remeber_me_id;
    private boolean eyesFlag = false;
    TextView title;
    private Button mVerifyOtpBtn;
    private EditText mOtpET;
    private TextView mOtpVerificationTV;
    private Button mResendOtpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

    }

    private void initUI() {
        setContentView(R.layout.new_login);
        bindViews();

        mContext = LoginActivity.this;


        mEdittextUserName.setTypeface(mEdittextUserName.getTypeface());
        mEdittextPassword.setTypeface(mEdittextPassword.getTypeface());


        if (prefs.getFromPrefs(mContext, "user", "") != null && !TextUtils.isEmpty(prefs.getFromPrefs(mContext, "user", ""))) {

            mEdittextUserName.setText(prefs.getFromPrefs(mContext, "user", ""));
            remeber_me_id.setChecked(true);
        }
        if (prefs.getFromPrefs(mContext, "pass", "") != null && !TextUtils.isEmpty(prefs.getFromPrefs(mContext, "pass", ""))) {
            mEdittextPassword.setText(prefs.getFromPrefs(mContext, "pass", ""));
            remeber_me_id.setChecked(true);
        }
    }

    private void bindViews() {
        mContext = LoginActivity.this;
        mTopLayout = (LinearLayout) findViewById(R.id.topLayout);
        mAppImageView = (ImageView) findViewById(R.id.appImageView);
        mEdittextUserName = (EditText) findViewById(R.id.edittextUserName);
        mEdittextPassword = (EditText) findViewById(R.id.edittextPassword);
        mCheck_show_pass = (CheckBox) findViewById(R.id.check_show_pass);
        remeber_me_id = (CheckBox) findViewById(R.id.remeber_me_id);

        mTxt_forgot_password = (ImageView) findViewById(R.id.forgetPassword);
       // mTxtSetupDomain = (TextView) findViewById(R.id.txtSetupDomain);
        mBtn_Login = (Button) findViewById(R.id.buttonLogin);
       // mSignup_text = (LinearLayout) findViewById(R.id.signup_text);
        mBtn_Login.setOnClickListener(this);
        mTxt_forgot_password.setOnClickListener(this);

        mCheck_show_pass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    mEdittextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    mEdittextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                if (TextUtils.isEmpty(mEdittextUserName.getText().toString().trim())) {
                    mEdittextUserName.setError("Enter username");
                } else {
                    mEdittextUserName.setError(null);
                    if (TextUtils.isEmpty(mEdittextPassword.getText().toString().trim())) {
                        mEdittextPassword.setError("Enter Password");
                    } else {
                        mEdittextPassword.setError(null);
                        getloginUser(mEdittextUserName.getText().toString().trim(), mEdittextPassword.getText().toString().trim(), picDeviceID());
                    }
                }
                break;
            case R.id.forgetPassword:
                Intent intent = new Intent(mContext, ForgetPassword.class);
                startActivity(intent);
                break;

        }
    }

    private String picDeviceID() {
        if (checkAndRequestPermissions()) {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            return telephonyManager.getDeviceId();
        }
        return "";


    }

    private boolean checkAndRequestPermissions() {
        int readPhoneStatePermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (readPhoneStatePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {
            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; i++) {

                    if (permissions[i].equals(Manifest.permission.READ_PHONE_STATE)) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            Log.e("msg", "location granted");
                            getloginUser(mEdittextUserName.getText().toString().trim(), mEdittextPassword.getText().toString().trim(), picDeviceID());

                        }
                    }

                }

            }


        }
    }

    private void getloginUser(String username, String password, String picDeviceID) {
        progressDialog = CustomProgressDialog.ctor(LoginActivity.this);
        progressDialog.show();

        service = ApiServiceGenerator.createService(ServiceCallApi.class);
        Call<LoginCheck> result = service.getLoginInfo(username, password, picDeviceID);
        result.enqueue(new Callback<LoginCheck>() {
            @Override
            public void onResponse(Call<LoginCheck> call, Response<LoginCheck> response) {
//
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                if (response != null) {
                    LoginCheck loginCheck = response.body();
                    if (loginCheck.getResponseStatus().equals("0")) {
                        ApplicationConstant.DisplayMessageDialog(LoginActivity.this, "Error", "you have entered a wrong username password..try again...! ");
                        mEdittextUserName.setText("");
                        mEdittextPassword.setText("");

                    }
                    else if (loginCheck.getResponseStatus().equals("2")) {
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        DisplayOTPDialog(mEdittextUserName.getText().toString().trim(),
                                mEdittextPassword.getText().toString().trim(), picDeviceID());
                    }

                    else {
                        DBHelper dbHelper = new DBHelper(mContext);
                        dbHelper.deleteExistingOperator();

                        if (remeber_me_id.isChecked()) {

                            prefs.saveToPrefs(mContext, "user", mEdittextUserName.getText().toString().trim());
                            prefs.saveToPrefs(mContext, "pass", mEdittextPassword.getText().toString().trim());
                        } else {
                            prefs.removeFromPrefs(mContext, "user");
                            prefs.removeFromPrefs(mContext, "pass");
                        }
                        prefs.saveToPrefs(mContext, Constant.USERDETAILS.UserType, loginCheck.getData().getUserType());
                        prefs.saveToPrefs(mContext, "userid", mEdittextUserName.getText().toString().trim());
                        prefs.saveToPrefs(mContext, "password", mEdittextPassword.getText().toString().trim());
                        prefs.saveToPrefs(mContext, Constant.USERDETAILS.UserName, loginCheck.getData().getUserName());
                        prefs.saveToPrefs(mContext, Constant.USERDETAILS.OwnerName, loginCheck.getData().getOwnerName());
                        prefs.saveToPrefs(mContext, Constant.USERDETAILS.MainBalance, loginCheck.getData().getMainBalance());
                        prefs.saveToPrefs(mContext, Constant.USERDETAILS.LastSeen, loginCheck.getData().getLastSeen());
                        prefs.saveToPrefs(mContext,Constant.USERDETAILS.UserName,loginCheck.getData().getUserName());
                        GetOperator(progressDialog);


                    }
                } else {
                    Toast.makeText(LoginActivity.this, "an error occured", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<LoginCheck> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }

        });
    }

    private void GetOperator(final ProgressDialog progressDialog) {
        final DBHelper dbHelper = new DBHelper(getApplicationContext());
        service = ApiServiceGenerator.createService(ServiceCallApi.class);
        Call<OperatorListServer> result = service.getoperatorlist();
        result.enqueue(new Callback<OperatorListServer>() {
            @Override
            public void onResponse(Call<OperatorListServer> call, Response<OperatorListServer> response) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                OperatorListServer operatorListServer = response.body();
                if (operatorListServer != null) {

                    List<OperatorData> data = new ArrayList<OperatorData>();
                    data = operatorListServer.getData();
                    if (data.size() > 0) {
                        for (int i = 0; i < data.size(); i++) {
                            OperatorData operatorData = data.get(i);
                            dbHelper.insertOperator(operatorData);
                        }
                        navigateToDashboard();
//
//                            Intent loginIntent = new Intent(LoginActivity.this, Dashboard.class);
//                            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(loginIntent);

                    } else {
                        Toast.makeText(mContext, "No Operator Found", Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<OperatorListServer> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                System.out.println("response failed" + t.getMessage());
            }


        });
    }

    private void navigateToDashboard() {
        if (prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserType, "").equalsIgnoreCase("Retailer")) {
            Intent loginIntent = new Intent(LoginActivity.this, Dashboard.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(loginIntent);
        } else {
            Intent loginIntent = new Intent(LoginActivity.this, DistributerDashboard.class);
            startActivity(loginIntent);
        }
    }


    private void DisplayOTPDialog(final String userName, final String passWord, final String deviceID) {
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.otp_confirm_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm OTP").setCancelable(false).setView(dialoglayout);
        mVerifyOtpBtn = (Button) dialoglayout.findViewById(R.id.verifyOtpBtn);
        mOtpET = (EditText) dialoglayout.findViewById(R.id.otpET);
        mOtpET.setInputType(InputType.TYPE_CLASS_TEXT);
        mResendOtpBtn = (Button) dialoglayout.findViewById(R.id.resendOtpBtn);
        mOtpVerificationTV = (TextView) dialoglayout.findViewById(R.id.otpVerificationTV);
        mResendOtpBtn.setVisibility(View.VISIBLE);
        mOtpVerificationTV.setVisibility(View.GONE);
        final AlertDialog alert = builder.create();
        //change input type to text
        mOtpET.setInputType(InputType.TYPE_CLASS_NUMBER);

        mResendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = CustomProgressDialog.ctor(LoginActivity.this);
                progressDialog.show();

                service = ApiServiceGenerator.createService(ServiceCallApi.class);
                Call<LoginCheck> result = service.getLoginInfo(userName, passWord, deviceID);
                result.enqueue(new Callback<LoginCheck>() {
                    @Override
                    public void onResponse(Call<LoginCheck> call, Response<LoginCheck> response) {
                        LoginCheck loginCheck = response.body();
                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        if (loginCheck != null) {
                            if (loginCheck.getResponseStatus().equals("0")) {
                                if (progressDialog.isShowing())
                                    progressDialog.dismiss();
                                ApplicationConstant.DisplayMessageDialog(LoginActivity.this, "Error", loginCheck.getRemarks());
                                mEdittextUserName.setText("");
                                mEdittextPassword.setText("");
                            }
                            // if response status 2 then => OTP Sent
                            else if (loginCheck.getResponseStatus().equals("2")) {
                                if (progressDialog.isShowing())
                                    progressDialog.dismiss();
                                ApplicationConstant.displayToastMessage(mContext, loginCheck.getRemarks());
                            }

                            // if response status 1 then => Valid User => goto Dashboard

                            else {
                                if (progressDialog.isShowing())
                                    progressDialog.dismiss();

                                if (remeber_me_id.isChecked()) {
                                    prefs.saveToPrefs(mContext, "user", mEdittextUserName.getText().toString().trim());
                                    prefs.saveToPrefs(mContext, "pass", mEdittextPassword.getText().toString().trim());
                                } else {
                                    prefs.removeFromPrefs(mContext, "user");
                                    prefs.removeFromPrefs(mContext, "pass");
                                }

                                prefs.saveToPrefs(mContext, Constant.USERDETAILS.UserType, loginCheck.getData().getUserType());
                                prefs.saveToPrefs(mContext, "userid", mEdittextUserName.getText().toString().trim());
                                prefs.saveToPrefs(mContext, "password", mEdittextPassword.getText().toString().trim());
                                prefs.saveToPrefs(mContext, Constant.USERDETAILS.UserName, mEdittextUserName.getText().toString().trim());
                                prefs.saveToPrefs(mContext, Constant.USERDETAILS.PSSWORD, mEdittextPassword.getText().toString().trim());
                                prefs.saveToPrefs(mContext, Constant.USERDETAILS.UserName, loginCheck.getData().getUserName());
                                prefs.saveToPrefs(mContext, Constant.USERDETAILS.OwnerName, loginCheck.getData().getOwnerName());
                                prefs.saveToPrefs(mContext, Constant.USERDETAILS.MainBalance, loginCheck.getData().getMainBalance());
                                prefs.saveToPrefs(mContext, Constant.USERDETAILS.LastSeen, loginCheck.getData().getLastSeen());

                                navigateToDashboard();


                            }
                        } else {

                        }


                    }

                    @Override
                    public void onFailure(Call<LoginCheck> call, Throwable t) {
                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();
                        if (t instanceof IOException) {
                            Toast.makeText(mContext,"Please turn on your internet connection ", Toast.LENGTH_SHORT).show();
                            // logging probably not necessary
                        }
                    }

                });
            }
        });

        mVerifyOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                ApplicationConstant.hideKeypad(LoginActivity.this);
                if (TextUtils.isEmpty(mOtpET.getText().toString().trim())) {
                    mOtpET.setError("Enter OTP");
                } else {
                    final ProgressDialog pDialog = CustomProgressDialog.ctor(LoginActivity.this);
                    pDialog.show();

                    ServiceCallApi serviceCallApi = ApiServiceGenerator.createService(ServiceCallApi.class);
                    Call<LoginOTPVerifuy> result = serviceCallApi.verifyLoginOTP(userName, passWord, deviceID, mOtpET.getText().toString().trim());
                    result.enqueue(new Callback<LoginOTPVerifuy>() {
                        @Override
                        public void onResponse(Call<LoginOTPVerifuy> call, Response<LoginOTPVerifuy> response) {
                            LoginOTPVerifuy loginCheck = response.body();
                            if (loginCheck.getResponseStatus().equals("0")) {
                                ApplicationConstant.DisplayMessageDialog(LoginActivity.this, "Error", "you have entered a wrong OTP..try again...! ");
                                mEdittextUserName.setText("");
                                mEdittextPassword.setText("");
                                if (pDialog.isShowing())
                                    pDialog.dismiss();
                            } else {
                                if (loginCheck.getResponseStatus().equals("2")) {
                                    if (pDialog != null && pDialog.isShowing())
                                        pDialog.dismiss();
                                    DisplayOTPDialog(mEdittextUserName.getText().toString().trim(), mEdittextPassword.getText().toString().trim(), deviceID);
                                } else {
                                    if (pDialog.isShowing())
                                        pDialog.dismiss();

                                    if (remeber_me_id.isChecked()) {
                                        prefs.saveToPrefs(mContext, "user", mEdittextUserName.getText().toString().trim());
                                        prefs.saveToPrefs(mContext, "pass", mEdittextPassword.getText().toString().trim());
                                    } else {
                                        prefs.removeFromPrefs(mContext, "user");
                                        prefs.removeFromPrefs(mContext, "pass");
                                    }
//

                                    prefs.saveToPrefs(mContext, Constant.USERDETAILS.UserType, loginCheck.getData().getUserType());
                                    prefs.saveToPrefs(mContext, "userid", mEdittextUserName.getText().toString().trim());
                                    prefs.saveToPrefs(mContext, "password", mEdittextPassword.getText().toString().trim());
                                    prefs.saveToPrefs(mContext, Constant.USERDETAILS.UserName, loginCheck.getData().getUserName());
                                    prefs.saveToPrefs(mContext, Constant.USERDETAILS.OwnerName, loginCheck.getData().getOwnerName());
                                    prefs.saveToPrefs(mContext, Constant.USERDETAILS.MainBalance, String.valueOf(loginCheck.getData().getMainBalance()));
                                    prefs.saveToPrefs(mContext, Constant.USERDETAILS.LastSeen, loginCheck.getData().getLastSeen());
                                    prefs.saveToPrefs(mContext, Constant.USERDETAILS.UserName, mEdittextUserName.getText().toString().trim());
                                    navigateToDashboard();


                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginOTPVerifuy> call, Throwable t) {
                            if (pDialog != null && pDialog.isShowing())
                                pDialog.dismiss();
                            if (t instanceof IOException) {
                                Toast.makeText(mContext,"Please turn on your internet connection ", Toast.LENGTH_SHORT).show();
                                // logging probably not necessary
                            }
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

}

