package com.example.cafenaporta.database;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.example.cafenaporta.R;

import java.util.ArrayList;
import java.util.List;

public class Produto {
        @PrimaryKey(autoGenerate = true)
        public int id;
        @ColumnInfo
        public String nome;
        @ColumnInfo
        public double preco;
        @ColumnInfo
        public String descricao;
        @ColumnInfo
        public String categoria;
        @ColumnInfo
        public int imagem; // Adiciona um campo para a imagem



    }

