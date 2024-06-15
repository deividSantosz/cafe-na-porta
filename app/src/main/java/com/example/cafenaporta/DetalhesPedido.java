package com.example.cafenaporta;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cafenaporta.singleton.PedidoSingleton;

public class DetalhesPedido extends AppCompatActivity {

    TextView txt_endereco, txt_metodo_entrega, txt_pagamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pedido);

        txt_endereco = findViewById(R.id.txt_endereco);
        txt_metodo_entrega = findViewById(R.id.txt_metodo_entrega);
        txt_pagamento = findViewById(R.id.txt_pagamento);

        String endereco = PedidoSingleton.getInstance().getEndereco();
        txt_endereco.setText(endereco);


    }
}