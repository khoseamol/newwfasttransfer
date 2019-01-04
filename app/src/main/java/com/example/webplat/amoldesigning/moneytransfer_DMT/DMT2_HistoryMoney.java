package com.example.webplat.amoldesigning.moneytransfer_DMT;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.SimpleDividerItemDecoration;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.tnxreport.DMT2Txt_Data;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.tnxreport.MOneyReport_Dmt2_pojo;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;


import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by webplat2 on 21/6/18.
 */

public class DMT2_HistoryMoney extends AppCompatActivity implements View.OnClickListener{



    Calendar myCalendar = Calendar.getInstance();
    Calendar myFromCalender = Calendar.getInstance();
    Calendar myToCalender = Calendar.getInstance();
    Context mContext;
    PrefUtils prefs;
    Toolbar toolbar;
    EditText mFromDate;
    EditText mtoDate;

   TextView mEmpty_view;

    RecyclerView mMy_recycler_view;

    List<DMT2Txt_Data> transDetails=new ArrayList<DMT2Txt_Data>();

    ServiceCallApiDMT2 service;
    DMT2HistoryAdapter sPaisaMoneyHistoryRecyclerAdapter ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_money_dtm2);
        BindView();

    }

    private void BindView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
        toolbar.setTitle("History");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        mFromDate =(EditText)findViewById  (R.id.fromDate);

         mtoDate =(EditText)findViewById  (R.id.toDate);
        mContext = DMT2_HistoryMoney.this;
        mEmpty_view=(TextView)findViewById( R.id.empty_view);
        mMy_recycler_view=(RecyclerView)findViewById (R.id.reportRecyclerView);

        mFromDate.setOnClickListener(this);
        mtoDate.setOnClickListener(this);
        mFromDate.setVisibility(View.VISIBLE);
        mtoDate.setVisibility(View.VISIBLE);
        mMy_recycler_view.addItemDecoration(new SimpleDividerItemDecoration(mContext));
        getMoneyHistory(mFromDate.getText().toString().trim(), mtoDate.getText().toString().trim(), prefs.getFromPrefs(mContext, "DMT2senderMobile", ""));



    }



    private void getMoneyHistory(String fromDate, String toDate, String mobileno) {
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(DMT2_HistoryMoney.this);
        progressDialog.show();
        ServiceCallApiDMT2 serviceCallApiDMT2 = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);
        Call<MOneyReport_Dmt2_pojo> result = serviceCallApiDMT2.dmt2_getyesbankMoneyHistory(prefs.getFromPrefs(DMT2_HistoryMoney.this, "userid", ""), prefs.getFromPrefs(DMT2_HistoryMoney.this, "password", ""), mobileno, fromDate, toDate);
        result.enqueue(new Callback<MOneyReport_Dmt2_pojo>() {
            @Override
            public void onResponse(Call<MOneyReport_Dmt2_pojo> call, Response<MOneyReport_Dmt2_pojo> response) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                if (response != null) {
                    MOneyReport_Dmt2_pojo sPaisaMoneyHistory = response.body();
                    if (sPaisaMoneyHistory != null) {
                        if (sPaisaMoneyHistory.getStatus().equals("Success")) {
                            if (sPaisaMoneyHistory.getData().size() > 0) {
                                mEmpty_view.setVisibility(View.GONE);
                                transDetails.clear();
                                transDetails = sPaisaMoneyHistory.getData();
                                mMy_recycler_view.setVisibility(View.VISIBLE);
                                sPaisaMoneyHistoryRecyclerAdapter = new DMT2HistoryAdapter(mContext,transDetails,DMT2_HistoryMoney.this);
                                mMy_recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
                                mMy_recycler_view.setAdapter(sPaisaMoneyHistoryRecyclerAdapter);

                            } else {
                                mMy_recycler_view.setVisibility(View.GONE);
                                mEmpty_view.setVisibility(View.VISIBLE);
                            }


                        } else {
                            mMy_recycler_view.setVisibility(View.GONE);
                            mEmpty_view.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(DMT2_HistoryMoney.this, "an error occured", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(DMT2_HistoryMoney.this, "an error occured", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<MOneyReport_Dmt2_pojo> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }

        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fromDate:
                DatePickerDialog dpd = DatePickerDialog.newInstance(myDatePickerDialogStartDate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
                dpd.setAccentColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    dpd.setMaxDate(myCalendar);
                }
                break;
            case R.id.toDate:
                DatePickerDialog datePickerEndDialog = DatePickerDialog.newInstance(myDatePickerDialogEndDate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerEndDialog.show(getFragmentManager(), "Datepickerdialog");
                datePickerEndDialog.setAccentColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    datePickerEndDialog.setMaxDate(myCalendar);
                }
                break;
        }

    }
    DatePickerDialog.OnDateSetListener myDatePickerDialogStartDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePickerDialog view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myFromCalender.set(Calendar.YEAR, year);
            myFromCalender.set(Calendar.MONTH, monthOfYear);
            myFromCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            mtoDate.setText("");
            mMy_recycler_view.setVisibility(View.GONE);
            updateLabel(mFromDate, myFromCalender);
        }
    };
    DatePickerDialog.OnDateSetListener myDatePickerDialogEndDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePickerDialog view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myToCalender.set(Calendar.YEAR, year);
            myToCalender.set(Calendar.MONTH, monthOfYear);
            myToCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(mtoDate, myToCalender);
            getMoneyHistory(mFromDate.getText().toString().trim(), mtoDate.getText().toString().trim(),prefs.getFromPrefs(mContext, "DMT2senderMobile", ""));
        }
    };

    private void updateLabel(EditText ed_date, Calendar myNewCalendar) {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ed_date.setText(sdf.format(myNewCalendar.getTime()));
    }
}
