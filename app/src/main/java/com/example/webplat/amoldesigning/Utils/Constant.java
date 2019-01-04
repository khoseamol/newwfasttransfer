package com.example.webplat.amoldesigning.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.inputmethod.InputMethodManager;

import com.example.webplat.amoldesigning.pojo.bbpshistory.Datum;

/**
 * Created by webplat6 on 4/2/18.
 */

public class Constant {
    public static final String SelectedOperatorID = "OPID";

    public static final String SelectedOperator = "Selected Operator";
    public static final String OPERATORTYPE = "operator_TYPE";
    public static Datum datum;
    public static final String SelectedServicCode="";
    public static   Double RemainingLimit =0.00;

    public class Response {
        public static final String ERROR = "Error";
        public static final String Success="SUCCESS";
    }
    public class USERDETAILS {
        public static final  String UserType="usertype";
        public static final String UserName="username";
        public static final  String OwnerName="OwnerName";
        public static final String MainBalance="MainBalance";
        public static final String LastSeen="LastSeen";


        public static final String PSSWORD="PSSWORD";
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

}
