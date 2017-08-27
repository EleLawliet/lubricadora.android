package com.grupopulpo.lubriacadora.duty;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.grupopulpo.lubriacadora.duty.adapters.MrServicioVehiculoAdapterRV;
import com.grupopulpo.lubriacadora.duty.entities.MrServicioVehiculo;
import com.grupopulpo.lubriacadora.duty.finals.ConfigEssentials;
import com.grupopulpo.lubriacadora.duty.http.RequestApp;
import com.grupopulpo.lubriacadora.duty.utils.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServiciosActivity extends com.grupopulpo.lubriacadora.duty.utils.BaseActivity {
    private List<MrServicioVehiculo> objListVehicles;
    RecyclerView objRecyclerView;
    private static final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 232;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle parametros = new Bundle();
        parametros.putInt("layout", R.layout.activity_servicios);
        parametros.putInt("verbtnatras", 0);
        parametros.putInt("vertoolbar", 0);
        super.onCreate(parametros);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView titulo = (TextView)findViewById(R.id.tv_titulo_vehiculos_servicios);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
                == PackageManager.PERMISSION_GRANTED) {
            Snackbar.make(toolbar.getRootView(),":V",Snackbar.LENGTH_INDEFINITE).show();
//prosigues como si nada, ya tienes el permiso
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_READ_CALENDAR);
            return;
        }
        objRecyclerView = (RecyclerView)findViewById(R.id.rvListServices);
        this.objRecyclerView.setHasFixedSize(true);
        this.objRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nombre_vehiculo = intent.getStringExtra("nombre");
        String placa_vehiculo = intent.getStringExtra("placa");
        titulo.setText(nombre_vehiculo.toUpperCase()+" Placa: "+placa_vehiculo);
        toolbar.setSubtitle(nombre_vehiculo);
        toolbar.setSubtitleTextColor(Color.CYAN);
        toolbar.setTitle(placa_vehiculo);
        verServicios(id);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void verServicios(final String id) {
        final String urlRequest = ConfigEssentials.API_SERVICIOS+id;
        Log.i("LubricadoraDuty","URL: "+urlRequest);
        RequestApp request = new RequestApp(Request.Method.GET,urlRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("duty","JSON RESPUESTA: "+response.toString());
                String exito = response.optString("codigo");
                JSONArray array;
                Log.i("duty","JSON BOOLEAN: "+exito);
                if(exito.equalsIgnoreCase("000")){
                    array = response.optJSONArray("datos");
                    Log.i("duty","JSON RESPUESTA: "+array.toString());
                    if (array.length() > 0) {
                        initializeData(array);
                        onConnectionFinished();
                    }
                }else {
                    try {
                        onConnectionFailed(response.get("mensaje").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onConnectionFailed(error.getMessage());
            }
        });
        // Utilidades.ocultarTeclado(THIS);
        agregarPeticionHttp(request);
    }


    private void initializeData(JSONArray lista){
        objListVehicles = new ArrayList<>();
        objListVehicles = (ArrayList<MrServicioVehiculo>) (new Utils().getDataClassArrayApps(lista,"Services"));
        initializeAdapter();
    }

    private void initializeAdapter(){
        objRecyclerView.setAdapter(new MrServicioVehiculoAdapterRV(objListVehicles,ServiciosActivity.this));
    }
}
