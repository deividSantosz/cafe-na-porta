package com.example.cafenaporta.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface ProdutoDao {

    @Query("SELECT * from Produto")
    public List<Produto> getAll();
    @Query("SELECT * FROM Produto WHERE nome = :nomeProduto")
    public List<Produto> getProdutoByNome(String nomeProduto);
    @Query("SELECT id FROM Produto WHERE nome = :nomeProduto")
    public int getProdutoByID(String nomeProduto);

    @Insert
    long insertProduto(Produto produto);
}
