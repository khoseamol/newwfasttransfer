package com.example.webplat.amoldesigning.Activities;

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
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.webplat.amoldesigning.Adapter.BBPSHistoryAdapter;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.bbpshistory.BBPSBillingHistory;
import com.example.webplat.amoldesigning.pojo.bbpshistory.Datum;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by webplat2 on 29/8/18.
 */

public class BBPSHistory extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rv;
    Toolbar toolbar;
    BBPSHistoryAdapter adapter;
    Context mContext;
    ServiceCallApi restService ;
    List<Datum> historyList= new ArrayList<>();
    Calendar myCalendar = Calendar.getInstance();
    Calendar myFromCalender = Calendar.getInstance();
    Calendar myToCalender = Calendar.getInstance();
    private EditText mEditTextFromDate;
    private EditText mEditTextToDate;
    PrefUtils prefs;

    private ProgressBar mRegistrationProgressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dth_report);
        rv = (RecyclerView)findViewById(R.id.my_recycler_view);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        toolbar.setTitle("BBPS report");
        LinearLayoutManager manager = new LinearLayoutManager(BBPSHistory.this);
        rv.setLayoutManager(manager);

        mContext = BBPSHistory.this;
        mEditTextFromDate = (EditText)findViewById(R.id.fromDate);
        mEditTextToDate = (EditText)findViewById(R.id.toDate);
        mRegistrationProgressBar = (ProgressBar)findViewById(R.id.registrationProgressBar);

        mRegistrationProgressBar.setVisibility(View.GONE);
        mEditTextFromDate.setOnClickListener(this);
        mEditTextToDate.setOnClickListener(this);
        mEditTextFromDate.setVisibility(View.VISIBLE);
        mEditTextToDate.setVisibility(View.VISIBLE);
        getLedgerHistory("", "");


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
                DatePickerDialog dpd1 = DatePickerDialog.newInstance(myDatePickerDialogEndDate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
                );
                dpd1.show(getFragmentManager(), "Datepickerdialog");
                dpd1.setAccentColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    dpd1.setMaxDate(myCalendar);
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

            mEditTextToDate.setText("");
            updateLabel(mEditTextFromDate, myFromCalender);
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
            updateLabel(mEditTextToDate, myToCalender);

            getLedgerHistory(mEditTextFromDate.getText().toString().trim(), mEditTextToDate.getText().toString().trim());
        }
    };

    private void getLedgerHistory(String fromDate, String toDate) {mRegistrationProgressBar.setVisibility(ProgressBar.GONE);

        final ProgressDialog progressDialog = CustomProgressDialog.ctor(BBPSHistory.this);
        progressDialog.show();
        restService = ApiServiceGenerator.createService(ServiceCallApi.class);
        Call<BBPSBillingHistory> call = restService.bbpshistory(prefs.getFromPrefs(mContext,"userid",""),prefs.getFromPrefs(mContext,"password",""),fromDate,toDate);
        call.enqueue(new Callback<BBPSBillingHistory>() {
            @Override
            public void onResponse(Call<BBPSBillingHistory> call, Response<BBPSBillingHistory> walletSummary) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                try { if(walletSummary.body().getStatus().equalsIgnoreCase("Success")){
                    rv.setVisibility(View.VISIBLE);
                    if(walletSummary.body().getData().size()>0){
                        adapter = new BBPSHistoryAdapter(mContext,walletSummary.body().getData(),BBPSHistory.this);
                        rv.setAdapter(adapter);
                    }
                    else

                        rv.setVisibility(View.GONE);
                }
                else

                    rv.setVisibility(View.GONE);

                }catch (Exception e){
                    Toast.makeText(mContext,"No service found",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BBPSBillingHistory> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });




    }

    private void updateLabel(EditText ed_date, Calendar myNewCalendar) {

        ed_date.setError(null);
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ed_date.setText(sdf.format(myNewCalendar.getTime()));
    }


}

