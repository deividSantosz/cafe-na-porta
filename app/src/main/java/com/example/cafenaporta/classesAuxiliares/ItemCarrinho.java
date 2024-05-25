package com.example.cafenaporta.classesAuxiliares;

public class ItemCarrinho {
    private int imagem;
    private String nome;
    private double preco;

    public ItemCarrinho() {

    }
    public ItemCarrinho(int imagem, String nome, double preco) {
        this.imagem = imagem;
        this.nome = nome;
        this.preco = preco;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
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
}
