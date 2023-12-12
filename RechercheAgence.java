package com.example.miniprojetv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class RechercheAgence extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_agence);

        Button buttonSearch = findViewById(R.id.buttonSearch);

        // Récupérer le Spinner depuis le layout XML
        Spinner spinnerCities = findViewById(R.id.spinnerCities);

        // Créer une liste des villes
        String[] cities = {"Tunis", "Nabeul", "Benzart", "Kairouane", "Touzeur"};

        // Créer un ArrayAdapter en utilisant la liste des villes et un layout par défaut
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);

        // Spécifier le layout pour les dropdown (liste déroulante)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Associer l'adapter au Spinner
        spinnerCities.setAdapter(adapter);

        ImageView imageView = findViewById(R.id.home);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RechercheAgence.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedAddress = spinnerCities.getSelectedItem().toString();

                // Passer à l'activité AgencesListActivity en lui transmettant l'adresse sélectionnée
                Intent intent = new Intent(RechercheAgence.this, AgencesListActivity.class);
                intent.putExtra("SELECTED_ADDRESS", selectedAddress);
                startActivity(intent);
            }
        });
    }
}
