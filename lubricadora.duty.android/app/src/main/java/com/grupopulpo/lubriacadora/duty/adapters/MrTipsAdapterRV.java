package com.grupopulpo.lubriacadora.duty.adapters;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grupopulpo.lubriacadora.duty.R;
import com.grupopulpo.lubriacadora.duty.entities.MrTips;
import com.grupopulpo.lubriacadora.duty.finals.ConfigEssentials;

import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Junior on 28/05/2016.
 */
public class MrTipsAdapterRV extends RecyclerView.Adapter<MrTipsAdapterRV.DeveloperViewHolder> {
    private Context objContext;
    private List<MrTips> objListMrTips;

    public static class DeveloperViewHolder extends RecyclerView.ViewHolder {

        ImageView cvImageTips = (ImageView)itemView.findViewById(R.id.iv_portada_publicacion);
        TextView tvTitulo = (TextView)itemView.findViewById(R.id.tvTitulo);
        TextView tvDescripcion = (TextView)itemView.findViewById(R.id.tvDescripcion);

        DeveloperViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public MrTipsAdapterRV(List<MrTips> objListMrTips, Context context) {
        this.objContext = context;
        this.objListMrTips = objListMrTips;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public DeveloperViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        return new DeveloperViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_publicacion, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final DeveloperViewHolder objDeveloperViewHolder, final int i) {
        Log.i("IMAGEN",objListMrTips.get(i).getEnlace().toString());
        Glide.with(objContext)
                .load(objListMrTips.get(i).getEnlace().toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(objDeveloperViewHolder.cvImageTips);
        objDeveloperViewHolder.tvTitulo.setText(objListMrTips.get(i).getTitulo().toString());
        objDeveloperViewHolder.tvDescripcion.setText(objListMrTips.get(i).getDescripcion().toString());
    }
    @Override
    public int getItemCount() {
        return objListMrTips.size();
    }
}
