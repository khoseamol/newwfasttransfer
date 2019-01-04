package com.example.webplat.amoldesigning.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.helper.ConvertDate;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.pojo.recharge_history.TransHistoryData;
import com.example.webplat.amoldesigning.pojo.wallet_history.WalletHistoryData;

import java.util.List;

public class WalletHistoryRecyclerAdapter extends RecyclerView.Adapter<WalletHistoryRecyclerAdapter.ViewHolder> {
    List<WalletHistoryData> walletHistoryDataList;
    PrefUtils prefs;
    String strremark = "";
    String finalstr = "";
    private Context mContext;

    public WalletHistoryRecyclerAdapter(Context mContext, List<WalletHistoryData> data) {
        this.walletHistoryDataList = data;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_wallet_statement_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (walletHistoryDataList.get(position).getPaymentType().equals("Initial Balance Deposit")) {
            holder.mTxtShortName.setText("D");
            holder.mTxtShortName.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_orange_dark));
        } else if (walletHistoryDataList.get(position).getPaymentType().equals("Debit")) {
            holder.mTxtShortName.setText("D");
            holder.mTxtShortName.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_orange_dark));
        } else if (walletHistoryDataList.get(position).getPaymentType().equals("Credit")) {
            holder.mTxtShortName.setText("C");
            holder.mTxtShortName.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_orange_dark));
        }

//        holder.mTxPadForOrder.setText(walletHistoryDataList.get(position).getRemarks().split("\\|")[0]);
//        holder.mTxtDate.setText(ConvertData.changeDateFormatWallet(walletHistoryDataList.get(position).getPaymentDate()));
//        holder.mTxtOrderId.setText("To "+walletHistoryDataList.get(position).getCrUserName());
//        holder.mTxtTransId.setText("TxnId #"+walletHistoryDataList.get(position).getId());
//        holder.mTxtFrom.setText("From "+walletHistoryDataList.get(position).getDrUserName());


//        holder.mTxPadForOrder.setText(walletHistoryDataList.get(position).getRemarks().split("\\|")[0]);

        holder.mTxtDate.setText(ConvertDate.getDate(walletHistoryDataList.get(position).getPaymentDate().toString()));
        holder.mTxtOrderId.setText("From " + walletHistoryDataList.get(position).getDrUserName());
        holder.mTxtTransId.setText("TxnId #" + walletHistoryDataList.get(position).getId());
        holder.mTxtFrom.setText("To " + walletHistoryDataList.get(position).getCrUserName());

        if (walletHistoryDataList.get(position).getPaymentType().equals("Credit"))
//          holder.mTxtAmount.setText("Rs " + String.format("%.2f", new Double(walletHistoryDataList.get(position).getAmount())));
            holder.mTxtAmount.setText(mContext.getString(R.string.Rs) + " " + String.format("%.2f", new Double(walletHistoryDataList.get(position).getAmount())));

        else
//          holder.mTxtAmount.setText("- Rs " + String.format("%.2f", new Double(walletHistoryDataList.get(position).getAmount())));
            holder.mTxtAmount.setText(mContext.getString(R.string.Rs) + " " + String.format("%.2f", new Double(walletHistoryDataList.get(position).getAmount())));


        try {
            strremark = walletHistoryDataList.get(position).getRemarks().split("\\|")[0];
            String finalstr = strremark.replace("Transfer To : ", "");
            holder.mTxPadForOrder.setText(finalstr);
        } catch (IndexOutOfBoundsException ee) {
            holder.mTxPadForOrder.setText(finalstr);
        }
    }

    @Override
    public int getItemCount() {
        return walletHistoryDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtShortName;
        private TextView mTxtDate;
        private TextView mTxPadForOrder;
        private TextView mTxtOrderId;
        private TextView mTxtTransId;
        private TextView mTxtFrom;
        private TextView mTxtAmount;


        public ViewHolder(View itemView) {
            super(itemView);
            mTxtShortName = (TextView) itemView.findViewById(R.id.txtShortName);
            mTxtDate = (TextView) itemView.findViewById(R.id.txtDate);
            mTxPadForOrder = (TextView) itemView.findViewById(R.id.txPadForOrder);
            mTxtOrderId = (TextView) itemView.findViewById(R.id.txtOrderId);
            mTxtTransId = (TextView) itemView.findViewById(R.id.txtTransId);
            mTxtFrom = (TextView) itemView.findViewById(R.id.txtFrom);
            mTxtAmount = (TextView) itemView.findViewById(R.id.txtAmount);

        }
    }

}