package com.example.cafenaporta.telasUsuario.telaPrincipal;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cafenaporta.R;

/* Classe que representa os itens da lista de um recyrcleviews */
public class LineViewHolder extends RecyclerView.ViewHolder {
    // atributos
    TextView txtNomeCafe, txtPrecoCafe;
    ImageView imgCafe;

    // construtor
    public LineViewHolder(View itemView){
        super(itemView);

        // captura dos componentes
        imgCafe = itemView.findViewById(R.id.ImgCafe);
        txtNomeCafe = itemView.findViewById(R.id.txtNomeCafe);
        txtPrecoCafe = itemView.findViewById(R.id.txtPrecoCafe);
    }
}
