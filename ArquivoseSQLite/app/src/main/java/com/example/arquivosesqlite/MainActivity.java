package com.example.arquivosesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DatabaseFactory banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banco = new DatabaseFactory(this);

        banco.criarBanco();
        //banco.deleteTable();
    }

    public void telaCadastrarOpen(View v){
        Intent it = new Intent(this, CadastrarActivity.class);
        startActivity(it);
    }
    public void telaConsultarOpen(View v){
        Intent it = new Intent(this, ConsultarActivity.class);
        startActivity(it);
    }
}