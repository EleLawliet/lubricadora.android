package com.grupopulpo.lubriacadora.duty.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.grupopulpo.lubriacadora.duty.finals.ConfigEssentials;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class RequestApp extends JsonObjectRequest {

    private String strToken="";

    public RequestApp(int get, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public RequestApp(int metodo, String url, JSONObject jsonrequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(metodo,url,jsonrequest,listener, errorListener);
    }

    public RequestApp(String strToken, int metodo, String url, JSONObject jsonrequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(metodo,url,jsonrequest,listener, errorListener);
        this.strToken=strToken;
    }
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        params.put("Content-Type","application/json");
        return params;
    }

    private String getTokenRequest() {
        return strToken;
    }

}
