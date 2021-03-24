package com.example.project3_loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class forgotpassword extends AppCompatActivity {
    EditText emailAd;
    Button find;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        emailAd = findViewById(R.id.email);
        find = findViewById(R.id.button);
        mFirebaseAuth = FirebaseAuth.getInstance();

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailAd.getText().toString();
                boolean valid = true;
                if(email.isEmpty()){
                    emailAd.setText("Tolong masukkan email");
                    emailAd.requestFocus();
                    Toast.makeText(forgotpassword.this, "Email kosong", Toast.LENGTH_SHORT).show();
                    valid = false;
                }
                if(valid){
                    mFirebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(forgotpassword.this, "Password telah dikirim", Toast.LENGTH_SHORT).show();
                            Intent g = new Intent(forgotpassword.this, login.class);
                            startActivity(g);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(forgotpassword.this, "Email tidak ditemukan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
