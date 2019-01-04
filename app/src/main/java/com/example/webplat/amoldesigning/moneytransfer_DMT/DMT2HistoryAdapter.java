package com.example.webplat.amoldesigning.moneytransfer_DMT;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.tnxreport.DMT2Txt_Data;

import java.util.List;

/**
 * Created by webplat2 on 21/6/18.
 */

public class DMT2HistoryAdapter extends RecyclerView.Adapter<DMT2HistoryAdapter.MYholder> {
   Context mContext ;
   List<DMT2Txt_Data> mtransDetails;
   Activity activity;
    public DMT2HistoryAdapter(Context mContext, List<DMT2Txt_Data> mtransDetails, DMT2_HistoryMoney dmt2_historyMoney) {
        this.mContext = mContext;
        this.mtransDetails = mtransDetails;
        this.activity = dmt2_historyMoney;
  
    }

    @NonNull
    @Override
    public MYholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_transaction_history_new_yesbank, parent, false);
        return new MYholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MYholder holder, final int position) {
        holder.mTxtAmount.setText(mContext.getString(R.string.Rs) + " " + mtransDetails.get(position).getAmount());
        holder.mTxtDate.setText(mtransDetails.get(position).getAccountname());
        holder.mTxtBankName.setText(mtransDetails.get(position).getBankname());

        holder.mTxtStatus.setText(mtransDetails.get(position).getStatus());
        holder.mTxtOrderDetails.setText("Trans Id " + mtransDetails.get(position).getTrxnid());
        holder.mTxtRemarks.setText(mtransDetails.get(position).getReason());


        holder.mTxtBankRef.setText(mtransDetails.get(position).getTrxndate());

        holder.mTxtIFSC.setText(mtransDetails.get(position).getIFSC());
        holder.mTXtBeneName.setText(mtransDetails.get(position).getTrxntype());

        if (mtransDetails.get(position).getStatus().equalsIgnoreCase("PENDING")) {
            holder.mTxtStatus.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_orange_dark));
            holder.mTxtAmount.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_orange_dark));
        } else if (mtransDetails.get(position).getStatus().equalsIgnoreCase("Failure")) {
            holder.mTxtStatus.setTextColor(ContextCompat.getColor(mContext, R.color.red));
            holder.mTxtAmount.setTextColor(ContextCompat.getColor(mContext, R.color.red));
        } else {
            holder.mTxtStatus.setTextColor(ContextCompat.getColor(mContext, R.color.green));
            holder.mTxtAmount.setTextColor(ContextCompat.getColor(mContext, R.color.green));
        }


        holder.mTxtAcNo.setText("Ac No : " + mtransDetails.get(position).getAccountno());
    }

    @Override
    public int getItemCount() {
        return mtransDetails.size();
    }

    public class MYholder extends RecyclerView.ViewHolder {
      TextView mTxtOrderDetails;
        TextView mTxtStatus;
        private TextView mTxtDate;
        private TextView mTxtAmount;
        private TextView mTxtAcNo;
        private TextView mTxtIFSC;
        private TextView mTxtBankName;
        private TextView mTxtBankRef;
        private TextView mTxtRemarks, mTXtBeneName, mtxtRefundAvailable;

        public MYholder(View itemView) {
            super(itemView);

            mTxtOrderDetails = (TextView) itemView.findViewById(R.id.txtOrderDetails);
            mTxtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            mTxtDate = (TextView) itemView.findViewById(R.id.txtDate);
            mTxtAmount = (TextView) itemView.findViewById(R.id.txtAmount);
            mTxtAcNo = (TextView) itemView.findViewById(R.id.txtAcNo);
            mTxtIFSC = (TextView) itemView.findViewById(R.id.TxtIFSC);
            mTxtBankName = (TextView) itemView.findViewById(R.id.TxtBankName);
            mTxtBankRef = (TextView) itemView.findViewById(R.id.TxtBankRef);
            mTxtRemarks = (TextView) itemView.findViewById(R.id.txtRemarks);
            mTXtBeneName = (TextView) itemView.findViewById(R.id.TXtBeneName);
            mtxtRefundAvailable = (TextView) itemView.findViewById(R.id.txtRefundAvailable);
          
        }
    }
}
