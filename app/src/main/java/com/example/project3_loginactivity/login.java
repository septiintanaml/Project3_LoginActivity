package com.example.project3_loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    EditText emailid, pass;
    Button signin;
    TextView tvdaftar;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        signin = findViewById(R.id.button);
        tvdaftar = findViewById(R.id.daftar);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null){
                    Toast.makeText(login.this,"Anda telah Berhasil Masuk",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login.this, home.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(login.this, "Silahkan Login Terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        };

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String password = pass.getText().toString();
                if(email.isEmpty()){
                    emailid.setText("Tolong masukkan alamat email");
                    emailid.requestFocus();
                }
                else if(password.isEmpty()){
                    pass.setText("Tolong masukkan password");
                    pass.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(login.this,"Isi yang kosong", Toast.LENGTH_SHORT).show();
                }
                else if(!email.isEmpty() && password.isEmpty()){
                    mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(login.this,"Login Error", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent i = new Intent(login.this, home.class);
                                startActivity(i);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(login.this, "Terjadi Kesalahan",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar = new Intent(login.this, MainActivity.class);
                startActivity(daftar);
            }
        });
    }
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}