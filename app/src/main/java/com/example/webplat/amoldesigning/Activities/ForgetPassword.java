package com.example.webplat.amoldesigning.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.bank_details.BankDetails;
import com.example.webplat.amoldesigning.pojo.recharge_response.RechargeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {
    private EditText mEditBoxForget;
    private Button mBtnForgetPassword;
    Context mContext;
    ServiceCallApi serviceCallApi;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getLayoutInflater().inflate(R.layout.forgot_password, frameLayout);
        setContentView(R.layout.forgot_password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("Payment Request");

        toolbar.setNavigationIcon(R.drawable.back);

        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                }
            });
        }

        bindViews();
    }

    private void bindViews() {
        mContext = ForgetPassword.this;
        mEditBoxForget = (EditText) findViewById(R.id.editBoxForget);
        relativeLayout = (RelativeLayout) findViewById(R.id.f_top);
        mBtnForgetPassword = (Button) findViewById(R.id.btnForgetPassword);

        mBtnForgetPassword.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnForgetPassword:

                if (TextUtils.isEmpty(mEditBoxForget.getText().toString().trim())) {
                    mEditBoxForget.setError("Enter Mobile number");
                }
                else {
                    mEditBoxForget.setError(null);
                    ApplicationConstant.hideKeypad(ForgetPassword.this);
                    final ProgressDialog progressDialog = CustomProgressDialog.ctor(ForgetPassword.this);
                    progressDialog.show();
                    ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
                    serviceCallApi = apiServiceGenerator.createService(ServiceCallApi.class);
                    final Call<RechargeResponse> objbanklist = serviceCallApi.
                            forgetPassword(mEditBoxForget.getText().toString().trim());
                    objbanklist.enqueue(new Callback<RechargeResponse>() {
                        @Override
                        public void onResponse(Call<RechargeResponse> call, Response<RechargeResponse> response) {
                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();

                            RechargeResponse banklistobj = response.body();
                        if (banklistobj.getStatus().equals("Success")) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            mEditBoxForget.setText("");
                            ApplicationConstant.DisplayMessageDialog(ForgetPassword.this, banklistobj.getStatus(), banklistobj.getRemarks());
                        } if (banklistobj.getStatus().equals("Failure")) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            mEditBoxForget.setText("");
                            ApplicationConstant.DisplayMessageDialog(ForgetPassword.this, banklistobj.getStatus(), banklistobj.getRemarks());
                        }
                        }

                        @Override
                        public void onFailure(Call<RechargeResponse> call, Throwable t) {
                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    });
                    break;
                }
        }
    }
}
