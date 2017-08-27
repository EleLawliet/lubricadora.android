package com.grupopulpo.lubriacadora.duty.tools;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.grupopulpo.lubriacadora.duty.R;
import com.grupopulpo.lubriacadora.duty.web.VolleySingleton;

/**
 * Creado por Andres Cantos el 13/11/2015
 */
public class BaseActivity extends AppCompatActivity {
    String ced;
    VolleySingleton objvolley;
    RequestQueue colaPeticioneshttp;
    Toolbar toolbar;
    ProgressDialog barraprogreso;
    private SirSessionManager preferenciadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenciadd = new SirSessionManager(this);

        int mostrarbotonatras =1;
        int mapeartoolbar = 0;
        mostrarbotonatras = savedInstanceState.getInt("verbtnatras",1);
        mapeartoolbar = savedInstanceState.getInt("vertoolbar",0);

        setContentView(savedInstanceState.getInt("layout"));
        objvolley = VolleySingleton.getInstance(this);
        colaPeticioneshttp = objvolley.getRequestQueue();

//        if(mapeartoolbar==1) {
//
//           // toolbar = (Toolbar) findViewById(R.id.toolbardinardap);
//            //toolbar.setSubtitle("Ingreso Factura");
//            setSupportActionBar(toolbar);
//
//            if(mostrarbotonatras==1)
//                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//            else
//                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//
//        }

    }

    public void agregarPeticionHttp(RequestApp request) {
        //HttpsTrustManager.allowAllSSL();
        Log.i("https","Si se ejecuto.");
        if (request != null) {
            request.setTag(this);
            if (colaPeticioneshttp == null)
                colaPeticioneshttp = objvolley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            onPreStartConnection();
            colaPeticioneshttp.add(request);
        }
    }

    public void agregarPeticionHttpSinLoad(RequestApp request) {
        //HttpsTrustManager.allowAllSSL();
        if (request != null) {
            request.setTag(this);
            if (colaPeticioneshttp == null)
                colaPeticioneshttp = objvolley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            //onPreStartConnection();
            colaPeticioneshttp.add(request);
        }
    }

    public void onPreStartConnection() {
        //this.setSupportProgressBarIndeterminateVisibility(true);
        if(barraprogreso==null) barraprogreso = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
        barraprogreso.show();
        barraprogreso.setContentView(R.layout.progressdialogeneral);
        barraprogreso.setCancelable(false);
        //barraprogreso.setProgressStyle(Widge);
    }

    public void onConnectionFinished() {
        //this.setProgressBarIndeterminateVisibility(false);
        if(barraprogreso!=null) barraprogreso.dismiss();
    }

    public void onConnectionFailed(String error) {
        //this.setProgressBarIndeterminateVisibility(false);
        if(barraprogreso!=null) barraprogreso.dismiss();
        Log.e("multicash", "error volley: " + error);
        Toast.makeText(this, this.getResources().getString(R.string.mensajeerrorconexion), Toast.LENGTH_SHORT).show();
    }

    public void onConnectionFailed() {
        //this.setProgressBarIndeterminateVisibility(false);
        if(barraprogreso!=null) barraprogreso.dismiss();
    }

    public SirSessionManager getPreferenciacb() {
        return preferenciadd;
    }

}
