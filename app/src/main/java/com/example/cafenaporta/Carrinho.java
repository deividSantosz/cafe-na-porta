package com.example.cafenaporta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Carrinho extends AppCompatActivity {

    ImageView img_back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);


        img_back = findViewById(R.id.img_back);

        img_back.setOnClickListener((View view) -> {
            finish();
        });

    }
}