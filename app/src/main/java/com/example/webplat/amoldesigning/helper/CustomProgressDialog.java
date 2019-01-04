package com.example.webplat.amoldesigning.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;

import com.example.webplat.amoldesigning.R;


/**
 * Created by webplat on 28/08/2015.
 */
public class CustomProgressDialog extends ProgressDialog {

    public static ProgressDialog ctor(Activity activity) {
        CustomProgressDialog dialog = new CustomProgressDialog(activity, R.style.myDialogTheme);
        dialog.setIndeterminate(true);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;

        dialog.setCancelable(false);
        return dialog;
    }


    public CustomProgressDialog(Context context, int theme) {
        super(context,theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_progress_dialog);
    }

    @Override
    public void show() {
        super.show();
    }



}