package com.example.webplat.amoldesigning.moneytransfer_DMT;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.DMT2_Beneficiary;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.add_beneficary.AddBeneficaryResponse;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dtn_event.DMT2_MoneyTransferValidateEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by webplat2 on 20/6/18.
 */

 public class DMT2_BottomSheetValidateConfirmDialog extends BottomSheetDialogFragment {
    static DMT2_Beneficiary beneficiaryData;

    Button mBtnCancel;
    Button mBtnConfirm;
   TextView mTxtMessage;
   ServiceCallApiDMT2 service;
    PrefUtils prefs;
    Context mContext;


    public static BottomSheetDialogFragment newInstance(DMT2_Beneficiary dmt2_beneficiary) {
        DMT2_BottomSheetValidateConfirmDialog f = new DMT2_BottomSheetValidateConfirmDialog();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.money_beneficiary_confirm_validate, container, false);
            initView(rootView);
            return rootView;
    }
    public void initView(View view){
        mContext = getActivity();
        mBtnCancel = (Button) view.findViewById(R.id.btnCancel);
        mBtnConfirm = (Button) view.findViewById(R.id.btnConfirm);
        mTxtMessage = (TextView) view.findViewById(R.id.txtMessage);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateBeneficiary(beneficiaryData);
            }
        });

        mTxtMessage.setText(getString(R.string.moneyTransferValidateString));
    }


    private void validateBeneficiary(final DMT2_Beneficiary beneficiary) {
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(getActivity());
        progressDialog.show();
        service = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);
        String userName =prefs.getFromPrefs(mContext,"userid","");
        String password =prefs.getFromPrefs(mContext, "password", "");
      String bank =  beneficiary.getBank();
      String getId = beneficiary.getId();
        String mobile = prefs.getFromPrefs(mContext, "DMT2senderMobile", "");
        String Name = beneficiary.getName() ;
        String account = beneficiary.getAccount();

           String ifsc =     beneficiary.getIfsc();
           String firstname = prefs.getFromPrefs(mContext, "senderFirstName", "");

        Log.d("UserName+++",userName);
        Log.d("UserName+++",password);
        Log.d("bank+++",bank);
        Log.d("getId+++",getId);
        Log.d("mobile+++",mobile);
        Log.d("Name+++",Name);

        Call<AddBeneficaryResponse> result = service.dmt2_benvalidateBeneficary(prefs.getFromPrefs(mContext, "userid", ""), prefs.getFromPrefs(mContext, "password", ""), beneficiary.getBank(), beneficiary.getId(), prefs.getFromPrefs(mContext, "DMT2senderMobile", ""), beneficiary.getName(), beneficiary.getAccount(),

                beneficiary.getIfsc(), prefs.getFromPrefs(mContext, "senderFirstName", ""));
        result.enqueue(new Callback<AddBeneficaryResponse>() {
            @Override
            public void onResponse(Call<AddBeneficaryResponse> call, Response<AddBeneficaryResponse> response) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                if (response != null) {
                    AddBeneficaryResponse moneyLoginCheck = response.body();
                    if (moneyLoginCheck != null) {
                        getDialog().dismiss();

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setMessage(moneyLoginCheck.getRemarks());
                        builder1.setCancelable(true);
                        builder1.setTitle(moneyLoginCheck.getStatus());

                        builder1.setPositiveButton(
                                "Okay",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                       EventBus.getDefault().post(new DMT2_MoneyTransferValidateEvent(Integer.parseInt(beneficiary.getId())));

                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    } else {
                        Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<AddBeneficaryResponse> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }

        });
    }

}
