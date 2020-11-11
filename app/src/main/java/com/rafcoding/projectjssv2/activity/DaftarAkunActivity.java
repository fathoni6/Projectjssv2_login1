package com.rafcoding.projectjssv2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rafcoding.projectjssv2.R;

public class DaftarAkunActivity extends AppCompatActivity {
    Button btn_daftarakunbarubaru,btn_batalkandaftarakunbaru;
    EditText nomor_nik, nama_ktp, alamat_rumah, alamat_email, nomor_handphone, username, password;
    DatabaseReference reference;
    String USERNAME = "usernamekey";
    String usernamekey = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_akun);
        nomor_nik = findViewById(R.id.nomor_nik);
        nama_ktp = findViewById(R.id.nama_ktp);
        alamat_rumah = findViewById(R.id.alamat_rumah);
        alamat_email = findViewById(R.id.alamat_email);
        nomor_handphone = findViewById(R.id.nomor_handphone);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_daftarakunbarubaru = findViewById(R.id.btn_daftarakunbaru);
        btn_batalkandaftarakunbaru = findViewById(R.id.btn_batalkandaftarakunbaru);

        btn_daftarakunbarubaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(usernamekey, username.getText().toString());
                editor.apply();

                reference = FirebaseDatabase.getInstance().getReference().child("DaftarAkun").child(username.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("ALAMAT_EMAIL").setValue(alamat_email.getText().toString());
                        dataSnapshot.getRef().child("ALAMAT_RUMAH").setValue(alamat_rumah.getText().toString());
                        dataSnapshot.getRef().child("NAMA_KTP").setValue(nama_ktp.getText().toString());
                        dataSnapshot.getRef().child("NOMOR_HANDPHONE").setValue(nomor_handphone.getText().toString());
                        dataSnapshot.getRef().child("NOMOR_NIK").setValue(nomor_nik.getText().toString());
                        dataSnapshot.getRef().child("PASSWORD_AKUN").setValue(password.getText().toString());
                        dataSnapshot.getRef().child("USERNAME_AKUN").setValue(username.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_SHORT).show();
                    }
                });

                Intent gotohome = new Intent(DaftarAkunActivity.this, HomeActivity.class);
                startActivity(gotohome);
            }
        });

        btn_batalkandaftarakunbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtodaftar = new Intent(DaftarAkunActivity.this, DaftarActivity.class);
                startActivity(backtodaftar);
            }
        });
    }
}