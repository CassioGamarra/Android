package com.example.slideshow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView slideshow;
    TextView explicacao;
    Button btnAnterior, btnProxima, btnDiminuirTransparencia, btnAumentarTransparencia;
    ArrayList<Integer> imagens = new ArrayList<>();
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slideshow = findViewById(R.id.slideshow);
        btnAnterior = findViewById(R.id.btnAnterior);
        btnProxima = findViewById(R.id.btnProxima);
        btnDiminuirTransparencia = findViewById(R.id.btnDiminuirTransparencia);
        btnAumentarTransparencia = findViewById(R.id.btnAumentarTransparencia);
        explicacao = findViewById(R.id.txtViewExplicacao);
        //Define o primeiro ID como 0, cada ID é uma posição do vetor de ID's
        id = 0;
        btnAnterior.setEnabled(false);
        btnAumentarTransparencia.setEnabled(false);
        //Adicionar imagens ao vetor de ID's
        imagens.add(R.drawable.img01);
        imagens.add(R.drawable.img02);
        imagens.add(R.drawable.img03);


        System.out.println("ALFA INICIAL: "+slideshow.getAlpha());
    }

    public void anteriorClick(View v) {
        if(id > 0) {
            btnProxima.setEnabled(true);
            id--;
            if(id == 0) {
                btnAnterior.setEnabled(false);
            } else {
                btnAnterior.setEnabled(true);
            }
            slideshow.setImageResource(imagens.get(id));
        }
    }

    public void proximoClick(View v) {
        if(id < 2) {
            btnAnterior.setEnabled(true);
            id++;
            if(id == 2) {
                btnProxima.setEnabled(false);
            } else {
                btnProxima.setEnabled(true);
            }
            slideshow.setImageResource(imagens.get(id));
        }
    }

    public void diminuirTransparencia(View v) {
        if(slideshow.getAlpha() > (0f)) {
            btnAumentarTransparencia.setEnabled(true);
            slideshow.setAlpha(slideshow.getAlpha()-0.1f);
            if(slideshow.getAlpha() < (0f)) {
                btnDiminuirTransparencia.setEnabled(false);
            }
        }
    }

    public void aumentarTransparencia(View v) {
        if (slideshow.getAlpha() < (1f)) {
            btnDiminuirTransparencia.setEnabled(true);
            slideshow.setAlpha(slideshow.getAlpha() + 0.1f);
            if (slideshow.getAlpha() == (1f)) {
                btnAumentarTransparencia.setEnabled(false);
            }
        }
    }

    public void scaleTypeClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                slideshow.setScaleType(ImageView.ScaleType.CENTER);
                explicacao.setText("Centraliza a imagem na view, porém não faz a escala.");
                explicacao.setVisibility(View.VISIBLE);
                break;
            case R.id.button2:
                slideshow.setScaleType(ImageView.ScaleType.CENTER_CROP);
                explicacao.setText("Dimensione a imagem uniformemente (mantenha a proporção da imagem) para que ambas as dimensões (largura e altura) da imagem sejam iguais ou maiores do que a dimensão correspondente da visualização (menos preenchimento).");
                explicacao.setVisibility(View.VISIBLE);
                break;
            case R.id.button3:
                slideshow.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                explicacao.setText("Dimensione a imagem uniformemente (mantenha a proporção da imagem) para que ambas as dimensões (largura e altura) da imagem sejam iguais ou menores que a dimensão correspondente da visualização (menos preenchimento).");
                explicacao.setVisibility(View.VISIBLE);
                break;
            case R.id.button4:
                slideshow.setScaleType(ImageView.ScaleType.FIT_CENTER);
                explicacao.setText("Dimensione a imagem usando Matrix.ScaleToFit#CENTER.");
                explicacao.setVisibility(View.VISIBLE);
                break;
            case R.id.button5:
                slideshow.setScaleType(ImageView.ScaleType.FIT_END);
                explicacao.setText("Dimensione a imagem usando Matrix.ScaleToFit#END.");
                explicacao.setVisibility(View.VISIBLE);
                break;
            case R.id.button6:
                slideshow.setScaleType(ImageView.ScaleType.FIT_START);
                explicacao.setText("Dimensione a imagem usando Matrix.ScaleToFit#START.");
                explicacao.setVisibility(View.VISIBLE);
                break;
            case R.id.button7:
                slideshow.setScaleType(ImageView.ScaleType.FIT_XY);
                explicacao.setText("Dimensione a imagem usando Matrix.ScaleToFit#FILL.");
                explicacao.setVisibility(View.VISIBLE);
                break;
            case R.id.button8:
                slideshow.setScaleType(ImageView.ScaleType.MATRIX);
                explicacao.setText("Escale usando a matriz de imagem ao desenhar.");
                explicacao.setVisibility(View.VISIBLE);
                break;
            default:
                explicacao.setVisibility(View.GONE);
                break;
        }
    }
}
