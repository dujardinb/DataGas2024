package fr.rvd.dsi.datagas2024.BduLib;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import fr.rvd.dsi.datagas2024.R;


/**
 * Created by B Dujardin on 07/03/2015.
 */
public class Globale {

    public static final String charsetUTF8 = "UTF-8";
    public static final String charsetISO = "ISO-8859-1";
    private static ProgressDialog mProgressDialog = null;

    /*****************************************************************************
     * Ajoute une entrée dans le fichier des logs
     * si le fichier des logs n'existe pas il est crée automatiquement sous le nom VEOLIAMAP.LOG
     *
     * @param
     ***************************************************************************************/
    public static final void WriteLog(String TAG, String ligne, Boolean onFile) {
        String myLigne = "";
        BDU_Date cal = new BDU_Date();

        if(onFile) {
            File file = new File(Environment.getExternalStorageDirectory(),
                    AppController.class.getSimpleName() + ".LOG");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            FileOutputStream fout;
            try {
                fout = new FileOutputStream(file, true);
                myLigne = cal.GetDateFormat("yyy-MM-dd HH:MM:SS") + " - " + TAG + "- " + ligne + "\r\n";
                fout.write(myLigne.getBytes());
                fout.flush();
                fout.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
             myLigne = cal.GetDateFormat("yyy-MM-dd HH:MM:SS") + " - " + ligne + "\r\n";
             Log.d(TAG, myLigne);
        }


    }

    /********************************************************
     * AFFICHE UN MESSAGE D ALERTE
     *
     * @param ct       context de l'appelant
     * @param pMessage message a afficher
     **************************************************************/
    public static void AlertMessage(Context ct, String pMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ct);
        builder.setTitle("Alerte");
        builder.setIcon(R.drawable.warning48);
        builder.setMessage(pMessage);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /************************************************
     * INDIQUE SI UNE CONNECTIVITE INTERNET EXISTE
     *
     * @return true ou false
     *************************************************/
    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getMacAdresse(Context ct) {
        WifiManager wifiManager = (WifiManager) ct.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();
        return macAddress;
    }

    /*****************************************
     * Ecriture d'un fichier texte
     *
     * @param sFileName
     * @param data
     * @return vide ou message d'erreur
     */
    public static String writeTextFile(String sFileName, String data) {
        String errorMessage = "";
        try {
            File root = new File(Environment.getExternalStorageDirectory(),
                    AppController.getApplicationName());
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(data);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            errorMessage = e.getMessage();

        }
        return errorMessage;
    }

    public static String writeTextFileCharset(String sFileName, String data, String charset) {
        FileOutputStream fileStream = null;
        String errorMessage = "";
        try {
            File root = new File(Environment.getExternalStorageDirectory(),
                    AppController.getApplicationName());
            if (!root.exists()) {
                root.mkdirs();
            }
            fileStream = new FileOutputStream(new File(root, sFileName));
            try {
                OutputStreamWriter writer = new OutputStreamWriter(fileStream, charset);
                try {
                    writer.write(data);
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                errorMessage = e.getMessage();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            errorMessage = e.getMessage();
        }

        return errorMessage;
    }

    // Retourne le numero de version "versionName" qui est défini dans le gradle
    public static String getVersion(Context ct) {
        PackageInfo pInfo;
        String ret;
        try {
            pInfo = ct.getPackageManager().getPackageInfo(ct.getPackageName(), 0);
            ret = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            ret = "N/A";
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Affiche une activité erreur
     *
     * @param ct    : Context
     * @param mess1 : Message principal
     * @param mess2 : Message secondaire ou null
     */
    public static void erreurMessage(Context ct, String mess1, String mess2) {
        Intent intent = new Intent(ct, Erreur.class);
        intent.putExtra("MESSAGE1", mess1);
        intent.putExtra("MESSAGE2", mess2);
        ct.startActivity(intent);
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    /**
     * ************************************************************************************
     *
     * @View view view de l'activité appelante
     * @String mess  Message a afficher (currentView = findViewById(android.R.id.content);)
     * @Boolean viewOnTop Afficher au haut de l'écran par défaut en bas
     */
    public static void snackbarShow(View view, String mess, Boolean viewOnTop) {
        Snackbar snack = Snackbar.make(view, mess, Snackbar.LENGTH_LONG);
        if (viewOnTop) {
            View viewSnack = snack.getView();
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) viewSnack.getLayoutParams();
            params.gravity = Gravity.TOP;
            viewSnack.setLayoutParams(params);
        }
        snack.show();
    }

    public static void toastShow(String mess) {
        Toast.makeText(AppController.getAppContext(),
                mess, Toast.LENGTH_LONG).show();
    }

    /**********************************************************
     * Affiche une boite de dialogue qui affiche un message
     *
     * @String mess : message a affichier
     */
    public static void dialogueErreur(Context ct, String mess) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(ct);
        builder.setMessage(mess);
        builder.setTitle("Erreur");
        builder.setIcon(R.drawable.ic_priority_high_black_24dp);
        builder.setCancelable(true);
        builder.show();
    }

    public static void showProgressDialog(Context ct, String message ) {


        if(message == null) message = "Patientez ...";
        mProgressDialog = new ProgressDialog(ct);
        mProgressDialog.setMessage(message);
        mProgressDialog.setIndeterminate(true);

        mProgressDialog.show();
    }

    public static void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
            mProgressDialog = null;
        }
    }

    /**************************************************
     * Vérification que l'email est un compte Veolia.com
     *
     * @return true ou false
     * @String email  email a vérifier
     */
    public static boolean chechEmailVeolia(String email) {
        boolean res = false;
        String[] split = email.split("@");
        String domaine = split[1].toString();
        if (domaine.equalsIgnoreCase("veolia.com")) res = true;
        if (domaine.equalsIgnoreCase("gmail.com")) res = true;
        return res;
    }


    /**
     * Retourne une chaine préparée pour insertion dans une BDD
     * @String value chaine source
     * @String chaine avec quote
     */
    public static String quote(String value) {
        String results =  value.replace("'","''");
        return "'"+results + "'";
    }

    /*****************************************
     * SUPPRESSION DU BOM SUR UNE CHAINE
     *
     * @return chaine sans le BOM
     * @String value chaine de traiter
     */
    public static String RemoveBOM(String value) {
        String res;
        try {
            res = value.substring(value.indexOf("{"),
                    value.lastIndexOf("}") + 1);
        } catch (Exception e) {
            res = value;
        }
        return res;
    }

    public static double getDebit(double diametre, double vitesse ) {
        double retvalue = 0.0;
        if(vitesse != 0.0 )  {
            retvalue = 2375 * vitesse * Math.pow((diametre/1000),2);
        }
        return retvalue;
    }
    public static double  convStringToDouble(String strValue) {
        String strnew = strValue.replace(",", ".");

        strnew = strnew.replace("\u00A0","");
        strnew = strnew.replace("\u8239","");
        strnew = strnew.replace("\u202F","");
        strnew = strnew.replace(" ","");

        strnew = strnew.trim();
        if(strnew.length() == 0) strnew = "0";

        Log.d("DATAGAS", "Valeur strnew : " + strnew);

        return Double.parseDouble(strnew);
    }

    /**************************************************************
     * Retourne la ressource String pour l'identifiant  astring
     * @Context ct
     * @pString aString
     * @return STring
     */
    public static String getStringResourceByName(String aString) {
        Context ct = AppController.getAppContext();

        String packageName = ct.getPackageName();
        int resId = ct.getResources().getIdentifier(aString, "string", packageName);
        if (resId == 0) {
            return aString;
        } else {
            return ct.getString(resId);
        }
    }


}
