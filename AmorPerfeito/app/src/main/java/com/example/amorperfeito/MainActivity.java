package com.example.amorperfeito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvNome, tvEstadoCivil, tvGenero, tvBebida, tvNomePretendente, tvTelefonePrentendente,
            tvEstadoCivilPretendente, tvGeneroPrentendente, tvBebidaPretendente;

    ImageView imgPessoa, imgPretendente;

    LinearLayout dadosPretendente;

    ArrayList<Pessoa> homens = new ArrayList<>();
    ArrayList<Pessoa> mulheres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Solicitante
        tvNome = findViewById(R.id.tvNome);
        tvEstadoCivil = findViewById(R.id.tvEstadoCivil);
        tvGenero = findViewById(R.id.tvGenero);
        tvBebida = findViewById(R.id.tvBebida);

        //Imagens
        imgPessoa = findViewById(R.id.pessoa);
        imgPretendente = findViewById(R.id.pretendente);
        //Salva a tela que realizou a chamada
        Intent it = getIntent();

        Pessoa p = (Pessoa) it.getSerializableExtra("paramPessoa");

        if(p.getEstadoCivil().equals("Casado")) {
            it.putExtra("retornoMensagem", "A aplicação só permite pessoas solteiras.");
            setResult(1, it);
            finish();
        } else {
            tvNome.setText(p.getNome());
            tvEstadoCivil.setText(p.getEstadoCivil());
            tvGenero.setText(p.getGenero());
            tvBebida.setText(p.getBebidaFavorita());

            if (tvGenero.getText().toString().equals("Masculino")) {
                imgPessoa.setImageResource(R.drawable.cara);
                imgPretendente.setImageResource(R.drawable.mulher);
            } else {
                imgPessoa.setImageResource(R.drawable.mulher);
                imgPretendente.setImageResource(R.drawable.cara);
            }
            //Gera as pessoas;
            gerarPessoas();
        }
    }

    public void exibirResultado(View v) {
        //Pretendente
        tvNomePretendente = findViewById(R.id.tvNomePretendente);
        tvTelefonePrentendente = findViewById(R.id.tvTelefonePretendente);
        tvEstadoCivilPretendente = findViewById(R.id.tvEstadoCivilPretendente);
        tvGeneroPrentendente = findViewById(R.id.tvGeneroPretendente);
        tvBebidaPretendente = findViewById(R.id.tvBebidaPretendente);
        dadosPretendente = findViewById(R.id.dadosPretendente);
        //Chama a classe random
        Random gerador = new Random();
        int id = gerador.nextInt(4)+1;

        if(tvGenero.getText().toString().equals("Masculino")) {
            for(Pessoa pessoa : mulheres) {
                if(pessoa.getId() == id) {
                    System.out.println("achei");
                    tvNomePretendente.setText(pessoa.getNome());
                    tvTelefonePrentendente.setText(pessoa.getTelefone());
                    tvEstadoCivilPretendente.setText(pessoa.getEstadoCivil());
                    tvGeneroPrentendente.setText(pessoa.getGenero());
                    tvBebidaPretendente.setText(pessoa.getBebidaFavorita());
                }
            }
        } else {
            for(Pessoa pessoa : homens) {
                if(pessoa.getId() == id) {
                    tvNomePretendente.setText(pessoa.getNome());
                    tvTelefonePrentendente.setText(pessoa.getTelefone());
                    tvEstadoCivilPretendente.setText(pessoa.getEstadoCivil());
                    tvGeneroPrentendente.setText(pessoa.getGenero());
                    tvBebidaPretendente.setText(pessoa.getBebidaFavorita());
                }
            }
        }
        dadosPretendente.setVisibility(View.VISIBLE);
    }

    public void gerarPessoas() {
        Pessoa p1 = new Pessoa();
        Pessoa p2 = new Pessoa();
        Pessoa p3 = new Pessoa();
        Pessoa p4 = new Pessoa();
        Pessoa p5 = new Pessoa();
        Pessoa p6 = new Pessoa();
        Pessoa p7 = new Pessoa();
        Pessoa p8 = new Pessoa();

        p1.setId(1);
        p1.setNome("Cássio");
        p1.setGenero("Masculino");
        p1.setEstadoCivil("Solteiro");
        p1.setBebidaFavorita("Vinho");
        p1.setTelefone("55999334455");
        homens.add(p1);

        p2.setId(2);
        p2.setNome("Frederico");
        p2.setGenero("Masculino");
        p2.setEstadoCivil("Solteiro");
        p2.setBebidaFavorita("Vinho");
        p2.setTelefone("55999334455");
        homens.add(p2);

        p3.setId(3);
        p3.setNome("Cancian");
        p3.setGenero("Masculino");
        p3.setEstadoCivil("Solteiro");
        p3.setBebidaFavorita("Refrigerante");
        p3.setTelefone("55999334455");
        homens.add(p3);

        p4.setId(4);
        p4.setNome("Marconatto");
        p4.setGenero("Masculino");
        p4.setEstadoCivil("Solteiro");
        p4.setBebidaFavorita("Cerveja");
        p4.setTelefone("55999334455");
        homens.add(p4);

        //Reseta os id's
        p5.setId(1);
        p5.setNome("Roziana");
        p5.setGenero("Feminino");
        p5.setEstadoCivil("Solteira");
        p5.setBebidaFavorita("Vinho");
        p5.setTelefone("55999334455");
        mulheres.add(p5);

        p6.setId(2);
        p6.setNome("Yasmin");
        p6.setGenero("Feminino");
        p6.setEstadoCivil("Solteira");
        p6.setBebidaFavorita("Vinho");
        p6.setTelefone("55999334455");
        mulheres.add(p6);

        p7.setId(3);
        p7.setNome("Katiane");
        p7.setGenero("Feminino");
        p7.setEstadoCivil("Solteira");
        p7.setBebidaFavorita("Vinho");
        p7.setTelefone("55999334455");
        mulheres.add(p7);

        p8.setId(4);
        p8.setNome("Isabel");
        p8.setGenero("Feminino");
        p8.setEstadoCivil("Solteira");
        p8.setBebidaFavorita("Vinho");
        p8.setTelefone("55999334455");
        mulheres.add(p8);
    }
}