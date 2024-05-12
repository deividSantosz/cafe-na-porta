package com.example.cafenaporta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cafenaporta.database.Database;
import com.example.cafenaporta.database.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editSenha;
    Button btn_entrar;
    Button btn_cadastro;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        insereDadosUsuario();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editEmail = findViewById(R.id.EditEmail);
        editSenha = findViewById(R.id.EditSenha);
        btn_entrar = findViewById(R.id.btn_entrar);
        btn_cadastro = findViewById(R.id.btn_cadastro);

        btn_entrar.setOnClickListener((View view) -> {
            logar();
        });
        btn_cadastro.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, Cadastro.class);
            startActivity(intent);
        });
    }

    private void logar() {
        if(validarCampos() == false) {
            return;
        }
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();
        if (validarLogin(email, senha) ==false) {
            Toast.makeText(this,"Dados de login incorretos",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    private boolean validarCampos(){
        if(editEmail.getText().toString().trim().equals("")){
            editEmail.setError("Email obrigatório!");
            return false;
        }
        if(editSenha.getText().toString().trim().equals("")){
            editSenha.setError("Senha obrigatória!");
            return false;
        }
        return true;
    }

    private boolean validarLogin(String email, String senha){
        Database db = Room.databaseBuilder(getApplicationContext(),Database.class,"Cafe na porta BD")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        Usuario usuario = db.getUserDao().getUserLogin(email,senha);
        if (usuario==null) {
            return false;
        }
        return true;
    }

    private void insereDadosUsuario(){
        Database db = Room.databaseBuilder(getApplicationContext(),Database.class,"Cafe na porta BD")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        List<Usuario> listaUsuarios = db.getUserDao().getAll();
        if (!listaUsuarios.isEmpty()) {
            return; // abandona
        }

        Usuario master = new Usuario();
        master.nome =  "master";
        master.email = "master123@gmail.com";
        master.telefone = "73998513975";
        master.senha = "master123";
        db.getUserDao().insereUsuario(master);

        Usuario usuario = new Usuario();

        usuario.nome =  "teste";
        usuario.email = "teste@gmail.com";
        usuario.telefone = "73998432784";
        usuario.senha = "teste123";
        db.getUserDao().insereUsuario(usuario);

    }
}