package fr.rvd.dsi.datagas2024.Src;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import fr.rvd.dsi.datagas2024.BduLib.Globale;
import fr.rvd.dsi.datagas2024.BduLib.Prefs;
import fr.rvd.dsi.datagas2024.BduLib.Utility;
import fr.rvd.dsi.datagas2024.R;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class Permission extends AppCompatActivity {
    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(Utility.permissionCheck(this)){
            runApplication();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST:
                boolean continuer = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) continuer = false;
                }
                if (continuer) {
                    //if (userChoosenTask.equals("STORAGE"))
                    //    performTaskOperation();//this method what you need to perform
                    Toast.makeText(this, "Permission OK", Toast.LENGTH_LONG).show();
                    runApplication();
                }
                break;
        }
    }

    private void runApplication(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    // -------------------------------------------------------------------------

    private void checkGoogleAut(){

        if (!isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices();
        }  else if (!Globale.isOnline()) {
            Globale.AlertMessage(this,getString(R.string.permission_001));
        } else {
            runApplication();
        }
    }

    /**
     * Check that Google Play services APK is installed and up to date.
     *
     * @return true if Google Play Services is available and up to
     * date on this device; false otherwise.
     */
    private boolean isGooglePlayServicesAvailable() {

        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }

    /**
     * Attempt to resolve a missing, out-of-date, invalid or disabled Google
     * Play Services installation via a user dialog, if possible.
     */
    private void acquireGooglePlayServices() {

        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
        }
    }
    /**
     * Display an error dialog showing that Google Play Services is missing
     * or out of date.
     *
     * @param connectionStatusCode code describing the presence (or lack of)
     *                             Google Play Services on this device.
     */
    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode) {

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(
                this,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
                    Globale.AlertMessage(this, getString(R.string.permission_002) +
                            getString(R.string.permission_003));

                } else {
                    checkGoogleAut();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);

                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode == RESULT_OK) {
                    checkGoogleAut();
                }
                break;
        }
    }

}
