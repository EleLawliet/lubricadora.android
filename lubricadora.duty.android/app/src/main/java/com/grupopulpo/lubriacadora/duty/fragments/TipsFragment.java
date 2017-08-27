package com.grupopulpo.lubriacadora.duty.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.grupopulpo.lubriacadora.duty.MenuActivity;
import com.grupopulpo.lubriacadora.duty.R;
import com.grupopulpo.lubriacadora.duty.adapters.MrTipsAdapterRV;
import com.grupopulpo.lubriacadora.duty.entities.MrTips;
import com.grupopulpo.lubriacadora.duty.finals.ConfigEssentials;
import com.grupopulpo.lubriacadora.duty.http.RequestApp;
import com.grupopulpo.lubriacadora.duty.tools.SirSessionManager;
import com.grupopulpo.lubriacadora.duty.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TipsFragment extends Fragment {
    private List<MrTips> objListVehicles;
    private Utils objUtils;
    private MrTipsAdapterRV adapterSubjects;
    private MenuActivity PADRE ;

    RecyclerView objRecyclerView;

    public TipsFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_generic_layout, container, false);
        this.objUtils=new Utils();
        this.PADRE = ((MenuActivity) getActivity());
        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        objRecyclerView = (RecyclerView)view.findViewById(R.id.rv_generica);
        this.objRecyclerView.setHasFixedSize(true);
        this.objRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        getInfoFaculties();
    }


    private void initializeData(JSONArray lista){
        objListVehicles = new ArrayList<>();
        objListVehicles = (ArrayList<MrTips>) (new Utils().getDataClassArrayApps(lista,"Tips"));
        initializeAdapter();
    }

    private void initializeAdapter(){
        objRecyclerView.setAdapter(new MrTipsAdapterRV(objListVehicles,PADRE));
    }

    public void getInfoFaculties()
    {
        SirSessionManager mySir = new SirSessionManager(PADRE);

        final String urlRequest = ConfigEssentials.API_URL_TIPS;
        try{
            RequestApp request = new RequestApp(Request.Method.GET,urlRequest, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonobj)  {
                    JSONArray lista = jsonobj.optJSONArray("datos");
                    Log.i("RESPONSE",lista.toString());
                    initializeData(lista);
                 //   objRecyclerView.setAdapter(new Fac  ultyAdapterRV(jsonobj,getActivity()));
//                    adapterSubjects.notifyDataSetChanged();
                    PADRE.onConnectionFinished();
                    //que hacer cuando devuelve los datos
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //que hacer cuando ocurrer algune error

                    PADRE.onConnectionFailed(error.toString());
                }
            });
            PADRE.agregarPeticionHttp(request);
        }catch (Exception e){
            Log.i("Error",e.getMessage());
            Toast.makeText(getActivity().getApplicationContext(),"empezamos a caernos" +
                    ": "+e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

}