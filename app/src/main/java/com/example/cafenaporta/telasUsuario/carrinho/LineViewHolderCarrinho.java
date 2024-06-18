package com.example.cafenaporta.telasUsuario.carrinho;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafenaporta.R;

public class LineViewHolderCarrinho extends RecyclerView.ViewHolder{
    TextView txt_produto, txt_preco;
    ImageView img_do_produto;
    public LineViewHolderCarrinho(@NonNull View itemView) {
        super(itemView);

        txt_produto = itemView.findViewById(R.id.txt_nome);
        txt_preco = itemView.findViewById(R.id.txt_preco);
        img_do_produto = itemView.findViewById(R.id.img_do_produto);
    }
}
