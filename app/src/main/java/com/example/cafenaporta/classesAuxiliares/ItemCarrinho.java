package com.example.cafenaporta.classesAuxiliares;

public class ItemCarrinho {
    private long produtoId;
    private int imagem;
    private String nome;
    private double preco;

    public ItemCarrinho() {

    }
    public ItemCarrinho(long produtoId, int imagem, String nome, double preco) {
        this.produtoId = produtoId;
        this.imagem = imagem;
        this.nome = nome;
        this.preco = preco;
    }
    public long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(long produtoId) {
        this.produtoId = produtoId;
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
