package com.example.cafenaporta.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface PedidoDao {
    @Query("SELECT * from Pedido")
    public List<Pedido> getAll();

    @Transaction
    @Query("SELECT * FROM Pedido")
    List<PedidoItem> Pedidos_itens();

    @Insert
    void insertPedido(Pedido pedido);
    @Insert
    public void ItensPedido(ItensPedido itens);
}
