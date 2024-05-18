package com.example.cafenaporta.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UsuarioPedido {
        @Embedded
        public Usuario usuario;

        @Relation(
                parentColumn = "id",
                entityColumn = "userId"
        )
        public List<Pedido> pedidos;
}
