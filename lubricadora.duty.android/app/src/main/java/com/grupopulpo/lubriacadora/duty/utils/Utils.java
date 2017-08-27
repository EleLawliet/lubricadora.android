package com.grupopulpo.lubriacadora.duty.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.View;

import com.android.volley.NetworkResponse;
import com.google.gson.Gson;
import com.grupopulpo.lubriacadora.duty.MenuActivity;
import com.grupopulpo.lubriacadora.duty.R;
import com.grupopulpo.lubriacadora.duty.entities.ExceptionOAUTH;
import com.grupopulpo.lubriacadora.duty.entities.MrServicioVehiculo;
import com.grupopulpo.lubriacadora.duty.entities.MrTips;
import com.grupopulpo.lubriacadora.duty.entities.MrVehiculo;
import com.grupopulpo.lubriacadora.duty.finals.ConfigEssentials;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by eliberio on 07/07/16.
 */
public class Utils {

    private Intent objIntent;
    MenuActivity PADRE = new MenuActivity();

    public boolean isEmailValid(String email) {
        return email.contains("@ug.edu.ec");
    }
    public boolean isUserValidLen(String user) {
        return (user.matches("[A-Z0-9]+") && user.length() > 6);
    }


    public boolean isPasswordValid(String password) {
        return password.length() > 2;
    }

    public void startAnimationLoading( AVLoadingIndicatorView objLoadingIndicator){
        objLoadingIndicator.setVisibility(View.VISIBLE);
    }
    public void stopAnimationLoading(AVLoadingIndicatorView objLoadingIndicator){  objLoadingIndicator.setVisibility(View.GONE);    }

