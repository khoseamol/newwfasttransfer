package com.example.webplat.amoldesigning.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.webplat.amoldesigning.Adapter.RechargePlansRecyclerAdapter;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ListViewDataFilter;
import com.example.webplat.amoldesigning.Utils.SimpleDividerItemDecoration;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.helper.ServiceCallApi;
import com.example.webplat.amoldesigning.pojo.plans.PlansDatum;
import com.example.webplat.amoldesigning.pojo.recghargeplan_pojo.RechargePlan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopupFragment extends Fragment {
    RechargePlansRecyclerAdapter rechargePlansRecyclerAdapter;
    Context mContext;
    ServiceCallApi serviceCallApi;
    RelativeLayout relativeLayout;
    PrefUtils prefs;
    private android.support.v7.widget.RecyclerView mMy_recycler_view;
    private TextView mEmpty_view;
    private LinearLayoutManager mLayoutManager;
    private TextView mPlanNotes;
    private ProgressBar mRegistrationProgressBar;
    private List<PlansDatum> data = new ArrayList<PlansDatum>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.order_history, container, false);
        bindViews(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void bindViews(View rootView) {

        mContext = getActivity();
        getActivity().setTitle(prefs.getFromPrefs(mContext, "AppName", ""));
        mRegistrationProgressBar = (ProgressBar) rootView.findViewById(R.id.registrationProgressBar);
        mEmpty_view = (TextView) rootView.findViewById(R.id.empty_view);
        mMy_recycler_view = (android.support.v7.widget.RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mEmpty_view = (TextView) rootView.findViewById(R.id.empty_view);
        relativeLayout = (RelativeLayout) rootView.findViewById(R.id.main_layout);

        String cirleId = getArguments().getString("circleName");
        String operator = getArguments().getString("operator");
        CallWebService(cirleId, operator);

        mPlanNotes = (TextView) rootView.findViewById(R.id.planNotes);
        mPlanNotes.setVisibility(View.VISIBLE);
        mPlanNotes.setText(Html.fromHtml(getActivity().getResources().getString(R.string.rechargePlanNotes)));


        mLayoutManager = new LinearLayoutManager(getActivity());
        mMy_recycler_view.setLayoutManager(mLayoutManager);
        mMy_recycler_view.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

    }

    private void CallWebService(String circle, String operator) {

        serviceCallApi = ApiServiceGenerator.createService(ServiceCallApi.class);
        Call<RechargePlan> result = serviceCallApi.getRechargePlans(circle,operator);

        result.enqueue(new Callback<RechargePlan>() {
            @Override
            public void onResponse(Call<RechargePlan> call, Response<RechargePlan> response) {
                RechargePlan rechargePlans= response.body();
                if (rechargePlans.getResult().getStatus().equals("Success")) {
                            data = rechargePlans.getResult().getData();;
                            data = ListViewDataFilter.filterByRecharegePlans(data, "Top up");
                            rechargePlansRecyclerAdapter = new RechargePlansRecyclerAdapter(getActivity(), data);
                            mMy_recycler_view.setAdapter(rechargePlansRecyclerAdapter);

                        } else {
                            mEmpty_view.setVisibility(View.VISIBLE);
                            mEmpty_view.setText("No plans found");
                        }
                        mRegistrationProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RechargePlan> call, Throwable t) {

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
        }
    }
}
