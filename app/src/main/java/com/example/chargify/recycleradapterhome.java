package com.example.chargify;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycleradapterhome extends RecyclerView.Adapter<recycleradapterhome.viewholder> {
    Context context;
    ArrayList<rvhomemodel> rvhome;


    recycleradapterhome(Context context, ArrayList<rvhomemodel> rvhome) {
        this.context = context;
        this.rvhome = rvhome;
    }

    @NonNull
    @Override
    public recycleradapterhome.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.homerv_design, parent, false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleradapterhome.viewholder holder, int position) {
        holder.tvrvhome.setText(rvhome.get(position).name);
        holder.imgrvhome.setImageResource(rvhome.get(position).image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position) {
                    case 0:
                        startApp();
                        break;
                    case 1:
                        shareApp();
                        break;
                    case 2:
                        exitApp();
                        break;
                }
            }
        });
    }


    private void startApp() {
        Intent intent = new Intent(context, MainActivityhome2.class);
        context.startActivity(intent);
    }

    // Method to share the app link
    private void shareApp() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this amazing app!:https://github.com/Muhammad-Daud-Kamran?tab=repositories");
        context.startActivity(Intent.createChooser(shareIntent, "Share App"));
    }


    private void exitApp() {

        if (context instanceof Activity) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View customView = inflater.inflate(R.layout.custom_dialogue, null);
            builder.setView(customView);


            Button btnexit = customView.findViewById(R.id.btnexit);
            Button btncancelalert = customView.findViewById(R.id.btncancelalert);


            AlertDialog dialog = builder.create();
            dialog.show();


            btnexit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) context).finish();
                    dialog.dismiss();
                }
            });
           btncancelalert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return rvhome.size();
    }


    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvrvhome;
        ImageView imgrvhome;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvrvhome = itemView.findViewById(R.id.tvrvhome);
            imgrvhome = itemView.findViewById(R.id.imgrvhome);
        }
    }
}
