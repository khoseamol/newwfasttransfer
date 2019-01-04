package com.example.webplat.amoldesigning.Activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.webplat.amoldesigning.Adapter.CreditHistoryAdapter;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.SimpleDividerItemDecoration;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.credit_summary_report.CreditReportDataaa;
import com.example.webplat.amoldesigning.pojo.credit_summary_report.CreditReporttt;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditReportActivity extends MainActivity implements View.OnClickListener {

    private LinearLayoutManager mLayoutManager;
    Context mContext;
    ServiceCallApi serviceCallApi;
    CreditHistoryAdapter transactionHistoryRecyclerAdapter;
    PrefUtils prefs;
    private EditText mFromDate;
    private EditText mToDate;
    String strfromDate,strTodate;
    FloatingActionButton filter;
    Calendar myCalendar = Calendar.getInstance();
    Calendar myFromCalender = Calendar.getInstance();
    Calendar myToCalender = Calendar.getInstance();
    private ProgressBar mRegistrationProgressBar;
    private TextView mEmpty_view;
    private android.support.v7.widget.RecyclerView mMy_recycler_view;
    private ImageView mImageViewPDF;
    private List<CreditReportDataaa> data = new ArrayList<CreditReportDataaa>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.credit_history, frameLayout);
        bindViews();

    }

    private void bindViews() {
        mContext = CreditReportActivity.this;
        setTitle(prefs.getFromPrefs(mContext, "AppName", ""));


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Billing History");

        mMy_recycler_view = (android.support.v7.widget.RecyclerView) findViewById(R.id.my_recycler_view);
        mRegistrationProgressBar = (ProgressBar) findViewById(R.id.registrationProgressBar);
        mEmpty_view = (TextView) findViewById(R.id.empty_view);
        mImageViewPDF = (ImageView) findViewById(R.id.imageViewPDF);
        mFromDate = (EditText) findViewById(R.id.fromDate);
        mToDate = (EditText) findViewById(R.id.toDate);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRegistrationProgressBar.setVisibility(View.GONE);
        mMy_recycler_view.setLayoutManager(mLayoutManager);
        mMy_recycler_view.addItemDecoration(new SimpleDividerItemDecoration(mContext));

        mFromDate.setOnClickListener(this);
        mToDate.setOnClickListener(this);
        mFromDate.setVisibility(View.VISIBLE);
        mToDate.setVisibility(View.VISIBLE);
        mImageViewPDF.setOnClickListener(this);
        mImageViewPDF.setVisibility(View.GONE);
        getdebitHistory("", "");


    }

    private void getdebitHistory(String fromDate, String toDate) {
        mRegistrationProgressBar.setVisibility(ProgressBar.VISIBLE);
//        restService.getCreditHistory(prefs.getFromPrefs(mContext, "userName", ""),
// prefs.getFromPrefs(mContext, "password", ""), fromDate, toDate,
//                new RestCallback<CreditReport>() {
//                    @Override
//                    public void success(CreditReport rechargeHistory) {
//
//                        mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
//                        if (rechargeHistory.getStatus().equals("Success")) {
//                            mEmpty_view.setVisibility(View.GONE);
//                            mMy_recycler_view.setVisibility(View.VISIBLE);
//                            data.clear();
//                            data = rechargeHistory.getData();
//                            transactionHistoryRecyclerAdapter = new CreditHistoryAdapter(mContext, rechargeHistory.getData());
//                            mMy_recycler_view.setAdapter(transactionHistoryRecyclerAdapter);
//                        } else {
//                            mEmpty_view.setVisibility(View.VISIBLE);
//                            mMy_recycler_view.setVisibility(View.GONE);
//                        }
//
//                    }
//
//                    @Override
//                    public void invalid() {
//                        mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
//                    }
//
//                    @Override
//                    public void failure(String errorMessage) {
//                        mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
//                        mEmpty_view.setVisibility(View.VISIBLE);
//                        mEmpty_view.setText(errorMessage);
//                    }
//                });


        mRegistrationProgressBar.setVisibility(ProgressBar.VISIBLE);
        ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
        serviceCallApi = apiServiceGenerator.createService(ServiceCallApi.class);
        Call<CreditReporttt> result = serviceCallApi.getCreditHistory(
                prefs.getFromPrefs(mContext, "userid", ""),
                prefs.getFromPrefs(mContext, "password", ""),
                fromDate, toDate
        );
        result.enqueue(new Callback<CreditReporttt>() {
            @Override
            public void onResponse(Call<CreditReporttt> call, Response<CreditReporttt> response) {
                mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                CreditReporttt rechargeHistory = response.body();
                if (rechargeHistory.getData() != null) {
                    if (rechargeHistory.getStatus().equals("Success")) {
                        mEmpty_view.setVisibility(View.GONE);
                        mMy_recycler_view.setVisibility(View.VISIBLE);
                        data.clear();
                        data = rechargeHistory.getData();
                        transactionHistoryRecyclerAdapter = new CreditHistoryAdapter(mContext, rechargeHistory.getData());
                        mMy_recycler_view.setAdapter(transactionHistoryRecyclerAdapter);


                    } else {
                        mEmpty_view.setVisibility(View.VISIBLE);
                        mMy_recycler_view.setVisibility(View.GONE);
                    }

                } else {
                    mEmpty_view.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onFailure(Call<CreditReporttt> call, Throwable t) {
                mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                        mEmpty_view.setVisibility(View.VISIBLE);
            }

        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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

            mToDate.setText("");
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
            updateLabel(mToDate, myToCalender);
            getdebitHistory(mFromDate.getText().toString().trim(), mToDate.getText().toString().trim());
        }
    };

    private void updateLabel(EditText ed_date, Calendar myNewCalendar) {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ed_date.setText(sdf.format(myNewCalendar.getTime()));
    }
}
