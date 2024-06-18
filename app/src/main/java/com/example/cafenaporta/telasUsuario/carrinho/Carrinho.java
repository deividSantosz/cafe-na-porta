package com.example.cafenaporta.telasUsuario.carrinho;

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

import com.example.cafenaporta.telasUsuario.Pagamento;
import com.example.cafenaporta.classesAuxiliares.ItemCarrinho;
import com.example.cafenaporta.R;
import com.example.cafenaporta.singleton.CarrinhoSingleton;
import com.example.cafenaporta.singleton.ItemPedidoSingleton;
import com.example.cafenaporta.telasUsuario.telaPrincipal.Menu;

import java.util.List;

public class Carrinho extends AppCompatActivity {

    ImageView img_back;
    RecyclerView recyclerView;
    TextView txt_total;
    Button btn_continuar_comprando, btn_confirmar;
    double total;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        recyclerView = findViewById(R.id.rv_pedidos);

        Intent intent = getIntent();
        Long id = intent.getLongExtra("id" , 0);
        int imagem = intent.getIntExtra("imagem", 0);
        String nome = intent.getStringExtra("nome");
        double preco = intent.getDoubleExtra("preco", 0.0);
        ItemCarrinho carrinho = new ItemCarrinho(id, imagem, nome, preco);
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
        txt_total = findViewById(R.id.txt_total_entrega);
        btn_confirmar = findViewById(R.id.btn_confirmar_carrinho);

        txt_total.setText(String.format(" R$ %.2f", total));
        img_back.setOnClickListener((View view) -> {
            finish();
        });

        btn_continuar_comprando.setOnClickListener((View view) -> {
            Intent intent1 = new Intent(this, Menu.class);
            startActivity(intent1);
        });

        btn_confirmar.setOnClickListener((View view) -> {
            int quantidade =0;
            for (ItemCarrinho item : lista_carrinho) {
                ItemPedidoSingleton.getInstance().setPreco(item.getPreco());
               quantidade = quantidade+1;
            }
            System.out.println("ID ANTES DE ADICIONAR NO SINGLETON: " + id);
            ItemPedidoSingleton.getInstance().adicionarProduto(id);
            System.out.println("SINGLETON COM O ID:::" + ItemPedidoSingleton.getInstance().getProdutosId());
            ItemPedidoSingleton.getInstance().setQuantidade(quantidade);
            ItemPedidoSingleton.getInstance().calculartotal();
            Intent intent2 = new Intent(this, Pagamento.class);
            intent2.putExtra("Total", total);
            startActivity(intent2);
        });
    }
}