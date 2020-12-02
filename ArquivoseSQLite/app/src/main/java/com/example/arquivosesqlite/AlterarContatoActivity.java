package com.example.arquivosesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AlterarContatoActivity extends AppCompatActivity {

    DatabaseFactory banco;
    EditText editTextNome, editTextEmail, editTextTelefone;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_contato);
        banco = new DatabaseFactory(this);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTelefone = findViewById(R.id.editTextTelefone);

        Intent it = getIntent();

        Contato c = (Contato) it.getSerializableExtra("paramContato");

        id = c.getId();
        editTextNome.setText(c.getNome());
        editTextEmail.setText(c.getEmail());
        editTextTelefone.setText(c.getTelefone());
    }

    public void alterar(View v) {
        String erros = "";

        if(editTextNome.getText().toString().equals("")) erros += "Preencha o nome!\n";
        if(editTextEmail.getText().toString().equals("")) erros += "Preencha o email!\n";
        if(editTextTelefone.getText().toString().equals("")) erros += "Preencha o telefone!";

        if(erros.length() > 0) {
            Toast.makeText(this, erros, Toast.LENGTH_LONG).show();
        } else {
            Contato c = new Contato();
            c.setId(id);
            c.setNome(editTextNome.getText().toString());
            c.setEmail(editTextEmail.getText().toString());
            c.setTelefone(editTextTelefone.getText().toString());

            if(banco.alterarContato(c)) {
                Toast.makeText(this, "Contato de ID: "+id+" alterado com sucesso!", Toast.LENGTH_SHORT).show();
                AlterarContatoActivity.super.onBackPressed();
            } else {
                Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void voltar(View v) {
        AlterarContatoActivity.super.onBackPressed();
    }
}