package com.example.webplat.amoldesigning.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.webplat.amoldesigning.Adapter.HistoryAdapter;
import com.example.webplat.amoldesigning.R;

public class HistoryActivity extends AppCompatActivity {


    private android.support.design.widget.TabLayout mHistoryTabs;
    private android.support.v4.view.ViewPager mViewpager;
    private int[] tabIcons = {
            R.drawable.tab_history,
            R.drawable.tab_wallet,
            R.drawable.tab_billing,
            R.drawable.tab_payment

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacitonlist);

        bindview();
        mViewpager = (android.support.v4.view.ViewPager) findViewById(R.id.viewpager);


        mHistoryTabs = (android.support.design.widget.TabLayout) findViewById(R.id.historyTabs);

        mHistoryTabs.addTab(mHistoryTabs.newTab().setText("History").setIcon(tabIcons[0]));
        mHistoryTabs.addTab(mHistoryTabs.newTab().setText("Billing").setIcon(tabIcons[1]));
        mHistoryTabs.addTab(mHistoryTabs.newTab().setText("Wallet").setIcon(tabIcons[2]));
        mHistoryTabs.addTab(mHistoryTabs.newTab().setText("Payment Request").setIcon(tabIcons[3]));

//        mHistoryTabs.addTab(mHistoryTabs.newTab().setText("Wallet"));
//        mHistoryTabs.addTab(mHistoryTabs.newTab().setText("Dispute"));


        HistoryAdapter adapter = new HistoryAdapter(getSupportFragmentManager(), mHistoryTabs.getTabCount());
        mViewpager.setAdapter(adapter);
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mHistoryTabs));
        mHistoryTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    private void bindview() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("History");

        toolbar.setNavigationIcon(R.drawable.back);

        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                }
            });
        }

    }

}


