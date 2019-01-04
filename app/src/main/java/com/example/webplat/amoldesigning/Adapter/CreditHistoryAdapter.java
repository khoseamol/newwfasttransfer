package com.example.webplat.amoldesigning.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.pojo.credit_summary_report.CreditReportDataaa;
import java.util.List;

public class CreditHistoryAdapter extends RecyclerView.Adapter<CreditHistoryAdapter.ViewHolder> {
    private Context mContext;
    List<CreditReportDataaa> transactionReport;
    PrefUtils prefs;
    public CreditHistoryAdapter(Context mContext, List<CreditReportDataaa> data) {
        this.mContext = mContext;
        this.transactionReport=data;
    }

//    @Override
//    public CreditHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        // create a new view
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.customsummaryreport, parent, false);
//        CreditHistoryAdapter.ViewHolder viewHolder = new CreditHistoryAdapter.ViewHolder(view);
//        return viewHolder;
//    }

    @Override
    public CreditHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customsummaryreport, parent, false);

        CreditHistoryAdapter.ViewHolder viewHolder = new CreditHistoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CreditHistoryAdapter.ViewHolder holder, final int position) {
        holder.mTxtOpeningBal.setText("Debit Username : "+transactionReport.get(position).getRTCRUsername());
        holder.mTxtCredits.setText("Date : "+transactionReport.get(position).getRechDate());

        holder.mTxtRefund.setText("Time : "+transactionReport.get(position).getRechTime());
        holder.mTxtDebits.setText("Amount : "+ mContext.getString(R.string.Rs)+" "+transactionReport.get(position).getAmount());
        holder.mTxtUsage.setText("Old Bal : "+mContext.getString(R.string.Rs)+" " + transactionReport.get(position).getOldBal());
        holder.mTxtSurcharge.setText("New Bal : " + mContext.getString(R.string.Rs) +" "+transactionReport.get(position).getNewBal());
        holder.mTxtUsage.setText("Old Bal :" + mContext.getString(R.string.Rs)+" " + transactionReport.get(position).getOldBal());
        holder.mTxtClosingBal.setText("Remark : "+transactionReport.get(position).getRemarks());
    }

    @Override
    public int getItemCount() {
        return transactionReport.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtOpeningBal;
        private TextView mTxtdate;
        private TextView mTxtCredits;
        private TextView mTxtRefund;
        private TextView mTxtDebits;
        private TextView mTxtUsage;
        private TextView mTxtSurcharge;
        private TextView mTxtClosingBal;




//        private TextView mTxtOpeningBal;
//        private TextView mTxtdate;
//        private RelativeLayout mOne;
//        private TextView mTxtCredits;
//        private TextView mTxtDebits;
//        private RelativeLayout mTree;
//        private TextView mTxtSurcharge;
//        private TextView mTxtDiscount;
//        private RelativeLayout mTwo;
//        private TextView mTxtRefund;
//        private TextView mTxtUsage;
//        private ImageView mImageOperator;
//        private TextView mTxtClosingBal;






        public ViewHolder(View itemView) {
            super(itemView);
            mTxtOpeningBal = (TextView)itemView. findViewById(R.id.txtOpeningBal);
            mTxtdate = (TextView)itemView. findViewById(R.id.txtdate);
            mTxtCredits = (TextView) itemView.findViewById(R.id.txtCredits);
            mTxtRefund = (TextView) itemView.findViewById(R.id.txtRefund);
            mTxtDebits = (TextView)itemView. findViewById(R.id.txtDebits);
            mTxtUsage = (TextView)itemView. findViewById(R.id.txtUsage);
            mTxtSurcharge = (TextView)itemView. findViewById(R.id.txtSurcharge);
            mTxtClosingBal = (TextView) itemView.findViewById(R.id.txtClosingBal);




//            mTxtOpeningBal = (TextView) itemView.findViewById(R.id.txtOpeningBal);
//            mTxtdate = (TextView) itemView.findViewById(R.id.txtdate);
//            mOne = (RelativeLayout) itemView.findViewById(R.id.one);
//            mTxtCredits = (TextView) itemView.findViewById(R.id.txtCredits);
//            mTxtDebits = (TextView) itemView.findViewById(R.id.txtDebits);
//            mTree = (RelativeLayout) itemView.findViewById(R.id.tree);
//            mTxtSurcharge = (TextView) itemView.findViewById(R.id.txtSurcharge);
//            mTxtDiscount = (TextView)itemView. findViewById(R.id.txtDiscount);
//            mTwo = (RelativeLayout) itemView.findViewById(R.id.two);
//            mTxtRefund = (TextView)itemView. findViewById(R.id.txtRefund);
//            mTxtUsage = (TextView) itemView.findViewById(R.id.txtUsage);
//            mImageOperator = (ImageView)itemView. findViewById(R.id.imageOperator);
//            mTxtClosingBal = (TextView) itemView.findViewById(R.id.txtClosingBal);
        }
    }

    }
