package com.example.webplat.amoldesigning.moneytransfer_DMT;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.DMT2_Beneficiary;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.benadd.YesAddBenResponse;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dtn_event.DMT2_DeleteBene;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.DELETE;

/**
 * Created by webplat2 on 20/6/18.
 */

 public class DMT2_BottomSheetDeleteConfirmDialog extends BottomSheetDialogFragment {
  ServiceCallApiDMT2 service;
  PrefUtils prefs;

    int sec = 0;
    int timerCounter = 61;
    Button mBtnCancel;


    DMT2_DeleteBene dmt2_deleteBene;

    Button mBtnConfirm;
    private Button mVerifyOtpBtn;
    private EditText mOtpET;
    private TextView mOtpVerificationTV;
    private Button mResendOtpBtn;    TextView mTxtMessage;
  Context mContext;static DMT2_Beneficiary beneficiaryData;
    private String SenderId;

    public static BottomSheetDialogFragment newInstance(DMT2_Beneficiary dmt2_beneficiary) {
        DMT2_BottomSheetDeleteConfirmDialog f = new DMT2_BottomSheetDeleteConfirmDialog();
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
       initeView(rootView);
        return rootView;
    }

    private void initeView(View rootView) {
        mBtnCancel= rootView.findViewById     (R.id.btnCancel);
        mContext= getContext();
        mBtnConfirm  =rootView.findViewById  (R.id.btnConfirm);
        SenderId = prefs.getFromPrefs(mContext,"senderId","");
        mTxtMessage = rootView.findViewById  (R.id.txtMessage);
        mTxtMessage.setText(getString(R.string.moneyTransferDeleteString));
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBeneficary(beneficiaryData);
            }
        });

        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            dmt2_deleteBene = (DMT2_DeleteBene) getActivity();
        } catch (ClassCastException e) {

//            // The activity doesn't implement the interface, throw exception
//            throw new ClassCastException(get.toString()
//                    + " must implement NoticeDialogListener");
        }
    }

    private void deleteBeneficary(final DMT2_Beneficiary beneficiary) {

        ApplicationConstant.hideKeypad(getActivity());
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(getActivity());
        progressDialog.show();
        ServiceCallApiDMT2 serviceCallApiDMT2 = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);
        service = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);
        Call<YesAddBenResponse> result = service.dmt2_deleteBeneficary(prefs.getFromPrefs(mContext, "userid", ""), prefs.getFromPrefs(mContext, "password", ""), prefs.getFromPrefs(mContext, "DMT2senderMobile", ""),beneficiary.getId(),prefs.getFromPrefs(mContext,"senderId",""));
        result.enqueue(new Callback<YesAddBenResponse>() {
            @Override
            public void onResponse(Call<YesAddBenResponse> call, Response<YesAddBenResponse> response) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                if (response != null) {
                    YesAddBenResponse moneyTransferSPaisa = response.body();
                    if (moneyTransferSPaisa != null) {
                        if (moneyTransferSPaisa.getStatus().equals("Success")) {
                            getDialog().dismiss();

                            dmt2_deleteBene.deletBeleData(beneficiary.getId());
//                            EventBus.getDefault().post(new DMT2_MoneyTransferOTPEvent(beneficiary.getId()));
                        } else {
                            ApplicationConstant.DisplayMessageDialog(getActivity(), moneyTransferSPaisa.getStatus(), moneyTransferSPaisa.getRemarks());
                        }
                    } else {
                        Toast.makeText(getActivity(), "an error occured", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "an error occured", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<YesAddBenResponse> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }

        });

    }


}
