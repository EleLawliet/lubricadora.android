package com.grupopulpo.lubriacadora.duty.adapters;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.grupopulpo.lubriacadora.duty.R;
import com.grupopulpo.lubriacadora.duty.entities.MrServicioVehiculo;
import com.grupopulpo.lubriacadora.duty.utils.AddToCalendar;
import com.grupopulpo.lubriacadora.duty.utils.Utils;

import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Junior on 28/05/2016.
 */
public class MrServicioVehiculoAdapterRV extends RecyclerView.Adapter<MrServicioVehiculoAdapterRV.DeveloperViewHolder> {
    private Context objContext;
    private List<MrServicioVehiculo> objListMrServicioVehiculo;
    AddToCalendar recordatorio = null;
    Utils utilitario = new Utils();



    public static class DeveloperViewHolder extends RecyclerView.ViewHolder {

        TextView tvFechaCaducidad = (TextView)itemView.findViewById(R.id.tv_caducidad_fecha);
        TextView tvTituloServicio = (TextView)itemView.findViewById(R.id.tv_nombre_servicio);
        TextView btnServices = (TextView)itemView.findViewById(R.id.iv_alarm);
        TextView tvMsgCambio = (TextView)itemView.findViewById(R.id.tv_msg_cambios);
        LinearLayout lyConainerMsg= (LinearLayout) itemView.findViewById(R.id.ly_container_msg);
        LinearLayout lyContainerViews= (LinearLayout) itemView.findViewById(R.id.container_vehicles);

        DeveloperViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public MrServicioVehiculoAdapterRV(List<MrServicioVehiculo> objListMrServicioVehiculo, Context context) {
        this.objContext = context;
        this.objListMrServicioVehiculo = objListMrServicioVehiculo;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public DeveloperViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        return new DeveloperViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_services_vehicles, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final DeveloperViewHolder objDeveloperViewHolder, final int i) {
//        Glide.with(objContext)
//                .load(objListMrServicioVehiculo.get(i).getImage().toString())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(objDeveloperViewHolder.cvImageProfile);
        //objDeveloperViewHolder.cvImageProfile.setImageResource(objListMrServicioVehiculo.get(i).getImage());
        String nombreTipoServicio = objListMrServicioVehiculo.get(i).getTipoTiempoNombre();
        String tiempoServicio= objListMrServicioVehiculo.get(i).getTiempoServicio();
        String nombreServicio = objListMrServicioVehiculo.get(i).getTipoServicioNombre().toString().toUpperCase();
        String fechaSustitucion = objListMrServicioVehiculo.get(i).getFechaSustitucion();
        String cantidad_cambio = objListMrServicioVehiculo.get(i).getCantidadCambio();

        if(nombreTipoServicio.equals("KM")){
            objDeveloperViewHolder.tvTituloServicio.setText(nombreServicio);
            objDeveloperViewHolder.tvFechaCaducidad.setText(fechaSustitucion);

        }else if(nombreTipoServicio.equals("MES")){
            objDeveloperViewHolder.tvTituloServicio.setText(nombreServicio);
            objDeveloperViewHolder.tvFechaCaducidad.setText(fechaSustitucion);

        }else if(nombreTipoServicio.equals("CAMBIOS")){
            if(tiempoServicio.equalsIgnoreCase(cantidad_cambio)) {
                objDeveloperViewHolder.tvTituloServicio.setText(nombreServicio);
                objDeveloperViewHolder.tvFechaCaducidad.setText(fechaSustitucion);
                objDeveloperViewHolder.lyConainerMsg.setVisibility(View.VISIBLE);
                objDeveloperViewHolder.tvMsgCambio.setText("Recuerda que en la siguiente visita a nuestro establecimiento, toca realizar un "+objDeveloperViewHolder.tvTituloServicio.getText());
            }else {
                objDeveloperViewHolder.lyContainerViews.setVisibility(View.GONE);
            }
        }


        try {
            objDeveloperViewHolder.btnServices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date fechaSustitucion  = utilitario.StringToDate(objDeveloperViewHolder.tvFechaCaducidad.getText().toString());
                    Date fechaInicioEvento = utilitario.sumarRestarDiasFecha(fechaSustitucion,-1);
                    Log.i("FI",String.valueOf(fechaInicioEvento.getTime()));
                    Log.i("FS",String.valueOf(fechaSustitucion.getTime()));
                    AddToCalendar.addToCal(objContext,objDeveloperViewHolder.tvTituloServicio.getText().toString().toUpperCase(),fechaInicioEvento.getTime(),fechaSustitucion.getTime());

                }
            });

        } catch (ActivityNotFoundException e) {
            Toast.makeText(objContext, ("error"),  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
    @Override
    public int getItemCount() {
        return objListMrServicioVehiculo.size();
    }
}