    public void startDialogSweet(Context objContext, String strMessage, String strTitle){
        new SweetAlertDialog(objContext, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(strTitle)
                .setContentText(strMessage)
                .show();
    }

    public void startDialogSweet(Context objContext, String strMessage, String strTitle, int e){
        new SweetAlertDialog(objContext, e)
                .setTitleText(strTitle)
                .setContentText(strMessage)
                .show();
    }


    public void startDialogSweet(final Context objContext, String strMessage, String strTitle, int e, final Class destino){
        new SweetAlertDialog(objContext, e)
                .setTitleText(strTitle)
                .setContentText(String.valueOf(Html.fromHtml(strMessage)))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent i = new Intent(objContext, destino);
//                        i.putExtra("cod_carrera",codCarrera);
//                        Log.i("cod_carrera",""+codCarrera);
                        objContext.startActivity(i);
                        sweetAlertDialog.dismiss();
                    }
                })
                .show();

    }
    public void startDialogSweetLogout(final MenuActivity objContext, String strMessage, String strTitle, int e){
        new SweetAlertDialog(objContext, e)
                .setConfirmText("Salir")
                .setTitleText(strTitle)
                .setContentText(strMessage)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        try {
                            sweetAlertDialog.dismiss();
                            objContext.finish();
                           /// finalize();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }

                    }
                }).setCancelText("Cancelar").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        })
                .show();

    }





    public void startProgressDialogSweet(SweetAlertDialog objSweetAlertDialog){
        objSweetAlertDialog.getProgressHelper().setBarColor(R.color.blue_900);
        objSweetAlertDialog.setTitleText("Espere");
        objSweetAlertDialog.setCancelable(false);
        objSweetAlertDialog.show();
    }


    public void stopProgressDialogSweet(SweetAlertDialog objSweetAlertDialog){
        objSweetAlertDialog.dismiss();
    }

    public void loadActivitieCustom(Context objContext, Class objActivity){
       // if(this.objIntent==null){
            this.objIntent=new Intent(objContext, objActivity);
        objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

       // }
        objContext.startActivity(this.objIntent);
    }

    public String getConvertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        Log.i("OUT", "" + out);
        return out;
    }

    public void setTypefaceTextEditor(android.widget.EditText[] objArrayData,
                                      android.graphics.Typeface objTypeface){
        for (android.widget.EditText objEditText:objArrayData) {
            objEditText.setTypeface(objTypeface);
        }
    }
    public ArrayList<?> getDataClassArrayApps(JSONArray jArray, String strEntity){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ArrayList dataClassArray = new ArrayList();
            for (int i = 0; i < jArray.length(); i++) {
                switch (strEntity){
                    case "Vehiculos":
                        try {
                            dataClassArray.add(new MrVehiculo(
                                    jArray.getJSONObject(i).getJSONObject("vehiculo").getInt("vehiculo_id"),
                                    jArray.getJSONObject(i).getJSONObject("vehiculo").getInt("clase_vehiculo_id"),
                                    jArray.getJSONObject(i).getJSONObject("cliente_vehiculo").getInt("cliente_vehiculo_id"),
                                    jArray.getJSONObject(i).getJSONObject("vehiculo").getInt("estado_id"),
                                    jArray.getJSONObject(i).getJSONObject("vehiculo").getString("marca"),
                                    jArray.getJSONObject(i).getJSONObject("vehiculo").getString("color"),
                                    jArray.getJSONObject(i).getJSONObject("vehiculo").getString("placa"),
                                    jArray.getJSONObject(i).getJSONObject("vehiculo").getString("uso_personal"),
                                    jArray.getJSONObject(i).getJSONObject("vehiculo").getString("anio"),
                                    jArray.getJSONObject(i).getJSONObject("vehiculo").getString("fecha_ingreso"),
                                    jArray.getJSONObject(i).getJSONObject("vehiculo").getString("usuario_ingreso"),
                                    jArray.getJSONObject(i).getJSONObject("vehiculo").getString("fecha_modificacion"),
                                    jArray.getJSONObject(i).getJSONObject("vehiculo").getString("usuario_modificacion"),
                                    jArray.getJSONObject(i).getJSONObject("claseVehiculo").get("nombre").toString(),
                                    jArray.getJSONObject(i).getJSONObject("claseVehiculo").get("descripcion").toString()
                                    ));
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case "Tips":
                        try {
                            dataClassArray.add(new MrTips(
                                    jArray.getJSONObject(i).getInt("tips_id"),
                                    jArray.getJSONObject(i).getString("enlace"),
                                    jArray.getJSONObject(i).getString("nombre"),
                                    jArray.getJSONObject(i).getString("descripcion"),
                                    jArray.getJSONObject(i).getString("fecha_ingreso")

                            ));
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case "Services":
                        try {
                            dataClassArray.add(new MrServicioVehiculo(
                                    jArray.getJSONObject(i).getJSONObject("detServiciosCliente").getString("kilometraje_inicio"),
                                    jArray.getJSONObject(i).getJSONObject("detServiciosCliente").getString("kilometraje_sustitucion"),
                                    jArray.getJSONObject(i).getJSONObject("detServiciosCliente").getString("fecha_inicio"),
                                    jArray.getJSONObject(i).getJSONObject("detServiciosCliente").getString("fecha_sustitucion"),
                                    jArray.getJSONObject(i).getJSONObject("tipoServicio").getString("nombre"),
                                    jArray.getJSONObject(i).getJSONObject("TipoTiempo").getString("nombre"),
                                    jArray.getJSONObject(i).getJSONObject("claseVehiculoServicio").getString("tiempo_servicio"),
                                    jArray.getJSONObject(i).getJSONObject("detServiciosCliente").getString("cantidad_cambio")
                            ));
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        break;
                }
            }
        return dataClassArray;
    }

    public int getIntByParameterMipMap(String strParameter) throws NoSuchFieldException, IllegalAccessException {
        Field resourceField = R.mipmap.class.getDeclaredField(strParameter);
        return resourceField.getInt(resourceField);
    }

    public int getIntByParameterDrawable(String strParameter) throws NoSuchFieldException, IllegalAccessException {
        Field resourceField = R.drawable.class.getDeclaredField(strParameter);
        return resourceField.getInt(resourceField);
    }

    public HashMap<String, String> getParametersAuthorization(){
        HashMap<String, String> params = new HashMap<>();
        params.put("grant_type", ConfigEssentials.API_GRANT_TYPE);
        params.put("client_id",ConfigEssentials.API_CLIENT_ID);
        params.put("client_secret",ConfigEssentials.API_CLIENT_SECRET);
        return params;
    }

    public void getProccessMessageObjectExceptionOAUTH(NetworkResponse objNetworkResponse, View objView, MenuActivity objFather){
        String strMessage=this.getMessageObjectExceptionOAUTH(objNetworkResponse,objView);
        objFather.actionLogoutByMessageApi(strMessage);
    }

    public String getMessageObjectExceptionOAUTH(NetworkResponse objNetworkResponse, View objView){
        ExceptionOAUTH objExceptionOAUTH;
        String strMessage="";
        try {
            objExceptionOAUTH = new Gson().fromJson(new String(objNetworkResponse.data), ExceptionOAUTH.class);
            if (objExceptionOAUTH != null) {
                objExceptionOAUTH.setCode(objNetworkResponse.statusCode);
                switch(objExceptionOAUTH.getCode()){
                    case 404:
                        //strMessage=objExceptionOAUTH.getError_description()==null?objExceptionOAUTH.getMessage():objExceptionOAUTH.getError_description();
                        strMessage="USUARIO/CONTRASEÑA NO COINCIDEN CON NUESTROS REGISTROS.";
                        break;
                    case 400:
                        strMessage="PARÁMETROS INCORRECTOS.";
                        break;

                    case 401:
                        strMessage="USUARIO NO AUTORIZADO.";
                        break;

                    default:
                        strMessage="ERROR DE PROCESAMIENTO INTERNO CONSULTE CON EL ADMINISTRADOR.";
                        break;
                }
            }
        }catch (Exception objException){
            strMessage=objException.getMessage();
        }
        return strMessage;
    }

    public void showSnackBar(View objView, String message){
        Snackbar snackbar = Snackbar
                .make(objView,message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    public  boolean getExistConnection(Context context)
    {
        try
        {
            ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected())
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public String encryptPassword(String password)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = this.byteToHex(crypt.digest());
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

    public String getOrientation(Context objContext)
    {
        if(objContext.getResources().getDisplayMetrics().widthPixels>objContext.getResources().getDisplayMetrics().heightPixels)
            return "LANDSCAPE";
            return "PORTRAIT";
    }

    public Date StringToDate (String dtStart){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dtStart);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public Date sumarRestarDiasFecha(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }
//    public String getImei(Context c) {
//        TelephonyManager telephonyManager = (TelephonyManager) c
//                .getSystemService(Context.TELEPHONY_SERVICE);
//        return telephonyManager.getDeviceId();
//    }
}
