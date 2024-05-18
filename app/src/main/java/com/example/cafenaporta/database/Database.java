package com.example.cafenaporta.database;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Usuario.class, Pedido.class, ItensPedido.class}, version = 3)
public abstract class Database extends RoomDatabase {
    public abstract UsuarioDao getUserDao();
    public abstract PedidoDao getPedidoDao();
    public abstract ItensPedidoDao getItensPedidoDao();
}
