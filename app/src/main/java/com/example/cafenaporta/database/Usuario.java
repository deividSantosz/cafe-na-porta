package com.example.cafenaporta.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nome;
    public String email;
    public String  telefone;
    public String senha;

}
