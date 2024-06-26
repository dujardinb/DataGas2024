package fr.rvd.dsi.datagas2024.Src;

import static fr.rvd.dsi.datagas2024.BduLib.Globale.WriteLog;
import static fr.rvd.dsi.datagas2024.BduLib.Globale.hideProgressDialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Locale;

import fr.rvd.dsi.datagas2024.BduLib.AppController;
import fr.rvd.dsi.datagas2024.BduLib.Globale;
import fr.rvd.dsi.datagas2024.BduLib.Prefs;
import fr.rvd.dsi.datagas2024.R;

@SuppressWarnings("deprecation")
public class Login extends AppCompatActivity {

    public static final String TAG = AppController.class.getSimpleName();
    private static final int REQUEST_CODE_SIGNIN = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    private String seConnecter = "Se connecter avec Google";
    private String seDeconnecter = "Se déconnecter";
    private boolean isConnected;

    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private GoogleSignInClient mGoogleSignInClient;

    private  Button btContinuer;
    private  Button  btLogin ;
    private  TextView  hello ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WriteLog(TAG,"onCreate", false);
        setContentView(R.layout.activity_login);

        btContinuer = findViewById(R.id.continuer);
        btLogin = findViewById(R.id.signin);
        TextView  hello = (TextView) findViewById(R.id.hello);

        TextView txtVersion = (TextView) findViewById(R.id.txtVersion);
        String strVersion = Globale.getVersion(this);
        txtVersion.setText("(c) RVD - DSI pôle metier -" + strVersion + "\n");

        String strLangage = Locale.getDefault().getLanguage();
        if(strLangage.equalsIgnoreCase("es")) {
            seConnecter = "conéctate con google";
            seDeconnecter = "desconectarse";
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        bt_loginListener();
        bt_continuerListener();

        // vérification que le terminal est une tablette 7pce
        double diag = getDiagonaleDevice();
        if (diag < 6.0) {
            String res1 = getString(R.string.google_login_003);
            String res2 = getString(R.string.google_login_004);
            Globale.erreurMessage(this, res1 +res2, null);
            finish();
        }

        // vérification GooglePlay Service
        if (!isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices();
        }





    }
    protected void onStart() {
        super.onStart();
        WriteLog(TAG,"OnStart", false);

    }

    @Override
    protected void onResume(){
        super.onResume();
        WriteLog(TAG,"onResume", false);

    }

    // gestion de l'authentification Google
    @SuppressWarnings("deprecation")
    private void signin() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, REQUEST_CODE_SIGNIN);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SIGNIN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                if (account == null) return;

                signinFirebaseWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void signinFirebaseWithGoogle(String token) {
        AuthCredential credential = GoogleAuthProvider.getCredential(token, null);
        auth
                .signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult result) {
                        FirebaseUser user = result.getUser();
                        assert user != null;

                        String s = String.format(Locale.getDefault(), "Welcome, %s!", user.getDisplayName());
                        Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();
                        WriteLog(TAG,s, false);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void bt_loginListener(){
        WriteLog(TAG,"bt_loginListener", false);
       Button  button = findViewById(R.id.signin);
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
    }

    private void bt_continuerListener(){
        WriteLog(TAG,"bt_continuerListener", false);
        btContinuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globale.showProgressDialog(Login.this, getString(R.string.google_login_012));
                if (Globale.isOnline()) {
                   // todo: chargeReferentiel();
                } else {
                    hideProgressDialog();
                    Globale.erreurMessage(Login.this, getString(R.string.google_login_013), null);
                }
            }
        });
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        hello.setText("");
        Prefs.setPrefAccountName(Login.this, null);
        Prefs.setDisplayName(Login.this, null);
        Prefs.setPrefAccountPhotoUrl(Login.this, null);
        isConnected = false;
        btLogin.setText(seConnecter);
        btContinuer.setVisibility(View.GONE);
        hideProgressDialog();
    }


    /**
     * Retour la diagonale en inch du device
     *
     * @double diagonale en inch
     */
    private double getDiagonaleDevice() {
        DisplayMetrics metrics = new DisplayMetrics();
        Login.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float yInches = metrics.heightPixels / metrics.ydpi;
        float xInches = metrics.widthPixels / metrics.xdpi;
        return Math.sqrt(xInches * xInches + yInches * yInches);
    }

    /**
     * Check that Google Play services APK is installed and up to date.
     *
     * @return true if Google Play Services is available and up to
     * date on this device; false otherwise.
     */
    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        final int connectionStatusCode = apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }


    /**
     * Attempt to resolve a missing, out-of-date, invalid or disabled Google
     * Play Services installation via a user dialog, if possible.
     */
    private void acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        final int connectionStatusCode = apiAvailability.isGooglePlayServicesAvailable(this);
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
    void showGooglePlayServicesAvailabilityErrorDialog(final int connectionStatusCode) {

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(this, connectionStatusCode, REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }



}