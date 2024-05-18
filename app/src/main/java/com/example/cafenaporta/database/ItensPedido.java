package com.example.cafenaporta.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (foreignKeys = @ForeignKey(entity = Usuario.class,
        parentColumns = "id",
        childColumns = "pedidoId",
        onDelete = ForeignKey.CASCADE))

public class ItensPedido {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nome;

    public String descricao;

    public Double preco;

    public String tipo;

    public int pedidoId;
}
