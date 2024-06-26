package fr.rvd.dsi.datagas2024.BduLib;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.rvd.dsi.datagas2024.R;


/**
 * Created by ASUS on 07/03/2015.
 */
public class BDU_Date {
    public static final int DATE_JOUR = 1;
    public static final int DATE_MOIS = 2;
    public static final int DATE_ANNEE = 3;
    public static final int DATE_HEURE = 4;
    public static final int DATE_MINUTE = 5;
    public static final int DATE_SECONDE = 6;

    public static final String DATE_FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_FORMAT_DATE_TIME1 = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_DATE = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DATE1 = "yyyyMMdd";
    public static final String DATE_FORMAT_TIME = "HH:mm:ss";

    private Calendar calendar = null;

    // constructeur
    public BDU_Date(){
        calendar = Calendar.getInstance();
    }
    public BDU_Date(Date dt){
        calendar.setTime(dt);
    }


    public Date Getdate(){
        return calendar.getTime();
    }


    /*******************************************************
     * RETOURNE UNE DATE SELON LE FORMAT format
     * @param format : Format de la date retourn
     * @return Chaine Date
     */
    public String GetDateFormat(String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(calendar.getTime());
    }
    /*****************************************************
     * AJOUTE UN NBRE DE JOUR,MOIS, ANNEE, HEURE, MINUTES, SECONDES A L OBJET
     * @param typeConstDate : Type d'unit� � ajouter ou soustraire
     * @param value : Nbre positif ou n�gatif
     */
    public void DateAdd(int typeConstDate, int value){
        switch (typeConstDate) {
            case DATE_JOUR:
                calendar.add(Calendar.DATE, value);
                break;
            case DATE_MOIS:
                calendar.add(Calendar.MONTH, value);
                break;
            case DATE_ANNEE:
                calendar.add(Calendar.YEAR, value);
                break;
            case DATE_HEURE:
                calendar.add(Calendar.HOUR, value);
                break;
            case DATE_MINUTE:
                calendar.add(Calendar.MINUTE, value);
                break;
            case DATE_SECONDE:
                calendar.add(Calendar.SECOND, value);
                break;
            default:

        }
    }



    /**
     * Retourne la date et l'heure courante ay format yyyy MM dd-HH:mm:ss
     * @return chaine date
     */
    public String GetCurrentDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd-HH:mm:ss");

        return sdf.format(calendar.getTime());

    }
    /**
     * Retourne la date strDate en millisecondes
     * @param strdate : Chaine date
     * @param strformat : format de la date
     * @return nombre de millisecondes
     */
    public long dateToMilliSeconde(String strdate, String strformat){
        DateFormat format = new SimpleDateFormat(strformat);
        long newdate =0;
        Date date;
        try {
            date = format.parse(strdate);
            newdate = date.getTime();
            return newdate;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * Converti un nombre de millisecondes en date
     * @param milliSecond : nombre a convertir
     * @param strFormat  : format a appliquer a la date
     * @return une chaine date
     */
    public String milliSecondeToDate(long milliSecond, String strFormat){
        SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSecond);
        return formatter.format(calendar.getTime());
    }
    /**
     * Convesion millisecondes dans l'unitꥠprꤩs鍊	 * @param milliSecond: Valeur en millisecondes a convertir
     * @param unite: unit顤e conversion 1:Convertir en Heures 2: Convertir en minutes
     * @return
     */
    public long milliSecondeToUnite(long milliSecond, int unite ){
        long res =0;
        if(unite ==  1 ){ // convertion en heure
            res = milliSecond / 3600000;
        }
        if(unite ==  2 ){ // convertion en minutes
            res = milliSecond / 60000;
        }
        return res;
    }

    public String millisecondetoDelai(long milliSeconde){
        String retValue = "";
        long ms = milliSeconde;
        long ms_Sec = 1000;
        long ms_minute = 60*ms_Sec;
        long ms_heure = 60* ms_minute;
        long ms_jour = 24 * ms_heure;
        long nbjours = (ms / ms_jour);
        ms = ms - (nbjours*ms_jour);
        long nbheure =  (ms / ms_heure);
        ms = ms - (nbheure * ms_heure);
        long nbmin = (ms / ms_minute);
        ms = ms -(nbmin * ms_minute);
        long nbsec = (ms / ms_Sec);
        if (nbjours > 0) retValue = retValue + String.valueOf(nbjours)+ R.string.bdu_date_001;
        if (nbheure > 0 ) retValue = retValue + String.valueOf(nbheure)+R.string.bdu_date_002;
        if(nbmin > 0 ) retValue = retValue + String.valueOf(nbmin)+R.string.bdu_date_003;
        if(nbsec > 0 ) retValue = retValue + String.valueOf(nbsec)+R.string.bdu_date_004;

        return retValue;

    }
}
