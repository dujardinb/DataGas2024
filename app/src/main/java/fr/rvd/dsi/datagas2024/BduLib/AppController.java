package fr.rvd.dsi.datagas2024.BduLib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;

import androidx.multidex.MultiDexApplication;

/**
 * Created by brunodujardin on 08/11/2015.
 * public static Context getAppContext() {
 * return mInstance;
 * }
 */

public class AppController extends MultiDexApplication {

    public static final String TAG = AppController.class.getSimpleName();

    private static AppController mInstance;
    private static Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        resources = getResources();
    }

    public static Context getAppContext() {
        return mInstance;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    // retourne le nom de l'application
    public static String getApplicationName() {
        ApplicationInfo applicationInfo = getAppContext().getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        String nomApp = null;
        if(stringId == 0 ) {
            nomApp =  applicationInfo.nonLocalizedLabel.toString();
        } else {
            nomApp = getAppContext().getString(stringId);
        }
        return nomApp;
    }




    public static Resources getAppResources() {
        return resources;
    }




}


