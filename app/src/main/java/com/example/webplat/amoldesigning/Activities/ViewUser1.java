package com.example.webplat.amoldesigning.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.webplat.amoldesigning.Adapter.ViewUserRecyclerAdapter;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.Utils.SimpleDividerItemDecoration;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.recharge_response.RechargeResponse;
import com.example.webplat.amoldesigning.pojo.userchildelist.UserChild;
import com.example.webplat.amoldesigning.pojo.userchildelist.UserChildListData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pc3 on 12/04/2017.
 */

public class ViewUser1 extends Fragment {

    private android.support.v7.widget.RecyclerView mMy_recycler_view;

    private LinearLayoutManager mLayoutManager;
    private TextView mEmpty_view, mUserText;
    private ProgressBar mRegistrationProgressBar;
    Context mContext;
    PrefUtils prefs;
    ViewUserRecyclerAdapter viewUserRecyclerAdapter;
    private List<UserChildListData> UserChildList = new ArrayList<UserChildListData>();
    private boolean isViewShown = false;
    ServiceCallApi serviceCallApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.order_history, container, false);
        bindView(rootView );
        return rootView;
    }


    private void bindView(View view) {
        mContext = getActivity();
        mRegistrationProgressBar = (ProgressBar)view. findViewById(R.id.registrationProgressBar);
        mEmpty_view = (TextView)view. findViewById(R.id.empty_view);
        mMy_recycler_view = (android.support.v7.widget.RecyclerView)view. findViewById(R.id.my_recycler_view);
        mEmpty_view = (TextView)view. findViewById(R.id.empty_view);

        mLayoutManager = new LinearLayoutManager(mContext);
        mRegistrationProgressBar.setVisibility(View.GONE);
        mMy_recycler_view.setLayoutManager(mLayoutManager);
        mMy_recycler_view.addItemDecoration(new SimpleDividerItemDecoration(mContext));
        getchildlist();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getView() != null) {
            isViewShown = true;
            getchildlist();
        } else {
            isViewShown = false;
        }
    }
    private void getchildlist() {
        ApplicationConstant.hideKeypad(getActivity());
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(getActivity());
        progressDialog.show();

        serviceCallApi = ApiServiceGenerator.createService(ServiceCallApi.class);
        Call<UserChild> result = serviceCallApi.userchildList(prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserName, ""),prefs.getFromPrefs(mContext,"password", ""));
        result.enqueue(new Callback<UserChild>() {
            @Override
            public void onResponse(Call<UserChild> call, Response<UserChild> response) {
                mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                UserChild rechargeResponse = response.body();
                        if (rechargeResponse.getStatus().equals("Success")) {
                            UserChild userChild = rechargeResponse;

                            if (userChild != null) {
                                if (userChild.getData().size() > 0) {
                                    UserChildList.clear();
                                    UserChildList = userChild.getData();
                                    viewUserRecyclerAdapter = new ViewUserRecyclerAdapter(mContext, userChild.getData());
                                    mMy_recycler_view.setAdapter(viewUserRecyclerAdapter);
                                } else {
                                    mEmpty_view.setVisibility(View.VISIBLE);
                                    mMy_recycler_view.setVisibility(View.GONE);
                                }
                            }
                        }
                        if (progressDialog != null)
                            progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserChild> call, Throwable t) {

            }
        });

//        restService.userChild(prefs.getFromPrefs(mContext, "userName", ""), prefs.getFromPrefs(mContext, "password", ""),
//                new RestCallback<UserChild>() {
//
//                    @Override
//                    public void success(UserChild rechargeResponse) {
//                        mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
//                        if (rechargeResponse.getStatus().equals("Success")) {
//                            UserChild userChild = rechargeResponse;
//
//                            if (userChild != null) {
//                                if (userChild.getData().size() > 0) {
//                                    UserChildList.clear();
//                                    UserChildList = userChild.getData();
//                                    viewUserRecyclerAdapter = new ViewUserRecyclerAdapter(mContext, userChild.getData());
//                                    mMy_recycler_view.setAdapter(viewUserRecyclerAdapter);
//                                } else {
//                                    mEmpty_view.setVisibility(View.VISIBLE);
//                                    mMy_recycler_view.setVisibility(View.GONE);
//                                }
//                            }
//                        }
//                        if (progressDialog != null)
//                            progressDialog.dismiss();
//                    }
//
//                    @Override
//                    public void invalid() {
//                        if (progressDialog != null)
//                            progressDialog.dismiss();
//                    }
//
//                    @Override
//                    public void failure(String errorMessage) {
//                        if (progressDialog != null)
//                            progressDialog.dismiss();
//                    }
//                });
    }

}
