package com.example.webplat.amoldesigning.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.bank_details.BankData;
import com.example.webplat.amoldesigning.pojo.bank_details.BankDetails;
import com.example.webplat.amoldesigning.pojo.recharge_response.RechargeResponse;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment_Request extends MainActivity implements View.OnTouchListener, View.OnClickListener {
    Context mContext;
    EditText edAmount, edPaymnetMode, edPaymentDate, edCheckNumber, edCheckDate, edRemarks, edDepositBank;
    Button btnRequest;
    PrefUtils prefs;
    String strBankCode, strPaymentTypeCode;
    String[] paymentTypeCode;
    String[] paymentType;
    ServiceCallApi callApi;
//    RelativeLayout Rellayout_checkDD_Date;
//    RelativeLayout Rellayout_checkDD;

    private android.support.design.widget.TextInputLayout Rellayout_checkDD;
    private android.support.design.widget.TextInputLayout Rellayout_checkDD_Date;

    Calendar myCalendar = Calendar.getInstance();
    Calendar mySdate = Calendar.getInstance();
    Calendar myEdate = Calendar.getInstance();
    List<BankData> bankDataArrayList = new ArrayList<BankData>();
    DatePickerDialog.OnDateSetListener myDatePickerDialogChequeDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePickerDialog view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            mySdate.set(Calendar.YEAR, year);
            mySdate.set(Calendar.MONTH, monthOfYear);
            mySdate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(edCheckDate, mySdate);
        }
    };

    DatePickerDialog.OnDateSetListener myDatePickerDialogPaymentDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePickerDialog view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myEdate.set(Calendar.YEAR, year);
            myEdate.set(Calendar.MONTH, monthOfYear);
            myEdate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(edPaymentDate, myEdate);
        }
    };
    private RelativeLayout mTopLayout;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.payment_request);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
//        setSupportActionBar(toolbar);
//        toolbar.setTitleTextColor(0xFFFFFFFF);
//        toolbar.setTitle("Payment Request");
//
//        toolbar.setNavigationIcon(R.drawable.back);
//
//        if (toolbar != null) {
//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }

