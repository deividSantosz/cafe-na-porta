package com.example.cafenaporta.telasUsuario.listaPedidos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.cafenaporta.R;
import com.example.cafenaporta.database.Database;
import com.example.cafenaporta.database.Pedido;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListaPedidosAdapter extends RecyclerView.Adapter<ViewHolderListaPedidos> {

    private List<Pedido> listaPedidos;
    private Context activityContext;

    public ListaPedidosAdapter(List<Pedido> listaPedidos, Context activityContext) {
        this.listaPedidos = listaPedidos;
        this.activityContext = activityContext;
    }

    @NonNull
    @Override
    public ViewHolderListaPedidos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewHolderListaPedidos viewHolder = new ViewHolderListaPedidos(inflater.inflate(R.layout.viewholder_lista_pedidos, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListaPedidos holder, int position) {
        Pedido pedido = listaPedidos.get(position);
        String status_pedido = listaPedidos.get(position).getStatus_pedido();
        if ("confirmado".equals(status_pedido)) {
            holder.btn_confirmar_pedido.setVisibility(View.GONE);
        } else {
            holder.btn_confirmar_pedido.setVisibility(View.VISIBLE);
        }
        Date dataPedido = new Date(pedido.getData());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dataFormatada = formatter.format(dataPedido);
        holder.txt_data_pedido.setText(dataFormatada);

        holder.txt_status.setText(pedido.getStatus_pedido());

        double totalPedido = pedido.getTotal();
        String totalFormatado = String.format(Locale.getDefault(), "R$ %.2f", totalPedido);
        holder.txt_preco.setText(totalFormatado);
        holder.btn_confirmar_pedido.setOnClickListener((View view) -> {
        AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
        builder.setMessage("Tem certeza que deseja confirmar a entrega do pedido?")
                .setTitle("Confirmação")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Código para confirmar a entrega do pedido
                        String pedidoConfirmado = "confirmado";
                        holder.txt_status.setText(pedidoConfirmado);
                        listaPedidos.get(position).setStatus_pedido(pedidoConfirmado);
                        long pedidoId = listaPedidos.get(position).getId(); // Supondo que getId() retorna o ID do pedido
                        Database db = Room.databaseBuilder(activityContext, Database.class, "Cafe na porta BD")
                                .allowMainThreadQueries()
                                .fallbackToDestructiveMigration()
                                .build();

                        Pedido pedido = db.getPedidoDao().getPedidoById(pedidoId); // Obter o pedido do banco de dados
                        if (pedido != null) {
                            pedido.setStatus_pedido(pedidoConfirmado);
                            db.getPedidoDao().updatePedido(pedido); // Atualizar o pedido no banco de dados
                        }
                        holder.btn_confirmar_pedido.setVisibility(View.GONE);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Código para cancelar a ação
                        dialog.dismiss(); // Fecha o diálogo
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    });

}

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }
}
