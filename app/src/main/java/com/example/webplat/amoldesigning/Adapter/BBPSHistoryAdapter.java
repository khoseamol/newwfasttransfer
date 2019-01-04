package com.example.webplat.amoldesigning.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.webplat.amoldesigning.Activities.DisplayRecipt;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.pojo.bbpshistory.Datum;

import java.io.File;
import java.util.List;

/**
 * Created by webplat2 on 29/8/18.
 */

public class BBPSHistoryAdapter extends RecyclerView.Adapter<BBPSHistoryAdapter.ViewHolder>{
    String TAG;
    Button proceed;
    Bitmap bitmap ;
    private File myPath;

    LinearLayout relativeLayout ;
    Button b;
    ImageView img;
    Dialog dialog;
    Activity mActivity;
Context mContext;
    List<Datum> data;

    public BBPSHistoryAdapter(Context context, List<Datum> data, Activity activity) {
        mContext = context;
        this.data = data;
        mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(mContext).inflate(R.layout.row_bbps_history,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Datum history = data.get(position);
        holder.txtOperator.setText(history.getOperator());
        holder.txtStatus.setText(history.getStatus());
        holder.txtNumber.setText(history.getTranNumber());
        holder.txtClossingBalance.setText(""+history.getClosingbal());
        holder.txtDateTime.setText(history.getTrandate());
        holder.txtPreBalance.setText(""+history.getOpeningbal());
        holder.txtCharges.setText("Charges:"+history.getCharge());
        holder.txtTxnId.setText(""+history.getTranNo());
        holder.txtAmount.setText(""+history.getAmount());
        holder.txtCategory.setText(""+history.getCategory());
        holder.txtCustomerMobileNumber.setText(""+history.getCustmobileno());
        holder.txtMode.setText(""+history.getTranmode());

        if(history.getStatus().equals("Success")){
            holder.txtStatus.setTextColor(mContext.getResources().getColor(R.color.green));

        } if(history.getStatus().equals("Failure")){
            holder.txtStatus.setTextColor(mContext.getResources().getColor(R.color.red));

        } if(history.getStatus().equals("Reversal")){
            holder.txtStatus.setTextColor(mContext.getResources().getColor(R.color.orange));

        }


        holder.txtViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.layout.getVisibility()== View.VISIBLE){
                    holder.layout.setVisibility(View.GONE);
                }

                else
                    holder.layout.setVisibility(View.VISIBLE);
            }
        });
        holder.txtReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.datum = data.get(position);
                Intent intent = new Intent(mContext,DisplayRecipt.class);
                mContext.startActivity(intent);

              //  getReciept(position);
            }
        });


    }



public void getReciept(int position){
    dialog   = new Dialog(mContext);
        dialog.setContentView(R.layout.layout_get_receipt);


    dialog.show();
}


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtOperator;
        TextView txtStatus;
        TextView txtAmount;
        TextView txtClossingBalance;
        TextView txtNumber;
        TextView txtDateTime;
        TextView txtTxnId;
        TextView txtReceipt;
        TextView txtPreBalance;
        TextView txtCharges;
        TextView txtViewDetails;
        TextView txtCategory;
        TextView txtCustomerMobileNumber;
        TextView txtMode;
        LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);

            txtOperator = (TextView)itemView.findViewById(R.id.txtOperator);
            txtCategory = (TextView)itemView.findViewById(R.id.txtpayrefid);
            txtCustomerMobileNumber = (TextView)itemView.findViewById(R.id.txtCustomerMobileNumber);
            txtMode = (TextView)itemView.findViewById(R.id.txtMode);
            txtStatus = (TextView)itemView.findViewById(R.id.txtStatus);
            txtAmount = (TextView)itemView.findViewById(R.id.txtAmount);
            txtClossingBalance = (TextView)itemView.findViewById(R.id.txtClosingBalance);
            txtNumber = (TextView)itemView.findViewById(R.id.txtNumber);
            txtDateTime = (TextView)itemView.findViewById(R.id.txtDateTime);
            txtTxnId = (TextView)itemView.findViewById(R.id.txtTxNId);
            txtReceipt = (TextView)itemView.findViewById(R.id.txtReciept);
            txtPreBalance = (TextView)itemView.findViewById(R.id.txtPreBalance);
            txtCharges = (TextView)itemView.findViewById(R.id.txtChagres);
            txtViewDetails = (TextView)itemView.findViewById(R.id.txtViewDetails);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);

        }
    }







}
