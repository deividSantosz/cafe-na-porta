package com.example.cafenaporta.telasUsuario.listaPedidos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.cafenaporta.telasUsuario.Favoritos;
import com.example.cafenaporta.telasUsuario.Perfil;
import com.example.cafenaporta.R;
import com.example.cafenaporta.database.Database;
import com.example.cafenaporta.database.Pedido;
import com.example.cafenaporta.singleton.UserSingleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ListaPedidos extends AppCompatActivity {

    RecyclerView rv_lista_pedidos;
    public BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);
        Database db = Room.databaseBuilder(getApplicationContext(), Database.class, "Cafe na porta BD")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        rv_lista_pedidos = findViewById(R.id.rv_lista_pedidos);
        long userId = UserSingleton.getInstance().getUserId();
        List<Pedido> pedidosDoUsuario = db.getPedidoDao().getPedidosByUserId(userId);
        ListaPedidosAdapter adapter = new ListaPedidosAdapter(pedidosDoUsuario, this);
        rv_lista_pedidos.setAdapter(adapter);
        rv_lista_pedidos.setLayoutManager(new LinearLayoutManager(this));
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_favoritos) {
                    Intent favoritesIntent = new Intent(ListaPedidos.this, Favoritos.class);
                    startActivity(favoritesIntent);
                    return true;
                }
                else {
                    Intent profileIntent = new Intent(ListaPedidos.this, Perfil.class);
                    startActivity(profileIntent);
                    return true;

                }
            }
        });
    }
}