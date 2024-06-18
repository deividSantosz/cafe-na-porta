package com.example.cafenaporta.telasUsuario.listaPedidos;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cafenaporta.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderListaPedidos extends RecyclerView.ViewHolder {
    Button btn_confirmar_pedido;
    TextView txt_preco, txt_status, txt_data_pedido;
    public ViewHolderListaPedidos(@NonNull View itemView) {
        super(itemView);
        btn_confirmar_pedido = itemView.findViewById(R.id.btn_confirmar_entrega_pedido);
        txt_preco = itemView.findViewById(R.id.txt_preco);
        txt_status = itemView.findViewById(R.id.txt_status);
        txt_data_pedido = itemView.findViewById(R.id.txt_data_pedido);
    }
}
