package com.fyp.malappdetector;
import android.content.Context;
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
class PrivRVAdapter extends RecyclerView.Adapter<PrivRVAdapter.RVViewHolder> {
    List<Privacy> ls;
//    form clForm;

    public PrivRVAdapter(List<Privacy> ls) {
        this.ls = ls;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.permissionsrow, parent, false);

        return new RVViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int position) {


        holder.privName.setText(ls.get(position).getPrivacyType());

        //       holder.P_Img=ls.get(position).getP_Image();


    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class RVViewHolder extends RecyclerView.ViewHolder {
        TextView privName;


        public RVViewHolder(@NonNull View itemView) {
            super(itemView);
            privName = itemView.findViewById(R.id.permissionType);

        }
    }

}
