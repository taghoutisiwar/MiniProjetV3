package com.example.miniprojetv2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DetailsAgenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_agence);

        TextView textViewNom = findViewById(R.id.textViewNom);
        TextView textViewVille = findViewById(R.id.textViewVille);
        TextView textViewTel = findViewById(R.id.textViewTel);
        TextView textViewCodePostale = findViewById(R.id.textViewCodePostale);
        TextView textViewFax = findViewById(R.id.textViewFax);
        TextView textViewAdresse = findViewById(R.id.textViewAdresse);
        Button contactButton = findViewById(R.id.buttonContact);



        ImageView imageView = findViewById(R.id.home);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsAgenceActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        Button buttonLocaliser = findViewById(R.id.buttonLocaliser);

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsAgenceActivity.this,ContactActivity.class);
                startActivity(i);
            }
        });

        buttonLocaliser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adresseAgence = "tunis"; // Remplacez par l'adresse de votre agence

                Uri uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + adresseAgence);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);

                // Vérifier si une application peut gérer l'intent
               /* if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }*/
                mapIntent.setPackage("com.google.android.apps.maps");
                mapIntent.setFlags(mapIntent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mapIntent);
                /* else {
                    // Si aucune application de navigation n'est disponible, proposer à l'utilisateur d'installer Google Maps depuis le Play Store
                    Toast.makeText(getApplicationContext(), "Veuillez installer Google Maps pour localiser l'agence.", Toast.LENGTH_SHORT).show();
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.apps.maps"));
                    startActivity(playStoreIntent);
                } */
            }
        });




        Intent intent = getIntent();
        if (intent != null) {
        // Récupérer le nom de l'agence sélectionnée de l'intent
        String selectedAgenceName = getIntent().getStringExtra("SELECTED_AGENCE");

        // Récupérer la référence de la base de données Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference agencesRef = database.getReference("agences");

            agencesRef.orderByChild("nom").equalTo(selectedAgenceName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Agence agence = snapshot.getValue(Agence.class);
                            if (agence != null) {
                                // Afficher les détails de l'agence dans les TextViews
                                textViewAdresse.setText(agence.getAdresse());
                                textViewCodePostale.setText(agence.getCodePostal());
                                textViewFax.setText(agence.getFax());
                                textViewNom.setText(agence.getNom());
                                textViewTel.setText(agence.getTel());
                                textViewVille.setText(agence.getVille());

                            }
                        }
                    } catch (Exception e) {
                        Log.e("FirebaseError", "Erreur : " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(DetailsAgenceActivity.this, "Erreur de lecture des données.",
                            Toast.LENGTH_SHORT).show();
                    Log.e("FirebaseError", "Erreur d'annulation : " + databaseError.getMessage());
                }
            });
    }
}}