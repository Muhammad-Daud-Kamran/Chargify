package com.example.chargify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class animationselection extends AppCompatActivity {

    private ImageView imgbackanimlist;
    private RecyclerView recyclerViewanimitem;
    private animationitemadapter adapter;
    private ArrayList<Animationlist> animationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animationselection);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainanimlist), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerViewanimitem = findViewById(R.id.recyclerViewanimitem);
        recyclerViewanimitem.setLayoutManager(new GridLayoutManager(this,2));

        recyclerViewanimitem.setItemViewCacheSize(30);
        recyclerViewanimitem.setHasFixedSize(true);

        imgbackanimlist = findViewById(R.id.imgbackanimlist);


        imgbackanimlist.setOnClickListener(view -> {
            Intent intent = new Intent(animationselection.this, MainActivityhome2.class);
            startActivity(intent);
        });


        animationList = new ArrayList<>();
        animationList.add(new Animationlist(R.raw.electric));
        animationList.add(new Animationlist(R.raw.anime1));
        animationList.add(new Animationlist(R.raw.anim2e));
        animationList.add(new Animationlist(R.raw.anim3e));
        animationList.add(new Animationlist(R.raw.anim10));
        animationList.add(new Animationlist(R.raw.anim11));
        animationList.add(new Animationlist(R.raw.anim12));
        animationList.add(new Animationlist(R.raw.anim14));
        animationList.add(new Animationlist(R.raw.anim15));
        animationList.add(new Animationlist(R.raw.anim18));
        animationList.add(new Animationlist(R.raw.anim19));
        animationList.add(new Animationlist(R.raw.anim20));
        animationList.add(new Animationlist(R.raw.anim21));
        animationList.add(new Animationlist(R.raw.anim22));
        animationList.add(new Animationlist(R.raw.animation1));
        animationList.add(new Animationlist(R.raw.animation2));
        animationList.add(new Animationlist(R.raw.animation3));
        animationList.add(new Animationlist(R.raw.animation4));
        animationList.add(new Animationlist(R.raw.animation5));






        adapter = new animationitemadapter(this,animationList);
        recyclerViewanimitem.setAdapter(adapter);



    }
}
