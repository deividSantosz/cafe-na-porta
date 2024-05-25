package com.example.cafenaporta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.cafenaporta.carrinho.CarrinhoAdapter;
import com.example.cafenaporta.classesAuxiliares.Favorito;
import com.example.cafenaporta.classesAuxiliares.ItemCarrinho;
import com.example.cafenaporta.singleton.CarrinhoSingleton;
import com.example.cafenaporta.singleton.UsuarioSingleton;
import com.example.cafenaporta.telaPrincipal.Menu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Favoritos extends AppCompatActivity {

    ImageView back_favoritos;

    RecyclerView rv_favoritos;

    BottomNavigationView bottomNavigationView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        back_favoritos = findViewById(R.id.back_favoritos);
        rv_favoritos = findViewById(R.id.rv_favoritos);
        List<ItemCarrinho> listaFavoritos = UsuarioSingleton.getInstance().getFavoritos();

        CarrinhoAdapter adapter = new CarrinhoAdapter(listaFavoritos, this);
        rv_favoritos.setAdapter(adapter);
        rv_favoritos.setLayoutManager(new LinearLayoutManager(this));

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_menu) {
                    Intent intent = new Intent(Favoritos.this, Menu.class);
                    startActivity(intent);
                    return true;
                }
                else {
                    Intent profileIntent = new Intent(Favoritos.this, Perfil.class);
                    startActivity(profileIntent);
                    return true;

                }
            }
        });

        back_favoritos.setOnClickListener((View view) -> {
            finish();

        });

    }
}