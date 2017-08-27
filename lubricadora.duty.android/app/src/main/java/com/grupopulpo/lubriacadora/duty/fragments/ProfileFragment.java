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
import com.grupopulpo.lubriacadora.duty.adapters.MrVehiculoAdapterRV;
import com.grupopulpo.lubriacadora.duty.entities.MrVehiculo;
import com.grupopulpo.lubriacadora.duty.finals.ConfigEssentials;
import com.grupopulpo.lubriacadora.duty.http.RequestApp;
import com.grupopulpo.lubriacadora.duty.tools.SirSessionManager;
import com.grupopulpo.lubriacadora.duty.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {
    public ProfileFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}