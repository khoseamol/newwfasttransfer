package com.example.webplat.amoldesigning.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;


import com.example.webplat.amoldesigning.pojo.operator.OperatorData;
import com.example.webplat.amoldesigning.pojo.operator.OperatorList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14/09/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "operator.db";
    private static final int DB_VERSION = 3;
    private static final String TAG = "DBHelper";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DataTable.TABLE_OPERATOR + " ("
                + DataTable._ID + " INTEGER PRIMARY KEY,"
                + DataTable.COLUMN_OPID + " TEXT,"
                + DataTable.CCOLUMN_OPTYPE + " TEXT,"
                + DataTable.CCOLUMN_OPERATORNAME + " TEXT,"
                + DataTable.CCOLUMN_OPERATORIMAGE + " TEXT,"
                + DataTable.COLUMN__OPDATETIME + " TIMESTAMP DEFAULT (datetime('now','localtime'))"
                + ");");





    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Logs that the database is being upgraded
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        // Kills the table and existing data
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS notes");
    }

    public void insertOperator(OperatorData operatorList2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataTable.COLUMN_OPID, operatorList2.getOurCode());
        contentValues.put(DataTable.CCOLUMN_OPTYPE, operatorList2.getServiceName());
        contentValues.put(DataTable.CCOLUMN_OPERATORNAME, operatorList2.getOperatorName());
        contentValues.put(DataTable.CCOLUMN_OPERATORIMAGE, operatorList2.getImage());

        db.insert(DataTable.TABLE_OPERATOR, null, contentValues);
    }


    public List<OperatorList> fetchAllOperatorByType(String queryString ){
        SQLiteDatabase db = this.getWritableDatabase();
        List<OperatorList> operatorListArrayList = new ArrayList<OperatorList>();


        Cursor cursor = db.rawQuery("select * from " + DataTable.TABLE_OPERATOR + " WHERE " + DataTable.CCOLUMN_OPTYPE + "='" + queryString + "'", null);

        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                OperatorList operatorList = new OperatorList();
                operatorList.setOpType(cursor.getString(cursor.getColumnIndex(DataTable.CCOLUMN_OPTYPE)));
                operatorList.setOpId(cursor.getString(cursor.getColumnIndex(DataTable.COLUMN_OPID)));
                operatorList.setOpName(cursor.getString(cursor.getColumnIndex(DataTable.CCOLUMN_OPERATORNAME)));
                operatorList.setOpImageURL(cursor.getString(cursor.getColumnIndex(DataTable.CCOLUMN_OPERATORIMAGE)));
                operatorListArrayList.add(operatorList);
                cursor.moveToNext();
            }
        }
        return operatorListArrayList;
    }

    public void deleteExistingOperator() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + DataTable.TABLE_OPERATOR);
    }




    public static final class DataTable implements BaseColumns {

        public static final String TABLE_OPERATOR = "operator_table";
        public static final String COLUMN_OPID = "opid";
        public static final String CCOLUMN_OPTYPE = "operator_type";
        public static final String CCOLUMN_OPERATORNAME = "operator_name";
        public static final String CCOLUMN_OPERATORIMAGE = "operator_image";
        public static final String COLUMN__OPDATETIME = "op_date_time";




    }

}