package com.grupopulpo.lubriacadora.duty.tools;

/**
 * Created by Robert on 27/06/2016.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;

/**
 * Created by Robert on 27/6/2016.
 */
public class Vistas {
    private static Gson gson;

    static ProgressDialog barraprogreso = null;
    public static void MostrarBarraProgreso(Context contexto, int id){
        try {
            if (barraprogreso == null)
                barraprogreso = new ProgressDialog(contexto, ProgressDialog.THEME_HOLO_LIGHT);


            Activity actividad = scanForActivity(contexto);
            if (actividad != null) {
                while (!(!actividad.isFinishing() || !actividad.isDestroyed())) {
                    //Log.i("Vistas.MostrarBarraProgreso", "ingreso en el ciclo");
                }
                barraprogreso.show();
                barraprogreso.setContentView(id);
                barraprogreso.setCancelable(false);
            }
        }catch(Exception e){
            android.util.Log.e("Error","Error al mostrar la publicidad",e);
            barraprogreso = null;
        }
    }

    /**
     * Obtiene el activity de forma nativa por medio del contexto, el aactivity puede
     * obtenerse por medio de fragment
     * @param contexto
     * @return
     */
    private static Activity scanForActivity(Context contexto) {
        if (contexto == null)
            return null;
        else if (contexto instanceof Activity)
            return (Activity)contexto;
        else if (contexto instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper)contexto).getBaseContext());

        return null;
    }

    public static void OcultarBarraProgreso(Context contexto){
        if(barraprogreso!=null)
            barraprogreso.dismiss();
    }
    public static Object ConvertirResponse(JSONArray response, Class clase){

        if(gson == null)
            gson = new Gson();
        return gson.fromJson(response.toString(), clase);
    }

    public static void setBadgeCount(Context context, LayerDrawable icon, int count) {

        BadgeDrawable badge;

        // Reusar drawable
//        Drawable reuse = icon.findDrawableByLayerId(R.id.);
//        if (reuse != null && reuse instanceof BadgeDrawable) {
//            badge = (BadgeDrawable) reuse;
//        } else {
//            badge = new BadgeDrawable(context);
//        }
//
//        badge.setCount(count);
//        icon.mutate();
//        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }
}