package com.example.webplat.amoldesigning.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webplat.amoldesigning.moneytransfer_DMT.DMT2_MoneyRegister;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ApplicationConstant {

    public static final String IMAGEWEBSERVICEURL = "http://login.vallinone.com//images/Recharge/Operators/";
    public static final String WEBSERVICEURL = "http://fasttransfer.in/api/";
    public static final String MOBILESERVICEID = "1";
    public static final String DTHSERVICEID = "2";
    public static final String DATACARDSERVICEID = "3";
    public static final String POSTPAIDSERVICEID = "4";
    public static final String ELECTRICITYSERVICEID = "6";
    public static final String INSURANCESERVICEID = "5";
    public static final String GASSERVICEID = "7";
    public static final String BRODBANDSERVICEID = "10";
    public static final String LANDLINESERVICEID = "11";
    private static Context context;

    public static void setEditTextMaxLength(EditText editText, int length) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        editText.setFilters(FilterArray);
    }

    public static void displayToastMessage(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public static void hideKeypad(Activity activity) {
        if (activity != null) {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) activity
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(activity
                        .getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
            }
        }
    }

    public static void DisplayMessageDialog(Activity activity, String title, String message) {
        if (activity != null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
            builder1.setMessage(message);
            builder1.setCancelable(true);
            builder1.setTitle(title);

            builder1.setPositiveButton(
                    "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    public static String getDaysBeetweenTwoDays(String fromDate, String toDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");


        try {
            Date date1 = myFormat.parse(fromDate);
            Date date2 = myFormat.parse(toDate);
            long diff = date2.getTime() - date1.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + " Days";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0 Days";
    }

    public static void DisplayMessageForDialog(Activity dmt2_moneyRegister, String error, String remarks) {
        if (context != null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage(remarks);
            builder1.setCancelable(true);
            builder1.setTitle(error);

            builder1.setPositiveButton(
                    "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
}
