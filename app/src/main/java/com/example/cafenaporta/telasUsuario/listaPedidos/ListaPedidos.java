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
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.cafenaporta.database.Pedido;
import com.example.cafenaporta.R;
import com.example.cafenaporta.database.Database;
import com.example.cafenaporta.singleton.UserSingleton;
import com.example.cafenaporta.telasUsuario.Favoritos;
import com.example.cafenaporta.telasUsuario.Perfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListaPedidos extends AppCompatActivity {

    ImageView img_filtro;
    RecyclerView rv_lista_pedidos;
    public BottomNavigationView bottomNavigationView;
    private ListaPedidosAdapter adapter;
    private List<Pedido> pedidosDoUsuario;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        // Inicializar o banco de dados
        Database db = Room.databaseBuilder(getApplicationContext(), Database.class, "Cafe na porta BD")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        img_filtro = findViewById(R.id.img_filtros);
        rv_lista_pedidos = findViewById(R.id.rv_lista_pedidos);

        // Obter o ID do usuário a partir do Singleton
        long userId = UserSingleton.getInstance().getUserId();

        // Obter a lista de pedidos do usuário
        pedidosDoUsuario = db.getPedidoDao().getPedidosByUserId(userId);

        // Inicializar o adaptador com a lista de pedidos
        adapter = new ListaPedidosAdapter(pedidosDoUsuario, this);
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
                } else {
                    Intent profileIntent = new Intent(ListaPedidos.this, Perfil.class);
                    startActivity(profileIntent);
                    return true;
                }
            }
        });

        // Configurar o clique na imagem de filtro para mostrar o PopupMenu
        img_filtro.setOnClickListener((View view) -> {
            mostrarMenuDeFiltros(view);
        });
    }

    private void mostrarMenuDeFiltros(View v) {
        PopupMenu popupMenu = new PopupMenu(ListaPedidos.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_filtros, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.filtro_data) {
                        aplicarFiltroPorData();

                        return true;
                    }

                    if (item.getItemId() == R.id.filtro_preco) {
                        System.out.println("ENTROU PREÇO");
                        aplicarFiltroPorPreco();

                        return true;
                    }

                return false;
            }

        });

        popupMenu.show();
    }

    private void aplicarFiltroPorPreco() {

        // Ordenar a lista de pedidos por preço
        Collections.sort(pedidosDoUsuario, new Comparator<Pedido>() {
            @Override
            public int compare(Pedido p1, Pedido p2) {
                return Double.compare(p1.getTotal(), p2.getTotal());
            }
        });

        // Notificar o adaptador que a lista foi alterada
        adapter.notifyDataSetChanged();
    }


    private void aplicarFiltroPorData() {
        Collections.sort(pedidosDoUsuario, new Comparator<Pedido>() {
            @Override
            public int compare(Pedido p1, Pedido p2) {
                return Long.compare(p1.getData(), p2.getData());
            }
        });
        adapter.notifyDataSetChanged();
    }

}
