package com.example.cafenaporta.telasUsuario.telaPrincipal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.cafenaporta.telasUsuario.Favoritos;
import com.example.cafenaporta.telasUsuario.Perfil;
import com.example.cafenaporta.R;
import com.example.cafenaporta.database.Database;
import com.example.cafenaporta.database.Produto;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    public SearchView searchView;
    public RecyclerView RV_item;
    public BottomNavigationView bottomNavigationView;
    public List<Produto> listaProdutos = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        RV_item = findViewById(R.id.RV_itens);
        listaProdutos = ProdutosIniciais();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        MenuAdapter adapter = new MenuAdapter(listaProdutos, this);
        RV_item.setAdapter(adapter);
        RV_item.setLayoutManager(layoutManager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_favoritos) {
                        Intent favoritesIntent = new Intent(Menu.this, Favoritos.class);
                        startActivity(favoritesIntent);
                        return true;
                }
                else {
                        Intent profileIntent = new Intent(Menu.this, Perfil.class);
                        startActivity(profileIntent);
                        return true;

                }
            }
        });

    }

    public List<Produto> ProdutosIniciais() {
        Database db = Room.databaseBuilder(getApplicationContext(), Database.class, "Cafe na porta BD")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        List<Produto> list = db.getProdutoDao().getAll();

        if (list.isEmpty()) {
            Produto cappucinoAvela = new Produto();
            cappucinoAvela.categoria = "cappucino";
            cappucinoAvela.nome = "Cappucino com Avelã";
            cappucinoAvela.descricao = "Confere o real autêntico sabor do café com um toque de avelã. " +
                    "Com formulação rica em leite integral e sem corantes, tem ótimo rendimento, cremosidade e textura aveludada.";
            cappucinoAvela.imagem = R.drawable.cappucino_avela3;
            cappucinoAvela.preco = 10.00;

            Produto cappucinoComChocolate = new Produto();
            cappucinoComChocolate.categoria = "cappucino";
            cappucinoComChocolate.nome = "Cappucino com chocolate";
            cappucinoComChocolate.descricao = "O sabor é doce, com suaves notas de café e uma pitada de canela que irá conquistar seu paladar. " +
                    "Ideal para uma pausa para o coffee. Aproveite!";
            cappucinoComChocolate.imagem = R.drawable.cappucino_com_chocolate3;
            cappucinoComChocolate.preco = 15.00;

            Produto cappucinoCremeso = new Produto();
            cappucinoCremeso.categoria = "cappucino";
            cappucinoCremeso.nome = "Cappucino cremoso";
            cappucinoCremeso.descricao = "O Cappuccino Amiste Café apresenta uma formulação exclusiva, " +
                    "cuidadosamente desenvolvida para proporcionar um cappuccino saboroso e com textura cremosa. ";
            cappucinoCremeso.imagem = R.drawable.cappucino_cremoso2;
            cappucinoCremeso.preco = 11.00;

            Produto cappucinoBanana = new Produto();
            cappucinoBanana.categoria = "cappucino";
            cappucinoBanana.nome = "Cappucino com banana";
            cappucinoBanana.descricao = "Um cappuccino clássico, muito famoso no Brasil, consiste em um terço de café expresso, " +
                    "um terço de leite vaporizado e um terço de espuma de leite vaporizado";
            cappucinoBanana.imagem = R.drawable.cappucino_banana2;
            cappucinoBanana.preco = 12.00;

            db.getProdutoDao().insertProduto(cappucinoAvela);
            db.getProdutoDao().insertProduto(cappucinoComChocolate);
            db.getProdutoDao().insertProduto(cappucinoCremeso);
            db.getProdutoDao().insertProduto(cappucinoBanana);

            list.add(cappucinoAvela);
            list.add(cappucinoComChocolate);
            list.add(cappucinoCremeso);
            list.add(cappucinoBanana);
        }

        return list;
    }




}
