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
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
class AppRVAdapter extends RecyclerView.Adapter<AppRVAdapter.RVViewHolder> {
    List<Application> ls;
//    form clForm;

    public AppRVAdapter(List<Application> ls) {
        this.ls = ls;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        return new RVViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, final int position) {

        holder.appName.setText(ls.get(position).getAppName());
        holder.logo.setImageDrawable(ls.get(position).getLogo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application app;
                app=ls.get(position);

                Intent intent=new Intent(v.getContext(),ApplicationDetails.class);
                intent.putExtra("appLabel",app.getAppName());


                v.getContext().startActivity(intent);

            }
        });

        //       holder.P_Img=ls.get(position).getP_Image();


    }


    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class RVViewHolder extends RecyclerView.ViewHolder {
        TextView appName;
        ImageView logo;

        public RVViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.appName_Txt);
            logo = itemView.findViewById(R.id.logo_Img);
            //         P_Img = itemView.findViewById(R.id.PriImage);

        }
    }

}
