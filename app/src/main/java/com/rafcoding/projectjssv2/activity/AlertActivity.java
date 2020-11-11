package com.rafcoding.projectjssv2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rafcoding.projectjssv2.R;

public class AlertActivity extends AppCompatActivity {

    Button acceptButton,cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        acceptButton = findViewById(R.id.acceptButton);
        cancelButton = findViewById(R.id.cancelButton);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotodaftarakun = new Intent(AlertActivity.this,DaftarAkunActivity.class);
                startActivity(gotodaftarakun);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtodaftar = new Intent(AlertActivity.this,DaftarActivity.class);
                startActivity(backtodaftar);
            }
        });
    }
}