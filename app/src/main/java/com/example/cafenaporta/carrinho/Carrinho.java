package com.example.cafenaporta.carrinho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cafenaporta.classesAuxiliares.ItemCarrinho;
import com.example.cafenaporta.R;
import com.example.cafenaporta.singleton.CarrinhoSingleton;
import com.example.cafenaporta.telaPrincipal.Menu;

import java.util.List;

public class Carrinho extends AppCompatActivity {

    ImageView img_back;
    RecyclerView recyclerView;
    TextView txt_total;
    Button btn_continuar_comprando;
    double total;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        recyclerView = findViewById(R.id.rv_pedidos);

        Intent intent = getIntent();
        int imagem = intent.getIntExtra("imagem", 0);
        String nome = intent.getStringExtra("nome");
        double preco = intent.getDoubleExtra("preco", 0.0);
        ItemCarrinho carrinho = new ItemCarrinho(imagem, nome, preco);
        List<ItemCarrinho> lista_carrinho = CarrinhoSingleton.getInstance().getItensCarrinho();
        lista_carrinho.add(carrinho);

        for (ItemCarrinho item : lista_carrinho) {
            total += item.getPreco();
        }

        CarrinhoAdapter adapter = new CarrinhoAdapter(lista_carrinho, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        img_back = findViewById(R.id.img_back);
        btn_continuar_comprando = findViewById(R.id.btn_continuar_comprando);
        txt_total = findViewById(R.id.txt_total);

        txt_total.setText(String.valueOf(total));
        img_back.setOnClickListener((View view) -> {
            finish();
        });

        btn_continuar_comprando.setOnClickListener((View view) -> {
            Intent intent1 = new Intent(this, Menu.class);
            startActivity(intent1);
        });
    }
}