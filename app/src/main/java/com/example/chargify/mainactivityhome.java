package com.example.chargify;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mainactivityhome extends AppCompatActivity {
RecyclerView rvmainhome;

ArrayList<rvhomemodel> rvhome= new ArrayList<>();
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainactivityhome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rvmainhome=findViewById(R.id.rvmainhome);
        rvmainhome.setLayoutManager(new LinearLayoutManager(this));

        rvhome.add(new rvhomemodel(R.drawable.rocket,"Start App"));
        rvhome.add(new rvhomemodel(R.drawable.share,"Share App"));
         rvhome.add(new rvhomemodel(R.drawable.exit__1_,"Exit App"));
         recycleradapterhome adapter=new recycleradapterhome(this,rvhome);
         rvmainhome.setAdapter(adapter);

    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        View customView = getLayoutInflater().inflate(R.layout.custom_dialogue, null);
        builder.setView(customView);

        Button btnexit = customView.findViewById(R.id.btnexit);
        Button btncancelalert = customView.findViewById(R.id.btncancelalert);
        btnexit.setOnClickListener(view -> finish());

        AlertDialog dialog = builder.create();
        dialog.show();
        btncancelalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
    }
}