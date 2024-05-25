package com.example.cafenaporta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cafenaporta.telaPrincipal.Menu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Perfil extends AppCompatActivity {

    TextView txtCadastrarCartao, txtEndereco, txtPedido;
    ImageView img_back;

    BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        txtEndereco = findViewById(R.id.txt_endereco);
        txtPedido = findViewById(R.id.txt_pedido);
        txtCadastrarCartao = findViewById(R.id.txt_cadastro_cartao);

        img_back = findViewById(R.id.img_back);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_favoritos) {
                    Intent favoritesIntent = new Intent(Perfil.this, Favoritos.class);
                    startActivity(favoritesIntent);
                    return true;
                }
                else {
                    Intent menuIntent = new Intent(Perfil.this, Menu.class);
                    startActivity(menuIntent);
                    return true;

                }
            }
        });


        txtPedido.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, Pedidos.class);
            startActivity(intent);
        });

        img_back.setOnClickListener((View view) -> {
            finish();
        });
    }
}