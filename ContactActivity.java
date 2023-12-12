package com.example.miniprojetv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        EditText nomEditText = findViewById(R.id.editTextNom);
        EditText telEditText = findViewById(R.id.editTextTel);
        EditText messageEditText = findViewById(R.id.editTextMessage);
        // Référence à la base de données Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
// Chemin vers la référence où les données seront stockées (dans ce cas, "messages")
        DatabaseReference myRef = database.getReference("messages");

// Écouteur pour le bouton "Envoyer"
        Button buttonEnvoyer = findViewById(R.id.button_envoyer);
        buttonEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupération des valeurs des champs nom, tel et message
                String nomValue = nomEditText.getText().toString();
                String telValue = telEditText.getText().toString();
                String messageValue = messageEditText.getText().toString();

                // Création d'un objet pour stocker les valeurs
                Map<String, Object> values = new HashMap<>();
                values.put("nom", nomValue);
                values.put("tel", telValue);
                values.put("message", messageValue);

                // Envoi des données à Firebase
                myRef.push().setValue(values)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Données enregistrées avec succès
                                Toast.makeText(ContactActivity.this, "Données envoyées avec succès", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Erreur lors de l'enregistrement des données
                                Toast.makeText(ContactActivity.this, "Erreur lors de l'envoi des données", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}