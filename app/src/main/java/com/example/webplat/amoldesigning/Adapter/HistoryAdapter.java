package com.example.webplat.amoldesigning.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.webplat.amoldesigning.fragment.history_fragments.BillingHistory;
import com.example.webplat.amoldesigning.fragment.history_fragments.OrderHistory;
import com.example.webplat.amoldesigning.fragment.history_fragments.PaymentRequestHistoryFragment;
import com.example.webplat.amoldesigning.fragment.history_fragments.WalletSummaryFragment;

public class HistoryAdapter extends FragmentStatePagerAdapter {
    Integer numberOfTabs;
    public HistoryAdapter(FragmentManager fm, int tabs) {
        super(fm);

        this.numberOfTabs = tabs;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();

        switch (position) {
            case 0:
                OrderHistory electricityFragment = new OrderHistory();
                return electricityFragment;
            case 1:
                BillingHistory utilityOfflineReport = new BillingHistory();
                return utilityOfflineReport;

            case 2:
                WalletSummaryFragment walletSummaryFragment = new WalletSummaryFragment();
                return walletSummaryFragment;

            case 3:
                PaymentRequestHistoryFragment paymentRequestHistoryFragment = new PaymentRequestHistoryFragment();
                return paymentRequestHistoryFragment;
        }
        return null;

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}