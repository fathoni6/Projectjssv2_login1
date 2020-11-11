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

public class DaftarActivity extends AppCompatActivity {

    Button btn_daftar,btn_batal;
    EditText edt_nik;
    DatabaseReference reference;
    String NIK_KEY = "nikkey";
    String nik_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        edt_nik = findViewById(R.id.edt_nik);
        btn_daftar = findViewById(R.id.btn_daftar);
        btn_batal = findViewById(R.id.btn_batal);

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(NIK_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(nik_key, edt_nik.getText().toString());
                editor.apply();

                reference = FirebaseDatabase.getInstance().getReference().child("Daftar").child(edt_nik.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("nik").setValue(edt_nik.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_SHORT).show();
                    }
                });

                Intent gotoalert = new Intent(DaftarActivity.this,AlertActivity.class);
                startActivity(gotoalert);
            }
        });

        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtologin = new Intent(DaftarActivity.this,LoginActivity.class);
                startActivity(backtologin);
            }
        });
    }
}