package com.example.cafenaporta.telasUsuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cafenaporta.R;
import com.example.cafenaporta.database.Database;
import com.example.cafenaporta.database.Usuario;
import com.example.cafenaporta.telasUsuario.telaPrincipal.Menu;

public class Cadastro extends AppCompatActivity {

    EditText editNome, editEmail, editTelefone, editSenha;
    Button btnCadastrar;
    TextView textlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        editNome = findViewById(R.id.textNome);
        editEmail = findViewById(R.id.textEmail);
        editTelefone = findViewById(R.id.textTelefone);
        editSenha = findViewById(R.id.textSenha);
        textlogin = findViewById(R.id.textLogin);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        textlogin.setOnClickListener((View view ) -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        btnCadastrar.setOnClickListener((View view ) -> {
           cadastro();
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
        });
    }

    public void cadastro() {
        if(validarCampos() == false) {
            return;
        }
        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String telefone = editTelefone.getText().toString();
        String senha = editSenha.getText().toString();
        if (validarCadastro(nome, email, telefone, senha) == true) {
            CadastrarUsuario(nome, email, telefone, senha);
        }

    }

    private boolean validarCampos(){

        if(editNome.getText().toString().trim().equals("")){
            editNome.setError("Nome obrigatório!");
            return false;
        }
        if(editEmail.getText().toString().trim().equals("")){
            editEmail.setError("Email obrigatório!");
            return false;
        }
        if(editTelefone.getText().toString().trim().equals("")){
            editTelefone.setError("Telefone obrigatório!");
            return false;
        }
        if(editSenha.getText().toString().trim().equals("")){
            editSenha.setError("Senha obrigatória!");
            return false;
        }
        return true;
    }

    private boolean validarCadastro(String nome, String email, String telefone, String senha) {
        Database db = Room.databaseBuilder(getApplicationContext(),Database.class,"Cafe na porta BD")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        Usuario usuario = db.getUserDao().getUserCadastro(email);
        if (usuario!=null) {
           return false;
        }
        return true;
    }

    private void CadastrarUsuario(String nome, String email, String telefone, String senha) {
        Database db = Room.databaseBuilder(getApplicationContext(),Database.class,"Cafe na porta BD")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        Usuario usuario = new Usuario();

        usuario.nome = nome;
        usuario.email = email;
        usuario.telefone = telefone;
        usuario.senha = senha;

        db.getUserDao().insereUsuario(usuario);

    }
}