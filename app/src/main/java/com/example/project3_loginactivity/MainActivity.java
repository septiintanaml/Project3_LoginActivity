package com.example.project3_loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText emailid, pass;
    Button daftar;
    TextView masuk;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        daftar = findViewById(R.id.button);
        masuk = findViewById(R.id.login);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String email = emailid.getText().toString();
                 String pwd = pass.getText().toString();
                 if(email.isEmpty()){
                     emailid.setText("Silahkan Masukkan Email");
                     emailid.requestFocus();
                 }
                 else if(pwd.isEmpty()){
                     pass.setText("Silahkan Masukkan Password");
                     pass.requestFocus();
                 }
                 else if(email.isEmpty() && pwd.isEmpty()){
                     Toast.makeText(MainActivity.this,"Silahkan Mengisi yang kosong", Toast.LENGTH_SHORT).show();
                 }
                 else if (!(email.isEmpty() && pwd.isEmpty())) {
                     mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if(!task.isSuccessful()){
                                 Toast.makeText(MainActivity.this, "Pendaftaran gagal, silahkan coba kembali",Toast.LENGTH_SHORT).show();
                             }
                             else {
                                 startActivity(new Intent((MainActivity.this), login.class));
                             }
                         }
                     });
                 }

                 else{
                     Toast.makeText(MainActivity.this,"Maaf terjadi kesalahan", Toast.LENGTH_SHORT).show();

                 }
            }
        });
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,login.class);
                startActivity(a);
            }
        });

    }
}