package com.example.webplat.amoldesigning.fragment.history_fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.webplat.amoldesigning.Adapter.TransactionHistoryRecyclerAdapter;
import com.example.webplat.amoldesigning.Adapter.WalletHistoryRecyclerAdapter;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.Utils.SimpleDividerItemDecoration;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.recharge_history.RechargeHistory;
import com.example.webplat.amoldesigning.pojo.wallet_history.WalletSummary;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WalletSummaryFragment extends Fragment {

    Context mContext;
    WalletHistoryRecyclerAdapter walletHistoryRecyclerAdapter;
    PrefUtils prefs;
    ServiceCallApi service;
    private LinearLayoutManager mLayoutManager;
    private EditText mFromDate;
    private EditText mToDate;
    private ProgressBar mRegistrationProgressBar;
    private TextView mEmpty_view;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    String strfromDate,strTodate;
    FloatingActionButton filter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.order_transaction_history, container, false);
        bindViews(rootView);
        return rootView;
    }

    private void bindViews(View rootView) {
        mContext = getActivity();
        mRegistrationProgressBar = (ProgressBar)rootView.findViewById(R.id.registrationProgressBar);
        mEmpty_view = (TextView) rootView.findViewById(R.id.empty_view);
        mRecyclerView = (android.support.v7.widget.RecyclerView) rootView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(mContext);
        mRegistrationProgressBar.setVisibility(View.GONE);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(mContext));
        getDisputeHistory("", "");
        filter = (FloatingActionButton)rootView.findViewById(R.id.filter);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.set_date);
                final DatePicker fromdate = (DatePicker)dialog.findViewById(R.id.fromDate);
                final DatePicker todate = (DatePicker)dialog.findViewById(R.id.toDate);
                TextView txtOk = (TextView)dialog.findViewById(R.id.txtOk);
                TextView txtCancel = (TextView)dialog.findViewById(R.id.txtcancel);
                txtCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }

                });
                txtOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int month = fromdate.getMonth()+1;
                        int month2 = todate.getMonth()+1;
                        strfromDate = month+"/"+fromdate.getDayOfMonth()+"/"+fromdate.getYear();
                        strTodate = month2+"/"+todate.getDayOfMonth()+"/"+todate.getYear();
                        getDisputeHistory(strfromDate,strTodate);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    private void getDisputeHistory(String fromDate, String toDate) {



        ApplicationConstant.hideKeypad(getActivity());
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(getActivity());
        progressDialog.show();
        ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
        service = apiServiceGenerator.createService(ServiceCallApi.class);
        Call<WalletSummary> result = service.getWalletHistory(
                prefs.getFromPrefs(mContext, "userid", ""),
                prefs.getFromPrefs(mContext, "password", ""),
                fromDate, toDate);
        result.enqueue(new Callback<WalletSummary>() {
            @Override
            public void onResponse(Call<WalletSummary> call, Response<WalletSummary> response) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                WalletSummary rechargeHistory = response.body();
                if (rechargeHistory.getData() != null) {
                    if (rechargeHistory.getStatus().equals("Success")) {
                        mRecyclerView.setVisibility(View.VISIBLE);
                        walletHistoryRecyclerAdapter = new WalletHistoryRecyclerAdapter(mContext, rechargeHistory.getData());
                            mRecyclerView.setAdapter(walletHistoryRecyclerAdapter);

                    }
                    else {
                        mEmpty_view.setVisibility(View.VISIBLE);

                        mRecyclerView.setVisibility(View.GONE);
                    }

                } else {
                    mRecyclerView.setVisibility(View.GONE);

                    mEmpty_view.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onFailure(Call<WalletSummary> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                mRecyclerView.setVisibility(View.GONE);

                mEmpty_view.setVisibility(View.VISIBLE);
            }

        });

    }
}
