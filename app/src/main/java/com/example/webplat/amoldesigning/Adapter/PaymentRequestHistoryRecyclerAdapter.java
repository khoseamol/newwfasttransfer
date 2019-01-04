package com.example.webplat.amoldesigning.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.helper.ConvertDate;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.pojo.payment_request_history.PaymentRequestData;

import java.util.List;

public class PaymentRequestHistoryRecyclerAdapter extends RecyclerView.Adapter<PaymentRequestHistoryRecyclerAdapter.ViewHolder> {
    List<PaymentRequestData> disputeReport;
    PrefUtils prefs;
    private Context mContext;


    public PaymentRequestHistoryRecyclerAdapter(Context mContext, List<PaymentRequestData> data) {
        this.disputeReport = data;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transection_report, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mTxtTransectionID.setText(disputeReport.get(position).getRefNo());
        holder.mTxtmobileNo.setText(disputeReport.get(position).getUserDetails());
        holder.mTxtTransectionStatusID.setText(disputeReport.get(position).getApproved());
        holder.mTxtOrderId.setText(ConvertDate.getDate(disputeReport.get(position).getRequestDate().toString()));
        holder.mTxtAmount.setText(disputeReport.get(position).getDepositBank());
//        holder.mTxtDateTime.setText(mContext.getString(R.string.Rs)+" "+disputeReport.get(position).getAmount());
        holder.mTxtOPRID.setText(mContext.getString(R.string.Rs)+" "+disputeReport.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return disputeReport.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTxtmobileNo;
        public TextView mTxtOrderId;
        public TextView mTxtAmount;
        private TextView mTxtDateTime;
        public TextView mTxtOPRID;
        public TextView mTxtTransectionID;
        public TextView mTxtTransectionStatusID;

        public ViewHolder(View itemView) {
            super(itemView);
            mTxtmobileNo = (TextView) itemView.findViewById(R.id.txtmobileNo);
            mTxtOrderId = (TextView) itemView.findViewById(R.id.txtOrderId);
            mTxtAmount = (TextView) itemView.findViewById(R.id.txtAmount);
            mTxtDateTime = (TextView) itemView.findViewById(R.id.txtDateTime);
            mTxtOPRID = (TextView) itemView.findViewById(R.id.txtOPRID);
            mTxtTransectionID = (TextView) itemView.findViewById(R.id.txtTransectionID);
            mTxtTransectionStatusID = (TextView) itemView.findViewById(R.id.txtTransectionStatusID);
        }

        @Override
        public void onClick(View v) {

        }
    }

}