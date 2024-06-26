package fr.rvd.dsi.datagas2024.BduLib;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import fr.rvd.dsi.datagas2024.R;


/********************************************************************************************
 * Utilisation de l'activité Erreur a partir d'une autre activité
 * ----------------------------------------------------------------
 * Intent intent = new Intent(this, Erreur.class);
 * intent.putExtra("MESSAGE",  getResources().getString(R.string.pas_de_reseau));
 * ou  intent.putExtra("MESSAGE",  "un message qui peut être mis en forme avec des balises html)
 * Si le message contient un séparateur | la premiere partie
 * est affichée la deuxieme est visible avec le bouton plus d'info...
 * startActivity(intent);
 * finish();
 ********************************************************************************************/
public class Erreur extends Activity {
    private Button btvalider = null;
    private Button btDetail = null;
    private TextView txtError = null;
    private boolean vudetail = false;

    private String message2, message1;
    /* -------------------------------------------------------
     * Dispatch vers les activités
     *-------------------------------------------------------*/
    private OnClickListener evtClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int resid = (v.getId());
            switch (resid) {
                case R.id.btValider:
                    finish();
                    break;
                case R.id.btDetail:
                    if (vudetail) {  // la vu détail est affiché on passe en normal
                        btDetail.setText("Retour");
                        txtError.setText(fromHtml(message2));
                        vudetail = false;
                    } else {        // la vu normal est affiché on veut la vu détail
                        btDetail.setText("Detail");
                        vudetail = true;
                        txtError.setText(fromHtml(message1));
                    }

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erreur);

        btvalider = (Button) findViewById(R.id.btValider);
        btDetail = (Button) findViewById(R.id.btDetail);
        txtError = (TextView) findViewById(R.id.txtInfo);
        btvalider.setOnClickListener(evtClick);
        btDetail.setOnClickListener(evtClick);
        Intent intent = getIntent();
        message1 = intent.getStringExtra("MESSAGE1");
        message2 = intent.getStringExtra("MESSAGE2");
        // Gestion du message détail
        if (message2 == null) {
            btDetail.setVisibility(View.GONE);
        }

        // on affiche le message principal
        txtError.setText(fromHtml(message1));
    }
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

}