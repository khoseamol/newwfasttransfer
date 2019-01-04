package com.example.webplat.amoldesigning.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;


import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.Utils.NDSpinner;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.operator.OperatorListServer;
import com.example.webplat.amoldesigning.pojo.recharge_response.RechargeResponse;
import com.example.webplat.amoldesigning.pojo.scheme_add_user.Datum;
import com.example.webplat.amoldesigning.pojo.scheme_add_user.SchemeData;
import com.example.webplat.amoldesigning.pojo.scheme_add_user.Schemes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 21/12/2015.
 */
public class AddUsertoService extends Fragment implements View.OnClickListener {

    private EditText mEditTextName;
    private EditText mEditTextMobileNumber;
    private EditText mEditTextFarmName;
    private EditText mEditTextPanNumber;
    private EditText mEditTextAddress;
    private Button mBtnAddUser;
    private EditText mEditTextOwnerName;
    Context mContext=getActivity();
    private ScrollView mTopLayout;
    private NDSpinner mSpinnerScheme;
    private NDSpinner mSpinnerUserType;
    private boolean isFirstTime = false;
    private List<Datum> schemeDataArrayList = new ArrayList<Datum>();
    List<SchemeData> schemes;
    ServiceCallApi serviceCallApi;
    PrefUtils prefs;
    final String pan_pattern = "(([A-Za-z]{5})([0-9]{4})([a-zA-Z]))";
    Pattern r;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.add_user, container, false);
        InitilizeControl(rootView);
        return rootView;
    }

    private void InitilizeControl(View view) {
        mContext = getActivity();
        mTopLayout = (ScrollView)view. findViewById(R.id.topLayout);
        mEditTextName = (EditText) view.findViewById(R.id.editTextName);
        mEditTextMobileNumber = (EditText)view. findViewById(R.id.editTextMobileNumber);
        mEditTextFarmName = (EditText) view.findViewById(R.id.editTextFarmName);
        mEditTextPanNumber = (EditText)view. findViewById(R.id.editTextPanNumber);
        mEditTextAddress = (EditText)view. findViewById(R.id.editTextAddress);
        mEditTextOwnerName = (EditText)view. findViewById(R.id.editTextOwnerName);
        mBtnAddUser = (Button) view.findViewById(R.id.btnAddUser);

        mSpinnerScheme = (NDSpinner)view.findViewById(R.id.spinnerScheme);
        mSpinnerUserType = (NDSpinner) view.findViewById(R.id.spinnerUserType);
        InitiizeSpiner();
        GetUserScheme();
        mBtnAddUser.setOnClickListener(this);
        r = Pattern.compile(pan_pattern);

    }

    private void GetUserScheme() {
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(getActivity());
        progressDialog.show();

        serviceCallApi = ApiServiceGenerator.createService(ServiceCallApi.class);
        Call<Schemes> result = serviceCallApi.getUserScheme(prefs.getFromPrefs(mContext,"userid",""),prefs.getFromPrefs(mContext,"password",""));

        result.enqueue(new Callback<Schemes>() {
            @Override
            public void onResponse(Call<Schemes> call, Response<Schemes> response) {
                Schemes schemes = response.body();
                if (schemes.getStatus().equals("Success")) {
                            schemeDataArrayList = schemes.getData();
                            InitiizeSpiner();

                        }

                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }

            @Override
            public void onFailure(Call<Schemes> call, Throwable t) {

            }

        });

    }

    private void InitiizeSpiner() {
        ArrayAdapter<String> adapter = null;
        mContext = getActivity();
        if (prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserType, "").equalsIgnoreCase("ZBP"))
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mContext.getResources().getStringArray(R.array.ZBP));
        if (prefs.getFromPrefs(mContext,  Constant.USERDETAILS.UserType, "").equalsIgnoreCase("AD"))
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mContext.getResources().getStringArray(R.array.AD));
        if (prefs.getFromPrefs(mContext,  Constant.USERDETAILS.UserType, "").equalsIgnoreCase("MD"))
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mContext.getResources().getStringArray(R.array.MD));

        if (adapter.getCount() > 0) {
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            mSpinnerUserType.setAdapter(adapter);
        }

        mSpinnerScheme.setVisibility(View.GONE);
        mSpinnerUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (isFirstTime) {
                    if(schemes!=null && schemes.size()>=0) {
                        schemes.clear();
                    }
                    mSpinnerScheme.setVisibility(View.VISIBLE);
                    schemes = getScheme(schemeDataArrayList, mSpinnerUserType.getSelectedItem().toString().trim());

                    ArrayAdapter<SchemeData> schemeAdapter = new ArrayAdapter<SchemeData>(getActivity(), android.R.layout.simple_spinner_item, schemes);
                    schemeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinnerScheme.setAdapter(schemeAdapter);

//                }
//                isFirstTime = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerScheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static List<SchemeData> getScheme(List<Datum> schemeDataArrayList, String userType) {
        List<SchemeData> item = new ArrayList<>();
        for (int i = 0; i < schemeDataArrayList.size(); i++) {
            Datum items = schemeDataArrayList.get(i);
            String strSchemeName = items.getSchemeType();
            if (strSchemeName.equalsIgnoreCase(userType)) {
                SchemeData schemeData = new SchemeData();
                schemeData.setId(items.getId());
                schemeData.setAmount(items.getAmount());
                schemeData.setSchemeName(items.getSchemeName());
                schemeData.setSchemeType(items.getSchemeType());
                item.add(schemeData);

            }
        }
        return item;
    }

    private boolean regex_matcher(Pattern pattern, String string) {
        Matcher m = pattern.matcher(string);
        return m.find() && (m.group(0) != null);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddUser:
                if (TextUtils.isEmpty(mEditTextName.getText().toString().trim())) {
                    mEditTextName.setError("Enter Name");
                } else {
                    mEditTextName.setError(null);
                    if (TextUtils.isEmpty(mEditTextOwnerName.getText().toString().trim())) {
                        mEditTextOwnerName.setError("Enter Owner Name");
                    } else {
                        mEditTextOwnerName.setError(null);

                        if (TextUtils.isEmpty(mEditTextMobileNumber.getText().toString().trim())) {
                            mEditTextMobileNumber.setError("Enter MobileRechargeMainActivity Number");
                        } else {
                            mEditTextMobileNumber.setError(null);
                            if (TextUtils.isEmpty(mEditTextFarmName.getText().toString().trim())) {
                                mEditTextFarmName.setError("Enter Farm Name");
                            } else {
                                mEditTextFarmName.setError(null);
                                if (TextUtils.isEmpty(mEditTextPanNumber.getText().toString().trim()) || !regex_matcher(r, mEditTextPanNumber.getText().toString())) {
                                    mEditTextPanNumber.setError("Enter Pan Number");
                                } else {
                                    mEditTextPanNumber.setError(null);
                                    if (TextUtils.isEmpty(mEditTextAddress.getText().toString().trim())) {
                                        mEditTextAddress.setError("Enter Address");
                                    } else {
                                        mEditTextAddress.setError(null);
                                        Constant.hideKeypad(getActivity());
//                                        if (mSpinnerUserType.getSelectedItemPosition() >= 0) {
                                        if(mSpinnerScheme.getSelectedItem()==null){
                                            Toast.makeText(mContext,"We are enable to find scheme ..please add a new One", Toast.LENGTH_LONG).show();
                                        }else {
                                            String spnScheme = mSpinnerScheme.getSelectedItem().toString().trim();
                                            if (spnScheme == null || TextUtils.isEmpty(spnScheme)) {
                                                Toast.makeText(mContext, "Select Scheme", Toast.LENGTH_LONG).show();
                                            } else {
                                                String schemeId = "0", schemeAmount = "0", schemeType;
                                                if (schemeDataArrayList != null && schemeDataArrayList.size() >= 0) {
                                                    schemeId = schemes.get(mSpinnerScheme.getSelectedItemPosition()).getId();
                                                    schemeAmount = schemes.get(mSpinnerScheme.getSelectedItemPosition()).getAmount();
                                                    schemeType = schemes.get(mSpinnerScheme.getSelectedItemPosition()).getSchemeType();
                                                } else {
                                                    schemeId = "0";
                                                    schemeAmount = "0";
                                                    schemeType = "";
                                                }

                                                final ProgressDialog pDialog = CustomProgressDialog.ctor(getActivity());
                                                pDialog.show();

                                                serviceCallApi = ApiServiceGenerator.createService(ServiceCallApi.class);
                                                Call<RechargeResponse> result = serviceCallApi.addUser(prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserName, ""),
                                                        prefs.getFromPrefs(mContext, "password", ""),
                                                        mEditTextName.getText().toString().trim(),
                                                        mEditTextOwnerName.getText().toString().trim(),
                                                        prefs.getFromPrefs(mContext,"userid",""),
                                                        mEditTextMobileNumber.getText().toString().trim(),
                                                        mEditTextPanNumber.getText().toString().trim(),
                                                        mEditTextFarmName.getText().toString().trim(),
                                                        mEditTextAddress.getText().toString().trim(),
                                                        mEditTextAddress.getText().toString().trim(),
                                                        schemeId, schemeAmount, schemeType);

                                                result.enqueue(new Callback<RechargeResponse>() {
                                                    @Override
                                                    public void onResponse(Call<RechargeResponse> call, Response<RechargeResponse> response) {
                                                        RechargeResponse rechargeResponse = response.body();

                                                        if (rechargeResponse.getStatus().equals("Success")) {
                                                                    Constant.DisplayMessageDialog(getActivity(), rechargeResponse.getStatus(), rechargeResponse.getRemarks());
                                                                    mEditTextName.setText("");
                                                                    mEditTextAddress.setText("");
                                                                    mEditTextFarmName.setText("");
                                                                    mEditTextMobileNumber.setText("");
                                                                    mEditTextAddress.setText("");
                                                                    mEditTextOwnerName.setText("");
                                                                    mEditTextPanNumber.setText("");

                                                                } else {

                                                                    Constant.DisplayMessageDialog(getActivity(), rechargeResponse.getStatus(), rechargeResponse.getRemarks());
                                                                    mEditTextName.setText("");
                                                                    mEditTextAddress.setText("");
                                                                    mEditTextFarmName.setText("");
                                                                    mEditTextMobileNumber.setText("");
                                                                    mEditTextAddress.setText("");
                                                                    mEditTextOwnerName.setText("");
                                                                    mEditTextPanNumber.setText("");
                                                                }
                                                                if (pDialog.isShowing())
                                                                    pDialog.dismiss();
                                                            }





                                                    @Override
                                                    public void onFailure(Call<RechargeResponse> call, Throwable t) {

                                                    }
                                                });




//                                                restService.addUser(prefs.getFromPrefs(mContext, "userName", ""),
//                                                        prefs.getFromPrefs(mContext, "password", ""),
//                                                        mEditTextName.getText().toString().trim(), mEditTextOwnerName.getText().toString().trim(),
//                                                        mEditTextMobileNumber.getText().toString().trim(),
//                                                        mEditTextPanNumber.getText().toString().trim(),
//                                                        mEditTextFarmName.getText().toString().trim(),
//                                                        mEditTextAddress.getText().toString().trim(),
//                                                        mEditTextAddress.getText().toString().trim(),
//
//                                                        schemeId, schemeAmount, schemeType,
//                                                        new RestCallback<RechargeResponse>() {
//                                                            @Override
//                                                            public void success(RechargeResponse rechargeResponse) {
//
//                                                                if (rechargeResponse.getStatus().equals("Success")) {
//                                                                    Constant.DisplayMessageDialog(getActivity(), rechargeResponse.getStatus(), rechargeResponse.getRemarks());
//                                                                    mEditTextName.setText("");
//                                                                    mEditTextAddress.setText("");
//                                                                    mEditTextFarmName.setText("");
//                                                                    mEditTextMobileNumber.setText("");
//                                                                    mEditTextAddress.setText("");
//                                                                    mEditTextOwnerName.setText("");
//                                                                    mEditTextPanNumber.setText("");
//
//                                                                } else {
//
//                                                                    Constant.DisplayMessageDialog(getActivity(), rechargeResponse.getStatus(), rechargeResponse.getRemarks());
//                                                                    mEditTextName.setText("");
//                                                                    mEditTextAddress.setText("");
//                                                                    mEditTextFarmName.setText("");
//                                                                    mEditTextMobileNumber.setText("");
//                                                                    mEditTextAddress.setText("");
//                                                                    mEditTextOwnerName.setText("");
//                                                                    mEditTextPanNumber.setText("");
//                                                                }
//                                                                if (pDialog.isShowing())
//                                                                    pDialog.dismiss();
//                                                            }
//
//                                                            @Override
//                                                            public void invalid() {
//                                                                if (pDialog.isShowing())
//                                                                    pDialog.dismiss();
//                                                            }
//
//                                                            @Override
//                                                            public void failure(String errorMesage) {
//                                                                if (pDialog.isShowing())
//                                                                    pDialog.dismiss();
//                                                            }
//
//                                                        });
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
                break;
        }
    }
}