//        InitilizeControl();
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.payment_request, frameLayout);
        InitilizeControl();
    }

    private void InitilizeControl() {
        mContext = Payment_Request.this;
        mTopLayout = (RelativeLayout) findViewById(R.id.topLayout);
        edAmount = (EditText) findViewById(R.id.editText_amount);
        edPaymentDate = (EditText) findViewById(R.id.editText_paymentDate);
        edPaymnetMode = (EditText) findViewById(R.id.editText_paymentMode);
        edCheckNumber = (EditText) findViewById(R.id.editText_chequeNumber);
        edCheckDate = (EditText) findViewById(R.id.editText_chequeDate);
        edDepositBank = (EditText) findViewById(R.id.editText_depositBank);
        edRemarks = (EditText) findViewById(R.id.editText_remarks);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Payment Request");

        Rellayout_checkDD_Date = (android.support.design.widget.TextInputLayout) findViewById(R.id.Rellayout_checkDD_Date);
        Rellayout_checkDD = (android.support.design.widget.TextInputLayout) findViewById(R.id.Rellayout_checkDD);

        btnRequest = (Button) findViewById(R.id.paymnetRequest);
        btnRequest.setOnClickListener(this);

        edPaymnetMode.setOnTouchListener(this);
        edPaymentDate.setOnTouchListener(this);
        //edCheckNumber.setOnTouchListener(this);
        edCheckDate.setOnTouchListener(this);
        edDepositBank.setOnTouchListener(this);

        paymentType = getResources().getStringArray(R.array.banks_payment_request_payment_type);
        paymentTypeCode = getResources().getStringArray(R.array.banks_payment_request_Payment_type_code);
        getBankList();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.editText_paymentMode:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        edPaymnetMode.setFocusable(true);
                        edPaymnetMode.requestFocus();
                        break;
                    case MotionEvent.ACTION_UP:
                        DisplayPaymentType(paymentType, paymentTypeCode);
                    default:
                        break;
                }

                break;
            case R.id.editText_depositBank:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        edDepositBank.setFocusable(true);
                        edDepositBank.requestFocus();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (bankDataArrayList != null && bankDataArrayList.size() > 0) {
                            DisplayBankList(bankDataArrayList);
                        } else {
                            ApplicationConstant.displayToastMessage(mContext, "Sorry we are enable to get bank details try again");
                        }

                    default:
                        break;
                }
                break;
            case R.id.editText_paymentDate:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        edPaymentDate.setFocusable(true);
                        edPaymentDate.requestFocus();
                        break;
                    case MotionEvent.ACTION_UP:
                        DatePickerDialog dpd = DatePickerDialog.newInstance(myDatePickerDialogPaymentDate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
                        );
                        dpd.show(getFragmentManager(), "Datepickerdialog");
                        dpd.setAccentColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            dpd.setMaxDate(myCalendar);
                        }
                    default:
                        break;
                }
                break;
            case R.id.editText_chequeDate:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        edCheckDate.setFocusable(true);
                        edCheckDate.requestFocus();
                        break;
                    case MotionEvent.ACTION_UP:
                        DatePickerDialog dpd = DatePickerDialog.newInstance(myDatePickerDialogChequeDate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
                        );
                        dpd.show(getFragmentManager(), "Datepickerdialog");
                        dpd.setAccentColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            dpd.setMaxDate(myCalendar);
                        }
                    default:
                        break;
                }
                break;
        }
        return true;
    }

    private void DisplayBankList(final List<BankData> bankDataArrayList) {
        ArrayAdapter<BankData> adapter = new ArrayAdapter<BankData>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, bankDataArrayList);

        final Dialog dialog = new Dialog(Payment_Request.this);
        dialog.setContentView(R.layout.material_list);
        dialog.setCancelable(false);
        dialog.setTitle("Select Bank");
        final ListView listView = (ListView) dialog.findViewById(R.id.list);

        dialog.show();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edDepositBank.setText(bankDataArrayList.get(position).getAccountName()
                        + " ( " + bankDataArrayList.get(position).getBranch()


                        + " )");

                strBankCode = bankDataArrayList.get(position).getId();
                dialog.cancel();
            }
        });

        handleBackPressed(dialog);
    }

    private void DisplayPaymentType(String[] paymentType, final String[] paymentTypeCode) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, paymentType);
        final Dialog dialog = new Dialog(Payment_Request.this);
        dialog.setContentView(R.layout.material_list);
        dialog.setCancelable(false);
        dialog.setTitle("Select Bank");
        final ListView listView = (ListView) dialog.findViewById(R.id.list);

        dialog.show();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edPaymnetMode.setText((String) listView.getItemAtPosition(position));

                if (listView.getItemAtPosition(position).equals("Cheque/DD")) {
//                    edCheckDate.setVisibility(View.VISIBLE);
//                    edCheckNumber.setVisibility(View.VISIBLE);
                    Rellayout_checkDD_Date.setVisibility(View.VISIBLE);
                    Rellayout_checkDD.setVisibility(View.VISIBLE);


                } else {
//                    edCheckDate.setVisibility(View.GONE);
//                    edCheckNumber.setVisibility(View.GONE);
                    Rellayout_checkDD_Date.setVisibility(View.GONE);
                    Rellayout_checkDD.setVisibility(View.GONE);

                }

                strPaymentTypeCode = paymentTypeCode[position];
                dialog.cancel();
            }
        });
        handleBackPressed(dialog);
    }

    private void handleBackPressed(final Dialog mDialog) {
        mDialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    mDialog.dismiss();
                    return true;
                }
                return false;
            }
        });
    }

    private void getBankList() {
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(Payment_Request.this);
        progressDialog.show();
        ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
        callApi = apiServiceGenerator.createService(ServiceCallApi.class);
        final Call<BankDetails> objbanklist = callApi.getbankList(
                prefs.getFromPrefs(mContext, "userid", ""),
                prefs.getFromPrefs(mContext, "password", ""));

        objbanklist.enqueue(new Callback<BankDetails>() {
            @Override
            public void onResponse(Call<BankDetails> call, Response<BankDetails> response) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                BankDetails banklistobj = response.body();
                if (banklistobj.getStatus().equals("Success")) {
                    bankDataArrayList = banklistobj.getData();
                }
            }

            @Override
            public void onFailure(Call<BankDetails> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paymnetRequest:
//                if (edAmount.getText().toString().trim().length() > 0) {

                if (TextUtils.isEmpty(edAmount.getText().toString().trim())) {
                    edAmount.setError("Enter Amount");
                } else {
                    edAmount.setError(null);

                    if (TextUtils.isEmpty(edPaymnetMode.getText().toString().trim())) {
                        edPaymnetMode.setError("Select Payment Mode");
                    } else {
                        edPaymnetMode.setError(null);

                        if (TextUtils.isEmpty(edPaymentDate.getText().toString().trim())) {
                            edPaymentDate.setError("Select Payment Date");
                        } else {
                            edPaymentDate.setError(null);
                            if (Rellayout_checkDD_Date.getVisibility() == View.VISIBLE) {
                                if (TextUtils.isEmpty(edCheckNumber.getText().toString().trim())) {
                                    edCheckNumber.setError("Enter Check Number ");
                                } else {
                                    edCheckNumber.setError(null);

                                    if (TextUtils.isEmpty(edCheckDate.getText().toString().trim())) {
                                        edCheckDate.setError("Select Cheque Date ");
                                    } else {
                                        edCheckDate.setError(null);

                                        if (TextUtils.isEmpty(edDepositBank.getText().toString().trim())) {
                                            edDepositBank.setError("Select Bank ");
                                        } else {
                                            edDepositBank.setError(null);
                                            if (TextUtils.isEmpty(edRemarks.getText().toString().trim())) {
                                                edRemarks.setError("Enter Remark");
                                            } else {
                                                edRemarks.setError(null);

                                                ApplicationConstant.hideKeypad(Payment_Request.this);
                                                long randomNo = new Date().getTime();

                                                makePaymentRequest(
                                                        strBankCode,
                                                        edAmount.getText().toString().trim(),
                                                        edPaymnetMode.getText().toString().trim(),
                                                        "Pay" + randomNo,
                                                        edPaymentDate.getText().toString().trim(),
                                                        edCheckNumber.getText().toString().trim(),
                                                        edCheckDate.getText().toString().trim(),
                                                        edRemarks.getText().toString().trim());
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (TextUtils.isEmpty(edDepositBank.getText().toString().trim())) {
                                    edDepositBank.setError("Select Bank ");
                                } else {
                                    edDepositBank.setError(null);
                                    if (TextUtils.isEmpty(edRemarks.getText().toString().trim())) {
                                        edRemarks.setError("Enter Remark");
                                    } else {
                                        edRemarks.setError(null);

                                        ApplicationConstant.hideKeypad(Payment_Request.this);
                                        long randomNo = new Date().getTime();

                                        makePaymentRequest(strBankCode,
                                                edAmount.getText().toString().trim(),
                                                edPaymnetMode.getText().toString().trim(),
                                                "Pay" + randomNo,
                                                edPaymentDate.getText().toString().trim(),
                                                "",
                                                "",
                                                edRemarks.getText().toString().trim());

                                    }
                                }
                            }
                        }
                        break;
                    }
                }
        }
    }

    private void makePaymentRequest(final String bankCode, String amount,
                                    String paymentTypeCode, String refId,
                                    String paymentDate,
                                    String chequeNumber,
                                    String chequeDate, String remarks) {

        final ProgressDialog pDialog = CustomProgressDialog.ctor(this);
        pDialog.show();
        ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
        callApi = apiServiceGenerator.createService(ServiceCallApi.class);
        final Call<RechargeResponse> rechargeress = callApi.getPaymentRequest(
                prefs.getFromPrefs(mContext, "userid", ""),
                prefs.getFromPrefs(mContext, "password", ""),
                bankCode, amount, paymentTypeCode,
                refId, paymentDate, chequeNumber, chequeDate, remarks);
        rechargeress.enqueue(new Callback<RechargeResponse>() {
            @Override
            public void onResponse(Call<RechargeResponse> call, Response<RechargeResponse> response) {
                if (pDialog != null && pDialog.isShowing())
                    pDialog.dismiss();
                RechargeResponse rechargeResponse = response.body();

                if (rechargeResponse.getStatus().equals("Success")) {
                    ApplicationConstant.DisplayMessageDialog(Payment_Request.this,
                            rechargeResponse.getStatus(),
                            rechargeResponse.getRemarks());
                    edAmount.setText("");
                    edCheckDate.setText("");
                    edCheckNumber.setText("");
                    edPaymentDate.setText("");
                    edRemarks.setText("");
                    edDepositBank.setText("");
                    edPaymnetMode.setText("");
                    strBankCode = "";

                } else {

                    ApplicationConstant.DisplayMessageDialog(Payment_Request.this,
                            rechargeResponse.getStatus(),
                            rechargeResponse.getRemarks());
                }
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<RechargeResponse> call, Throwable t) {
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }
        });
    }

    private void updateLabel(EditText ed_date, Calendar myNewCalendar) {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ed_date.setText(sdf.format(myNewCalendar.getTime()));
    }
}