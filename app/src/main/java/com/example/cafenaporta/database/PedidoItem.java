package com.example.cafenaporta.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PedidoItem {
    @Embedded
    public Pedido pedido;

    @Relation(
            parentColumn = "id",
            entityColumn = "pedidoId"
    )
    public List<ItensPedido> itens;
}
