package com.example.webplat.amoldesigning.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.webplat.amoldesigning.Adapter.DistributerDashboardAdapter;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.Utils.Transction;
import com.example.webplat.amoldesigning.fragment.history_fragments.WalletSummaryFragment;

import java.util.ArrayList;

public class DistributerDashboard extends MainActivity {
    private RecyclerView mRVDistributer;
    DistributerDashboardAdapter adapter;
    ArrayList<Transction> DistributerService = new ArrayList<>();
    Context mContext = this;
    private TextView txt_username_distributor;
    private TextView txt_balance_distributor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_distributer_dashboard);
        getLayoutInflater().inflate(R.layout.activity_distributer_dashboard, frameLayout);
        bindViews();
    }

    private void bindViews() {
        mRVDistributer = (RecyclerView) findViewById(R.id.RVDistributerPanel);

        txt_username_distributor = (TextView) findViewById(R.id.txt_username_distributor);
        txt_balance_distributor = (TextView) findViewById(R.id.txt_balance_distributor);

        txt_username_distributor.setText("Username: " + prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserName, ""));
        txt_balance_distributor.setText("Balance: " + mContext.getString(R.string.Rs)+ " "+prefs.getFromPrefs(mContext, Constant.USERDETAILS.MainBalance, ""));
        getDataList();

        adapter = new DistributerDashboardAdapter(mContext, DistributerService, new DistributerDashboardAdapter.ItemClickEvent() {
            @Override
            public void clikc(int position) {

                switch (position) {
                    case 0:
                        Intent mIntent = new Intent(mContext, AddUser.class);
                        mIntent.putExtra("PaymentReq", 0);
                        mContext.startActivity(mIntent);
                        break;

                    case 1:
                        mIntent = new Intent(mContext, AddUser.class);
                        mIntent.putExtra("PaymentReq", 1);
                        mContext.startActivity(mIntent);
                        break;
                    case 2:
                        mIntent = new Intent(mContext, WalletSummaryFragment_Distributor.class);
                        mContext.startActivity(mIntent);
                        break;

                    case 3:
                        mIntent = new Intent(mContext, BillingHistory_Distributor.class);
                        mContext.startActivity(mIntent);
                        break;

                    case 4:
                        mIntent = new Intent(mContext, Payment_Request.class);
                        mContext.startActivity(mIntent);
                        break;

                    case 5:
                        mIntent = new Intent(mContext, CreditReportActivity.class);
                        mContext.startActivity(mIntent);
                        break;
                }
            }
        });

        GridLayoutManager linearLayoutManager1 = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
        mRVDistributer.setLayoutManager(linearLayoutManager1);
        mRVDistributer.setAdapter(adapter);
    }

    private void getDataList() {
        Transction t1 = new Transction();
        t1.setImageID(R.mipmap.add_user);
        t1.setStatus("Add User");

        Transction t2 = new Transction();
        t2.setImageID(R.mipmap.view_user);
        t2.setStatus("View User");

        Transction t3 = new Transction();
        t3.setImageID(R.mipmap.history1);
        t3.setStatus("Top-Up History");

        Transction t4 = new Transction();
        t4.setImageID(R.mipmap.history2);
        t4.setStatus("Statment History");

        Transction t5 = new Transction();
        t5.setImageID(R.mipmap.payment_request);
        t5.setStatus("Payment Request");

        Transction t6 = new Transction();
        t6.setImageID(R.mipmap.history3);
        t6.setStatus("Billing History");

        DistributerService.add(t1);
        DistributerService.add(t2);
        DistributerService.add(t3);
        DistributerService.add(t4);
        DistributerService.add(t5);
        DistributerService.add(t6);


    }


}
