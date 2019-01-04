package com.example.webplat.amoldesigning.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.example.webplat.amoldesigning.Adapter.AddAdapter;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.helper.PrefUtils;

public class AddUser extends MainActivity {

    private android.support.v4.view.ViewPager mViewpager;
    AddAdapter adapter;
    Context mContext;
    RelativeLayout relativeLayout;
    PrefUtils prefs;

    TabLayout mDetail_tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.history, frameLayout);
        bindViews();
    }

    private void bindViews() {
        mContext = AddUser.this;
        setTitle(prefs.getFromPrefs(mContext, "AppName", ""));

        Intent intent = getIntent();
        int intValue = intent.getIntExtra("PaymentReq", 0);
        relativeLayout = (RelativeLayout) findViewById(R.id.main_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("ADD USER");
        mDetail_tabs = (TabLayout) findViewById(R.id.detail_tabs);
        mViewpager = (android.support.v4.view.ViewPager) findViewById(R.id.viewpager);

        mDetail_tabs.addTab(mDetail_tabs.newTab().setText("Add User "));
        mDetail_tabs.addTab(mDetail_tabs.newTab().setText("Transfer Balance"));
//        mDetail_tabs.addTab(mDetail_tabs.newTab().setText("Payment Request"));
//        mDetail_tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        mDetail_tabs.setTabMode(TabLayout.MODE_FIXED);

        adapter = new AddAdapter(getSupportFragmentManager(), mDetail_tabs.getTabCount());

        mViewpager.setAdapter(adapter);
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mDetail_tabs));
        if (intent != null) {
            mViewpager.setCurrentItem(intValue);
        }

        mDetail_tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
    }


}
