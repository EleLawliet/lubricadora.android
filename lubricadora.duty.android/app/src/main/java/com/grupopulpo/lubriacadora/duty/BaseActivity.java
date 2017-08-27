package com.grupopulpo.lubriacadora.duty;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.grupopulpo.lubriacadora.duty.http.RequestApp;
import com.grupopulpo.lubriacadora.duty.http.ResourceVolley;
import com.grupopulpo.lubriacadora.duty.tools.HttpsTrustManager;

import java.util.HashMap;




/**
 Creado por Andres Cantos
 Update by WVera
 **/
public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BASE ACTIVITY";
    String ced;
    ResourceVolley objvolley;
    RequestQueue colaPeticioneshttp;
    Toolbar toolbar;
    ProgressDialog barraprogreso;
  //  private PreferenciasDD preferenciadd;

    //Diccionario de palabras
    public HashMap<String,String> diccionarioservidor = new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // preferenciadd = new PreferenciasDD(this);

        int mostrarbotonatras =1;
        int mapeartoolbar = 1;
        mostrarbotonatras = savedInstanceState.getInt("verbtnatras",1);
        mapeartoolbar = savedInstanceState.getInt("vertoolbar",1);
        setContentView(savedInstanceState.getInt("layout"));
        objvolley = ResourceVolley.getInstance(this);
        colaPeticioneshttp = objvolley.getQueueVolley();


        if(mapeartoolbar==1) {

            toolbar = (Toolbar) findViewById(R.id.toolbardinardap);
            setSupportActionBar(toolbar);

            if(mostrarbotonatras==1)
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            else
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        }
    }
    public void agregarPeticionHttp(RequestApp request) {
        //HttpsTrustManager.allowAllSSL();
        if (request != null) {
            request.setTag(this);
            if (colaPeticioneshttp == null)
                colaPeticioneshttp = objvolley.getQueueVolley();
            request.setRetryPolicy(new DefaultRetryPolicy(60000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            onPreStartConnection();
            colaPeticioneshttp.add(request);
        }
    }
    /*Nombre: agregarPeticionHttp
       *Parametro: RequestApp request
       *Descripción: Al contrario de las otras peticiones este metodo no tiene una interfaz de cargando,
       *este metodo es el eencargado de agregar una nueva petición de un servicio web,
       *en caso de que haya una falla del internet este realizara 3 intentos de conexión en 1 minuto
       *y agrega la petiión a una "cola de peticiones" */
    public void agregarPeticionHttpSinLoad(RequestApp request) {
      //  HttpsTrustManager.allowAllSSL();
        if (request != null) {
            request.setTag(this);
            if (colaPeticioneshttp == null)
                colaPeticioneshttp = objvolley.getQueueVolley();
            request.setRetryPolicy(new DefaultRetryPolicy(60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            //onPreStartConnection();
            colaPeticioneshttp.add(request);
        }
    }

    /*Nombre: onPreStartConnection
     *Descripción: Este metodo crea una vista "cargando". */
    public void onPreStartConnection() {
        //this.setSupportProgressBarIndeterminateVisibility(true);
        if(barraprogreso==null) barraprogreso = new ProgressDialog(this,ProgressDialog.THEME_HOLO_LIGHT);
        barraprogreso.show();
        barraprogreso.setContentView(R.layout.progressdialogeneral);
        barraprogreso.setCancelable(false);
        //barraprogreso.setProgressStyle(Widge);
    }
    /*Nombre: onConnectionFinished
     *Descripción: Este metodo finaliza la vista de "cargando". */
    public void onConnectionFinished() {
        //this.setProgressBarIndeterminateVisibility(false);
        if(barraprogreso!=null) barraprogreso.dismiss();
    }
    /*Nombre: onConnectionFailed
        *Parametro: String error
        *Descripción: Crea una vista el cual desplega un mensaje notificando al usuario
        *que ha ocurrido un error de conexión. */
    public void onConnectionFailed(String error) {
        //this.setProgressBarIndeterminateVisibility(false);
        if(barraprogreso!=null) barraprogreso.dismiss();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
    /*Nombre: onConnectionFailed
        *@param errorusuario
        *@param errorsistema
        *Descripción: Crea una vista la cual desplega un mensaje notificando al usuario
        *que ha ocurrido un error de conexión. */
    public void onConnectionFailed(String errorusuario, String errorsistema) {
        //this.setProgressBarIndeterminateVisibility(false);
        if(barraprogreso!=null) barraprogreso.dismiss();
        Log.e("DINARDAP", "errorUsuario: "+errorusuario+ " \n error de Sistema: " + errorsistema);
        Toast.makeText(this, errorusuario, Toast.LENGTH_SHORT).show();
    }
    /*Nombre: onConnectionFailed
           *Descripción: Cancela la conexión y elimina el cargando visual*/
    public void onConnectionFailed(VolleyError error) {
        Log.d(TAG, "onConnectionFailed: "+error);
        Toast.makeText(this, "El usuario ingresado no forma parte de nuestra clientela.", Toast.LENGTH_SHORT).show();
        //this.setProgressBarIndeterminateVisibility(false);
        if(barraprogreso!=null) barraprogreso.dismiss();
    }
}
