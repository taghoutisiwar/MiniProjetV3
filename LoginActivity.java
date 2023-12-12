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

public class LoginActivity extends AppCompatActivity {

    EditText login , password ;
    Button btn_inscrire , btn_connecter ;
    FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        btn_inscrire = findViewById(R.id.btn_inscrire);
        mAuth = FirebaseAuth.getInstance();
        btn_connecter = findViewById(R.id.btn_connecter);

        btn_inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email , password_ ;
                email = String.valueOf(login.getText());
                password_ = String.valueOf(password.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this , "Enter email " , Toast.LENGTH_SHORT).show();
                    return ;
                }

                if (TextUtils.isEmpty(password_)){
                    Toast.makeText(LoginActivity.this , "Enter password " , Toast.LENGTH_SHORT).show();
                    return ;
                }
                mAuth.signInWithEmailAndPassword(email, password_)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Authentication created",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }
}