package com.fyp.malappdetector;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
class WhitelistRVAdapter extends RecyclerView.Adapter<WhitelistRVAdapter.RVViewHolder> {
    List<Application> ls;
//    form clForm;

    public WhitelistRVAdapter(List<Application> ls) {
        this.ls = ls;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.whitelistrow, parent, false);

        return new RVViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull final RVViewHolder holder, final int position) {

        holder.appName.setText(ls.get(position).getAppName());
        holder.logo.setImageDrawable(ls.get(position).getLogo());
        holder.remWhitelist.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }


    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class RVViewHolder extends RecyclerView.ViewHolder {
        TextView appName;
        ImageView logo;
        ImageButton remWhitelist;

        public RVViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.whitelistLabel);
            logo = itemView.findViewById(R.id.whitelistIcon);
            remWhitelist=itemView.findViewById(R.id.remWhitelist);
            //         P_Img = itemView.findViewById(R.id.PriImage);


        }
    }

}
