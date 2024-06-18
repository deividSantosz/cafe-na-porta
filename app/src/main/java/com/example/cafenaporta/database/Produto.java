package com.example.cafenaporta.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cafenaporta.R;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Produto  {

        @PrimaryKey(autoGenerate = true)
        public long id;
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

        public double getPreco() {
                return preco;
        }

        public void setPreco(double preco) {
                this.preco = preco;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public String getCategoria() {
                return categoria;
        }

        public void setCategoria(String categoria) {
                this.categoria = categoria;
        }

        public int getImagem() {
                return imagem;
        }

        public void setImagem(int imagem) {
                this.imagem = imagem;
        }
}

