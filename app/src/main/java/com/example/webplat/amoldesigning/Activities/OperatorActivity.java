package com.example.webplat.amoldesigning.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.webplat.amoldesigning.Adapter.OperatorAdapter;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.DBHelper;
import com.example.webplat.amoldesigning.pojo.operator.OperatorList;

import java.util.ArrayList;
import java.util.List;

public class OperatorActivity extends AppCompatActivity {

    RecyclerView operatorView;
    Context context = this;
    OperatorAdapter operatorAdapter;
    List<OperatorList> operatorDataArrayList = new ArrayList<OperatorList>();
    Toolbar toolbar;
    private DBHelper dbHelper;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);
        bindViews();
        setOperator();
    }

    private void bindViews() {
        operatorView = (RecyclerView) findViewById(R.id.OperatorRecyclerView);

        toolbar = (Toolbar) findViewById(R.id.toolbar1); // Attaching the layout to the toolbar object
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setTitle("Operators");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //   toolbar.setTitleTextColor(ContextCompat.getColor(mContext, R.color.white));
    }

    private void setOperator() {
        mContext = this;
        dbHelper = new DBHelper(mContext);
        if(getIntent().getStringExtra(Constant.OPERATORTYPE).equals( ApplicationConstant.MOBILESERVICEID))
        operatorDataArrayList.addAll(operatorDataArrayList = dbHelper.fetchAllOperatorByType("Mobile"));
 if(getIntent().getStringExtra(Constant.OPERATORTYPE).equals( ApplicationConstant.POSTPAIDSERVICEID))
        operatorDataArrayList.addAll(operatorDataArrayList = dbHelper.fetchAllOperatorByType("Postpaid"));
 if(getIntent().getStringExtra(Constant.OPERATORTYPE).equals( ApplicationConstant.DTHSERVICEID))
        operatorDataArrayList.addAll(operatorDataArrayList = dbHelper.fetchAllOperatorByType("DTH"));
 if(getIntent().getStringExtra(Constant.OPERATORTYPE).equals( ApplicationConstant.DATACARDSERVICEID))
        operatorDataArrayList.addAll(operatorDataArrayList = dbHelper.fetchAllOperatorByType("Datacard"));

        if (operatorDataArrayList.size() > 0) {
            setAdapter(operatorDataArrayList);
        } else {
            Toast.makeText(mContext,"No operator found",Toast.LENGTH_SHORT) .show();       }


    }


//    public void onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(R.anim.slide_out_up, R.anim.slide_out_down);
//    }


    private void setAdapter(final List<OperatorList> operatorDataArrayList) {

        operatorAdapter = new OperatorAdapter(mContext, operatorDataArrayList);
        LinearLayoutManager horizontalLayoutManagaer1
                = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        operatorView.setLayoutManager(horizontalLayoutManagaer1);
        operatorView.setAdapter(operatorAdapter);
        operatorAdapter.ChooseOperator(new OperatorAdapter.SelectOperator() {
            @Override
            public void selectOperatorfromlist(int position) {
                Intent intent = new Intent();
                intent.putExtra(Constant.SelectedOperator, operatorDataArrayList.get(position).getOpName());
                intent.putExtra(Constant.SelectedOperatorID, operatorDataArrayList.get(position).getOpId());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }


}
