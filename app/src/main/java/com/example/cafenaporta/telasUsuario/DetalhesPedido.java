package com.example.cafenaporta.telasUsuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafenaporta.R;
import com.example.cafenaporta.telasUsuario.carrinho.CarrinhoAdapter;
import com.example.cafenaporta.classesAuxiliares.ItemCarrinho;
import com.example.cafenaporta.database.Database;
import com.example.cafenaporta.database.Item_pedido;
import com.example.cafenaporta.database.Pedido;
import com.example.cafenaporta.database.Usuario;
import com.example.cafenaporta.telasUsuario.listaPedidos.ListaPedidos;
import com.example.cafenaporta.singleton.CarrinhoSingleton;
import com.example.cafenaporta.singleton.ItemPedidoSingleton;
import com.example.cafenaporta.singleton.PedidoSingleton;
import com.example.cafenaporta.singleton.UserSingleton;

import java.util.List;

public class DetalhesPedido extends AppCompatActivity {

    TextView txt_endereco, txt_metodo_entrega, txt_pagamento, txt_endereco_final, txt_total_final;
    CardView card_endereco;
    RecyclerView rv_pedidos;
    Button btn_confirmar_pedido;
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
        btn_confirmar_pedido = findViewById(R.id.btn_pedido);

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

        btn_confirmar_pedido.setOnClickListener((View view) -> {

            Database db = Room.databaseBuilder(getApplicationContext(), Database.class, "Cafe na porta BD")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

            long userId = UserSingleton.getInstance().getUserId();
            Usuario usuario = db.getUserDao().getUserById(userId);

            if (usuario != null) {
                Pedido pedido = new Pedido();
                pedido.setMetodo_pagamento(PedidoSingleton.getInstance().getMetodo_pagamento());
                pedido.setEndereco(PedidoSingleton.getInstance().getEndereco());
                pedido.setStatus_pedido("Em preparação");
                pedido.setTotal(PedidoSingleton.getInstance().getTotal());
                pedido.setUserId(UserSingleton.getInstance().getUserId());
                long timestamp = System.currentTimeMillis();
                pedido.setData(timestamp);
                Long pedido_id = db.getPedidoDao().insertPedido(pedido);

                if (pedido_id > 0) {
                    System.out.println("ID DO PEDIDO::: " +pedido_id);
                    System.out.println("ID DOS PRODUTOS::: " + ItemPedidoSingleton.getInstance().getProdutosId() );
                    List<Long> listaProdutosId = ItemPedidoSingleton.getInstance().getProdutosId();
                    for (Long produtoId : listaProdutosId) {
                        System.out.println("ID DO PRODUTO:::::" + produtoId);
                        Item_pedido itemPedido = new Item_pedido();
                        itemPedido.setPedidoId(pedido_id);
                        itemPedido.setProdutoId(produtoId);
                        itemPedido.setQuantidade(ItemPedidoSingleton.getInstance().getQuantidade());
                        itemPedido.setPreco(ItemPedidoSingleton.getInstance().getPreco());

                        db.getItem_pedidoDAO().insertItemPedido(itemPedido);

                        System.out.println("ITEM PEDIDO: " + itemPedido.getPedidoId() + " " + itemPedido.getId() + " " + itemPedido.getQuantidade() + " " + itemPedido.getPreco() + " " + itemPedido.getProdutoId());
                        CarrinhoSingleton.getInstance().limparCarrinho();
                    }
                } else {
                    Toast.makeText(this, "Falha ao inserir o pedido", Toast.LENGTH_SHORT).show();
                }
            }

            Intent intent = new Intent(this, ListaPedidos.class);
            startActivity(intent);
        });

    }
}