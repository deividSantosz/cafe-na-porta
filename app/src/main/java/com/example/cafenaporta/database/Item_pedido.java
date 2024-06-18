package com.example.cafenaporta.database;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Pedido.class,
                parentColumns = "id",
                childColumns = "pedidoId",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Produto.class,
                parentColumns = "id",
                childColumns = "produtoId",
                onDelete = ForeignKey.CASCADE)
})
public class Item_pedido {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public long pedidoId;
    public long produtoId;
    public int quantidade;

    public Double preco;

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

    public long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(long produtoId) {
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
}
