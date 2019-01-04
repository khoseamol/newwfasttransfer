package com.example.webplat.amoldesigning.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.change_password.Changpassword;
import com.example.webplat.amoldesigning.pojo.user_profile.UserDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends MainActivity {

    private de.hdodenhof.circleimageview.CircleImageView mProfilePicImgView;
    private TextView mTxtUserName;
    private TextView mTxtEmailID;
    private TextView mTxtUserMobile;
    private TextView mTxtParentName;
    private TextView mTxtUserType;
    private TextView mTxtFarmName;
    private TextView mTxtPanNo;
    PrefUtils prefs;
    Context mContext;
    ServiceCallApi serviceCallApi;
    TextView changePassword,logout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.user_profile, frameLayout);
        InitilizeControl();
    }

    private void InitilizeControl() {
        mContext = ProfileActivity.this;
        setTitle(prefs.getFromPrefs(mContext, "AppName", ""));
        mTxtUserName = (TextView) findViewById(R.id.txtUserName);
        mProfilePicImgView = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.profilePicImgView);
        mTxtUserName = (TextView) findViewById(R.id.txtUserName);
        mTxtEmailID = (TextView) findViewById(R.id.txtEmailID);
        mTxtUserMobile = (TextView) findViewById(R.id.txtUserMobile);
        mTxtParentName = (TextView) findViewById(R.id.txtParentName);
        mTxtUserType = (TextView) findViewById(R.id.txtUserType);
        mTxtFarmName = (TextView) findViewById(R.id.txtFarmName);
        mTxtPanNo = (TextView) findViewById(R.id.txtPanNo);
        changePassword = (TextView) findViewById(R.id.changepassword);
        logout = (TextView) findViewById(R.id.logout);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,ChangePasswordActivity.class));

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    prefs.removeFromPrefs(ProfileActivity.this, "userid");
                    prefs.removeFromPrefs(ProfileActivity.this, "password");
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } catch (Exception e){}
            }
        });
        getSupportActionBar().setTitle("Profile");
        GetUserProfileInfo();
    }

    private void GetUserProfileInfo() {
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(ProfileActivity.this);
        progressDialog.show();
        ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
        serviceCallApi = apiServiceGenerator.createService(ServiceCallApi.class);
        final Call<UserDetails> objbanklist = serviceCallApi.getUserInfo(
                prefs.getFromPrefs(mContext, "userid", ""),
                prefs.getFromPrefs(mContext, "password", "")
        );
        objbanklist.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                UserDetails userDetails = response.body();
                if (userDetails.getStatus().equalsIgnoreCase("Success")) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                    if (userDetails.getData().size() > 0) {
                        mTxtUserName.setText(userDetails.getData().get(0).getUserName());
                        mTxtUserType.setText(userDetails.getData().get(0).getUserType());
                        mTxtParentName.setText(userDetails.getData().get(0).getParentName());
                        mTxtUserMobile.setText(userDetails.getData().get(0).getOwnerName());
                        mTxtEmailID.setText(userDetails.getData().get(0).getEmailId());
                        mTxtFarmName.setText(userDetails.getData().get(0).getFirmName());
                        mTxtPanNo.setText(userDetails.getData().get(0).getPanCard());
                    } else {
                    }
                }
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });


    }
}