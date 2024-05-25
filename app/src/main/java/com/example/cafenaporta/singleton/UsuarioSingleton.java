package com.example.cafenaporta.singleton;

import com.example.cafenaporta.classesAuxiliares.Favorito;
import com.example.cafenaporta.classesAuxiliares.ItemCarrinho;

import java.util.ArrayList;
import java.util.List;

public class UsuarioSingleton {
    private static com.example.cafenaporta.singleton.UsuarioSingleton instance;
    private List<ItemCarrinho> favoritos;

    private UsuarioSingleton() {
        favoritos = new ArrayList<>();
    }

    public static com.example.cafenaporta.singleton.UsuarioSingleton getInstance() {
        if (instance == null) {
            instance = new com.example.cafenaporta.singleton.UsuarioSingleton();
        }
        return instance;
    }

    public void adicionarFavorito(ItemCarrinho favorito) {
        favoritos.add(favorito);
    }

    public boolean isFavorito(ItemCarrinho item) {
        return favoritos.contains(item);
    }
    public List<ItemCarrinho> getFavoritos() {
        return favoritos;
    }


    /*public void removerFavorito(ItemCarrinho item) {
        favoritos.remove(item);
    }*/
}
