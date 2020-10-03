package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //Declaração dos compontentes
    Button btnIniciar; //Botão de inicio
    LinearLayout layoutTabuleiro;
    RadioButton rdJogadorVsJogador, rdJogadorVsComputador;
    //Botões de jogo
    Button btnTab0, btnTab1, btnTab2, btnTab3, btnTab4, btnTab5, btnTab6, btnTab7, btnTab8;

    Button botoes[][] = new Button[3][3];
    final int XIS = 0, BOLINHA = 1, VAZIO = 2;
    int vez = XIS;
    int jogadas = 0;

    int tabuleiro[][] = new int[3][3];

    TextView txtVencedor, txtVezJogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Ligação do JAVA com o XML
        //Liga o Button da interface com o do java
        btnIniciar = findViewById(R.id.btnIniciar);
        //Radio buttons
        rdJogadorVsJogador = findViewById(R.id.rdJogadorVsJogador);
        rdJogadorVsComputador = findViewById(R.id.rdJogadorVsComputador);
        //Tabuleiro de jogo
        layoutTabuleiro = findViewById(R.id.tabuleiro);
        //Texto de vitória
        txtVencedor = findViewById(R.id.txtVencedor);
        txtVezJogador = findViewById(R.id.txtVezJogador);

        //Faz o mapeamento dos botões e liga com o java
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                int btnID = getResources().getIdentifier("btn" + i + j, "id", getPackageName());
                botoes [i][j] = findViewById(btnID);
            }
        }

        //Verifica se existe algo salvo em memória e carrega novamente
        if(savedInstanceState != null){
            //Percorre o vetor de botões e do tabuleiro preenchendo
            for (int i=0; i<3; i++){
                for (int j=0; j<3; j++){
                    botoes[i][j].setText(savedInstanceState.getString("btn"+i+j));
                    tabuleiro[i][j] = savedInstanceState.getInt("tabuleiro"+i+j);
                }
            }
            jogadas = savedInstanceState.getInt("jogadas");
            vez = savedInstanceState.getInt("vez");
            txtVezJogador.setText(savedInstanceState.getString("txtVezJogador"));
            txtVezJogador.setVisibility(savedInstanceState.getInt("visibilidadeTxtVezJogador"));
            btnIniciar.setEnabled(savedInstanceState.getBoolean("enabledBtnIniciar"));
            btnIniciar.setClickable(savedInstanceState.getBoolean("clickableIniciar"));
            rdJogadorVsJogador.setEnabled(savedInstanceState.getBoolean("enabledRdJogadorVsJogador"));
            rdJogadorVsJogador.setClickable(savedInstanceState.getBoolean("clickableRdJogadorVsJogador"));
            rdJogadorVsComputador.setEnabled(savedInstanceState.getBoolean("enabledRdJogadorVsComputador"));
            rdJogadorVsComputador.setClickable(savedInstanceState.getBoolean("clickableRdJogadorVsComputador"));
            layoutTabuleiro.setVisibility(savedInstanceState.getInt("visibilidadeLayoutTabuleiro"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        //salva o valor de x na variável de estado "savedState"
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                savedState.putString("btn"+i+j, botoes[i][j].getText().toString());
                savedState.putInt("tabuleiro"+i+j, tabuleiro[i][j]);
            }
        }
        savedState.putInt("visibilidadeLayoutTabuleiro", layoutTabuleiro.getVisibility()); //Salva a visibilidade do tabuleiro
        savedState.putInt("jogadas", jogadas); //Salva o número de jogadas efetuadas até o momento
        savedState.putInt("visibilidadeTxtVezJogador", txtVezJogador.getVisibility()); //Salva a visibilidade da vez do jogador
        savedState.putString("txtVezJogador", txtVezJogador.getText().toString()); //Salva o texto da vez do jogador
        savedState.putInt("vez", vez); //Salva de quem é a vez
        //Salva os estados de ativos e clicaveis dos botões
        savedState.putBoolean("enabledBtnIniciar", btnIniciar.isEnabled());
        savedState.putBoolean("clickableIniciar", btnIniciar.isClickable());
        savedState.putBoolean("enabledRdJogadorVsJogador", rdJogadorVsJogador.isEnabled());
        savedState.putBoolean("clickableRdJogadorVsJogador", rdJogadorVsJogador.isClickable());
        savedState.putBoolean("enabledRdJogadorVsComputador", rdJogadorVsComputador.isEnabled());
        savedState.putBoolean("clickableRdJogadorVsComputador", rdJogadorVsComputador.isClickable());
        super.onSaveInstanceState(savedState);
    }

    public void zeraTabuleiro(){
        //reseta a variável vez
        if(jogadas == 0) {
            vez = XIS;
            //zera o tabuleiro e reativa os botões sem texto
            for (int i=0; i<3; i++){
                for (int j=0; j<3; j++){
                    tabuleiro[i][j] = VAZIO;
                    botoes[i][j].setText("");
                    botoes[i][j].setClickable(true);
                }
            }
            //mostra o tabuleiro
            layoutTabuleiro.setVisibility(View.VISIBLE);
            txtVezJogador.setVisibility(View.VISIBLE);
            txtVezJogador.setText("Vez do Jogador 1");
            //desativa o botao iniciar
            btnIniciar.setClickable(false);
            btnIniciar.setEnabled(false);
            rdJogadorVsJogador.setEnabled(false);
            rdJogadorVsComputador.setEnabled(false);
        }
    }

    //Evento onClick para iniciar
    public void iniciarClick(View v) {
        if (rdJogadorVsJogador.isChecked() || rdJogadorVsComputador.isChecked()) {
            zeraTabuleiro();
        } else {
            Toast.makeText(this, "Selecione um modo de jogo!", Toast.LENGTH_SHORT).show();
        }
    }

    public void jogadaComputador(){
        final Handler handler = new Handler();
        //espera 1seg para o computador jogar
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                while(true){
                    //gera uma linha e coluna aleatória para o computador jogar
                    int linha = random.nextInt(3); //gera um valor aleatório entre 0 e 2
                    int coluna = random.nextInt(3); //gera um valor aleatório entre 0 e 2
                    //se a linha e coluna sorteadas foram em uma posição vazia
                    if(tabuleiro[linha][coluna] == VAZIO){
                        botoes[linha][coluna].setText("O");
                        tabuleiro[linha][coluna] = BOLINHA;
                        //troca a vez
                        vez = XIS;
                        //incrementa o numero de jogadas
                        jogadas++;
                        break;
                    }
                }
                gerenciarBotoes(0);
                txtVezJogador.setText("Vez do Jogador 1");
            }
        }, 1000);
    }

    //Função para jogar
    public void jogar(View v) {
        //converte a View v em um Button
        Button botaoClicado = (Button) v;
        //recupera a tag do botão, que contem a posição dele no tabuleiro
        String tag = botaoClicado.getTag().toString();
        //transforma a String da tag no formato linha,coluna em dois inteiros para linha e coluna
        String vetTag[] = tag.split(",");
        int linha = Integer.parseInt(vetTag[0]);
        int coluna = Integer.parseInt(vetTag[1]);

        //incrementa o numero de jogadas
        jogadas++;
        //Testa de quem é a vez
        if(vez == XIS){
            if(tabuleiro[linha][coluna] == VAZIO){
                botaoClicado.setText("X");
                tabuleiro[linha][coluna] = XIS;
                //troca a vez
                vez = BOLINHA;
                //verifica se é Jogador vs Computador, pois se for, simula uma jogada do computador
                if(rdJogadorVsComputador.isChecked() && jogadas < 9){
                    gerenciarBotoes(1);
                    txtVezJogador.setText("Vez do Computador");
                    jogadaComputador();
                } else {
                    txtVezJogador.setText("Vez do Jogador 2");
                }
            }
        }
        else if(vez == BOLINHA){
            if(tabuleiro[linha][coluna] == VAZIO) {
                botaoClicado.setText("O");
                tabuleiro[linha][coluna] = BOLINHA;
                //troca a vez
                vez = XIS;
                txtVezJogador.setText("Vez do Jogador 1");
            }
        }
        //desabilita o onClick do botao
        botaoClicado.setClickable(false);
        //verifica se houve algum vencedor nessa jogada
        verificaEstadoJogo();
    }

    public void verificaEstadoJogo(){
        //se o XIS venceu
        if(verificaVencedor(XIS)){
            txtVencedor.setVisibility(View.VISIBLE);
            txtVencedor.setText("X Venceu!!");
            //esconde o tabuleiro
            esconderTabuleiro();
        }
        else if(verificaVencedor(BOLINHA)){
            txtVencedor.setVisibility(View.VISIBLE);
            txtVencedor.setText("O Venceu!!");
            //esconde o tabuleiro
            esconderTabuleiro();
        }
        else if(jogadas == 9){
            txtVencedor.setVisibility(View.VISIBLE);
            txtVencedor.setText("Empatou!!");
            //esconde o tabuleiro
            esconderTabuleiro();
        }
    }

    public boolean verificaVencedor(int jog){
        if(
            //verifica as linhas
                (tabuleiro[0][0] == jog && tabuleiro[0][1] == jog && tabuleiro[0][2] == jog) ||
                        (tabuleiro[1][0] == jog && tabuleiro[1][1] == jog && tabuleiro[1][2] == jog) ||
                        (tabuleiro[2][0] == jog && tabuleiro[2][1] == jog && tabuleiro[2][2] == jog) ||
                        //verifica as colunas
                        (tabuleiro[0][0] == jog && tabuleiro[1][0] == jog && tabuleiro[2][0] == jog) ||
                        (tabuleiro[0][1] == jog && tabuleiro[1][1] == jog && tabuleiro[2][1] == jog) ||
                        (tabuleiro[0][2] == jog && tabuleiro[1][2] == jog && tabuleiro[2][2] == jog) ||
                        //verifica as diagonal principal
                        (tabuleiro[0][0] == jog && tabuleiro[1][1] == jog && tabuleiro[2][2] == jog) ||
                        //verifica as diagonal secundária
                        (tabuleiro[0][2] == jog && tabuleiro[1][1] == jog && tabuleiro[2][0] == jog)){
            return true;
        }
        else{
            return false;
        }
    }

    public void gerenciarBotoes(int acao) {
        if(acao == 1) {
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    botoes[i][j].setClickable(false);
                }
            }
        } else {
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    botoes[i][j].setClickable(true);
                }
            }
        }

    }

    public void esconderTabuleiro() {
        gerenciarBotoes(1);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutTabuleiro.setVisibility(View.INVISIBLE);
                txtVencedor.setText("");
                txtVezJogador.setVisibility(View.GONE);
                txtVencedor.setVisibility(View.GONE);
            }
        }, 1000);
        jogadas = 0;
        btnIniciar.setClickable(true);
        btnIniciar.setEnabled(true);
        rdJogadorVsJogador.setEnabled(true);
        rdJogadorVsComputador.setEnabled(true);
    }
}