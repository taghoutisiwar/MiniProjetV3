package com.example.miniprojetv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MonCompteActivity extends AppCompatActivity {

    Button btn_Compte ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moncompte_activity);
        btn_Compte = findViewById(R.id.btn_Compte);
        btn_Compte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonCompteActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}