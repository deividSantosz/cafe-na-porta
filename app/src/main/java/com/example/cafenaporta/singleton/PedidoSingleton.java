package com.example.cafenaporta.singleton;

import com.example.cafenaporta.database.Pedido;

// Classe para implementar o padrão Singleton para a classe Pedido
public class PedidoSingleton {

    private static Pedido instance;

    public static synchronized Pedido getInstance() {
        if (instance == null) {
            instance = new Pedido();
        }
        return instance;
    }

    // Métodos getter e setter para acessar os campos do Pedido

    public Double getTotal() {
        return getInstance().total;
    }

    public void setTotal(Double total) {
        getInstance().total = total;
    }

    public String getEndereco() {
        return getInstance().endereco;
    }

    public void setEndereco(String endereco) {
        getInstance().endereco = endereco;
    }

    public String getMetodoPagamento() {
        return getInstance().metodo_pagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        getInstance().metodo_pagamento = metodoPagamento;
    }

    public long getUserId() {
        return getInstance().userId;
    }

    public void setUserId(int userId) {
        getInstance().userId = userId;
    }
}

