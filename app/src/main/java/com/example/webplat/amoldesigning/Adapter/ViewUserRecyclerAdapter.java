package com.example.webplat.amoldesigning.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.webplat.amoldesigning.Activities.WalletTransfer;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.pojo.userchildelist.UserChildListData;

import java.util.List;


/**
 * Created by webplat on 14/09/2015.
 */
public class ViewUserRecyclerAdapter extends RecyclerView.Adapter<ViewUserRecyclerAdapter.ViewHolder> {
    private Context mContext;
    List<UserChildListData> transactionReport;
    PrefUtils prefs;
    public ViewUserRecyclerAdapter(Context mContext, List<UserChildListData> data) {
        this.mContext = mContext;
        this.transactionReport=data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewalluser, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextUserbalance.setText("Bal : "+mContext.getString(R.string.Rs) + " " + transactionReport.get(position).getBalance());

        holder.mTextUsername.setText("Name : "+transactionReport.get(position).getUserName());
        holder.mTxtuserOwnername.setText("S Name : "+transactionReport.get(position).getOwnerName());
        holder.mTxtUserType.setText("Type : "+transactionReport.get(position).getUserType());
        holder.mTxtAddBalance.setOnClickListener(new ItemVIewRowClickListener(position));

    }


    @Override
    public int getItemCount() {
        return transactionReport.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextUserbalance;
        private TextView mTextUsername;
        private TextView mTxtUserType;
        private TextView mTxtuserOwnername;

        private TextView mTxtAddBalance;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextUserbalance = (TextView) itemView.findViewById(R.id.txtuserBalance);
            mTxtUserType = (TextView) itemView.findViewById(R.id.txtusertype);
            mTextUsername = (TextView) itemView.findViewById(R.id.txtusername);
            mTxtuserOwnername = (TextView) itemView.findViewById(R.id.txtuseownername);
            mTxtAddBalance=(TextView)itemView.findViewById(R.id.txtAddBalance);
        }


    }
    private class ItemVIewRowClickListener implements View.OnClickListener {
        int groupPosition;

        public ItemVIewRowClickListener(int section) {
            this.groupPosition = section;

        }

        @Override
        public void onClick(View view) {

            Intent intent=new Intent(mContext,WalletTransfer.class);
            intent.putExtra("viewUsername",transactionReport.get(groupPosition).getUserName());
            intent.putExtra("viewOwnername",transactionReport.get(groupPosition).getOwnerName());
            mContext.startActivity(intent);

        }
    }

}