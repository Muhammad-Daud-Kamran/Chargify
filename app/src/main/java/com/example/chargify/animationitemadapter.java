package com.example.chargify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class animationitemadapter extends RecyclerView.Adapter<animationitemadapter.AnimationViewHolder> {

    private ArrayList<Animationlist> animationList;
    private Context context;


    public animationitemadapter(Context context, ArrayList<Animationlist> animationList) {
        this.context = context;
        this.animationList = animationList;
    }

    @NonNull
    @Override
    public AnimationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.anim_items, parent, false);
        return new AnimationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimationViewHolder holder, int position) {
        Animationlist item = animationList.get(position);
        holder.animationView.setAnimation(item.getAnimationRes());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Animationpreview.class);
            intent.putExtra("animationRes", item.getAnimationRes());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return animationList.size();
    }

    public static class AnimationViewHolder extends RecyclerView.ViewHolder {

        LottieAnimationView animationView;

        public AnimationViewHolder(@NonNull View itemView) {
            super(itemView);
            animationView = itemView.findViewById(R.id.animationView);
        }
    }


}
