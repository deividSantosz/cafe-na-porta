package com.example.cafenaporta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cafenaporta.carrinho.CarrinhoAdapter;
import com.example.cafenaporta.classesAuxiliares.ItemCarrinho;
import com.example.cafenaporta.singleton.CarrinhoSingleton;
import com.example.cafenaporta.singleton.ItemPedidoSingleton;
import com.example.cafenaporta.singleton.PedidoSingleton;

import java.util.List;

public class DetalhesPedido extends AppCompatActivity {

    TextView txt_endereco, txt_metodo_entrega, txt_pagamento, txt_endereco_final, txt_total_final;
    CardView card_endereco;
    RecyclerView rv_pedidos;
    double total;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pedido);

        txt_endereco = findViewById(R.id.txt_endereco);
        txt_metodo_entrega = findViewById(R.id.txt_metodo_entrega);
        txt_pagamento = findViewById(R.id.txt_pagamento);
        card_endereco = findViewById(R.id.card_endereco);
        txt_endereco_final = findViewById(R.id.txt_endereco_final);
        rv_pedidos = findViewById(R.id.rv_pedidos);
        txt_total_final = findViewById(R.id.txt_total_final);

        List<ItemCarrinho> lista_carrinho = CarrinhoSingleton.getInstance().getItensCarrinho();

        for (ItemCarrinho item : lista_carrinho) {
            total += item.getPreco();
        }
        txt_total_final.setText(String.format(" R$ %.2f", total));

        CarrinhoAdapter adapter = new CarrinhoAdapter(lista_carrinho, this);
        rv_pedidos.setAdapter(adapter);
        rv_pedidos.setLayoutManager(new LinearLayoutManager(this));


        String metodo_pagamento = PedidoSingleton.getInstance().getMetodo_pagamento();
        txt_pagamento.setText(metodo_pagamento);

        String metodo_entrega = PedidoSingleton.getInstance().getEndereco();
        if (metodo_entrega == null) {
            card_endereco.setVisibility(View.GONE);
            txt_endereco_final.setVisibility(View.GONE);
            String retirada = "Retirada";
            txt_metodo_entrega.setText(retirada);
        }
        else {
            String endereco = PedidoSingleton.getInstance().getEndereco();
            txt_endereco.setText(endereco);
            txt_metodo_entrega.setText("Entrega");
        }


    }
}