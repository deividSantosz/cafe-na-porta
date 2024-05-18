package com.example.cafenaporta.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT * from usuario WHERE email=:email AND senha=:senha")
    public Usuario getUserLogin(String email, String senha);

    @Query("SELECT * from usuario WHERE email=:email")
    public Usuario getUserCadastro( String email);

    @Query("SELECT * from usuario")
    public List<Usuario> getAll();

    @Transaction
    @Query("SELECT * FROM Usuario")
    List<UsuarioPedido> getUsuariosWithPedidos();

    @Insert
    public void insereUsuario(Usuario usuario);

    @Insert
    void insertPedido(Pedido pedido);
}
