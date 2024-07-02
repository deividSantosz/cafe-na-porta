package com.example.cafenaporta.telasUsuario.telaPrincipal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafenaporta.telasUsuario.DetalhesProduto;
import com.example.cafenaporta.R;
import com.example.cafenaporta.database.Produto;


import java.util.List;

/* Classe que representa o adapter do recyrcleview
 * Nesta classe especificamos o comportamento do ciclo de vida dos dados do recyrcleview,
 * bem como o comportamento geral dos viewholders*/
public class MenuAdapter extends RecyclerView.Adapter<LineViewHolder> {
    // lista global de entidade

    private  List<Produto> listaProdutos;
    private Context activityContext;

    // contrutor que recebe a lista de entidades
    public MenuAdapter( List<Produto> listaProdutos, Context activityContext){
       // this.listaProdutosCompleta = listaProdutosCompleta;
        this.listaProdutos = listaProdutos;
        this.activityContext=activityContext;
    }

    @Override // especifica como cada viewholder será criado (especifica o xml do layout a ser usado)
    public LineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LineViewHolder viewHolder = new LineViewHolder(inflater.inflate(R.layout.viewholder_menu,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LineViewHolder holder, int position) {
        int imagem = listaProdutos.get(position).imagem;
        holder.imgCafe.setImageResource(imagem);
        holder.txtNomeCafe.setText(listaProdutos.get(position).nome);

        String precoFormatado = String.format("%.2f", listaProdutos.get(position).preco);
        holder.txtPrecoCafe.setText(precoFormatado);

        holder.imgCafe.setOnClickListener((View view) -> {
            Intent intent = new Intent(activityContext, DetalhesProduto.class);
            intent.putExtra("imagem", listaProdutos.get(position).imagem);
            intent.putExtra("id", listaProdutos.get(position).id);
            intent.putExtra("nome", listaProdutos.get(position).nome);
            intent.putExtra("preco", listaProdutos.get(position).preco);
            intent.putExtra("descricao", listaProdutos.get(position).descricao);

            System.out.println(" ID DO PRODUTO:::" + listaProdutos.get(position).id);
            activityContext.startActivity(intent);
        });

    }

    @Override // retorna a quantidade de elementos na lista de dados
    public int getItemCount() {
        if(listaProdutos==null) return 0;
        return listaProdutos.size();
    }



    public void atualizarLista(List<Produto> listaFiltrada) {
        this.listaProdutos = listaFiltrada;
        notifyDataSetChanged();
    }
}
