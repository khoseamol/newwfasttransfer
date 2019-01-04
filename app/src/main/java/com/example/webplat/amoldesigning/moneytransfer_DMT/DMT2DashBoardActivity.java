package com.example.webplat.amoldesigning.moneytransfer_DMT;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.helper.ApiServiceGenerator;
import com.example.webplat.amoldesigning.helper.CustomProgressDialog;
import com.example.webplat.amoldesigning.helper.PrefUtils;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.DMT2_Beneficiary;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.DMT2_LoginPojo;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dmt2_pojo.benadd.YesAddBenResponse;
import com.example.webplat.amoldesigning.moneytransfer_DMT.dtn_event.DMT2_DeleteBene;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by webplat2 on 20/6/18.
 */

public class DMT2DashBoardActivity extends AppCompatActivity implements DMT2_DeleteBene {
  CountDownTimer timer;
  PrefUtils prefs;
  Toolbar toolbar;
  TextView mTxtBalance;
  List<DMT2_Beneficiary> beneficiary = new ArrayList<DMT2_Beneficiary>();
  Context mContext;
  ServiceCallApiDMT2 service;
  int sec = 0;
  int timerCounter = 61;
  private EditText mOtpET;
  private TextView mOtpVerificationTV, mTxtUserName;
  private Button mResendOtpBtn;
  private ImageView imageCancel;
  private TextView mTxtEmptyView;
  private ListView mListView;
  private Button mVerifyOtpBtn;
  private TextView mTextAvailableBalance, mTextRemainingBalance;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_viewbeneficiay_dmt);
    BindeView();


  }

  private void BindeView() {
    mContext = DMT2DashBoardActivity.this;
    mTextAvailableBalance = (TextView) findViewById(R.id.txtBalance);
    mTextRemainingBalance = (TextView) findViewById(R.id.txtRemitanceLimit);
    mTxtEmptyView = (TextView) findViewById(R.id.txtEmptyView);
    mListView = (ListView) findViewById(R.id.listView);
    toolbar = (Toolbar) findViewById(R.id.tool_bar_about);
    toolbar.setVisibility(View.VISIBLE);
    mTxtUserName = (TextView) findViewById(R.id.mobilenumbertxt);
    toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
    toolbar.setTitle("Beneficiary List");
    mTxtUserName.setText("Welcome" + " " + prefs.getFromPrefs(mContext, "senderFirstName", ""));
    setSupportActionBar(toolbar);
    //getSupportActionBar().setDisplayShowHomeEnabled(true);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();

      }
    });
    mTxtBalance = (TextView) findViewById(R.id.txtBalance);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();

      }
    });

    getUserDetails(prefs.getFromPrefs(mContext, "DMT2senderMobile", ""));
    getBeneficaryList();
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_eko, menu);
    for (int i = 0; i < menu.size(); i++) {
      MenuItem item = menu.getItem(i);
      SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
      spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.white)), 0, spanString.length(), 0); //fix the color to white
      item.setTitle(spanString);
    }
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    switch (id) {
      case R.id.action_add_ben:
        Intent intent = new Intent(mContext, DMT2_AddBeneficiary.class);

        startActivity(intent);

        break;
      case R.id.action_history:
        getMoneyHistoryy();
        break;
    }

    return super.onOptionsItemSelected(item);
  }

  private void getMoneyHistoryy() {
    Intent intent = new Intent(DMT2DashBoardActivity.this, DMT2_HistoryMoney.class);
    mContext.startActivity(intent);

  }

  private void getUserDetails(final String userName) {
    ApplicationConstant.hideKeypad(DMT2DashBoardActivity.this);

    final ProgressDialog progressDialog = CustomProgressDialog.ctor(DMT2DashBoardActivity.this);
    progressDialog.show();
    service = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);
    Call<DMT2_LoginPojo> result = service.dmtLogin(prefs.getFromPrefs(mContext, "userid", ""), prefs.getFromPrefs(mContext, "password", ""), userName);
    result.enqueue(new Callback<DMT2_LoginPojo>() {
      @Override
      public void onResponse(Call<DMT2_LoginPojo> call, Response<DMT2_LoginPojo> response) {
        if (progressDialog != null && progressDialog.isShowing())
          progressDialog.dismiss();
        if (response != null) {
          DMT2_LoginPojo moneyLoginCheck = response.body();
          if (moneyLoginCheck != null) {

            if (moneyLoginCheck.getResponseCode() == 1 && moneyLoginCheck.getStatus().equals("Success")) {

              DisplayLimit(moneyLoginCheck.getData().getRemitter().getConsumedlimit(), moneyLoginCheck.getData().getRemitter().getRemaininglimit());


            }
          }
        }

      }

      @Override
      public void onFailure(Call<DMT2_LoginPojo> call, Throwable t) {
        if (progressDialog != null && progressDialog.isShowing())
          progressDialog.dismiss();
      }

    });

  }

  private void getBeneficaryList() {
    mTxtEmptyView.setVisibility(View.GONE);
    mListView.setVisibility(View.GONE);
    ApplicationConstant.hideKeypad(DMT2DashBoardActivity.this);
    final ProgressDialog progressDialog = CustomProgressDialog.ctor(DMT2DashBoardActivity.this);
    progressDialog.show();
    service = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);
    Call<DMT2_LoginPojo> result = service.dmtLogin(prefs.getFromPrefs(mContext, "userid", ""), prefs.getFromPrefs(mContext, "password", ""), prefs.getFromPrefs(mContext, "DMT2senderMobile", ""));
    result.enqueue(new Callback<DMT2_LoginPojo>() {
      @Override
      public void onResponse(Call<DMT2_LoginPojo> call, Response<DMT2_LoginPojo> response) {
        if (progressDialog != null && progressDialog.isShowing())
          progressDialog.dismiss();
        if (response != null) {
          DMT2_LoginPojo moneyLoginCheck = response.body();
          if (moneyLoginCheck != null) {
            if (moneyLoginCheck.getStatus().equalsIgnoreCase("Failure")) {
              Toast.makeText(mContext, moneyLoginCheck.getRemarks(), Toast.LENGTH_LONG).show();

            } else {
              prefs.saveToPrefs(mContext, "senderFirstName", moneyLoginCheck.getData().getRemitter().getName());
              prefs.saveToPrefs(mContext, "senderLastName", moneyLoginCheck.getData().getRemitter().getName());

              if (moneyLoginCheck.getData().getBeneficiary().size() > 0) {
                beneficiary.clear();
                beneficiary = moneyLoginCheck.getData().getBeneficiary();
                BenefiacryAdapter benefiacryAdapter = new BenefiacryAdapter(mContext, beneficiary);
                mListView.setAdapter(benefiacryAdapter);
                mListView.setVisibility(View.VISIBLE);

              } else {
                mTxtEmptyView.setVisibility(View.VISIBLE);
                mTxtEmptyView.setText("No Beneficiary found ..!");
              }
            }

          } else {
            Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
          }

        } else {
          Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
        }

      }

      @Override
      public void onFailure(Call<DMT2_LoginPojo> call, Throwable t) {
        if (progressDialog != null && progressDialog.isShowing())
          progressDialog.dismiss();
      }

    });

  }

  private void DisplayLimit(String remLimit, String availableBalance) {
    int totalLimit = Integer.parseInt(remLimit) + Integer.parseInt(availableBalance);
    mTextAvailableBalance.setText("Available limit\n " + availableBalance + ".0" + " RS");
    mTextRemainingBalance.setText(" Remaining limit:\n " + " " + availableBalance + " " + "   ");
    mTextAvailableBalance.setText("Total Limit\n " + totalLimit);
    Constant.RemainingLimit =Double.parseDouble(availableBalance);

  }


  @Override
  public void deletBeleData(String id) {
    DisplayOTPDialog(id);
  }


  private void DisplayOTPDialog(final String beneid) {
    LayoutInflater inflater = getLayoutInflater();
    View dialoglayout = inflater.inflate(R.layout.otp_confirm_dialog, null);
    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DMT2DashBoardActivity.this);
    builder.setCancelable(false).setView(dialoglayout);
    mVerifyOtpBtn = (Button) dialoglayout.findViewById(R.id.verifyOtpBtn);
    mOtpET = (EditText) dialoglayout.findViewById(R.id.otpET);
    mResendOtpBtn = (Button) dialoglayout.findViewById(R.id.resendOtpBtn);
    ImageView imageCancel = (ImageView) dialoglayout.findViewById(R.id.imageCancel);
    mOtpVerificationTV = (TextView) dialoglayout.findViewById(R.id.otpVerificationTV);


    final android.app.AlertDialog alert = builder.create();
    alert.getWindow().setBackgroundDrawable(
      new ColorDrawable(android.graphics.Color.TRANSPARENT));
    //change input type to text
    mOtpET.setInputType(InputType.TYPE_CLASS_NUMBER);
    imageCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        alert.dismiss();
        timer.cancel();
      }
    });

    //change input type to text
    mOtpET.setInputType(InputType.TYPE_CLASS_NUMBER);


    final Timer t = new Timer();
    mResendOtpBtn.setClickable(false);
    mResendOtpBtn.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.darker_gray));
    mResendOtpBtn.setEnabled(false);
    timer = new CountDownTimer(60000, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        mOtpVerificationTV.setText("0:" + millisUntilFinished / 1000);

      }

      @Override
      public void onFinish() {
        mOtpVerificationTV.setText("1:00");
        mResendOtpBtn.setClickable(true);
        mResendOtpBtn.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        mResendOtpBtn.setEnabled(true);
      }
    };
    timer.start();
      /*  t.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (DMT2DashBoardActivity.this == null)
                    return;
                DMT2DashBoardActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (timerCounter == sec) {
                            t.cancel();
                            timerCounter = 0;
                            mResendOtpBtn.setClickable(true);
                            mResendOtpBtn.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                            mResendOtpBtn.setEnabled(true);
                            return;
                        }
                        timerCounter--;
                        mOtpVerificationTV.setText("0 :" + String.valueOf(timerCounter));
                    }
                });
            }
        }, 0, 1000);*/

    mResendOtpBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        timer.start();
        ApplicationConstant.hideKeypad(DMT2DashBoardActivity.this);
        final ProgressDialog progressDialog = CustomProgressDialog.ctor(DMT2DashBoardActivity.this);
        progressDialog.show();
        service = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);

        Call<YesAddBenResponse> result = service.dmt2_recipentdelete_resendOTP(prefs.getFromPrefs(mContext, "userid", ""), prefs.getFromPrefs(mContext, "password", ""), prefs.getFromPrefs(mContext, "DMT2senderMobile", ""), beneid, prefs.getFromPrefs(mContext, "senderId", ""));
        result.enqueue(new Callback<YesAddBenResponse>() {
          @Override
          public void onResponse(Call<YesAddBenResponse> call, Response<YesAddBenResponse> response) {
            if (progressDialog != null && progressDialog.isShowing())
              progressDialog.dismiss();
            if (response != null) {
              YesAddBenResponse rechargeResponse = response.body();
              if (rechargeResponse.getStatus().equals("Success")) {
                Toast.makeText(mContext, "otp resend successful.", Toast.LENGTH_SHORT).show();

              } else {
                Toast.makeText(mContext, rechargeResponse.getRemarks(), Toast.LENGTH_SHORT).show();
              }

            } else {
              Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
            }

          }

          @Override
          public void onFailure(Call<YesAddBenResponse> call, Throwable t) {
            if (progressDialog != null && progressDialog.isShowing())
              progressDialog.dismiss();
          }

        });


      }
    });
    mVerifyOtpBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ApplicationConstant.hideKeypad(DMT2DashBoardActivity.this);
        if (TextUtils.isEmpty(mOtpET.getText().toString().trim())) {
          mOtpET.setError("Enter OTP");
        } else {
          ApplicationConstant.hideKeypad(DMT2DashBoardActivity.this);
          final ProgressDialog progressDialog = CustomProgressDialog.ctor(DMT2DashBoardActivity.this);
          progressDialog.show();
          service = ApiServiceGenerator.createService(ServiceCallApiDMT2.class);
          Call<YesAddBenResponse> result = service.dmt2_benedelet_verifyOTP(prefs.getFromPrefs(mContext, "userid", ""), prefs.getFromPrefs(mContext, "password", ""), prefs.getFromPrefs(mContext, "DMT2senderMobile", ""), mOtpET.getText().toString().trim(), prefs.getFromPrefs(mContext, "senderId", ""), beneid);
          result.enqueue(new Callback<YesAddBenResponse>() {
            @Override
            public void onResponse(Call<YesAddBenResponse> call, Response<YesAddBenResponse> response) {
              if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
              if (response != null) {
                YesAddBenResponse rechargeResponse = response.body();
                if (rechargeResponse.getStatus().equals("Success")) {
                  Toast.makeText(mContext, "Beneficiary deleted successfully", Toast.LENGTH_SHORT).show();
                  alert.dismiss();

                  Intent intent = new Intent(DMT2DashBoardActivity.this, DMT2DashBoardActivity.class);
                  startActivity(intent);
                  finish();


                } else {
                  ApplicationConstant.DisplayMessageDialog(DMT2DashBoardActivity.this, Constant.Response.ERROR, "Please enter valid OTP");
                }

              } else {
                Toast.makeText(mContext, "an error occured", Toast.LENGTH_LONG).show();
              }

            }

            @Override
            public void onFailure(Call<YesAddBenResponse> call, Throwable t) {
              if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
            }
          });
        }

      }
    });

    alert.setOnKeyListener(new Dialog.OnKeyListener() {

      @Override
      public boolean onKey(DialogInterface arg0, int keyCode,
                           KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
          alert.dismiss();
          return true;
        }
        return false;
      }
    });

    alert.show();

  }

  class BenefiacryAdapter extends BaseAdapter {
    List<DMT2_Beneficiary> beneficaryList;
    Context mContext;

    public BenefiacryAdapter(Context mContext, List<DMT2_Beneficiary> beneficaryList) {
      this.mContext = mContext;
      this.beneficaryList = beneficaryList;

    }

    @Override
    public int getCount() {
      return beneficaryList.size();
    }

    @Override
    public DMT2_Beneficiary getItem(int position) {
      return beneficaryList.get(position);
    }


    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
      if (convertView == null) {
        convertView = View.inflate(mContext, R.layout.row_beneficiary, null);
        new ViewHolder(convertView);
      }
      ViewHolder holder = (ViewHolder) convertView.getTag();
      holder.mTxtAcountNumber.setText(beneficaryList.get(position).getAccount() + "");
      holder.mTxtName.setText(beneficaryList.get(position).getName() + "");
      holder.mTxtBankName.setText(beneficaryList.get(position).getBank() + "");

      if (beneficaryList.get(position).getLast_success_imps().equals("1")) {
        holder.mTxtValidate.setText("Conform_validation");
      } else {
        holder.mTxtValidate.setText("Validate");

      }
      if (beneficaryList.get(position).getLast_success_imps().equals("1")) {
        holder.mTxtValidate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            ApplicationConstant.DisplayMessageDialog(DMT2DashBoardActivity.this, "Response", "Already Validate Beneficiary");
          }
        });
      } else {
        holder.mTxtValidate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            final BottomSheetDialogFragment myBottomSheet = DMT2_BottomSheetValidateConfirmDialog.newInstance(beneficiary.get(position));
            myBottomSheet.show(DMT2DashBoardActivity.this.getSupportFragmentManager(), myBottomSheet.getTag());
          }
        });
      }


      holder.mTxtTransfer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          final BottomSheetDialogFragment myBottomSheet = DMT2_BottomSheetMoneyTransferDialog.newInstance(beneficiary.get(position));
          myBottomSheet.show(DMT2DashBoardActivity.this.getSupportFragmentManager(), myBottomSheet.getTag());

        }
      });
      holder.mTxtDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          final BottomSheetDialogFragment myBottomSheet = DMT2_BottomSheetDeleteConfirmDialog.newInstance(beneficiary.get(position));
          myBottomSheet.show(DMT2DashBoardActivity.this.getSupportFragmentManager(), myBottomSheet.getTag());

        }
      });

      return convertView;
    }


  }

  class ViewHolder {
    private TextView mTxtName;
    private TextView mTxtAcountNumber;
    private TextView mTxtBankName;
    private TextView mTxtTransfer;
    private TextView mTxtDelete;
    private TextView mTxtValidate;
    private ImageView mimageViewVerifiedNotVerified;

    public ViewHolder(View view) {
      mTxtName = (TextView) view.findViewById(R.id.txtName);
      mTxtAcountNumber = (TextView) view.findViewById(R.id.txtAcountNumber);
      mTxtBankName = (TextView) view.findViewById(R.id.txtBankName);
      mTxtTransfer = (TextView) view.findViewById(R.id.txtTransfer);
      mTxtDelete = (TextView) view.findViewById(R.id.txtDelete);
      mTxtValidate = (TextView) view.findViewById(R.id.txtValidate);
      mimageViewVerifiedNotVerified = (ImageView) view.findViewById(R.id.imageViewVerifiedNotVerified);
      view.setTag(this);
    }
  }

}


