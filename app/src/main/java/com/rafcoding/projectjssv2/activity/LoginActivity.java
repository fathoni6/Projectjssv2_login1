package com.rafcoding.projectjssv2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rafcoding.projectjssv2.R;

public class LoginActivity extends AppCompatActivity {
    Button btn_daftar,btn_login;
    TextView aktivasi_ulang,lupa_password;
    EditText xusername, xpassword;
    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_daftar = findViewById(R.id.btn_daftar);
        btn_login = findViewById(R.id.btn_login);
        aktivasi_ulang = findViewById(R.id.aktivasi_ulang);
        lupa_password = findViewById(R.id.lupa_password);
        xusername = findViewById(R.id.xusername);
        xpassword = findViewById(R.id.xpassword);

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotodaftar = new Intent(LoginActivity.this, DaftarActivity.class);
                startActivity(gotodaftar);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = xusername.getText().toString();
                final String password = xpassword.getText().toString();

                reference = FirebaseDatabase.getInstance().getReference().child("DaftarAkun").child(username);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){

                            String passwordFromFirebase = dataSnapshot.child("PASSWORD_AKUN").getValue().toString();

                            if(password.equals(passwordFromFirebase)){
                                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(username_key, xusername.getText().toString());
                                editor.apply();

                                Intent gotohome = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(gotohome);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Password Salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"username tidak ada", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        aktivasi_ulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoaktivasiulang = new Intent(LoginActivity.this, AktivasiAkunActivity.class);
                startActivity(gotoaktivasiulang);
            }
        });

        lupa_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotolupapassword = new Intent(LoginActivity.this,LupaPasswordActivity.class);
                startActivity(gotolupapassword);
            }
        });
    }
}