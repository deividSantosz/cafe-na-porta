package com.example.cafenaporta.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PedidoDao {
    @Query("SELECT * from Pedido")
    public List<Pedido> getAll();
    @Insert
    long insertPedido(Pedido pedido);
    @Query("SELECT * FROM pedido WHERE userId = :userId")
    List<Pedido> getPedidosByUserId(long userId);
    @Query("SELECT * FROM Pedido WHERE id=:pedidoId")
    Pedido getPedidoById(long pedidoId);

    @Update
    void updatePedido(Pedido pedido);
}
