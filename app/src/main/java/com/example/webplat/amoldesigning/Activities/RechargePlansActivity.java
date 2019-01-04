package com.example.webplat.amoldesigning.Activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.webplat.amoldesigning.Adapter.ViewPagerPlansAdapter;
import com.example.webplat.amoldesigning.R;

public class RechargePlansActivity extends AppCompatActivity {
    Intent intent;
    RelativeLayout relativeLayout;
    Toolbar toolbar;
    TabLayout tabLayout;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        mContext = RechargePlansActivity.this;
        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
        toolbar.setTitle("Recharge Plans");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitleTextColor(ContextCompat.getColor(mContext, R.color.white));

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Top Up"));
        tabLayout.addTab(tabLayout.newTab().setText("Full Talktime"));
        tabLayout.addTab(tabLayout.newTab().setText("3G Data"));
        tabLayout.addTab(tabLayout.newTab().setText("2G Data"));
        tabLayout.addTab(tabLayout.newTab().setText("Roaming"));
        tabLayout.addTab(tabLayout.newTab().setText("Special Recharge"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        relativeLayout = (RelativeLayout) findViewById(R.id.main_layout);

        intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("circleName", intent.getStringExtra("circleName"));
        bundle.putString("operator", intent.getStringExtra("operatorName"));

        final ViewPagerPlansAdapter adapter = new ViewPagerPlansAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), bundle);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200) {
            setResult(resultCode, data);
            finish();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }



}
