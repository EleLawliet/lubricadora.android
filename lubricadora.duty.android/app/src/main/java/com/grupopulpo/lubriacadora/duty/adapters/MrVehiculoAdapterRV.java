package com.grupopulpo.lubriacadora.duty.adapters;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grupopulpo.lubriacadora.duty.R;
import com.grupopulpo.lubriacadora.duty.ServiciosActivity;
import com.grupopulpo.lubriacadora.duty.entities.MrVehiculo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Junior on 28/05/2016.
 */
public class MrVehiculoAdapterRV extends RecyclerView.Adapter<MrVehiculoAdapterRV.DeveloperViewHolder> {
    private Context objContext;
    private List<MrVehiculo> objListMrVehiculo;

    public static class DeveloperViewHolder extends RecyclerView.ViewHolder {

        TextView tvBrand = (TextView)itemView.findViewById(R.id.tv_card_brand);
        TextView tvColor = (TextView)itemView.findViewById(R.id.tv_card_color);
        TextView tvPlaca = (TextView)itemView.findViewById(R.id.tv_card_placa);
        TextView tvVehicleClass = (TextView)itemView.findViewById(R.id.tv_card_vehicle_class);
        TextView tvVehicleClassDescrip = (TextView)itemView.findViewById(R.id.tv_card_vehicle_descrip);
        Button btnServices = (Button)itemView.findViewById(R.id.btn_card_services);

        DeveloperViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public MrVehiculoAdapterRV(List<MrVehiculo> objListMrVehiculo, Context context) {
        this.objContext = context;
        this.objListMrVehiculo = objListMrVehiculo;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public DeveloperViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        return new DeveloperViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_vehiculos, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final DeveloperViewHolder objDeveloperViewHolder, final int i) {
//        Glide.with(objContext)
//                .load(objListMrVehiculo.get(i).getImage().toString())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(objDeveloperViewHolder.cvImageProfile);
        //objDeveloperViewHolder.cvImageProfile.setImageResource(objListMrVehiculo.get(i).getImage());
        objDeveloperViewHolder.tvBrand.setText(objListMrVehiculo.get(i).getMarca().toString().toUpperCase());
        objDeveloperViewHolder.tvColor.setText(objListMrVehiculo.get(i).getColor().toString().toUpperCase());
        objDeveloperViewHolder.tvPlaca.setText(objListMrVehiculo.get(i).getPlaca().toString());
        objDeveloperViewHolder.tvVehicleClass.setText(objListMrVehiculo.get(i).getClase_vehiculo_nombre().toUpperCase());
        objDeveloperViewHolder.tvVehicleClassDescrip.setText(objListMrVehiculo.get(i).getClase_vehiculo_descripcion());
        objDeveloperViewHolder.btnServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(objContext,ServiciosActivity.class) ;
                    intent.putExtra("id",String.valueOf(objListMrVehiculo.get(i).getCliente_vehiculo_id()));
                    intent.putExtra("nombre",objListMrVehiculo.get(i).getClase_vehiculo_nombre());
                    intent.putExtra("placa",objListMrVehiculo.get(i).getPlaca().toString());
//                    Snackbar snackbar = Snackbar
//                            .make(view, "ID"+objListMrVehiculo.get(i).getClase_vehiculo_id(), Snackbar.LENGTH_LONG);
//
//                    snackbar.show();
                    objContext.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(objContext, ("error"),  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return objListMrVehiculo.size();
    }
}
