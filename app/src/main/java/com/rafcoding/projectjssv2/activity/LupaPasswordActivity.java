package com.rafcoding.projectjssv2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.rafcoding.projectjssv2.R;

public class LupaPasswordActivity extends AppCompatActivity {

    Button btn_kode_verifikasi,btn_batal_verifikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        btn_kode_verifikasi = findViewById(R.id.btn_kode_verifikasi);
        btn_batal_verifikasi = findViewById(R.id.btn_batal_verifikasi);

        btn_kode_verifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotohome = new Intent(LupaPasswordActivity.this, HomeActivity.class);
                startActivity(gotohome);
            }
        });

        btn_batal_verifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtodaftar = new Intent(LupaPasswordActivity.this, LoginActivity.class);
                startActivity(backtodaftar);
            }
        });
    }
}