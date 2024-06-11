package com.example.cafenaporta.database;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Usuario.class, Pedido.class, Produto.class, Item_pedido.class}, version = 5)
public abstract class Database extends RoomDatabase {
    public abstract UsuarioDao getUserDao();
    public abstract PedidoDao getPedidoDao();
    public abstract ProdutoDao getProdutoDao();
    public abstract Item_pedidoDAO getItem_pedidoDAO();
}
