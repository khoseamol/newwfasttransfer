package com.example.webplat.amoldesigning.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.bbpsbilling.BBPSBilling;
import com.example.webplat.amoldesigning.pojo.bbpscategory.BBPSCategory;
import com.example.webplat.amoldesigning.pojo.bbpscategory.Datum;
import com.example.webplat.amoldesigning.pojo.bbpssubcategory.BBPSSubcategory;
import com.example.webplat.amoldesigning.pojo.utility_bbps.utility_bill.CheckUtilityBIll;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by webplat2 on 12/6/18.
 */


public class BBPSActivity extends AppCompatActivity implements View.OnClickListener {
        Context mContext;
        Spinner mSpinnerCategary , mSpinnerAccountType , mSpinnerPayMode;
        EditText mspinnerService;
        public Dialog dialog;
        TextView mTxt1,mTxt2,mTxt3;
        EditText mEdit1,mEdit2,mEdit3;
        LinearLayout l1,l2,l4;
        int CategoryId;
        String GeoCode="";
        int ourcode;
        String Duedate="";
        String BillDate="";
        String ConsumerName="";
        String Billernumber="";
        String param1="";
        String param2="";
        String param3="";
        String mCode = "";
        long BBPSdicountValue;
        Button mBtnRecharge;
        ProgressDialog progressDialog;
        Boolean isPermissionGranted = false;
        PrefUtils prefs;
        Double lat,longi;
        Double latitude , longitude;
        String[] acType = {"LLI", "LLC"};
        String[] paymentMode = {"Cash", "Debit Card", "Credit Card"};
        ServiceCallApi service;
        int sizeOfExtrafield = 0;
        private boolean isUtilityBIllPay = false;
        private  boolean isUtilityBillFetched = false;
        String mMode = "",mOurCode="";
        private List<Datum> bbpsCatagaryList = new ArrayList<>();
        private List<com.example.webplat.amoldesigning.pojo.bbpssubcategory.Datum> ServiceDataArrayList = new ArrayList<>();
        // private List<Operator_Datum> operatorDataArrayList = new ArrayList<>();
        ArrayList<String> permissions = new ArrayList<>();
        EditText mEdittextSubscriberID,mEdittextMobileNumber,mEdittextAcNumber,mEdittextAmount,mEditTextGasAccountNumber,mEditTextCycleNumber,mEditTextRelianceEnergyCycleNumber,mEditTextBillingUnit,mEdittextCity;
        LinearLayout mPostpaidHiddenLayout,mRelianceEnergyHiddenLayout,mMahanagarGasHiddenLayout,mTorrentPowerHiddenLayout,mAmountHiddenLayout, mMsedcHiddenLayout;
        private Location mLastLocation;
        private String operatorName;
    private String PartialAmount="";
    private boolean flag=false;

    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bbps);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
            toolbar.setTitleTextColor(0xFFFFFFFF);
            toolbar.setTitle("BBPS");
            setSupportActionBar(toolbar);

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
            mContext = BBPSActivity.this;
            mSpinnerCategary = (Spinner) findViewById(R.id.spinnerCategary);

            mspinnerService = (EditText) findViewById(R.id.spinnerService);
            // mEdittextSubscriberID = (EditText) findViewById(R.id.edittextSubscriberID);
            mEdit1 = (EditText) findViewById(R.id.edit1);
            mEdit2 = (EditText) findViewById(R.id.edit2);
            mEdit3 = (EditText) findViewById(R.id.edit3);
            mTxt1 = (TextView)findViewById(R.id.txt1);
            mTxt2= (TextView)findViewById(R.id.txt2);
            mTxt3 = (TextView)findViewById(R.id.txt3);
            l1 = (LinearLayout) findViewById(R.id.l1);
            l2 = (LinearLayout)findViewById(R.id.l2);
            l4 = (LinearLayout)findViewById(R.id.l4);

            mEdittextMobileNumber = (EditText) findViewById(R.id.edittxtmobile);
            mPostpaidHiddenLayout = (LinearLayout) findViewById(R.id.postpaidHiddenLayout);
            mEdittextAcNumber = (EditText) findViewById(R.id.EdittextAcNumber);
            mSpinnerAccountType = (Spinner) findViewById(R.id.spinnerAccountType);
            mAmountHiddenLayout = (LinearLayout) findViewById(R.id.amountHiddenLayout);
            mEdittextAmount = (EditText) findViewById(R.id.EdittextAmount);
            mBtnRecharge = (Button) findViewById(R.id.btnRecharge);
            mRelianceEnergyHiddenLayout = (LinearLayout) findViewById(R.id.relianceEnergyHiddenLayout);
            mEditTextRelianceEnergyCycleNumber = (EditText) findViewById(R.id.editTextRelianceEnergyCycleNumber);
            mMahanagarGasHiddenLayout = (LinearLayout) findViewById(R.id.mahanagarGasHiddenLayout);
            mEditTextGasAccountNumber = (EditText) findViewById(R.id.editTextGasAccountNumber);
            mSpinnerPayMode = (Spinner) findViewById(R.id.spinnerPayMode);

            // mMsedcHiddenLayout = (LinearLayout) findViewById(R.id.msedcHiddenLayout);
            // mEditTextCycleNumber = (EditText) findViewById(R.id.editTextCycleNumber);
            mEditTextBillingUnit = (EditText)findViewById(R.id.editTextBillingUnit);
            mTorrentPowerHiddenLayout = (LinearLayout) findViewById(R.id.torrentPowerHiddenLayout);
            mEdittextCity = (EditText) findViewById(R.id.edittextCity);

            ArrayAdapter<String> actypeAdapter = new ArrayAdapter<String>(mContext, R.layout.bbps_simple_spiner_item, android.R.id.text1, acType);
            actypeAdapter.setDropDownViewResource(R.layout.simple_spiner_item_bbps);
            mSpinnerAccountType.setAdapter(actypeAdapter);

            ArrayAdapter<String> paymentModeAdapter = new ArrayAdapter<String>(mContext, R.layout.bbps_simple_spiner_item, android.R.id.text1, paymentMode);
            paymentModeAdapter.setDropDownViewResource(R.layout.simple_spiner_item_bbps);
            mSpinnerPayMode.setAdapter(paymentModeAdapter);
            mBtnRecharge.setVisibility(View.GONE);
            mBtnRecharge.setOnClickListener(this);
            callCatagary();


            mspinnerService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setViisiblityGone();
                    Intent intent = new Intent(mContext, DisplayListActivity.class);
                    intent.putExtra("myList",(Serializable)ServiceDataArrayList);

                    startActivityForResult(intent, 10);
                }
            });




        }



        private void callCatagary() {
            progressDialog = new CustomProgressDialog(mContext, R.layout.custom_progress_dialog);
            progressDialog.show();
            service = ApiServiceGenerator.createService(ServiceCallApi.class);
            Call<BBPSCategory> bbpsCategoryCall = service.getBBPSCatagaryURL();
            bbpsCategoryCall.enqueue(new Callback<BBPSCategory>() {
                @Override
                public void onResponse(Call<BBPSCategory> call, Response<BBPSCategory> response) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                    if (response.body().getStatus().equals("Success")) {


                        if (response.body().getData() != null) {

                            if (response.body().getData() != null) {
                                if (response.body().getData().size() > 0) {
//                  bbpsCatagaryList.addAll(rechargeResponse.getData());
                                    try {
                                        bbpsCatagaryList.addAll(response.body().getData());

                                    } catch (ArrayIndexOutOfBoundsException e) {
                                        e.printStackTrace();
                                    }
                                    ArrayAdapter<Datum> adapter1 = new ArrayAdapter<Datum>(
                                            mContext, R.layout.bbps_simple_spiner_item,
                                            bbpsCatagaryList);

                                    adapter1.setDropDownViewResource(R.layout.simple_spiner_item_bbps);
                                    mSpinnerCategary.setAdapter(adapter1);
                                    CategoryId = response.body().getData().get(0).getCategoryid();
                                    getSubCatgory(CategoryId);
                                }


                            } else {
                                Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<BBPSCategory> call, Throwable t) {
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            mSpinnerCategary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    ((TextView) view).setTextSize(18);
                    ((TextView) view).setTypeface(  Typeface.DEFAULT_BOLD);

                    Datum operatorData = (Datum) adapterView.getItemAtPosition(position);
                    mAmountHiddenLayout.setVisibility(View.GONE);
                    if(operatorData.getCategoryid()==6){

                        mMode = "Electricity";
                    }
                    if(operatorData.getCategoryid()==7){

                        mMode = "Gas";
                    }
                    if(operatorData.getCategoryid()==5){

                        mMode = "Insurance";
                    }
                    if(operatorData.getCategoryid()==4){

                        mMode = "Postpaid";
                    }
                    if(operatorData.getCategoryid()==8){

                        mMode = "Water";
                    }


                    //callService(operatorData.getId());
                    getSubCatgory(operatorData.getCategoryid());

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.bbps, menu);

            return  true ;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();
            switch (id) {
                case   R.id.action_history:
                    Intent intent = new Intent(mContext,BBPSHistory.class);
                    startActivity(intent);
                    break;


            }
            return super.onOptionsItemSelected(item);
        }
        private void getSubCatgory(int categoryId) {
            service = ApiServiceGenerator.createService(ServiceCallApi.class);
            ServiceDataArrayList.clear();
            Call<BBPSSubcategory> call = service.getBBPSServiceURL(categoryId);
            call.enqueue(new Callback<BBPSSubcategory>() {
                @Override
                public void onResponse(Call<BBPSSubcategory> call, Response<BBPSSubcategory> response) {
                    if(response.body().getStatus().equalsIgnoreCase("Success")){

                        if(response.body().getData().size()>0){
                            ServiceDataArrayList.clear();
                            ServiceDataArrayList.addAll(response.body().getData());

                        }


                    }

                    if(response.body().getStatus().equalsIgnoreCase("Failure")){
                        ServiceDataArrayList.clear();
                        mspinnerService.setText(null);
                        Toast.makeText(mContext,"No services available",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BBPSSubcategory> call, Throwable t) {
                    Toast.makeText(mContext,"No services available",Toast.LENGTH_SHORT).show();
                }
            });


        }







        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 10 && resultCode == RESULT_OK) {

                mspinnerService.setText(data.getStringExtra(Constant.SelectedOperator));
                ourcode = data.getIntExtra(Constant.SelectedOperatorID,0);
                mCode = data.getStringExtra(Constant.SelectedServicCode);
                isUtilityBIllPay = data.getBooleanExtra("isfeatch",false);

                if(data.getBooleanExtra("ispartialy",false))
                {flag = data.getBooleanExtra("ispartialy",false);
                    mEdittextAmount.setEnabled(true);}
                else
                    mEdittextAmount.setEnabled(false);
                if(isUtilityBIllPay) {
                    mBtnRecharge.setText("Fetch Bill");
                    isUtilityBillFetched = false;
                    flag = data.getBooleanExtra("ispartialy",false);
                }
                else {
                    mBtnRecharge.setText("Pay Bill");
                    isUtilityBillFetched = true;
                    mEdittextAmount.setEnabled(true);

                }





                getBillingUnits(ourcode);
            }

        }

        private void getBillingUnits(int ourcode) {
            service = ApiServiceGenerator.createService(ServiceCallApi.class);
            Call<BBPSBilling> call = service.getBBPSUnit(
                    ourcode);
            call.enqueue(new Callback<BBPSBilling>() {
                @Override
                public void onResponse(Call<BBPSBilling> call, Response<BBPSBilling> response) {
                    if(response.body().getStatus().equals("Success")){
                        if(response.body().getData().size()>0){
                            final List<com.example.webplat.amoldesigning.pojo.bbpsbilling.Datum> data = response.body().getData();
                            sizeOfExtrafield = data.size();



                            l1.setVisibility(View.VISIBLE);
                            mTxt1.setVisibility(View.VISIBLE);
                            mTxt1.setText(data.get(0).getName());
                            if(data.get(0).getIsmandatory()){
                                mTxt1.setText(data.get(0).getName()+"*");
                                mTxt1.setTextColor(getResources().getColor(R.color.red));
                            }
                            if (data.get(0).getFieldType().equals("NUMERIC")) {
                                mEdit1.setInputType(InputType.TYPE_CLASS_NUMBER);
                            }
                            else
                                mEdit1.setInputType(InputType.TYPE_CLASS_TEXT);
                            mEdit1.setVisibility(View.VISIBLE);
                            mEdit1.setHint(data.get(0).getName());
                            mEdit1.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if(s.toString().length()<data.get(0).getMinLenght()|| s.toString().length()>data.get(0).getMaxLength()|| s.toString().isEmpty()){
                                        mEdit1.setError("charcater length should be in between"+ data.get(0).getMinLenght()+"-"+data.get(0).getMaxLength());
                                    }
                                    else {
                                        //  if(!mCode.equals("EA"))
                                        if(sizeOfExtrafield>=2){
                                            mEdit2.setVisibility(View.VISIBLE);
                                            mTxt2.setVisibility(View.VISIBLE);
                                            if(data.get(1).getIsmandatory()){
                                                mTxt2.setText(data.get(1).getName()+"*");
                                                mTxt2.setTextColor(getResources().getColor(R.color.red));
                                            }
                                            else
                                                mTxt2.setText(data.get(1).getName());
                                            param1= mEdit2.getText().toString();
                                            mEdit2.setHint(data.get(1).getName());
                                            if (data.get(1).getFieldType().equals("NUMERIC")) {
                                                mEdit2.setInputType(InputType.TYPE_CLASS_NUMBER);
                                            }
                                            else
                                                mEdit2.setInputType(InputType.TYPE_CLASS_TEXT);

                                        }
                                        else
                                            mEdittextMobileNumber.setVisibility(View.VISIBLE);
                                    }
                                }
                            });

                            mEdit2.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if(s.toString().length()<data.get(1).getMinLenght()||s.toString().length()>data.get(1).getMaxLength()|| s.toString().isEmpty()){
                                        mEdit2.setError("length should be in between "+ data.get(1).getMinLenght()+"-"+data.get(1).getMaxLength());

                                    }

                                    else {
                                        if(sizeOfExtrafield==3){
                                            l2.setVisibility(View.VISIBLE);
                                            mTxt3.setVisibility(View.VISIBLE);
                                            if(data.get(2).getIsmandatory()){
                                                mTxt3.setText(data.get(2).getName()+"*");
                                                mTxt3.setTextColor(getResources().getColor(R.color.red));
                                            }
                                            else
                                                mTxt3.setText(data.get(2).getName());
                                            if (data.get(2).getFieldType().equals("NUMERIC")) {
                                                mEdit3.setInputType(InputType.TYPE_CLASS_NUMBER);
                                            }
                                            else
                                                mEdit3.setInputType(InputType.TYPE_CLASS_TEXT);

                                            mEdit3.setHint(data.get(2).getName());
                                            param2= mEdit3.getText().toString();

                                            mEdit3.setVisibility(View.VISIBLE);
                                        }
                                        else {
                                            mEdittextMobileNumber.setVisibility(View.VISIBLE);
                                        }
                                    }

                                }
                            });

                            mEdit3.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if(s.toString().isEmpty()||s.toString().length()<data.get(2).getMinLenght()||s.toString().length()>data.get(2).getMaxLength()){
                                        mEdit3.setError("length should be in between"+ data.get(2).getMinLenght()+"-"+data.get(2).getMaxLength());
                                        mEdittextMobileNumber.setVisibility(View.VISIBLE);

                                    }
                                    else
                                    {
                                        mEdittextMobileNumber.setVisibility(View.VISIBLE);
                                    }
                                }
                            });

                            mEdittextMobileNumber.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if(s.toString().length()!=10){
                                        mEdittextMobileNumber.setError("Please enter mobile number");
                                    }

                                    else {
                                        l4.setVisibility(View.VISIBLE);
                                        if(isUtilityBIllPay==false)
                                            mAmountHiddenLayout.setVisibility(View.VISIBLE);
                                        mBtnRecharge.setVisibility(View.VISIBLE);
                                    }
                                }
                            });





                        }

                    }
                    else {
                        Toast.makeText(mContext, "No billing parameter availabele", Toast.LENGTH_SHORT).show();
                        l1.setVisibility(View.GONE);
                        l2.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<BBPSBilling> call, Throwable t) {
                    Toast.makeText(mContext, "No billing parameter availabele", Toast.LENGTH_SHORT).show();

                }
            });

        }

        private void setViisiblityGone() {


            l1.setVisibility(View.GONE);
            mTxt1.setVisibility(View.GONE);
            mTxt2.setVisibility(View.GONE);
            mTxt3.setVisibility(View.GONE);
            mEdit1.setVisibility(View.GONE);
            mEdit2.setVisibility(View.GONE);
            mEdit3.setVisibility(View.GONE);
            l2.setVisibility(View.GONE);
            mEdittextMobileNumber.setVisibility(View.GONE);
            l4.setVisibility(View.GONE);
            mAmountHiddenLayout.setVisibility(View.GONE);


        }


        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btnRecharge:
                    //permissionUtils.check_permission(permissions, "Need GPS permission for getting your location", 1);

                    getLocation();
                    break;
            }

        }

        private void getLocation() {

//            LocationPrefManager
//                    locPref = new LocationPrefManager(mContext);
//            lat = Double.parseDouble(locPref.getLat());
//            longi = Double.parseDouble(locPref.getLongi());
//            MainActivity.getInstance().getLocation();
//            if (lat == 0.0 || longi == 0.0){
//                lat =  MainActivity.getInstance().getLatitude();
//                longi =MainActivity.getInstance().getLongitude();
//            }
            procedTopay();

        }

        private boolean validateField(String mOurCode) {
            if (TextUtils.isEmpty(mEdit1.getText().toString().trim())) {
                mEdit1.setError("Enter Number");
                mEdit1.requestFocus();
            } else {
                mEdit1.setError(null);
                if (TextUtils.isEmpty(mEdittextMobileNumber.getText().toString().trim())) {
                    mEdittextMobileNumber.setError("Enter Mobile Number");
                    mEdittextMobileNumber.requestFocus();
                } else {
                    mEdittextMobileNumber.setError(null);
                    if (mSpinnerPayMode.getSelectedItemPosition() == 0) {
                        return true;

                    } else {

                        Toast.makeText(mContext, "Only cash mode available", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
            return false;
        }


        private void procedTopay() {
            //   GeoCode = lat + "," + longi;
            ApplicationConstant.hideKeypad((Activity)mContext);
            if (TextUtils.isEmpty(mCode) || mCode.equals("")) {
                Toast.makeText(mContext, "Please try again letter", Toast.LENGTH_SHORT).show();
            } else {

                if (validateField(mCode)) {
                    if (isUtilityBIllPay) {
                        if (isUtilityBillFetched) {
                            param1 = mEdit2.getText().toString().trim();
                            param2 = mEdit3.getText().toString().trim();

                            doBBPSRecharge(mEdit1.getText().toString().trim(), mEdittextAmount.getText().toString().trim(), mMode, param1, param2, param3, mEdittextMobileNumber.getText().toString().trim(), mCode, GeoCode);

                        } else {
                            proceedToFetchBill(mCode);
                        }
                    } else {
                        if (validateField(mCode)) {
                            if (mCode.equals("LB")) {
                                if (TextUtils.isEmpty(mEdit1.getText().toString().trim())) {
                                    mEdit1.setError("Enter account Number");
                                    mEdit1.requestFocus();
                                } else {
                                    if (TextUtils.isEmpty(mEdit1.getText().toString().trim())) {
                                        mEdittextAmount.setError("Enter Amount");
                                        mEdittextAmount.requestFocus();
                                    } else {
                                        param1 = mEdittextAcNumber.getText().toString().trim();
                                        param2 = mSpinnerAccountType.getSelectedItem().toString().trim();
                                        doBBPSRecharge(mEdittextSubscriberID.getText().toString().trim(), mEdittextAmount.getText().toString().trim(), mMode, param1, param2, param3, mEdittextMobileNumber.getText().toString().trim(), mCode, GeoCode);
                                    }
                                }
                            }else{
                                if (TextUtils.isEmpty(mEdittextAmount.getText().toString().trim())) {
                                    mEdittextAmount.setError("Enter Amount");
                                    mEdittextAmount.requestFocus();
                                } else {
                                    getConformation(mCode);
                                    // doBBPSRecharge(mEdit1.getText().toString().trim(), mEdittextAmount.getText().toString().trim(), mMode, param1, param2, param3, mEdittextMobileNumber.getText().toString().trim(), mCode, GeoCode);
                                }
                            }
                        }

                    }
                }
            }
        }


        private void doBBPSRecharge(String subscriberID, String amount, final String mMode, String p1, String p2, String p3, String customerMobile, String mOurCode, String geoCode) {
            final ProgressDialog progressDialog = CustomProgressDialog.ctor(BBPSActivity.this);
            progressDialog.show();


            Call<CheckUtilityBIll> call = service.payBBPSBillURL(prefs.getFromPrefs(mContext, "userid", ""),
                    prefs.getFromPrefs(mContext, "password", ""),subscriberID,amount,mMode,param1,param2,param3,customerMobile,mCode,"0", Duedate,BillDate,ConsumerName,Billernumber);
            call.enqueue(new Callback<CheckUtilityBIll>() {
                @Override
                public void onResponse(Call<CheckUtilityBIll> call, Response<CheckUtilityBIll> response) {
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    if (response != null) {

                        if (response.body().getStatus().equals("Success")) {
                            try {
                                ApplicationConstant.DisplayMessageDialog(BBPSActivity.this, response.body().getStatus(), response.body().getRemarks());
                                l1.setVisibility(View.GONE);
                                l2.setVisibility(View.GONE);
                                mAmountHiddenLayout.setVisibility(View.GONE);
                                mEdittextMobileNumber.setVisibility(View.GONE);
                                if(dialog.isShowing())
                                    dialog.dismiss();

                            }catch (Exception e){
                                ApplicationConstant.DisplayMessageDialog(BBPSActivity.this, "Error", "Error occured");

                            }

                        }if (response.body().getStatus().equals("Failure")) {
                            ApplicationConstant.DisplayMessageDialog(BBPSActivity.this, response.body().getStatus(), response.body().getRemarks());
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.GONE);
                            mAmountHiddenLayout.setVisibility(View.GONE);
                            mEdittextMobileNumber.setVisibility(View.GONE);
                            if(dialog.isShowing())
                                dialog.dismiss();

                        } else {
                            mEdit1.setText("");
                            mEdit2.setText("");
                            mEdittextAmount.setText("");

                            if(dialog.isShowing())
                                dialog.dismiss();
                            setViisiblityGone();
                            ApplicationConstant.DisplayMessageDialog(BBPSActivity.this,response.body().getStatus(), response.body().getRemarks());
                        }
                    } else {
                        ApplicationConstant.DisplayMessageDialog(BBPSActivity.this,response.body().getStatus(), response.body().getRemarks());
                    }
                }

                @Override
                public void onFailure(Call<CheckUtilityBIll> call, Throwable t) {
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();
                    Toast.makeText(mContext,"Server error",Toast.LENGTH_SHORT).show();
                    if(dialog.isShowing())
                        dialog.dismiss();
                }
            });






        }

        private void proceedToFetchBill(String mOurCode) {
        if(mOurCode.equals("ER")){
            if (TextUtils.isEmpty(mEdit2.getText().toString().trim())) {
                mEdit2.setError("Enter cycle");
                mEdit2.requestFocus();
            }
            else
            {String cycleNumber = "";
                cycleNumber = mEdit2.getText().toString().trim();
                if (cycleNumber.length() == 1)
                    cycleNumber = "0" + cycleNumber;
                ValidateOperator(mOurCode, mEdit1.getText().toString().trim(),"", cycleNumber);

            }
        }
           else if (mOurCode.equals("EM")) {
                if (TextUtils.isEmpty(mEdit2.getText().toString().trim())) {
                    mEdit2.setError("Enter Bil Unit");
                    mEdit2.requestFocus();
                } else {
                    mEdit2.setError(null);
                    if (TextUtils.isEmpty(mEdit3.getText().toString().trim())) {
                        mEdit3.setError("Enter Cycle");
                        mEdit3.requestFocus();
                    } else {
                        mEdit3.setError(null);
                        String cycleNumber = "";
                        cycleNumber = mEdit3.getText().toString().trim();
                        if (cycleNumber.length() == 1)
                            cycleNumber = "0" + cycleNumber;
                        ValidateOperator(mOurCode, mEdit1.getText().toString().trim(),mEdit2.getText().toString(), cycleNumber);

                    }
                }

            } else if (mOurCode.equals("ET")) {
                if (TextUtils.isEmpty(mEdittextCity.getText().toString().trim())) {
                    mEdittextCity.setError("Enter Cycle");
                    mEdittextCity.requestFocus();
                } else {
                    mEdittextCity.setError(null);
                    ValidateOperator(mOurCode, mEdit1.getText().toString().trim(), "", mEdittextCity.getText().toString().trim());
                }
            } else if (mOurCode.equals("GM")) {
                if (TextUtils.isEmpty(mEditTextGasAccountNumber.getText().toString().trim())) {
                    mEditTextGasAccountNumber.setError("Enter Gas Account Number");
                    mEditTextGasAccountNumber.requestFocus();
                } else {
                    mEditTextGasAccountNumber.setError(null);
                    ValidateOperator(mOurCode, mEdit1.getText().toString().trim(), mEdit2.getText().toString(), mEditTextGasAccountNumber.getText().toString().trim());
                }
            } else {
                if(isUtilityBIllPay){
                        if(!mOurCode.equals("ER"))
                        ValidateOperator(mOurCode, mEdit1.getText().toString().trim(),mEdit2.getText().toString(), mEdit3.getText().toString().trim());

                }

                else
                    getConformation(mOurCode);
            }


        }

        private void getConformation(String mOurCode) {
            dialog = new Dialog(mContext);
            dialog.setTitle("Conform your bill");
            dialog.setContentView(R.layout.displaybill);
            TextView remark = (TextView)dialog.findViewById(R.id.remark);
            TextView name = (TextView)dialog.findViewById(R.id.name);
            TextView mobile = (TextView)dialog.findViewById(R.id.mobile);
            TextView due = (TextView)dialog.findViewById(R.id.duedate);
            TextView price = (TextView)dialog.findViewById(R.id.price);
            TextView customernumber = (TextView)dialog.findViewById(R.id.cn);
            TextView mode = (TextView)dialog.findViewById(R.id.mode);
            Button pay = (Button) dialog.findViewById(R.id.paybill);
            remark.setText("");
            name.setText("");
            mobile.setText(mEdittextMobileNumber.getText().toString());
            due.setText("");
//            ConsumerName = "NA";
//            Billernumber ="NA";
//            BillDate = "NA";

            price.setText(mEdittextAmount.getText().toString());
            customernumber.setText(mEdit1.getText().toString());
            PartialAmount = price.getText().toString();
            if(!PartialAmount.contains("."))
                PartialAmount = price.getText().toString()+".00";
            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                    alert.setMessage("Do you want to pay the bill");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            param1 = mEdit2.getText().toString().trim();
                            param2 = mEdit3.getText().toString().trim();
                            doBBPSRecharge(mEdit1.getText().toString().trim(),PartialAmount, mMode, mEdit2.getText().toString(), mEdit3.getText().toString(), param3, mEdittextMobileNumber.getText().toString().trim(), mCode, GeoCode);
                            dialog.dismiss();
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    Dialog dialog1 = alert.create();
                    dialog1.show();
                }
            });
            dialog.show();



        }






        private void ValidateOperator(String opcode, String subScriberId, String billingUnit, String cycleNumber) {

            service = ApiServiceGenerator.createService(ServiceCallApi.class);
            ApplicationConstant.hideKeypad(BBPSActivity.this);
            final ProgressDialog pDialog = CustomProgressDialog.ctor(BBPSActivity.this);
            pDialog.show();



            Call<CheckUtilityBIll> call =  service.validateBill(prefs.getFromPrefs(mContext, "userid", ""), prefs.getFromPrefs(mContext, "password", ""),
                    opcode, subScriberId, billingUnit, cycleNumber
            );
            call.enqueue(new Callback<CheckUtilityBIll>() {
                @Override
                public void onResponse(Call<CheckUtilityBIll> call, final Response<CheckUtilityBIll> response) {
                    if (pDialog.isShowing())
                        pDialog.dismiss();

                    if (response != null) {
                        if (response.body().getResponseStatus().equals("0")) {
                            Toast.makeText(mContext,response.body().getRemarks(), Toast.LENGTH_LONG).show();
                            mAmountHiddenLayout.setVisibility(View.GONE);


                        } else {
                            isUtilityBillFetched = true;

//                            mBtnRecharge.setText("Pay Bill");
//                            mAmountHiddenLayout.setVisibility(View.VISIBLE);
//                            mEdittextAmount.setFocusableInTouchMode(false);
//                            mEdittextAmount.setText(response.body().getData().getPrice());
                            dialog = new Dialog(mContext);
                            dialog.setTitle("Conform your bill");
                            dialog.setContentView(R.layout.displaybill);
                            TextView remark = (TextView)dialog.findViewById(R.id.remark);
                            TextView name = (TextView)dialog.findViewById(R.id.name);
                            TextView mobile = (TextView)dialog.findViewById(R.id.mobile);
                            TextView due = (TextView)dialog.findViewById(R.id.duedate);
                            final TextView price = (TextView)dialog.findViewById(R.id.price);
                            TextView customernumber = (TextView)dialog.findViewById(R.id.cn);
                            TextView mode = (TextView)dialog.findViewById(R.id.mode);
                            Duedate = response.body().getData().getDuedate();
                            ConsumerName = response.body().getData().getConsumername();
                            Billernumber = response.body().getData().getBillernumber();
                            BillDate = response.body().getData().getBilldate();
                            final EditText partialAmount = (EditText) dialog.findViewById(R.id.etpartialAmount);
                            if(flag)
                                partialAmount.setVisibility(View.VISIBLE);

                            Button pay = (Button) dialog.findViewById(R.id.paybill);
                            remark.setText(response.body().getRemarks());
                            name.setText(response.body().getData().getConsumername());
                            mobile.setText(mEdittextMobileNumber.getText().toString());
                            due.setText(response.body().getData().getDuedate().toString());
                            price.setText(String.valueOf(response.body().getData().getPrice()));
                            customernumber.setText(mEdit1.getText().toString());
                            mode.setText(mMode);
                            pay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(partialAmount.getVisibility()==View.VISIBLE){
                                        if(partialAmount.getText().toString().isEmpty()){
                                            partialAmount.setError("Please enter amount");

                                        }

                                        else
                                        {
                                            partialAmount.setError(null);
                                            if(!partialAmount.getText().toString().contains("."))
                                                PartialAmount = partialAmount.getText().toString()+".00";
                                            else
                                                PartialAmount = partialAmount.getText().toString();
                                        }
                                        if(partialAmount.getVisibility()==View.GONE){
                                            PartialAmount =""+response.body().getData().getPrice();
                                        }
                                    }
                                    else
                                        PartialAmount = price.getText().toString();

                                    AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                                    alert.setMessage("Do you want to pay the bill");
                                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            param1 = mEdit2.getText().toString().trim();
                                            param2 = mEdit3.getText().toString().trim();
                                            doBBPSRecharge(mEdit1.getText().toString().trim(),PartialAmount, mMode, param1, param2, param3, mEdittextMobileNumber.getText().toString().trim(), mCode, GeoCode);
                                            dialog.dismiss();
                                        }
                                    });
                                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    Dialog dialog1 = alert.create();
                                    dialog1.show();
                                }
                            });
                            dialog.show();

                        }
                    } else {
                        Toast.makeText(mContext, "An Error Occured", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<CheckUtilityBIll> call, Throwable t) {
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();
                    Toast.makeText(mContext, "Server not found", Toast.LENGTH_LONG).show();

                }
            });


        }



    }