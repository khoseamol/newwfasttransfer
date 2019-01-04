package com.example.webplat.amoldesigning.Adapter;

/**
 * Created by Administrator on 08/03/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.webplat.amoldesigning.Fragments.FullTalkTimeFragment;
import com.example.webplat.amoldesigning.Fragments.RomingFragment;
import com.example.webplat.amoldesigning.Fragments.SpecialFragment;
import com.example.webplat.amoldesigning.Fragments.ThreeeGFragment;
import com.example.webplat.amoldesigning.Fragments.TopupFragment;
import com.example.webplat.amoldesigning.Fragments.TwoGFragment;


public class ViewPagerPlansAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Bundle bundle;
    public ViewPagerPlansAdapter(FragmentManager fm, int NumOfTabs, Bundle bundle) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.bundle = bundle;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TopupFragment tab1 = new TopupFragment();
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                FullTalkTimeFragment tab2 = new FullTalkTimeFragment();
                tab2.setArguments(bundle);
                return tab2;
            case 2:
                ThreeeGFragment tab3 = new ThreeeGFragment();
                tab3.setArguments(bundle);
                return tab3;
            case 3:
                TwoGFragment tab4 = new TwoGFragment();
                tab4.setArguments(bundle);
                return tab4;
            case 4:
                RomingFragment tab5 = new RomingFragment();
                tab5.setArguments(bundle);
                return tab5;
            case 5:
                SpecialFragment tab6 = new SpecialFragment();
                tab6.setArguments(bundle);
                return tab6;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}