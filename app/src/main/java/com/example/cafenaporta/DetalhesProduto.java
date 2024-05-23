package com.example.cafenaporta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalhesProduto extends AppCompatActivity {

    ImageView img_produto, img_back;
    TextView txt_nome_produto, txt_preco_produto;
    EditText txt_descricao;

    Button btn_adicionar_carrinho;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        txt_nome_produto = findViewById(R.id.txt_nome_produto);
        txt_preco_produto = findViewById(R.id.txt_preco_produto);
        img_produto = findViewById(R.id.img_produto);
        txt_descricao = findViewById(R.id.txt_descricao);
        img_back = findViewById(R.id.img_back);
        btn_adicionar_carrinho = findViewById(R.id.btn_adicionar_carrinho);

        Intent intent = getIntent();
        int imagem = intent.getIntExtra("imagem", 0); // 0 é o valor padrão se a chave não for encontrada
        String nome = intent.getStringExtra("nome");
        double preco = intent.getDoubleExtra("preco", 0.0); // 0.0 é o valor padrão se a chave não for encontrada
        String descricao = intent.getStringExtra("descricao");

        img_produto.setImageResource(imagem);
        txt_nome_produto.setText(nome);
        txt_preco_produto.setText(String.valueOf(preco));
        txt_descricao.setText(descricao);


        btn_adicionar_carrinho.setOnClickListener((View view) ->{
            Intent intent_carrinho = new Intent(this, Carrinho.class);
            startActivity(intent_carrinho);
        });
        img_back.setOnClickListener((View view) -> {
            finish();
        });
    }
}