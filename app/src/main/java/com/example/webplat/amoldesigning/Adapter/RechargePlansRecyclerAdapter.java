package com.example.webplat.amoldesigning.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.pojo.plans.PlansDatum;

import java.util.List;

public class RechargePlansRecyclerAdapter extends RecyclerView.Adapter<RechargePlansRecyclerAdapter.ViewHolder> {
    private static Context mContext;
    String searchType;
    List<PlansDatum> data;
    private int PLANREQUESTCODE = 200;

    public RechargePlansRecyclerAdapter(Context aContext, List<PlansDatum> data) {
        mContext = aContext;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recharge_plans_layout,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mTxtAmount.setText(mContext.getResources().getString(R.string.Rs) + " " + String.valueOf(data.get(position).getRechargeAmount()));
        holder.mTxtDescriptionType.setText(data.get(position).getRechargeLongdesc());
        holder.mTxtTalkTime.setText("N/A");
        holder.mTxtValidaty.setText(data.get(position).getRechargeValidity());

        String talkTime = "";
        if (data.get(position).getRechargeType().equals("Full Talktime") || data.get(position).getRechargeType().equals("Top up")) {
            //talkTime=data.get(position).getRechargeLongdesc().replace("Talktime of Rs.","");
            if ((data.get(position).getRechargeLongdesc().contains("Talktime of Rs."))) {
                String[] talk = data.get(position).getRechargeLongdesc().replace("Talktime of Rs.", "").trim().split("\\s+");
                talkTime = talk[0];
            }
        } else {
            talkTime = "N/A";
        }
        holder.mTxtTalkTime.setText(talkTime);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTxtTalkTime;
        private TextView mTxtDescriptionType;
        private TextView mTxtAmount;
        private TextView mTxtValidaty;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTxtTalkTime = (TextView) itemView.findViewById(R.id.txtTalkTime);
            this.mTxtDescriptionType = (TextView) itemView.findViewById(R.id.txtDescriptionType);
            this.mTxtAmount = (TextView) itemView.findViewById(R.id.txtAmount);
            this.mTxtValidaty = (TextView) itemView.findViewById(R.id.txtValidaty);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("Amount", data.get(pos).getRechargeAmount());
            ((Activity) mContext).setResult(PLANREQUESTCODE,returnIntent);
            ((Activity) mContext).finish();
        }
    }
}
