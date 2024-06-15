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
import android.view.animation.ScaleAnimation;

import com.example.cafenaporta.carrinho.Carrinho;
import com.example.cafenaporta.classesAuxiliares.ItemCarrinho;
import com.example.cafenaporta.database.Pedido;
import com.example.cafenaporta.singleton.ItemPedidoSingleton;
import com.example.cafenaporta.singleton.PedidoSingleton;
import com.example.cafenaporta.singleton.UsuarioSingleton;

public class DetalhesProduto extends AppCompatActivity {
    int imagem;
    String nome;
    double preco;
    ImageView img_produto, img_back, img_favorito;
    TextView txt_nome_produto, txt_preco_produto;
    EditText txt_descricao;
    Button btn_adicionar_carrinho;
    int id;
    private boolean isFavorited = false;
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
        btn_adicionar_carrinho = findViewById(R.id.btn_confirmar_entrega);
        img_favorito = findViewById(R.id.img_favorito);

        Intent intent = getIntent();
         imagem = intent.getIntExtra("imagem", 0); // 0 é o valor padrão se a chave não for encontrada
         nome = intent.getStringExtra("nome");
         preco = intent.getDoubleExtra("preco", 0.0); // 0.0 é o valor padrão se a chave não for encontrada
         id = intent.getIntExtra("id", 0);
         String descricao = intent.getStringExtra("descricao");

        ItemCarrinho item = new ItemCarrinho(imagem, nome, preco);
        img_produto.setImageResource(imagem);
        txt_nome_produto.setText(nome);
        txt_preco_produto.setText(String.format("Preço: R$ %.2f", preco));
        txt_descricao.setText(descricao);
        UsuarioSingleton singleton = UsuarioSingleton.getInstance();
        if (!singleton.isFavorito(item)) {
            img_favorito.setImageResource(R.drawable.baseline_favorite_border_24);
        } else {
            img_favorito.setImageResource(R.drawable.baseline_favorite_24_preenchido);

        }

        btn_adicionar_carrinho.setOnClickListener((View view) ->{

            Intent intent_carrinho = new Intent(this, Carrinho.class);

            // Adicione os dados do produto à Intent
            intent_carrinho.putExtra("imagem", imagem);
            intent_carrinho.putExtra("nome", nome);
            intent_carrinho.putExtra("preco", preco);
            // Inicie a nova atividade com a Intent que contém os dados
            ItemPedidoSingleton itemPedidoSingleton = ItemPedidoSingleton.getInstance();
            itemPedidoSingleton.setProdutoId(id);
            Pedido pedido = PedidoSingleton.getInstance();
            startActivity(intent_carrinho);
        });

        img_favorito.setOnClickListener((View view) -> {
                toggleFavorite();
                Intent intent_carrinho = new Intent(this, Favoritos.class);
                intent_carrinho.putExtra("imagem", imagem);
                intent_carrinho.putExtra("nome", nome);
                intent_carrinho.putExtra("preco", preco);
        });
        img_back.setOnClickListener((View view) -> {
            finish();
        });
    }

    private void toggleFavorite() {
        ItemCarrinho item = new ItemCarrinho(imagem, nome, preco);
        UsuarioSingleton singleton = UsuarioSingleton.getInstance();
        if (!singleton.isFavorito(item)) {
            animateHeart();
            // Adiciona o item aos favoritos
            singleton.adicionarFavorito(item);
            img_favorito.setImageResource(R.drawable.baseline_favorite_24_preenchido);

        }
    }

    private void animateHeart() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0.7f, 1.0f, 0.7f, 1.0f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillAfter(true);
        img_favorito.startAnimation(scaleAnimation);
    }

}