package com.example.webplat.amoldesigning.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.PrefUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public FrameLayout frameLayout;
    PrefUtils prefs;
    Context mContext = this;
    NavigationView navigationView;
    private TextView mUserType,mNav_header_uname;
    ImageView history, setting,profile,more;
    FloatingActionButton home ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout = (FrameLayout) findViewById(R.id.content_main_frame);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        home = (FloatingActionButton) findViewById(R.id.fab);
        history = (ImageView) findViewById(R.id.history);
        setting = (ImageView) findViewById(R.id.setting);
        profile = (ImageView) findViewById(R.id.profile);
        more = (ImageView) findViewById(R.id.more);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.getHeaderView(0);
        sateNavigationHead(headerLayout);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent loginIntent = new Intent(mContext, Dashboard.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);
                    finish();

            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Comming soon",Toast.LENGTH_SHORT).show();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProfileActivity.class);
                startActivity(intent);            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HistoryActivity.class);
                startActivity(intent);                 }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void sateNavigationHead(View headerLayout) {
        mUserType = (TextView)headerLayout.findViewById(R.id.txtUserType);
        mNav_header_uname = (TextView)headerLayout. findViewById(R.id.textView);


        if (prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserName, "") != null && !TextUtils.isEmpty(prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserName, "")))
            mUserType.setText(prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserName,""));
        if (prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserType, "") != null && !TextUtils.isEmpty(prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserType, "")))
            mNav_header_uname.setText(prefs.getFromPrefs(mContext,Constant.USERDETAILS.UserType,""));

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);

        if (prefs.getFromPrefs(mContext, Constant.USERDETAILS.UserType, "").equals("Retailer")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_dashboard_drawer);

        } else {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_distributer_dashboard_drawer);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(MainActivity.this, DistributerDashboard.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_share) {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                String sAux = "\nI Tried This App For Recharge And its Cool\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=" + getPackageName();
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "Share"));
            } catch (Exception e) { //e.toString();
            }
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_logout) {
            prefs.removeFromPrefs(MainActivity.this, "userid");
            prefs.removeFromPrefs(MainActivity.this, "password");
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (id == R.id.nav_change_password) {
            Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        }
else if (id == R.id.nav_mobile) {
            Intent intent = new Intent(MainActivity.this, MobileRecharge.class);
            startActivity(intent);
        }
else if (id == R.id.nav_datacard) {
            Intent intent = new Intent(MainActivity.this, DatacardRecharge.class);
            startActivity(intent);
        }
else if (id == R.id.nav_postpaid) {
            Intent intent = new Intent(MainActivity.this, MobileRecharge.class);
            startActivity(intent);
        }
else if (id == R.id.nav_dth) {
            Intent intent = new Intent(MainActivity.this, DTHRecharge.class);
            startActivity(intent);
        }else if (id == R.id.nav_bbps) {
            Intent intent = new Intent(MainActivity.this, BBPSActivity.class);
            startActivity(intent);        }
else if (id == R.id.nav_change_password) {
            Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}