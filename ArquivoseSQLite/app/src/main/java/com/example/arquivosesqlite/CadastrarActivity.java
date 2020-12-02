package com.example.arquivosesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarActivity extends AppCompatActivity {

    DatabaseFactory banco;
    EditText editTextNome, editTextEmail, editTextTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        banco = new DatabaseFactory(this);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTelefone = findViewById(R.id.editTextTelefone);
    }

    public void cadastrar(View v) {
        String erros = "";

        if(editTextNome.getText().toString().equals("")) erros += "Preencha o nome!\n";
        if(editTextEmail.getText().toString().equals("")) erros += "Preencha o email!\n";
        if(editTextTelefone.getText().toString().equals("")) erros += "Preencha o telefone!";

        if(erros.length() > 0) {
            Toast.makeText(this, erros, Toast.LENGTH_LONG).show();
        } else {
            Contato c = new Contato();
            c.setNome(editTextNome.getText().toString());
            c.setEmail(editTextEmail.getText().toString());
            c.setTelefone(editTextTelefone.getText().toString());

            long id = banco.cadastrarContato(c);

            if(id > 0) {
                Toast.makeText(this, "Contato de ID: "+id+" criado com sucesso!", Toast.LENGTH_SHORT).show();
                editTextNome.setText("");
                editTextEmail.setText("");
                editTextTelefone.setText("");
            } else {
                Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
            }
        }
    }
}