package fr.rvd.dsi.datagas2024.BduLib;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;


/**
 * Created by brunodujardin on 08/01/2017.
 */

public class Utility {

    public static final int MY_PERMISSIONS_REQUEST = 123;

    public static boolean checkPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{Manifest.permission.ACCESS_WIFI_STATE,

                                        Manifest.permission.READ_PHONE_STATE,
                                        Manifest.permission.INTERNET,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.GET_ACCOUNTS},
                                MY_PERMISSIONS_REQUEST);
                    } else {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{Manifest.permission.ACCESS_WIFI_STATE,

                                        Manifest.permission.READ_PHONE_STATE,
                                        Manifest.permission.INTERNET,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.GET_ACCOUNTS},
                                MY_PERMISSIONS_REQUEST);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean permissionCheck(Context ct){
        boolean res = Utility.checkPermissions(ct,

                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.GET_ACCOUNTS);
        return res;
    }
}

