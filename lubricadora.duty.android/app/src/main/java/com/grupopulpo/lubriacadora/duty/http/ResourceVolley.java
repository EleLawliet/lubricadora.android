package com.grupopulpo.lubriacadora.duty.http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 *
 * Update by Ing. Ernesto Liberio on 07/07/16.
 * Create by Andres Cantos on 13/nov/2015
 */
public class ResourceVolley {
    private  static ResourceVolley objQueuePetitionsVolley = null;
    private RequestQueue objQueueVolley = null;

    /*constructor privado crea la cola de peticioneshttp*/
    private ResourceVolley(Context ctx){
        objQueueVolley =  Volley.newRequestQueue(ctx);
    }
    public static ResourceVolley getInstance(Context ctx){
        if(objQueuePetitionsVolley == null){
            objQueuePetitionsVolley = new ResourceVolley(ctx);
        }
        return objQueuePetitionsVolley;
    }

    public RequestQueue getQueueVolley() {
        return objQueueVolley;
    }
}
