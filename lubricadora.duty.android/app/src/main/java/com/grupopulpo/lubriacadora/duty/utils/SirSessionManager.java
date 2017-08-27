package com.grupopulpo.lubriacadora.duty.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
/**
 * Created by Junior on 15/09/2015.
 * Updated by Ing. Ernesto Liberio 16/07/2016
 */
public class SirSessionManager {
    SharedPreferences objSharedPreferences;
    SharedPreferences.Editor objSharedPreferencesEditor;
    Context _context;
    //variables
    int PRIVATE_MODE = Context.MODE_PRIVATE;
    private static final String PREF_NAME = "preferenciasugmovil";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_PHOTO = "KEY_PHOTO";
    private static final String KEY_NUIC = "KEY_NUIC";
    private static final String KEY_FULLNAME = "KEY_FULLNAME";
    private static final String KEY_TOKEN = "KEY_TOKEN";
    private static final String KEY_EMAIL="KEY_EMAIL";
    private static final String KEY_BIRTH_DATE="KEY_BIRTH_DATE";
    private static final String KEY_ROLES="KEY_ROLES";
    private static final String COD_CARRERA="COD_CARRERA";


    public SirSessionManager(Context context)
    {
        this._context = context;
        objSharedPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        objSharedPreferencesEditor = objSharedPreferences.edit();
        objSharedPreferencesEditor.apply();
    }



    public boolean isLoggedIn(){
        return objSharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser(){
        clearCredentials();
    }

    public void clearCredentials(){
        objSharedPreferencesEditor.remove(IS_LOGIN);
        objSharedPreferencesEditor.remove(KEY_PHOTO);
        objSharedPreferencesEditor.remove(KEY_NUIC);
        objSharedPreferencesEditor.remove(KEY_FULLNAME);
        objSharedPreferencesEditor.remove(KEY_TOKEN);
        objSharedPreferencesEditor.remove(KEY_BIRTH_DATE);
        objSharedPreferencesEditor.remove(KEY_EMAIL);
        objSharedPreferencesEditor.remove(KEY_ROLES);
        objSharedPreferencesEditor.commit();
    }


    public void setCodCarrera(String codCarrera){
        objSharedPreferencesEditor.putString(COD_CARRERA, codCarrera);
        objSharedPreferencesEditor.commit();
        Log.i("codigo_carrera", COD_CARRERA);
    }

    public String getCodCarrera(){
        return  objSharedPreferences.getString(COD_CARRERA,"");
    }


}
