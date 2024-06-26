package fr.rvd.dsi.datagas2024.BduLib;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class InfoDevice {
    private Context ct;

    public InfoDevice(Context context) {
        setCt(context);
    }

    public String GetSimNumber() {
        String retvalue = "";
        try {
            TelephonyManager telMgr =
                    (TelephonyManager) getCt().getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(ct, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                String simSerialNumber = "Inconu.";
                retvalue = simSerialNumber;
            }

        } catch (Exception e) {
            retvalue = "NA";
        }
        return  retvalue;
    }

    @SuppressLint("MissingPermission")
    public String GetIMEI() {
        String retvalue = "";
        String identifier = null;
        TelephonyManager tm = (TelephonyManager) getCt().getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
                identifier = tm.getDeviceId();
                if(identifier == null) identifier = getMacAddr();
                retvalue = identifier;

        } else {
            retvalue = identifier;
        }
        return  retvalue;
    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

    public String GetphoneOperator(){
        try{
            TelephonyManager telephonyManager = ((TelephonyManager) getCt().getSystemService(Context.TELEPHONY_SERVICE));
            String operatorName = telephonyManager.getNetworkOperatorName();
            return operatorName;
        }
        catch (Exception e) {
            return "NA";
        }
    }

    public String GetMarque(){
        return Build.BRAND + " -" + Build.MODEL;

    }
    public String getDeviceID(){
        return Secure.getString(getCt().getContentResolver(),  Secure.ANDROID_ID);
    }

    private Context getCt() {
        return ct;
    }

    private void setCt(Context ct) {
        this.ct = ct;
    }
}
