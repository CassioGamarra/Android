package com.example.amorperfeito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    ArrayList<String> estadoCivil = new ArrayList<>();
    ArrayList<String> genero = new ArrayList<>();
    ArrayList<String> bebidas = new ArrayList<>();

    Spinner spnEstadoCivil, spnGenero, spnBebidas;
    EditText editTextNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spnEstadoCivil = findViewById(R.id.spinnerEstadoCivil);
        spnGenero = findViewById(R.id.spinnerGenero);
        spnBebidas = findViewById(R.id.spinnerBebidaFavorita);
        editTextNome = findViewById(R.id.editTextNome);

        //Valores para estado civil
        estadoCivil.add("Selecione");
        estadoCivil.add("Solteiro");
        estadoCivil.add("Casado");
        //Valores para gênero
        genero.add("Selecione");
        genero.add("Masculino");
        genero.add("Feminino");
        //Valores para bebidas favoritas
        bebidas.add("Selecione");
        bebidas.add("Vinho");
        bebidas.add("Refrigerante");
        bebidas.add("Cerveja");

        //Criando um adapter para cada valor
        ArrayAdapter adapterEstadoCivil = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, estadoCivil);
        ArrayAdapter adapterGenero = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, genero);
        ArrayAdapter adapterBebidas = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, bebidas);

        spnEstadoCivil.setAdapter(adapterEstadoCivil);
        spnGenero.setAdapter(adapterGenero);
        spnBebidas.setAdapter(adapterBebidas);
    }

    public void loginClick(View v) {
        String nome = editTextNome.getText().toString();
        String estadoCivil = spnEstadoCivil.getSelectedItem().toString();
        String genero = spnGenero.getSelectedItem().toString();
        String bebida = spnBebidas.getSelectedItem().toString();

        if(nome.trim().equals("")) {
            Toast.makeText(this, "Preencha seu nome!", Toast.LENGTH_SHORT).show();
        } else if(estadoCivil.equals("Selecione")) {
            Toast.makeText(this, "Selecione seu estado civil!", Toast.LENGTH_SHORT).show();
        } else if(genero.equals("Selecione")) {
            Toast.makeText(this, "Selecione seu gênero!", Toast.LENGTH_SHORT).show();
        } else if(bebida.equals("Selecione")) {
            Toast.makeText(this, "Selecione sua bebida favorita!", Toast.LENGTH_SHORT).show();
        } else {
            Intent it = new Intent(this, MainActivity.class);

            Pessoa p = new Pessoa();
            p.setNome(nome);
            p.setEstadoCivil(estadoCivil);
            p.setGenero(genero);
            p.setBebidaFavorita(bebida);

            it.putExtra("paramPessoa", p);

            startActivityForResult(it, 3000);
        }
    }
    @Override
    public void onActivityResult(int codigo_da_tela, int resultado, Intent it) {
        super.onActivityResult(codigo_da_tela, resultado, it);

        if(codigo_da_tela == 3000){
            if(resultado == 1){
                String mensagem = it.getStringExtra("retornoMensagem");
                Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            }
        }

    }
}