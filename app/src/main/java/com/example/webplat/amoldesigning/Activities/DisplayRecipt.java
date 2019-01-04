package com.example.webplat.amoldesigning.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.Constant;
import com.example.webplat.amoldesigning.pojo.bbpshistory.Datum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by webplat2 on 30/8/18.
 */

public class DisplayRecipt extends AppCompatActivity {
    TextView txtBillerCategory, txtBBPSID, txtBillerId,txtBillerName, txtTransactiondateTime,txtStatus, txtAmount,txtConveniancefee,txtBillerAmount,txtBillDate,txtpaymentmode,txtTransactionreferenceNumber,txtCustomerMobileNumber;
    LinearLayout relativeLayout;
    ImageView img;
    Bitmap bitmap;
    Button b;

    String TAG;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_get_receipt);
        txtBillerCategory = (TextView)findViewById(R.id.BiilerCategory1);
        txtBillDate = (TextView)findViewById(R.id.billdate1);
        txtBBPSID = (TextView)findViewById(R.id.BBPSID1);
        txtAmount = (TextView)findViewById(R.id.TotalAmount1);
        txtBillerAmount = (TextView)findViewById(R.id.billAmount1);
        txtBillerName = (TextView)findViewById(R.id.BillerName1);
        txtConveniancefee = (TextView)findViewById(R.id.Customerconviniencefree1);
        txtCustomerMobileNumber = (TextView)findViewById(R.id.Customermobilenumber1);
        txtpaymentmode = (TextView)findViewById(R.id.paymentmode1);
        txtStatus = (TextView)findViewById(R.id.TransactionStatus1);
        txtTransactionreferenceNumber = (TextView)findViewById(R.id.transactionReferenceId1);
        txtTransactiondateTime = (TextView)findViewById(R.id.transactiondateandtime1);
        txtBillerId = (TextView)findViewById(R.id.BillerId1);
        final EditText filename = (EditText) findViewById(R.id.textTitle);
        relativeLayout = (LinearLayout)findViewById(R.id.layout);
        b = (Button) findViewById(R.id.b);
        img = (ImageView)findViewById(R.id.image);
        Datum datum = Constant.datum;
        try{
            txtBillerId.setText(datum.getBillerid());
            txtTransactiondateTime.setText(datum.getTrandate());
            txtTransactionreferenceNumber.setText(datum.getTranNumber());
            txtStatus.setText(datum.getStatus());
            txtpaymentmode.setText("Cash");
            txtBillerName.setText(datum.getConsumnername()+"");
            txtCustomerMobileNumber.setText(datum.getCustmobileno());
            txtConveniancefee.setText(datum.getCost()+"");
            txtAmount.setText(datum.getAmount()+"");
            txtBillerAmount.setText(datum.getAmount()+"");
            txtBBPSID.setText(datum.getBBPSID());
            txtBillDate.setText(datum.getBilldate()+"");
            txtBillerCategory.setText(datum.getCategory());

        }catch (Exception e){

        }



b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(filename.getText().toString().isEmpty()) {
            filename.setError("Please enter file name ");
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Bimap is created",Toast.LENGTH_SHORT).show();
            if( isReadStoragePermissionGranted()&& isWriteStoragePermissionGranted()){
                try {
                    AddImage();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
});




    }

    private void AddImage() throws IOException {

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);
        String targetPdf = "/sdcard/test.pdf";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"test.pdf");
        PdfDocument document = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);

            Canvas canvas = page.getCanvas();


            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#ffffff"));
            canvas.drawPaint(paint);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

            paint.setColor(Color.BLUE);
            canvas.drawBitmap(bitmap, 0, 0 , null);
            document.finishPage(page);
            document.writeTo(new FileOutputStream(file));
            document.close();
    }}

    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted1");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted1");
            return true;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted2");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted2");
            return true;
        }
    }

    private void addImageToImageView(View v, String s, Bitmap mbitmap) {
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);
        String targetPdf = "/sdcard/"+s+".pdf";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),s+".pdf");
        PdfDocument document = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(mbitmap.getWidth(), mbitmap.getHeight(), 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);

            Canvas canvas = page.getCanvas();


            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#ffffff"));
            canvas.drawPaint(paint);
            this.bitmap = Bitmap.createScaledBitmap(this.bitmap,mbitmap.getWidth(), mbitmap.getHeight(), true);

            paint.setColor(Color.BLUE);
            canvas.drawBitmap(this.bitmap, 0, 0, null);
            document.finishPage(page);
            try {
                document.writeTo(new FileOutputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
            document.close();

        }
    }
}
