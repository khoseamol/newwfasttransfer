package com.example.webplat.amoldesigning.moneytransfer_DMT;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.DMT2_Beneficiary;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.fundtransfer.DMT2_Trnsfer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by webplat2 on 20/6/18.
 */

@SuppressLint("ValidFragment")
public class DMT2_BottomSheetMoneyTransferDialog extends BottomSheetDialogFragment {

    static DMT2_Beneficiary beneficiaryData;
    EditText mEditAcountNumber;
    EditText mEditAcHolderName;
    EditText mEditAmount;
    Spinner mSpinnerTransferType;
    Button mBtnTransfer;
    EditText mEditIFSCCode;
    EditText mEditBankName;

    TextView mTxtNotice;


    TextView mTxtNote;
    ServiceCallApiDMT2 service;
    PrefUtils prefs;
    Context mContext;
    String[] transferType = {"IMPS"};

    public static BottomSheetDialogFragment newInstance(DMT2_Beneficiary dmt2_beneficiary) {
        DMT2_BottomSheetMoneyTransferDialog f = new DMT2_BottomSheetMoneyTransferDialog();
        Bundle args = new Bundle();
        args.putSerializable("beneficiaryData", dmt2_beneficiary);
        f.setArguments(args);
        return f;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beneficiaryData = (DMT2_Beneficiary) getArguments().getSerializable("beneficiaryData");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.money_transfer1, container, false);

        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {

        mEditAcountNumber = (EditText)rootView.findViewById (R.id.editAcountNumber);

         mEditAcHolderName=(EditText)rootView.findViewById(R.id.editAcHolderName);
         mEditAmount=(EditText)rootView.findViewById(R.id.editAmount);
         mSpinnerTransferType=(Spinner)rootView.findViewById (R.id.spinnerTransferType);

         mBtnTransfer = (Button)rootView.findViewById(R.id.btnTransfer);
       mEditIFSCCode=(EditText)rootView.findViewById(R.id.editIFSCCode);

         mEditBankName = (EditText)rootView.findViewById(R.id.editBankName);
        mTxtNote =(TextView) rootView.findViewById(R.id.TxtNote);

         mTxtNotice=  (TextView)rootView.findViewById(R.id.TxtNotice);


        mContext = getActivity();
        mEditAmount.setInputType(InputType.TYPE_CLASS_NUMBER);

        mEditAcHolderName.setText(beneficiaryData.getBank());
        mEditAcountNumber.setText(beneficiaryData.getAccount());
        mEditIFSCCode.setText(beneficiaryData.getIfsc());
        mTxtNotice.setVisibility(View.GONE);






        mTxtNote.setText("Note : Transfer Amount should be 1 - 25000 Rs.");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, transferType); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerTransferType.setAdapter(spinnerArrayAdapter);

        mBtnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(mEditAmount.getText().toString().trim())) {
                    mEditAmount.setError("Enter Amount");
                } else {
                    mEditAmount.setError(null);
                    if(Integer.parseInt(mEditAmount.getText().toString().trim())>=1){
                        ApplicationConstant.hideKeypad(getActivity());
                        final ProgressDialog progressDialog = CustomProgressDialog.ctor(getActivity());
                        progressDialog.show();
                        if (Double.parseDouble(mEditAmount.getText().toString())<= Constant.RemainingLimit){
                        service = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);
                        Call<DMT2_Trnsfer> result = service.dmt2_transferMoney(prefs.getFromPrefs(mContext, "userid", ""), prefs.getFromPrefs(mContext, "password", ""), prefs.getFromPrefs(mContext, "DMT2senderMobile", ""), prefs.getFromPrefs(mContext, "senderFirstName", ""), beneficiaryData.getBank(), beneficiaryData.getId(), beneficiaryData.getName(), beneficiaryData.getAccount(), beneficiaryData.getIfsc(), mSpinnerTransferType.getSelectedItem().toString().trim()
                                , mEditAmount.getText().toString().trim());
                        result.enqueue(new Callback<DMT2_Trnsfer>() {
                            @Override
                            public void onResponse(Call<DMT2_Trnsfer> call, Response<DMT2_Trnsfer> response) {
                                if (progressDialog != null && progressDialog.isShowing())
                                    progressDialog.dismiss();
                                if (response != null) {
                                    DMT2_Trnsfer moneyTransferSPaisa = response.body();
                                    if (moneyTransferSPaisa != null) {
                                        if (moneyTransferSPaisa.getStatus().equalsIgnoreCase("Success")) {
                                            getDialog().dismiss();
                                            ApplicationConstant.DisplayMessageDialog(getActivity(), "Success", moneyTransferSPaisa.getRemarks());

                                        } else {
                                            ApplicationConstant.DisplayMessageDialog(getActivity(), "Failed", moneyTransferSPaisa.getRemarks());
                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "an error occured", Toast.LENGTH_LONG).show();
                                    }

                                } else {
                                    Toast.makeText(getActivity(), "an error occured", Toast.LENGTH_LONG).show();
                                }
                            }


                            @Override
                            public void onFailure(Call<DMT2_Trnsfer> call, Throwable t) {
                                if (progressDialog != null && progressDialog.isShowing())
                                    progressDialog.dismiss();
                            }

                        });
                    }
                        if (Double.parseDouble(mEditAmount.getText().toString())> Constant.RemainingLimit){
                            ApplicationConstant.DisplayMessageDialog(getActivity(),"Message","insufficient balance");
                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    else {
                        Toast.makeText(mContext,"Invalid Amount , Please Enter Amount greater than  10",Toast.LENGTH_LONG).show();
                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();
                    }}

                }

//
            }
        });
    }
}
