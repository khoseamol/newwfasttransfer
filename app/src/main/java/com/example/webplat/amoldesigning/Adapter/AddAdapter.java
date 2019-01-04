package com.example.webplat.amoldesigning.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.webplat.amoldesigning.Activities.AddUsertoService;
import com.example.webplat.amoldesigning.Activities.ViewUser1;


/**
 * Created by webplat_pc on 4/3/2017.
 */

public class AddAdapter extends FragmentStatePagerAdapter {

    Integer numberOfTabs;// This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created

    public AddAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.numberOfTabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)      // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            AddUsertoService user = new AddUsertoService();
            return  user;
        }
        if (position == 1)      // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
           ViewUser1 viewUser = new ViewUser1();

            return viewUser;
        }
//        if (position == 2)      // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
//       {
//           AddUsertoService user = new AddUsertoService();
//           return  user;
//            Distributer_Payment_Request gasFragment = new
//                    Distributer_Payment_Request();
//            return gasFragment;
//        }
// else
        else
            return null;
    }



    @Override
    public int getCount() {
        return numberOfTabs;
    }
}