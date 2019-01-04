package com.example.webplat.amoldesigning.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.recharge_response.RechargeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DTHRecharge extends AppCompatActivity {

    private FrameLayout mFrameLayout;
    private ImageView mServiceImage;
    private TextView mServiceName;
    private RadioGroup mRadioGroupOperatorType;
    private RadioButton mRadioButtonPrepaid;
    private RadioButton mRadioButtonPostpaid;
    private android.support.design.widget.TextInputLayout mMobilenumberTextInputLayout;
    private EditText mEdittextNumber;
    private EditText mEdittextOperator;
    private EditText mEdittextAmount;
    private TextView mTxtBrowsePlans;
    private LinearLayout mPostpaidHiddenLayout1;
    private EditText mEdittextAcNumber;
    private LinearLayout mPostpaidHiddenLayout2;
    private Spinner mSpinnerAccountType;
    private Button mMbtnRecharge;
    PrefUtils prefs;
    private Context mContext=this;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private int CONTACTPICK = 301;
    private int OPERATORREQUESTCODE=303;
    private String OPID="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_service);
        bindViews();
    }

    private void bindViews() {
        mFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        mServiceImage = (ImageView) findViewById(R.id.serviceImage);
        mServiceName = (TextView) findViewById(R.id.serviceName);
        mRadioGroupOperatorType = (RadioGroup) findViewById(R.id.radioGroupOperatorType);
        mRadioButtonPrepaid = (RadioButton) findViewById(R.id.radioButtonPrepaid);
        mRadioButtonPostpaid = (RadioButton) findViewById(R.id.radioButtonPostpaid);
        mMobilenumberTextInputLayout = (android.support.design.widget.TextInputLayout) findViewById(R.id.mobilenumberTextInputLayout);
        mEdittextNumber = (EditText) findViewById(R.id.EdittextNumber);
        mEdittextOperator = (EditText) findViewById(R.id.edittextOperator);
        mEdittextAmount = (EditText) findViewById(R.id.EdittextAmount);
        mTxtBrowsePlans = (TextView) findViewById(R.id.txtBrowsePlans);
        mPostpaidHiddenLayout1 = (LinearLayout) findViewById(R.id.postpaidHiddenLayout1);
        mEdittextAcNumber = (EditText) findViewById(R.id.EdittextAcNumber);
        mPostpaidHiddenLayout2 = (LinearLayout) findViewById(R.id.postpaidHiddenLayout2);
        mSpinnerAccountType = (Spinner) findViewById(R.id.spinnerAccountType);
        mMbtnRecharge = (Button) findViewById(R.id.mbtnRecharge);

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

        mRadioGroupOperatorType.setVisibility(View.GONE);
        mServiceName.setText("DTH RECHARGE");
        mEdittextOperator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceID = "";

                serviceID = ApplicationConstant.DTHSERVICEID;


                Intent intent = new Intent(mContext, OperatorActivity.class);
                intent.putExtra(Constant.OPERATORTYPE, serviceID);
                startActivityForResult(intent, OPERATORREQUESTCODE);

            }
        });
        mEdittextNumber.setOnTouchListener(new View.OnTouchListener() {
                                               @Override
                                               public boolean onTouch(View v, MotionEvent event) {
                                                   final int DRAWABLE_RIGHT = 2;
                                                   if (event.getAction() == MotionEvent.ACTION_UP) {
                                                       if (event.getRawX() >= (mEdittextNumber.getRight() - mEdittextNumber.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                                           picContact();
                                                           return true;
                                                       }
                                                   }

                                                   return false;
                                               }
                                           }

        );

        mTxtBrowsePlans.setVisibility(View.GONE);

        mMbtnRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEdittextNumber.getText().toString().trim())) {
                    mEdittextNumber.setError("Enter number");
                } else {
                    mEdittextNumber.setError(null);
                    if (TextUtils.isEmpty(mEdittextAmount.getText().toString().trim())) {
                        mEdittextAmount.setError("Enter amount");
                    } else {
                        mEdittextAmount.setError(null);
                        mobileRecharge(
                                mEdittextAmount.getText().toString().trim(),
                                mEdittextNumber.getText().toString().trim(),
                                OPID,
                                "");
                    }
                }
            }
        });

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
    private void mobileRecharge(String amount,
                                String mobile_num,
                                String ourcode,
                                String circle) {

        final ProgressDialog pDialog = CustomProgressDialog.ctor(this);
        pDialog.show();
        ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
      ServiceCallApi  serviceCallApi = apiServiceGenerator.createService(ServiceCallApi.class);
        final Call<RechargeResponse> rechargeress = serviceCallApi.doRecharge(
                prefs.getFromPrefs(mContext, "userid", ""),
                prefs.getFromPrefs(mContext, "password", ""),
                amount,
                mobile_num,
               OPID,
                "0");
        rechargeress.enqueue(new Callback<RechargeResponse>() {
            @Override
            public void onResponse(Call<RechargeResponse> call, Response<RechargeResponse> response) {
                if (pDialog != null && pDialog.isShowing())
                    pDialog.dismiss();
                RechargeResponse rechargeResponse = response.body();

                if (rechargeResponse.getStatus().equals("Success")) {
                    ApplicationConstant.DisplayMessageDialog(DTHRecharge.this,
                            "",
                            rechargeResponse.getData());
                    mEdittextAmount.setText("");
                    mEdittextNumber.setText("");

                } else {

                    ApplicationConstant.DisplayMessageDialog(DTHRecharge.this,
                            "",
                            rechargeResponse.getData());
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPERATORREQUESTCODE && resultCode == RESULT_OK) {

            OPID = data.getStringExtra(Constant.SelectedOperatorID);
            mEdittextOperator.setText(data.getStringExtra(Constant.SelectedOperator));


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
                            mEdittextNumber.setText(phn_no1);
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
}
