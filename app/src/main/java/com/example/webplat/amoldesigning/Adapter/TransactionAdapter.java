package com.example.webplat.amoldesigning.Adapter;

/**
 * Created by webplat on 31/1/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.Transction;

import java.util.ArrayList;
import java.util.List;




public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    private List<Transction> transactionList;
    Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView mTxtDate;
        private TextView mTxtAmount;
        private TextView mTxtTransactionID;
        private TextView mTxtMobileNumber;
        private TextView mTxtStatus;


        public MyViewHolder(View view) {
            super(view);
            mImage = (ImageView)view. findViewById(R.id.image);
            mTxtDate = (TextView)view. findViewById(R.id.txtDate);
            mTxtAmount = (TextView)view. findViewById(R.id.txtAmount);
            mTxtTransactionID = (TextView)view. findViewById(R.id.txtTransactionID);
            mTxtMobileNumber = (TextView) view.findViewById(R.id.txtMobileNumber);
            mTxtStatus = (TextView)view. findViewById(R.id.txtStatus);

        }
    }

    public TransactionAdapter(Context mContext, ArrayList<Transction> horizontalList) {
        this.transactionList = horizontalList;
        this.mContext=mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontalsingleitem_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mTxtAmount.setText("Rs."+transactionList.get(position).getAmount());
        holder.mTxtDate.setText(transactionList.get(position).getDate());
        holder.mTxtMobileNumber.setText(transactionList.get(position).getMobileNumber());
        holder.mTxtStatus.setText(transactionList.get(position).getStatus());
        holder.mTxtTransactionID.setText("Transaction ID :"+transactionList.get(position).getTransactionID());
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}

