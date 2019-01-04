package com.example.webplat.amoldesigning.fragment.history_fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webplat.amoldesigning.Adapter.TransactionHistoryRecyclerAdapter;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.SimpleDividerItemDecoration;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.recharge_history.RechargeHistory;
import com.example.webplat.amoldesigning.pojo.recharge_history.TransHistoryData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistory extends Fragment {
    Context mContext;
    TransactionHistoryRecyclerAdapter transactionHistoryRecyclerAdapter;
    PrefUtils prefs;
    private LinearLayoutManager mLayoutManager;
    private ProgressBar mRegistrationProgressBar;
    ServiceCallApi service;
    FloatingActionButton filter;
    private TextView mEmpty_view;
    String strfromDate,strTodate;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private List<TransHistoryData> data = new ArrayList<TransHistoryData>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.order_transaction_history,container,false);
        bindViews(v);
        return  v;

    }

    private void bindViews(View rootView) {
        mContext = getActivity();
        mRegistrationProgressBar = (ProgressBar) rootView.findViewById(R.id.registrationProgressBar);
        mEmpty_view = (TextView) rootView.findViewById(R.id.empty_view);
        mRecyclerView = (android.support.v7.widget.RecyclerView) rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
       filter = (FloatingActionButton) rootView.findViewById(R.id.filter);

        mRegistrationProgressBar.setVisibility(View.GONE);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(mContext));
        getRechargeHistory("", "");
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
                        getRechargeHistory(strfromDate,strTodate);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    private void getRechargeHistory(String fromDate, String toDate) {
        ApplicationConstant.hideKeypad(getActivity());
        ApplicationConstant.hideKeypad(getActivity());
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(getActivity());
        progressDialog.show();
        ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
        service = apiServiceGenerator.createService(ServiceCallApi.class);
        Call<RechargeHistory> result = service.getOrderHistory(
                prefs.getFromPrefs(mContext, "userid", ""),
                prefs.getFromPrefs(mContext, "password", ""),
                fromDate, toDate);
        result.enqueue(new Callback<RechargeHistory>() {
            @Override
            public void onResponse(Call<RechargeHistory> call, Response<RechargeHistory> response) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                RechargeHistory rechargeHistory = response.body();
                if (rechargeHistory.getData() != null) {
                    if (rechargeHistory.getStatus().equals("Success")) {
                        mRecyclerView.setVisibility(View.VISIBLE);
                        transactionHistoryRecyclerAdapter = new TransactionHistoryRecyclerAdapter(mContext, rechargeHistory.getData());
                        mRecyclerView.setAdapter(transactionHistoryRecyclerAdapter);

                    } else {

                        mRecyclerView.setVisibility(View.GONE);
                    }
                } else {
                    mRecyclerView.setVisibility(View.GONE);

                    mEmpty_view.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onFailure(Call<RechargeHistory> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                mRecyclerView.setVisibility(View.GONE);

                mEmpty_view.setVisibility(View.VISIBLE);
            }

        });

    }
}