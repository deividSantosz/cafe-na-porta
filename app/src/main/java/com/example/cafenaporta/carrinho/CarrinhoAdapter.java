package com.example.cafenaporta.carrinho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafenaporta.classesAuxiliares.ItemCarrinho;
import com.example.cafenaporta.R;

import java.util.List;

public class CarrinhoAdapter extends RecyclerView.Adapter<LineViewHolderCarrinho> {

    // lista global de entidade
    private final List<ItemCarrinho> listaProdutos;
    private Context activityContext;
    // contrutor que recebe a lista de entidades

    public CarrinhoAdapter(List<ItemCarrinho> listaProdutos, Context activityContext) {
        this.listaProdutos = listaProdutos;
        this.activityContext = activityContext;
    }

    @Override // especifica como cada viewholder será criado (especifica o xml do layout a ser usado)
    public LineViewHolderCarrinho onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LineViewHolderCarrinho viewHolder = new LineViewHolderCarrinho(inflater.inflate(R.layout.viewholder_carrinho,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LineViewHolderCarrinho holder, int position) {
        int imagem = listaProdutos.get(position).getImagem();
        holder.img_do_produto.setImageResource(imagem);
        holder.txt_produto.setText(listaProdutos.get(position).getNome());
        holder.txt_preco.setText(String.valueOf(listaProdutos.get(position).getPreco()));

    }


    @Override // retorna a quantidade de elementos na lista de dados
    public int getItemCount() {
        if(listaProdutos==null) return 0;
        return listaProdutos.size();
    }
}
