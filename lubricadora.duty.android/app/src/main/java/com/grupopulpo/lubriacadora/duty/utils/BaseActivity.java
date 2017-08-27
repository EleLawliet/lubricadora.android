package com.grupopulpo.lubriacadora.duty.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.grupopulpo.lubriacadora.duty.R;
import com.grupopulpo.lubriacadora.duty.http.RequestApp;
import com.grupopulpo.lubriacadora.duty.http.ResourceVolley;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Creado por Andres Cantos el 13/11/2015
 */
public class BaseActivity extends AppCompatActivity {
    String ced;
    ResourceVolley objvolley;
    RequestQueue colaPeticioneshttp;
    Toolbar toolbar;
    Utils objUtils;
    private SirSessionManager preferenciadd;
    private  SweetAlertDialog objSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferenciadd = new SirSessionManager(this);

        int mostrarbotonatras =1;
        int mapeartoolbar = 0;
        mostrarbotonatras = savedInstanceState.getInt("verbtnatras",0);
        mapeartoolbar = savedInstanceState.getInt("vertoolbar",0);

        setContentView(savedInstanceState.getInt("layout"));
        objvolley = ResourceVolley.getInstance(this);
        colaPeticioneshttp = objvolley.getQueueVolley();
        objUtils=new Utils();
        if(mapeartoolbar==1) {

            toolbar = (Toolbar) findViewById(R.id.toolbardinardap);
            //toolbar.setSubtitle("Ingreso Factura");
            setSupportActionBar(toolbar);

            if(mostrarbotonatras==1)
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            else
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        }


    }
    public void agregarPeticionHttp(RequestApp request) {
       // HttpsTrustManager.allowAllSSL();
        Log.i("https","Si se ejecuto.");
        if (request != null) {
            request.setTag(this);
            if (colaPeticioneshttp == null)
                colaPeticioneshttp = objvolley.getQueueVolley();
            request.setRetryPolicy(new DefaultRetryPolicy(60000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            onPreStartConnection();
            colaPeticioneshttp.add(request);
        }
    }

    public void agregarPeticionHttpSinLoad(RequestApp request) {
       // HttpsTrustManager.allowAllSSL();
        if (request != null) {
            request.setTag(this);

            if (colaPeticioneshttp == null)
                colaPeticioneshttp = objvolley.getQueueVolley();//mir
            request.setRetryPolicy(new DefaultRetryPolicy(60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            //onPreStartConnection();
            colaPeticioneshttp.add(request);//Ya viste si esta llegando el request
        }
    }

    public void onPreStartConnection() {
        //this.setSupportProgressBarIndeterminateVisibility(true);
        if(objSweetAlertDialog==null)
            objSweetAlertDialog=new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            objUtils.startProgressDialogSweet(objSweetAlertDialog);
    }

    public void onConnectionFinished() {
        //this.setProgressBarIndeterminateVisibility(false);
        if(objSweetAlertDialog!=null)objUtils.stopProgressDialogSweet(objSweetAlertDialog);
    }

    public void onConnectionFailed(String error) {
        //this.setProgressBarIndeterminateVisibility(false);
        if(objSweetAlertDialog!=null)objUtils.stopProgressDialogSweet(objSweetAlertDialog);
        Log.e("APPUK", "error volley: " + error);
        Toast.makeText(this, this.getResources().getString(R.string.str_not_connection), Toast.LENGTH_SHORT).show();
    }

    public void onConnectionFailedNotLoad(String error) {
        Log.e("multicash", "error volley: " + error);
    }

    public void onConnectionFailedView(View objView, String error) {
        if(objSweetAlertDialog!=null)objUtils.stopProgressDialogSweet(objSweetAlertDialog);
        Log.e("multicash", "error volley: " + error);
        objUtils.showSnackBar(objView,error);

    }

    public void onConnectionFailed() {
        if(objSweetAlertDialog!=null)objUtils.stopProgressDialogSweet(objSweetAlertDialog);
    }

    public SirSessionManager getPreferenciacb() {
        return preferenciadd;
    }


}
