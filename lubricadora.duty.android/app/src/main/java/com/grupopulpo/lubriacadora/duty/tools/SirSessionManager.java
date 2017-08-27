package com.grupopulpo.lubriacadora.duty.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.grupopulpo.lubriacadora.duty.entities.MrSesion;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Created by Junior on 15/09/2015.
 */
public class SirSessionManager {
    private static final String PREF_NAME ="lubriDUTY" ;
    private static final String USER_CED = "0940552155";
    private static final String USER_ID = "0";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    //variables
    int PRIVATE_MODE = Context.MODE_PRIVATE;
    public static final String ESTA_LOGEADO = "true";
    public static final String KEY_NOMBRE = "nombreusuariologin";
    public static final String KEY_APELLIDO = "1.74";
    public static final String KEY_EMAIL = "60.6";
    public static final String IMG_USER = ".jpg";

    public SirSessionManager(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
        //editor.commit();
    }

    public void createLoginSession(String cedulaID,
                                   String nombre,
                                   String email
    )
    {
        editor.putBoolean(ESTA_LOGEADO, true);
//        editor.putInt(USER_ID, idCliente);
        editor.putString(USER_CED, cedulaID);
        editor.putString(KEY_NOMBRE, nombre);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(ESTA_LOGEADO,true);
    }
    public void logoutUser(){
        removercredenciales();
    }
    public void removercredenciales(){
        editor.remove(ESTA_LOGEADO);
        editor.putBoolean(ESTA_LOGEADO, false);
        editor.remove(KEY_EMAIL);
        editor.remove(USER_ID);
        editor.remove(USER_CED);
        editor.remove(KEY_NOMBRE);
        editor.commit();
    }

    public MrSesion obtenerUserData(){
        MrSesion objparam = new MrSesion();
        objparam.setIsLogin(pref.getBoolean(ESTA_LOGEADO, false));
        objparam.setNombre(pref.getString(KEY_NOMBRE, ""));
        objparam.setCedulaID(pref.getString(USER_CED, ""));
        objparam.setEmail(pref.getString(KEY_EMAIL, ""));
        return objparam;
    }

    public String encryptPassword(String password)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return sha1;
    }

    public String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
