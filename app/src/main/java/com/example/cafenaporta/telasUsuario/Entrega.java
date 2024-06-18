package com.example.cafenaporta.telasUsuario;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafenaporta.R;
import com.example.cafenaporta.singleton.ItemPedidoSingleton;
import com.example.cafenaporta.singleton.PedidoSingleton;
import com.example.cafenaporta.telasUsuario.DetalhesPedido;

public class Entrega extends AppCompatActivity {
    RadioButton rb_entrega;
    RadioGroup metodo_entrega;
    TextView text_endereco, txt_total;
    EditText editText_endereco;
    ImageView img_voltar;
    Button confirmar_entrega;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega);
        rb_entrega = findViewById(R.id.rb_entrega);
        text_endereco = findViewById(R.id.text_endereco);
        editText_endereco = findViewById(R.id.editText_endereco);
        metodo_entrega = findViewById(R.id.rg_metodo_entrega);
        img_voltar = findViewById(R.id.img_voltar);
        txt_total = findViewById(R.id.txt_total_entrega);
        confirmar_entrega = findViewById(R.id.btn_pedido);


        System.out.println("ID DO PRODUTO QUE FOI ADICIONADO NA ENTREGA:: " + ItemPedidoSingleton.getInstance().getProdutosId());
        Intent intent = getIntent();
        Double precoCalculado = intent.getDoubleExtra("precoCalculado", 0.0);
        txt_total.setText(String.format(" R$ %.2f", precoCalculado));

        metodo_entrega.setOnCheckedChangeListener((group, checkedId) -> {
            int radioButtonId = group.getCheckedRadioButtonId();

            // Verifica qual RadioButton foi selecionado no momento da mudança
            if (radioButtonId == R.id.rb_entrega) {
                // Se "Receber em casa" foi selecionado, mostra o TextView e o EditText
                text_endereco.setVisibility(View.VISIBLE);
                editText_endereco.setVisibility(View.VISIBLE);
            } else if (radioButtonId == R.id.rb_retirada) {
                // Se "Retirada" foi selecionado, esconde o TextView e o EditText
                text_endereco.setVisibility(View.GONE);
                editText_endereco.setVisibility(View.GONE);
            }
        });

        confirmar_entrega.setOnClickListener((View view) -> {
            int radioButtonId = metodo_entrega.getCheckedRadioButtonId();

            if (radioButtonId == -1) {
                // Nenhum RadioButton selecionado
                Toast.makeText(this, "Selecione uma opção de entrega", Toast.LENGTH_SHORT).show();
            }
            if (radioButtonId == R.id.rb_entrega) {
               // RadioButton opcaoSelecionada = findViewById(radioButtonId);

                if (editText_endereco.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, "Digite um endereço", Toast.LENGTH_SHORT).show();
                } else {
                    String endereco = editText_endereco.getText().toString();
                    System.out.println(endereco);
                    PedidoSingleton.getInstance().setEndereco(endereco);
                    Intent intent2 = new Intent(this, DetalhesPedido.class);
                    startActivity(intent2);
                }


            } else if (radioButtonId == R.id.rb_retirada) {
                PedidoSingleton.getInstance().setEndereco(null);
                Intent intent2 = new Intent(this, DetalhesPedido.class);
                startActivity(intent2);
            }
        });

        img_voltar.setOnClickListener((View view) -> {
            finish();
        });
    }
}