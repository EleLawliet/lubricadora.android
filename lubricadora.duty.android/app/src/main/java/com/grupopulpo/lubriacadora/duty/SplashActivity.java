package com.grupopulpo.lubriacadora.duty;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;


import com.google.gson.Gson;
import com.grupopulpo.lubriacadora.duty.tools.SirSessionManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {


    SplashActivity THIS;
    SirSessionManager mySir ;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle parametros = new Bundle();
        parametros.putInt("layout", R.layout.activity_splash);
        parametros.putInt("verbtnatras", 0);
        parametros.putInt("vertoolbar", 0);
        super.onCreate(parametros);
        THIS  = this;
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.facebook.samples.hellofacebook",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        if (mySir==null){
            mySir =  new SirSessionManager(THIS);
        }
        Log.i("LOGEO", String.valueOf(mySir.isLoggedIn()));

        if(mySir.isLoggedIn()){
            RedireccionaraLogin("L");
        }else
            RedireccionaraLogin("M");
    }
    /**
     * NOMBRE : RedireccionaraLogin
     * Metodo usado para pruebas, este metodo crea una puerta de acceso
     * a las opciones de dinardaap aunque los datos no sean los correctos.
     * @param m
     */
    private void RedireccionaraLogin(final String m){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent;
                if (m.equalsIgnoreCase("L")){
                    mainIntent= new Intent().setClass(SplashActivity.this, MenuActivity.class);
                    startActivity(mainIntent);
                    finish();
                }else {
                    mainIntent= new Intent().setClass(SplashActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                }

            }
        };
        new Timer().schedule(task, 2000);
    }

    /**
     * NOMBRE : lanzarActividadPrincipal
     * Este metodo lleva a la pantalla de Login.
     */
    private void lanzarActividadPrincipal(){
        Intent mainIntent = new Intent().setClass(SplashActivity.this, LoginActivity.class);
        startActivity(mainIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
