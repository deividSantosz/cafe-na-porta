package com.example.cafenaporta.database;


import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface Item_pedidoDAO {

    @Insert
    long insertItemPedido(Item_pedido itemPedido);

}
