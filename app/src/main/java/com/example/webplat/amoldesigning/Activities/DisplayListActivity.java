package com.example.webplat.amoldesigning.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.example.webplat.amoldesigning.Adapter.CustomListAdapter;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.pojo.bbpssubcategory.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webplat2 on 1/8/18.
 */

public  class DisplayListActivity extends AppCompatActivity {
    RecyclerView rv;
  Context mContext;
    CustomListAdapter adapter;
    private List<Datum> serviceList = new ArrayList<>();
    String call ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        serviceList.clear();
        serviceList = (List<Datum>) getIntent().getSerializableExtra("myList");
        BindView();


    }

    private void BindView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
            toolbar.setTitle("Select operator");
        rv = (RecyclerView)findViewById(R.id.rv);
        setAdapter(serviceList);





    }




    private void setAdapter(final List<Datum> operatorDataArrayList) {
        mContext = DisplayListActivity.this;
        adapter = new CustomListAdapter(mContext, operatorDataArrayList);
        LinearLayoutManager horizontalLayoutManagaer1
                = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(horizontalLayoutManagaer1);
        rv.setAdapter(adapter);
        adapter.ChooseOperator(new CustomListAdapter.SelectOperator() {
            @Override
            public void selectOperatorfromlist(int position) {

                Intent intent = new Intent();
                intent.putExtra(Constant.SelectedOperator, operatorDataArrayList.get(position).getBillername());
                intent.putExtra(Constant.SelectedOperatorID,operatorDataArrayList.get(position).getBillerid());
                intent.putExtra(Constant.SelectedServicCode,operatorDataArrayList.get(position).getBillercode());
               intent.putExtra("isfeatch",operatorDataArrayList.get(position).getValidateallow());
               intent.putExtra("ispartialy",operatorDataArrayList.get(position).getPartialallow());
                setResult(Activity.RESULT_OK, intent);






                finish();
            }
        });

    }




}
