package com.example.cafenaporta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Favoritos extends AppCompatActivity {

    ImageView back_favoritos;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        back_favoritos = findViewById(R.id.back_favoritos);

        back_favoritos.setOnClickListener((View view) -> {
            finish();

        });

    }
}