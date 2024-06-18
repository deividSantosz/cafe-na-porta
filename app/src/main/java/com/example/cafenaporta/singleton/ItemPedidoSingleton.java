package com.example.cafenaporta.singleton;

import com.example.cafenaporta.database.Item_pedido;

import java.util.ArrayList;
import java.util.List;

public class ItemPedidoSingleton {

    private static volatile ItemPedidoSingleton instance;

    private long id;
    private long pedidoId;
    private List<Long> produtoId;
    private int quantidade;
    private Double preco;

    private ItemPedidoSingleton() {
        // Evite instanciar a classe diretamente
        produtoId = new ArrayList<>();

    }

    public static ItemPedidoSingleton getInstance() {
        if (instance == null) {
            synchronized (ItemPedidoSingleton.class) {
                if (instance == null) {
                    instance = new ItemPedidoSingleton();
                }
            }
        }
        return instance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public void adicionarProduto(Long produtoId) {
        this.produtoId.add(produtoId);
    }

    public List<Long> getProdutosId() {
        return produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double calculartotal() {
        return preco*quantidade;
    }

}
