package com.fyp.malappdetector;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MaliciousRVAdapter extends RecyclerView.Adapter<MaliciousRVAdapter.RVViewHolder> {
    List<Application> ls;
//    form clForm;

    public MaliciousRVAdapter(List<Application> ls) {
        this.ls = ls;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.maliciousrow, parent, false);

        return new RVViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull final RVViewHolder holder, final int position) {

        holder.appName.setText(ls.get(position).getAppName());
        holder.logo.setImageDrawable(ls.get(position).getLogo());
        holder.remMalicious.setOnLongClickListener(new View.OnLongClickListener() {
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
        ImageButton remMalicious;

        public RVViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.maliciousLabel);
            logo = itemView.findViewById(R.id.maliciousIcon);
            remMalicious = itemView.findViewById(R.id.remMalcious);
            //         P_Img = itemView.findViewById(R.id.PriImage);


        }
    }

}
