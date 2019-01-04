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
import com.example.webplat.amoldesigning.pojo.billing_history.BillingHistoryData;

import java.util.List;

public class UtilityOfflineHistoryRecyclerAdapter extends RecyclerView.Adapter<UtilityOfflineHistoryRecyclerAdapter.ViewHolder> {
    List<BillingHistoryData> transactionReport;
    PrefUtils prefs;
    String neworignalstring = "";
    String firstword1 = "";
    String strfinal = "";
    private Context mContext;

    public UtilityOfflineHistoryRecyclerAdapter(Context mContext, List<BillingHistoryData> data) {
        this.mContext = mContext;
        this.transactionReport = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.billing_report_history, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

//        holder.mTxtOpeningBal.setText("Use "+mContext.getString(R.string.Rs)+" "+transactionReport.get(position).getAmount());
        holder.mTxtdate.setText(ConvertDate.getDate(transactionReport.get(position).getDate().toString()));
//        holder.mTxtDebits.setText(ConvertDate.getTime(transactionReport.get(position).getDate()));

//        holder.mTxtCredits.setText("New Bal : " + transactionReport.get(position).getNewBalance());
//        holder.mTxtSurcharge.setText("Old Bal :  " + "#" + transactionReport.get(position).getOldBalance());


        holder.mTxtCredits.setText(mContext.getString(R.string.Rs)+" "+transactionReport.get(position).getNewBalance());
        holder.mTxtSurcharge.setText(mContext.getString(R.string.Rs)+" "+transactionReport.get(position).getOldBalance());


        holder.mTxtDiscount.setText(" ID - " + transactionReport.get(position).getId());
//        holder.mTxtClosingBal.setText(transactionReport.get(position).getRemarks());

        // get Number From Remark
//        try {
//            neworignalstring = transactionReport.get(position).getRemarks();
//            firstword1 = neworignalstring.substring(0, neworignalstring.indexOf("Comm")).trim();
//            String arr[] = firstword1.split("\\|");
//            strfinal = arr[0].replace("Number: ", "").replace("Wallet transfer To:","");
//            holder.mTxtOpeningBal.setText(strfinal);
//        } catch (IndexOutOfBoundsException ee) {
////            Toast.makeText(mContext, "Error On String", Toast.LENGTH_SHORT).show();
//            holder.mTxtOpeningBal.setText(strfinal);
//        }

        holder.mTxtOpeningBal.setText(ConvertDate.getTime(transactionReport.get(position).getDate().toString()));
        holder.txt_remark.setText(""+transactionReport.get(position).getRemarks());
    }

    @Override
    public int getItemCount() {
        return transactionReport.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTxtOpeningBal;
        private TextView mTxtdate;
        private TextView mTxtCredits;
        private TextView mTxtDebits;
        private TextView mTxtSurcharge;
        private TextView mTxtDiscount;
        private TextView txt_remark;

        //        private TextView mTxtUsage;
//        private ImageView mImageOperator;
//        private TextView mTxtClosingBal;

        public ViewHolder(View itemView) {
            super(itemView);
            mTxtOpeningBal = (TextView) itemView.findViewById(R.id.txtOpeningBal);
            mTxtdate = (TextView) itemView.findViewById(R.id.txtdate);
            mTxtCredits = (TextView) itemView.findViewById(R.id.txtCredits);
            mTxtDebits = (TextView) itemView.findViewById(R.id.txtDebits);
            mTxtSurcharge = (TextView) itemView.findViewById(R.id.txtSurcharge);
            mTxtDiscount = (TextView) itemView.findViewById(R.id.txtDiscount);
            txt_remark= (TextView) itemView.findViewById(R.id.txt_remark);
//            mTxtRefund = (TextView) itemView.findViewById(R.id.txtRefund);
//            mTxtUsage = (TextView) itemView.findViewById(R.id.txtUsage);
//            mImageOperator = (ImageView)itemView. findViewById(R.id.imageOperator);
//            mTxtClosingBal = (TextView) itemView.findViewById(R.id.txtClosingBal);

        }
    }

}