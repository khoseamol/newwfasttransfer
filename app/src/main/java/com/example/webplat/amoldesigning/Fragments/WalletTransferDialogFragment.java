package com.example.webplat.amoldesigning.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.telecom.Call;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.recghargeplan_pojo.RechargePlanResponse;
import com.example.webplat.amoldesigning.pojo.recharge_response.RechargeResponse;
import com.example.webplat.amoldesigning.pojo.userchildelist.UserChild;

import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by pc3 on 15/11/2016.
 */

public class WalletTransferDialogFragment extends DialogFragment implements View.OnClickListener {

    Context mContext;
    TextView txtMobileNumber, txtOperatorName, txtCircleName, txtAmount;
    Button confirmRecharge;
    PrefUtils prefs;
    private OnCompleteListener mListener;
    ServiceCallApi serviceCallApi;
    Dialog dialog;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirmRecharge:
                transferBalance(
                        getArguments().getString("Username", ""),
                        getArguments().getString("Amount", ""),
                        getArguments().getString("Remark", ""));
                break;
        }
    }

    public static interface OnCompleteListener {
        public abstract void onComplete(int responseStatus, String status, String message);
    }

    // make sure the Activity implemented it
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteListener) activity;
        } catch (final ClassCastException e) {
            // throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.gravity = Gravity.CENTER | Gravity.CENTER;
        window.setAttributes(lp);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wallet_transfer_dailogue, container,
                false);
        setStyle(STYLE_NORMAL, 0);

        bindViews(rootView);
        return rootView;
    }

    private void bindViews(View view) {
        mContext = getContext();
        txtAmount = (TextView) view.findViewById(R.id.txtAmount);
        txtMobileNumber = (TextView) view.findViewById(R.id.txtMobileNumber);
        txtCircleName = (TextView) view.findViewById(R.id.txtCircleName);
 //       txtOperatorName = (TextView) view.findViewById(R.id.txtOperatorName);
        confirmRecharge = (Button) view.findViewById(R.id.confirmRecharge);

        txtAmount.setText(getArguments().getString("Username", ""));
        txtMobileNumber.setText(getArguments().getString("Amount", ""));
        txtCircleName.setText(getArguments().getString("Remark", ""));

        confirmRecharge.setOnClickListener(this);
    }

    private void transferBalance(String name, String amount, String remarks) {

        ApplicationConstant.hideKeypad(getActivity());
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(getActivity());
        progressDialog.show();

        serviceCallApi = ApiServiceGenerator.createService(ServiceCallApi.class);
        retrofit2.Call<RechargeResponse> result = serviceCallApi.transferBalace(
                prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserName, ""),
                prefs.getFromPrefs(mContext,"password", ""),
                name,
                amount,
                remarks);

        result.enqueue(new Callback<RechargeResponse>() {
            @Override
            public void onResponse(retrofit2.Call<RechargeResponse> call, Response<RechargeResponse> response) {
                getDialog().dismiss();
                RechargeResponse rechargeResponse = response.body();
                if (rechargeResponse.getStatus().equals("Success")) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage(rechargeResponse.getRemarks());
                    builder1.setCancelable(true);
                    builder1.setTitle(rechargeResponse.getStatus());

                    builder1.setPositiveButton(
                            "Okay",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

                else {
                 ApplicationConstant.DisplayMessageDialog(getActivity(), rechargeResponse.getStatus(), rechargeResponse.getRemarks());
               }
                        if (progressDialog != null)
                            progressDialog.dismiss();
            }
            @Override
            public void onFailure(retrofit2.Call<RechargeResponse> call, Throwable t) {
                if (progressDialog != null)
                            progressDialog.dismiss();
                        ApplicationConstant.DisplayMessageDialog(getActivity(), "Error", t.getMessage());
                    }
            });

    }
}