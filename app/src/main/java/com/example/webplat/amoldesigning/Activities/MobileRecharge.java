package com.example.webplat.amoldesigning.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.DBHelper;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.GetOperatorByNum.GetOperatorByNum;
import com.example.webplat.amoldesigning.pojo.operator.OperatorList;
import com.example.webplat.amoldesigning.pojo.recharge_response.RechargeResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileRecharge extends AppCompatActivity implements View.OnClickListener {

    EditText mEdittxtnumber;
    private Context mContext = this;
    String OPID;
    ServiceCallApi serviceCallApi;
    PrefUtils prefs;
    String circle = "0";
    String ourcode = "";

    private static final int OPERATORREQUESTCODE = 1001;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    GetOperatorByNum getOperatorByNum;
    private ImageView mServiceImage;
    private TextView mServiceName;
    private RadioGroup mRadioGroupOperatorType;
    private RadioButton mRadioButtonPrepaid;
    private RadioButton mRadioButtonPostpaid;
    private android.support.design.widget.TextInputLayout mMobilenumberTextInputLayout;
    private EditText mEdittextNumber;
    private EditText mEdittextAmount;
    private EditText mEdittextOperator;
    private TextView mTxtBrowsePlans;
    private LinearLayout mPostpaidHiddenLayout1;
    private EditText mEdittextAcNumber;
    private LinearLayout mPostpaidHiddenLayout2;
    private Spinner mSpinnerAccountType;
    private Button mMbtnRecharge;

    private int SELECTOPERATOR = 1101;
    private int PLANREQUESTCODE = 200;
    private int CONTACTPICK = 301;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_service);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("Recharge");

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
        mEdittxtnumber = (EditText) findViewById(R.id.EdittextNumber);

        mServiceImage = (ImageView) findViewById(R.id.serviceImage);
        mServiceName = (TextView) findViewById(R.id.serviceName);
        mRadioGroupOperatorType = (RadioGroup) findViewById(R.id.radioGroupOperatorType);
        mRadioButtonPrepaid = (RadioButton) findViewById(R.id.radioButtonPrepaid);
        mRadioButtonPostpaid = (RadioButton) findViewById(R.id.radioButtonPostpaid);
        mMobilenumberTextInputLayout = (android.support.design.widget.TextInputLayout) findViewById(R.id.mobilenumberTextInputLayout);
        mEdittextOperator = (EditText) findViewById(R.id.edittextOperator);
        mEdittextAmount = (EditText) findViewById(R.id.EdittextAmount);
        mTxtBrowsePlans = (TextView) findViewById(R.id.txtBrowsePlans);
        mPostpaidHiddenLayout1 = (LinearLayout) findViewById(R.id.postpaidHiddenLayout1);
        mEdittextAcNumber = (EditText) findViewById(R.id.EdittextAcNumber);
        mPostpaidHiddenLayout2 = (LinearLayout) findViewById(R.id.postpaidHiddenLayout2);
        mSpinnerAccountType = (Spinner) findViewById(R.id.spinnerAccountType);
        mMbtnRecharge = (Button) findViewById(R.id.mbtnRecharge);

        mMbtnRecharge.setOnClickListener(this);
        mEdittextOperator.setOnClickListener(this);
        mTxtBrowsePlans.setOnClickListener(this);


        mEdittxtnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEdittxtnumber.getText().toString().length() == 10) {
                    CallWebService(mEdittxtnumber.getText().toString().trim().substring(0, 5));
                    mEdittextAmount.requestFocus();

                }
            }
        });


        mEdittxtnumber.setOnTouchListener(new View.OnTouchListener() {
                                              @Override
                                              public boolean onTouch(View v, MotionEvent event) {
                                                  final int DRAWABLE_RIGHT = 2;
                                                  if (event.getAction() == MotionEvent.ACTION_UP) {
                                                      if (event.getRawX() >= (mEdittxtnumber.getRight() - mEdittxtnumber.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                                          picContact();
                                                          return true;
                                                      }
                                                  }

                                                  return false;
                                              }
                                          }

        );
        mRadioGroupOperatorType.setVisibility(View.VISIBLE);
        mRadioButtonPrepaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    mTxtBrowsePlans.setVisibility(View.VISIBLE);
                    mMbtnRecharge.setText("Proceed to recharge");

                } else {
                    mTxtBrowsePlans.setVisibility(View.GONE);
                    mMbtnRecharge.setText("Proceed to Pay BillS");

                }
            }
        });
        mRadioButtonPostpaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {


                } else {

                }
            }
        });
    }

    private void CallWebService(String substring) {
        progressDialog = CustomProgressDialog.ctor(MobileRecharge.this);
        progressDialog.show();
        serviceCallApi = ApiServiceGenerator.createService(ServiceCallApi.class);
        Call<GetOperatorByNum> result = serviceCallApi.getOperatorByNumber(substring);
        result.enqueue(new Callback<GetOperatorByNum>() {
            @Override
            public void onResponse(Call<GetOperatorByNum> call, Response<GetOperatorByNum> response) {
                try {
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();
                    getOperatorByNum = response.body();
                    if (response != null) {
                        if(isExist(response.body().getResult().getData().getOperators())) {
                            mEdittextOperator.setText(getOperatorByNum.getResult().getData().getOperators());
                            circle = "0";
                            ourcode = getOperatorByNum.getResult().getData().getOurCode();
                        }


                    }
                }catch (Exception e){
                    Toast.makeText(mContext,"Please enter data properly",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetOperatorByNum> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });
    }

    private Boolean isExist(String operators) {
        Boolean flag = false;
     DBHelper   dbHelper = new DBHelper(mContext);
        List<OperatorList> operatorDataArrayList = new ArrayList<>();

        if(mRadioButtonPrepaid.isChecked()){
            operatorDataArrayList.addAll(operatorDataArrayList = dbHelper.fetchAllOperatorByType("Mobile"));

        }
        else
            operatorDataArrayList.addAll(operatorDataArrayList = dbHelper.fetchAllOperatorByType("Postpaid"));

        for(int i =0; i<operatorDataArrayList.size();i++){
                if(operatorDataArrayList.get(i).getOpName().trim().toLowerCase().equals(operators.trim().toLowerCase())){
                    flag = true;
                }
            }

            return  flag;

    }

    private void picContact() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasWriteContactsPermission = mContext.checkSelfPermission(Manifest.permission.READ_CONTACTS);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                        REQUEST_CODE_ASK_PERMISSIONS);
                //ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_CONTACTS},REQUEST_CODE_ASK_PERMISSIONS);
                return;
            } else {
                Intent it = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(it, CONTACTPICK);
            }
        } else {
            Intent it = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(it, CONTACTPICK);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPERATORREQUESTCODE && resultCode == RESULT_OK) {

         ourcode =   OPID = data.getStringExtra(Constant.SelectedOperatorID);
            mEdittextOperator.setText(data.getStringExtra(Constant.SelectedOperator));


        }
        if (requestCode == PLANREQUESTCODE) {
            if (resultCode == PLANREQUESTCODE) {
                mEdittextAmount.setText(String.valueOf(data.getStringExtra("Amount")));
            }
        }
        if (requestCode == CONTACTPICK) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contactData = data.getData();
                Cursor c = mContext.getContentResolver().query(contactData, null, null, null, null);
                if (c.moveToFirst()) {
                    String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                    String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    if (hasPhone.equalsIgnoreCase("1")) {
                        Cursor phones = mContext.getContentResolver().query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                null, null);
                        phones.moveToFirst();
                        try {
                            String phn_no = phones.getString(phones.getColumnIndex("data1")).trim().replace(" ", "");
                            int len = phn_no.length() % 10;
                            String phn_no1 = phn_no.substring(len, phn_no.length());
                            mEdittxtnumber.setText(phn_no1);
//                            if (mRadioButtonPrepaid.isChecked()) {
//                                CallWebService(mEdittextNumber.getText().toString().trim().substring(0, 5));
//                            }
                        } catch (Exception e) {
                        }


                    }
                }

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edittextOperator:
                int id = mRadioGroupOperatorType.getCheckedRadioButtonId();
                String serviceID = "";
                if (id == R.id.radioButtonPrepaid) {
                    serviceID = ApplicationConstant.MOBILESERVICEID;
                } else {
                    serviceID = ApplicationConstant.POSTPAIDSERVICEID;
                }

                Intent intent = new Intent(mContext, OperatorActivity.class);
                intent.putExtra(Constant.OPERATORTYPE, serviceID);
                startActivityForResult(intent, OPERATORREQUESTCODE);

                break;

            case R.id.txtBrowsePlans:
                if (mEdittxtnumber.getText().toString().trim().length() != 0 && mEdittextOperator.getText().toString().trim().length() != 0) {
                    Intent intent1 = new Intent(mContext, RechargePlansActivity.class);
                    intent1.putExtra("circleName", getOperatorByNum.getResult().getData().getCircle());
                    intent1.putExtra("operatorName", getOperatorByNum.getResult().getData().getOperators());
                    startActivityForResult(intent1, PLANREQUESTCODE);
                } else {
                    Toast.makeText(mContext, "Invalid mobile number or operator.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.mbtnRecharge:
                if (TextUtils.isEmpty(mEdittxtnumber.getText().toString().trim())) {
                    mEdittxtnumber.setError("Enter number");
                } else {
                    mEdittxtnumber.setError(null);
                    if (TextUtils.isEmpty(mEdittextAmount.getText().toString().trim())) {
                        mEdittextAmount.setError("Enter amount");
                    } else {
                        mEdittextAmount.setError(null);
                        mobileRecharge(
                                mEdittextAmount.getText().toString().trim(),
                                mEdittxtnumber.getText().toString().trim(),
                                ourcode,
                                circle);
                }
                        break;
                }
        }
    }

    private void mobileRecharge(String amount,
                                String mobile_num,
                                String ourcode,
                                String circle) {

        final ProgressDialog pDialog = CustomProgressDialog.ctor(this);
        pDialog.show();
        ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
        serviceCallApi = apiServiceGenerator.createService(ServiceCallApi.class);
        final Call<RechargeResponse> rechargeress = serviceCallApi.doRecharge(
                prefs.getFromPrefs(mContext, "userid", ""),
                prefs.getFromPrefs(mContext, "password", ""),
                amount,
                mobile_num,
                ourcode,
                circle);
        rechargeress.enqueue(new Callback<RechargeResponse>() {
            @Override
            public void onResponse(Call<RechargeResponse> call, Response<RechargeResponse> response) {
                if (pDialog != null && pDialog.isShowing())
                    pDialog.dismiss();
                RechargeResponse rechargeResponse = response.body();

                if (rechargeResponse.getStatus().equals("Success")) {
                    ApplicationConstant.DisplayMessageDialog(MobileRecharge.this,
                            "",
                            rechargeResponse.getData());
                    mEdittextAmount.setText("");
                    mEdittxtnumber.setText("");

                }
                if (rechargeResponse.getStatus().equals("Failure")) {
                    ApplicationConstant.DisplayMessageDialog(MobileRecharge.this,
                            "",
                            rechargeResponse.getData());
                    mEdittextAmount.setText("");
                    mEdittxtnumber.setText("");

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
}
