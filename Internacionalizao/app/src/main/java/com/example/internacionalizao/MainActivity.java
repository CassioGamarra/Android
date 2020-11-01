package com.example.internacionalizao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteNacionalidade;
    Spinner spinnerEscolaridade;
    RadioButton rbMasculino, rbFeminino, rbOutro;
    ImageView imgGenero;
    TextView textViewGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbMasculino = findViewById(R.id.rbMasculino);
        rbFeminino = findViewById(R.id.rbFeminino);
        rbOutro = findViewById(R.id.rbOutro);

        imgGenero = findViewById(R.id.imgGenero);
        textViewGenero = findViewById(R.id.textViewGenero);

        autoCompleteNacionalidade = findViewById(R.id.autoCompleteNacionalidade);
        spinnerEscolaridade = findViewById(R.id.spinnerEscolaridade);

        ArrayAdapter<CharSequence> adapterNacionalidade = ArrayAdapter.createFromResource(this, R.array.listaNacionalidade, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterEscolaridade = ArrayAdapter.createFromResource(this, R.array.listaEscolaridade, android.R.layout.simple_spinner_item);

        autoCompleteNacionalidade.setAdapter(adapterNacionalidade);
        spinnerEscolaridade.setAdapter(adapterEscolaridade);
    }

    public void generoClick(View v) {
        RadioButton selecionado = (RadioButton) v;

        if (selecionado.equals(rbMasculino)) {
            textViewGenero.setText(R.string.textRbMasculino);
            imgGenero.setImageResource(R.drawable.homem);
        } else if(selecionado.equals(rbFeminino)) {
            textViewGenero.setText(R.string.textRbFeminino);
            imgGenero.setImageResource(R.drawable.mulher);
        } else if(selecionado.equals(rbOutro)) {
            textViewGenero.setText(R.string.textRbOutro);
            imgGenero.setImageResource(R.drawable.lgbtq);
        } else {
            Toast.makeText(this, R.string.mensagemErro, Toast.LENGTH_SHORT).show();
        }
    }
}