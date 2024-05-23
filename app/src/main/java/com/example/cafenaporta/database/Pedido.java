package com.example.cafenaporta.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity (foreignKeys = @ForeignKey(entity = Usuario.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = ForeignKey.CASCADE))

public class Pedido {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public Double preco;

    public String  endereco;

    public int userId;
}
