package com.example.webplat.amoldesigning.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.ConvertDate;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.pojo.recharge_history.TransHistoryData;

import java.util.List;

public class TransactionHistoryRecyclerAdapter extends RecyclerView.Adapter<TransactionHistoryRecyclerAdapter.ViewHolder> {
    private Context mContext;
    List<TransHistoryData> transactionReport;
    PrefUtils prefs;
    public TransactionHistoryRecyclerAdapter(Context mContext, List<TransHistoryData> data) {
        this.mContext = mContext;
        this.transactionReport=data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_transaction_history, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        TransHistoryData c = transactionReport.get(position);
        holder.mTxtid.setText("Order No "+c.getId());
        holder.mTxtDate.setText(ConvertDate.getDate(c.getRechargeDate()));
        holder.mTxtTime.setText(ConvertDate.getTime(c.getRechargeDate()));
        holder.mTxtRecharge.setText("Recharge of "+ c.getOperatorName() +"  "+c.getNumber());
        holder.mTxtAmount.setText(holder.itemView.getContext().getResources().getString(R.string.Rs)+" "+c.getAmount());
        holder.mTxtorderStatus.setText("your recharge status is "+ String.valueOf(c.getStatus()));

//        Uri imageUri = Uri.parse(ApplicationConstant.IMAGEWEBSERVICEURL+c.getImage());
//        holder.mSdvImage.setImageURI(imageUri);
//        holder.mSdvImage.setImageResource(R.mipmap.ic_launcher);

        Glide.with(mContext).
                load(ApplicationConstant.IMAGEWEBSERVICEURL+c.getImage()).
                error(R.mipmap.logo).
                placeholder(R.mipmap.logo).
                into(holder.mSdvImage);
//
//        Glide.with(mContext).
//                load(ApplicationConstant.IMAGEWEBSERVICEURL +
//                        transactionReport.get(position).getImage()).
//                placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).
//                into(holder.mSdvImage);
    }

    @Override
    public int getItemCount() {
        return transactionReport.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtid;
        private TextView mTxtDate;
        private TextView mTxtTime;
        private ImageView mSdvImage;
        private TextView mTxtRecharge;
        private TextView mTxtAmount;
        private TextView mTxtorderStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            mTxtid = (TextView) itemView.findViewById(R.id.Txtid);
            mTxtDate = (TextView) itemView.findViewById(R.id.TxtDate);
            mTxtTime = (TextView)itemView. findViewById(R.id.TxtTime);
            mSdvImage = (ImageView)itemView. findViewById(R.id.sdvImage);
            mTxtRecharge = (TextView) itemView.findViewById(R.id.TxtRecharge);
            mTxtAmount = (TextView) itemView.findViewById(R.id.TxtAmount);
            mTxtorderStatus = (TextView)itemView. findViewById(R.id.TxtorderStatus);

        }

    }
}