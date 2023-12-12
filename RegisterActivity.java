package com.example.miniprojetv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText cin , nom , prenom , tel , email , adresse , login , password ;
    Button btn_inscrire ;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        cin = findViewById(R.id.cin);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        tel = findViewById(R.id.tel);
        email = findViewById(R.id.email);
        adresse = findViewById(R.id.adresse);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        btn_inscrire = findViewById(R.id.btn_inscrire);
        mAuth = FirebaseAuth.getInstance();

        btn_inscrire.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email_ , password_ , Cin , Nom , Prenom , Tel , Email , Adresse  ;
                email_ = String.valueOf(login.getText());
                password_ = String.valueOf(password.getText());
                Cin = cin.getText().toString();
                Nom = nom.getText().toString();
                Prenom = prenom.getText().toString();
                Adresse = adresse.getText().toString();
                Tel = tel.getText().toString();
                Email = email.getText().toString();

                if (Cin.isEmpty()){
                    cin.setError("Cannot be empty");
                    return ;
                }
                if (Nom.isEmpty()){
                    nom.setError("Cannot be empty");
                    return ;
                }
                if (Prenom.isEmpty()){
                    prenom.setError("Cannot be empty");
                    return ;
                }
                if (Adresse.isEmpty()){
                    adresse.setError("Cannot be empty");
                    return ;
                }
                if (Tel.isEmpty()){
                    tel.setError("Cannot be empty");
                    return ;
                }
                if (Email.isEmpty()){
                    email.setError("Cannot be empty");
                    return ;
                }

                addUserToBD(Cin , Nom , Prenom , Adresse , Tel , Email , email_ , password_);
                
                mAuth.createUserWithEmailAndPassword(email_, password_)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Authentication created.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void addUserToBD(String cin, String nom, String prenom, String adresse, String tel, String email, String email_, String password_)
    {
        HashMap <String , Object> usersHashmap = new HashMap<>();
        usersHashmap.put("N cin " , cin );
        usersHashmap.put("Nom " , nom );
        usersHashmap.put("Prenom " , prenom );
        usersHashmap.put("Adresse" , adresse );
        usersHashmap.put("Tel" , tel );
        usersHashmap.put("Email " , email );
        usersHashmap.put("login" , email_ );
        usersHashmap.put("Password" , password_ );

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        String key = usersRef.push().getKey();
        usersHashmap.put("key",key);

        usersRef.child(key).setValue(usersHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(RegisterActivity.this,"Added" , Toast.LENGTH_SHORT).show();
                cin.toString();

            }
        });
    }
}