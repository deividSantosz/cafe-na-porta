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
    public int id;
    public int pedidoId;
    public int produtoId;
    public int quantidade;

    public Double preco;


}
