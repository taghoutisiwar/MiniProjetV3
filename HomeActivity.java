package com.example.miniprojetv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button btn_reservations , btn_agences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_reservations = findViewById(R.id.btn_reservations);
        btn_agences = findViewById(R.id.btn_agences);

        btn_reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MesReservations.class);
                startActivity(intent);
            }
        });
        btn_agences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RechercheAgence.class);
                startActivity(intent);
            }
        });

    }
}