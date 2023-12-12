package com.example.miniprojetv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AgencesListActivity extends AppCompatActivity {

    ListView agencesListView;
    ArrayList<String> agencesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agences_list);

        ImageView imageView = findViewById(R.id.home);
        agencesListView = findViewById(R.id.agencesListView);
        agencesList = new ArrayList<>();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgencesListActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Création d'une instance d'Agence avec les valeurs spécifiées
        Agence agence = new Agence(
                "Moto Rent Car",
                "Tunis",
                "2043",
                "55222333",
                "71000111",
                "Ben Arous"
        );
       /* Agence agence2 = new Agence(
                "Express Rent Car",
                "Maamoura",
                "8050",
                "97654789",
                "72632896",
                "AV Hbib Bourguiba"
        ); */

        // Récupérer la référence de la base de données Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference agencesRef = database.getReference("agences");

        // Enregistrer l'instance d'Agence dans Firebase Realtime Database
        String agenceId = agencesRef.push().getKey(); // Générer une clé unique pour l'agence
        agencesRef.child(agenceId).setValue(agence);
        //agencesRef.child(agenceId).setValue(agence2);

        // Récupérer les noms des agences depuis Firebase
        agencesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                agencesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String nom = snapshot.child("nom").getValue(String.class);
                    if (nom != null) {
                        agencesList.add(nom);
                    }
                }

                // Afficher la liste des noms d'agences dans ListView
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AgencesListActivity.this,
                        android.R.layout.simple_list_item_1, agencesList);
                agencesListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AgencesListActivity.this, "Erreur de lecture des données.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        agencesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer l'élément sélectionné dans la liste
                String selectedAgenceName = agencesList.get(position);

                // Passer à l'activité DetailsAgenceActivity avec l'agence sélectionnée
                Intent intent = new Intent(AgencesListActivity.this, DetailsAgenceActivity.class);
                intent.putExtra("SELECTED_AGENCE", selectedAgenceName);
                startActivity(intent);
            }
        });

    }
}