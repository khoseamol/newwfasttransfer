package com.example.webplat.amoldesigning.helper;

import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
    public static String getDate(String date) {
        if(date==null)
            return "";
        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(testDate);
    }


    public static String getTime(String date) {
        if(date==null)
            return "";
        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        return formatter.format(testDate);
    }
    public static String getDate(){
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        System.out.println(today.month);
        return  getMonthName(today.month)  +" "+today.year;
    }

    public static String getMonthName(int month){
        switch(month+1){
            case 1:
                return "Jan";

            case 2:
                return "Feb";

            case 3:
                return "Mar";

            case 4:
                return "Apr";

            case 5:
                return "May";

            case 6:
                return "Jun";

            case 7:
                return "Jul";

            case 8:
                return "Aug";

            case 9:
                return "Sep";

            case 10:
                return "Oct";

            case 11:
                return "Nov";

            case 12:
                return "Dec";
        }

        return "";
    }
}
