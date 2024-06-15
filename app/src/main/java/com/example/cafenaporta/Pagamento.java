package com.example.cafenaporta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafenaporta.singleton.ItemPedidoSingleton;
import com.example.cafenaporta.singleton.PedidoSingleton;

public class Pagamento extends AppCompatActivity {

    TextView txt_total;
    RadioGroup metodo_pagamento;

    Button btn_confirmar;

    ImageView img_voltar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        txt_total = findViewById(R.id.txt_total_entrega);
        btn_confirmar = findViewById(R.id.btn_confirmar_entrega);
        metodo_pagamento = findViewById(R.id.rg_metodo_entrega);
        img_voltar = findViewById(R.id.img_voltar);


        Intent intent = getIntent();
        Double total = intent.getDoubleExtra("Total", 0.0);
        txt_total.setText(String.format(" R$ %.2f", total));


//          Definindo o total no PedidoSingleton
        PedidoSingleton.getInstance().setTotal(total);


        btn_confirmar.setOnClickListener((View view) -> {

            // Obtendo o ID do RadioButton selecionado
            int radioButtonID = metodo_pagamento.getCheckedRadioButtonId();
            if (radioButtonID != -1) { // Verifica se algum RadioButton foi selecionado
                // Encontrar o RadioButton selecionado usando o ID
                RadioButton opcaoSelecionada = findViewById(radioButtonID);
                String opcaoTexto = opcaoSelecionada.getText().toString();

                // Definindo o método de pagamento no PedidoSingleton
                PedidoSingleton.getInstance().setMetodo_pagamento(opcaoTexto);

                // Iniciar a atividade de Entrega
                Intent intent2 = new Intent(this, Entrega.class);
                intent2.putExtra("precoCalculado", total);
                startActivity(intent2);
            } else {
                // Caso nenhum RadioButton tenha sido selecionado
                Toast.makeText(this, "Selecione uma opção de pagamento", Toast.LENGTH_SHORT).show();
            }
        });

        img_voltar.setOnClickListener((View view) -> {
            finish();
        });



    }
}