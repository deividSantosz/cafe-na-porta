package com.example.cafenaporta.singleton;

public class ItemPedidoSingleton {

    private static volatile ItemPedidoSingleton instance;

    private int id;
    private int pedidoId;
    private int produtoId;
    private int quantidade;
    private Double preco;

    private ItemPedidoSingleton() {
        // Evite instanciar a classe diretamente
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
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
