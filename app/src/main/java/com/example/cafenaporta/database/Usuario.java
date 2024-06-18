package com.example.cafenaporta.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cafenaporta.classesAuxiliares.Favorito;

import java.util.List;

@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String nome;
    public String email;
    public String  telefone;
    public String senha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
