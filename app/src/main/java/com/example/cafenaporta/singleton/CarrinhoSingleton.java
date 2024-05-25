package com.example.cafenaporta.singleton;

import com.example.cafenaporta.classesAuxiliares.ItemCarrinho;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoSingleton {

    // Instância única do Singleton
    private static CarrinhoSingleton instance;

    // Lista de itens no carrinho
    private List<ItemCarrinho> itensCarrinho;

    // Construtor privado para evitar a criação de instâncias diretamente
    private CarrinhoSingleton() {
        itensCarrinho = new ArrayList<>();
    }

    // Método estático para obter a instância única do Singleton
    public static CarrinhoSingleton getInstance() {
        // Se a instância ainda não foi criada, cria uma nova
        if (instance == null) {
            instance = new CarrinhoSingleton();
        }
        // Retorna a instância existente
        return instance;
    }

    // Método para adicionar um item ao carrinho
    public void adicionarItem(ItemCarrinho item) {
        itensCarrinho.add(item);
    }

    // Método para obter a lista de itens no carrinho
    public List<ItemCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }

    // Outros métodos úteis para manipulação do carrinho podem ser adicionados aqui
}

