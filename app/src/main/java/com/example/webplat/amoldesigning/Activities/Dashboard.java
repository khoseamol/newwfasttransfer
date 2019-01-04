package com.example.webplat.amoldesigning.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webplat.amoldesigning.Adapter.CustomPagerAdapter;
import com.example.webplat.amoldesigning.Adapter.DashboardAdapter;
import com.example.webplat.amoldesigning.Adapter.OtherserviceListAdapter;
import com.example.webplat.amoldesigning.Adapter.TravelDashboardAdapter;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.Utils.Transction;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.model.TravelModel;
import com.example.webplat.amoldesigning.moneytransfer_DMT.MoneyTransferLoginActivity_IDMT;
import com.example.webplat.amoldesigning.pojo.balance.GetBalance;
import com.example.webplat.amoldesigning.pojo.user_profile.UserDetails;

import java.util.ArrayList;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Dashboard  extends MainActivity {

    DashboardAdapter adapter;
    ImageView mimgReport, mimg1, img_payment_req;
    TravelDashboardAdapter Traveladapter;
    OtherserviceListAdapter otherserviceAdapter;
    DashboardAdapter OtherServicsadapter;
    ArrayList<Transction> transctionArrayList = new ArrayList<>();
    ArrayList<Transction> otherServiceList = new ArrayList<>();
    ArrayList<TravelModel> travelModels = new ArrayList<>();
    AutoScrollViewPager mViewPager;
    int[] mResources = {
            R.drawable.banner1 , R.drawable.banner2, R.drawable.banner3, R.drawable.banner4,};

    CustomPagerAdapter mCustomPagerAdapter;
    private android.support.v7.widget.RecyclerView mRVtransactionList1, mRVtravelsandBooking, mRVotherList;
    private TextView mTxtUserName;
    private TextView mTxtLastSeen;
    private TextView mTxtBalance;
    Context mContext;
    PrefUtils prefs;
    ImageView bbps,moneyTransfer;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_dashboard, frameLayout);
        bindView();
    }

    private void bindView() {
        mContext = Dashboard.this;
        mTxtUserName = (TextView) findViewById(R.id.TxtUserName);
        mTxtLastSeen = (TextView) findViewById(R.id.TxtLastSeen);
        mTxtBalance = (TextView) findViewById(R.id.TxtBalance);

        moneyTransfer = (ImageView) findViewById(R.id.moneytransfer);
        bbps = (ImageView) findViewById(R.id.bbps);


moneyTransfer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Dashboard.this, MoneyTransferLoginActivity_IDMT.class);
                 startActivity(intent);
    }
});bbps.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Dashboard.this, BBPSActivity.class);
        startActivity(intent);
    }
});


        mViewPager = (AutoScrollViewPager) findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(mResources.length - 1);
        mCustomPagerAdapter = new CustomPagerAdapter(mContext, mResources);
        mViewPager.setInterval(2000);
        mViewPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_TO_PARENT);
        mViewPager.startAutoScroll();
        mViewPager.setAdapter(mCustomPagerAdapter);

        mimg1 = (ImageView) findViewById(R.id.img1);
        mimgReport = (ImageView) findViewById(R.id.imgReport);
        img_payment_req = (ImageView) findViewById(R.id.img_payment_req);

        img_payment_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Payment_Request.class);
                startActivity(intent);
            }
        });

        mimgReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HistoryActivity.class);
                startActivity(intent);
            }
        });

        mimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserBalance();
            }
        });
        mTxtUserName.setText(prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserName, ""));
        mTxtLastSeen.setText(prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserType, ""));
        mTxtBalance.setText("Balance: " + mContext.getString(R.string.Rs)+ " "+prefs.getFromPrefs(mContext, Constant.USERDETAILS.MainBalance, ""));

        mRVtransactionList1 = (android.support.v7.widget.RecyclerView) findViewById(R.id.RVtransactionList1);


        getDataList();

        adapter = new DashboardAdapter(mContext, transctionArrayList, new DashboardAdapter.ItemClickEvent() {
            @Override
            public void clikc(int position) {
                if (position == 0) {
                    Intent intent = new Intent(Dashboard.this, MobileRecharge.class);


                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(Dashboard.this, DatacardRecharge.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(Dashboard.this, DTHRecharge.class);
                    startActivity(intent);
                } else if (position == 3) {
//                    Intent intent = new Intent(Dashboard.this, MobileRecharge.class);
//                    startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(Dashboard.this, Electricity.class);
                    startActivity(intent);
                }
            }
        });
        GridLayoutManager linearLayoutManager = new GridLayoutManager(mContext,3);
        mRVtransactionList1.setLayoutManager(linearLayoutManager);
        mRVtransactionList1.setAdapter(adapter);

//
//        Traveladapter = new TravelDashboardAdapter(mContext, travelModels, new TravelDashboardAdapter.ItemClickEvent() {
//            @Override
//            public void clikc(int position) {
//                if (position == 1) {
//                    Intent intent = new Intent(Dashboard.this, MoneyTransferLoginActivity_IDMT.class);
//                    startActivity(intent);
//                } else if (position == 0) {
//                    Toast.makeText(mContext,"Comming soon",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
//        mRVtravelsandBooking.setLayoutManager(linearLayoutManager1);
//        mRVtravelsandBooking.setAdapter(Traveladapter);


//        adapter = new DashboardAdapter(mContext, otherServiceList, new DashboardAdapter.ItemClickEvent() {
//            @Override
//            public void clikc(int transction) {
//                if (transction == 0) {
//                    Intent intent = new Intent(Dashboard.this, MoneyTransferLoginActivity_IDMT.class);
//                    startActivity(intent);
//                }
//            }
//        });
//        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
//        mRVotherList.setLayoutManager(linearLayoutManager2);
//        mRVotherList.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void getUserBalance() {
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(Dashboard.this);
        progressDialog.show();
        ApiServiceGenerator apiServiceGenerator = new ApiServiceGenerator();
     ServiceCallApi   resCallApi = apiServiceGenerator.createService(ServiceCallApi.class);
        final Call<GetBalance> objbanklist = resCallApi.getUserBalance(
                prefs.getFromPrefs(mContext, "userid", ""),
                prefs.getFromPrefs(mContext, "password", "")
        );
        objbanklist.enqueue(new Callback<GetBalance>() {
            @Override
            public void onResponse(Call<GetBalance> call, Response<GetBalance> response) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                GetBalance balanceResponse = response.body();
                if (balanceResponse.getStatus().equals("Success"))
                    UpdateBalance(new Double(balanceResponse.getData().getMainBalance()));
            }

            @Override
            public void onFailure(Call<GetBalance> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });
    }

    private void UpdateBalance(double totalBalance) {
        if (mTxtBalance != null)
            mTxtBalance.setText("Balance: " + mContext.getResources().getString(R.string.Rs) + " " + String.format("%.2f", totalBalance));
        prefs.saveToPrefs(mContext, "Wallet_Main_Balance", String.format("%.2f", totalBalance));


    }
    private void getDataList() {
        Transction t1 = new Transction();
        t1.setImageID(R.drawable.mobile);
        t1.setStatus("Mobile");
        Transction t2 = new Transction();
        t2.setImageID(R.drawable.datacard);
        t2.setStatus("Datacard");
        Transction t3 = new Transction();
        t3.setImageID(R.drawable.dth);
        t3.setStatus("DTH");
        Transction t4 = new Transction();
        t4.setImageID(R.drawable.postpaid);
        t4.setStatus("Postpaid");


        TravelModel TravelModel1 = new TravelModel();
        TravelModel1.setImageID(R.drawable.bbps);
        TravelModel1.setStatus("BBPS");

        TravelModel TravelModel2 = new TravelModel();
        TravelModel2.setImageID(R.drawable.money_transfer);
        TravelModel2.setStatus("Money Transfer");


        transctionArrayList.add(t1);
        transctionArrayList.add(t2);
        transctionArrayList.add(t3);
       // transctionArrayList.add(t4);





    }

}
