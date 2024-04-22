package com.qppd.rizalelearning.Libs.DateTimez;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeClass {

    SimpleDateFormat sdf;
    Calendar calendar;

    public DateTimeClass(){

    }

    public DateTimeClass(String format){

        calendar = Calendar.getInstance();
        sdf = new SimpleDateFormat(format, Locale.US);

    }

    public String getFormattedTime(){
        return sdf.format(calendar.getTime());
    }

    public Date getDateFromString(String value){
        try {

            return sdf.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getMonth(){
        return calendar.get(Calendar.DATE);
        //return calendar.get(Calendar.MONTH);;
    }

    public int getEndDate(){
        return calendar.getActualMaximum(Calendar.DATE);
    }

    public int getCurrentDay(){
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public int getYear(){
        return calendar.get(Calendar.YEAR);
    }


}
