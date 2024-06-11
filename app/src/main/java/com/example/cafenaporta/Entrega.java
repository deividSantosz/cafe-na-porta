package com.example.cafenaporta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Entrega extends AppCompatActivity {
    RadioButton rb_entrega;
    RadioGroup metodo_entrega;
    TextView text_endereco;
    EditText editText_endereco;
    ImageView img_voltar;

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

        metodo_entrega.setOnCheckedChangeListener((group, checkedId) -> {
            // Obtém o ID do RadioButton selecionado
            int radioButtonId = group.getCheckedRadioButtonId();
            // Verifica qual RadioButton foi selecionado
            if (radioButtonId == R.id.rb_entrega) {
                // Se "Receber em casa" foi selecionado, mostra o TextView e o EditText
                text_endereco.setVisibility(View.VISIBLE);
                editText_endereco.setVisibility(View.VISIBLE);
            } else {
                // Caso contrário, esconde o TextView e o EditText
                text_endereco.setVisibility(View.GONE);
                editText_endereco.setVisibility(View.GONE);
            }
        });

        img_voltar.setOnClickListener((View view) -> {
            finish();
        });
    }
}