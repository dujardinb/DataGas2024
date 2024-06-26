package fr.rvd.dsi.datagas2024.BduLib;

import java.util.Calendar;

/**
 * Created by BDUJARDI on 01/03/2017.
 */

public class Bdate {
    private static Calendar calendar;
    public static final int FORMAT_DDMMAAAA = 1;
    public static final int FORMAT_AAAAMMDD = 2;


    public Bdate(){
        this.calendar = Calendar.getInstance();
    }

    public   String  getDate(int dateFormat){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dateRetour = "";

        month = month +1;
        String strDay = Integer.toString(day);
        if(strDay.length() == 1)  strDay = "0" + strDay;

        String strmois = Integer.toString(month);
        if(strmois.length() == 1)  strmois = "0" + strmois;
        if(dateFormat == FORMAT_DDMMAAAA)  dateRetour = strDay +"/" + strmois +"/" + year;
        if(dateFormat == FORMAT_AAAAMMDD)  dateRetour = year + strmois+ year;
        return  dateRetour;
    }

    public  String getTime() {

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int secondes = calendar.get(Calendar.SECOND);

        String strHeure = Integer.toString(hour);
        if(strHeure.length() == 1)  strHeure = "0" + strHeure;

        String strminutes = Integer.toString(minutes);
        if(strminutes.length() == 1)  strminutes = "0" + strminutes;

        String strSecondes = Integer.toString(secondes);
        if(strSecondes.length() == 1)  strSecondes = "0" + strSecondes;
        return strHeure+ ":" + strminutes + ":" + strSecondes;

    }

    public  String getDay() {
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        String strDay = Integer.toString(d);
        if(strDay.length() == 1)  strDay = "0" + strDay;
        return strDay;
    }

    public  String getMonth() {
        int m = calendar.get(Calendar.MONTH) + 1;
        String strMonth = Integer.toString(m);
        if(strMonth.length() == 1)  strMonth = "0" + strMonth;
        return strMonth;
    }
    public String getYear() {
        int y = calendar.get(Calendar.YEAR);
        String strYear = Integer.toString(y);
        return strYear;
    }

    public String getHour() {
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        String strHour = Integer.toString(h);
        return strHour;
    }

    public  String getMin() {
        int m = calendar.get(Calendar.MINUTE);
        String strMin = Integer.toString(m);
        return strMin;
    }
    public  String getSec() {
        int s = calendar.get(Calendar.SECOND);
        String strSec = Integer.toString(s);
        return strSec;
    }

}
