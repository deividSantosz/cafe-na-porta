package com.example.cafenaporta.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface ItensPedidoDao {
    @Query("SELECT * from ItensPedido")
    public List<ItensPedido> getAll();
    @Insert
    void insertItem(ItensPedido itensPedido);

}
