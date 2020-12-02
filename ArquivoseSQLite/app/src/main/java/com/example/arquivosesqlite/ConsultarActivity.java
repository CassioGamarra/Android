package com.example.arquivosesqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultarActivity extends AppCompatActivity {

    DatabaseFactory banco;
    TextView consulta;
    TableLayout tabela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        consulta = findViewById(R.id.editTextBusca);
        tabela = (TableLayout) findViewById(R.id.tabela);

        banco = new DatabaseFactory(this);
    }

    public void buscar(View v) {
        if (consulta.getText().toString().equals("")) {
            Toast.makeText(this, "Preencha o campo de busca", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<Contato> contatos = new ArrayList<>();
            contatos = banco.buscarDados(consulta.getText().toString());
            tabela.removeAllViews();
            if(contatos.size() == 0) {
                TextView textView = new TextView(this);
                textView.setText("Sem dados");
                tabela.addView(textView);
            } else {
                for (Contato contato : contatos) {
                    TableRow tableRow = new TableRow(this);
                    Button btnEditar = new Button(this);
                    Button btnExcluir = new Button(this);
                    TextView textView = new TextView(this);
                    textView.setText(contato.getNome());
                    btnEditar.setText("EDITAR");
                    btnExcluir.setText("EXCLUIR");

                    final int idContato = contato.getId();

                    btnEditar.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            alterarContato(idContato);
                        }
                    });

                    btnExcluir.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            deletar(idContato);
                        }
                    });

                    tableRow.addView(textView);
                    tableRow.addView(btnEditar);
                    tableRow.addView(btnExcluir);

                    tabela.addView(tableRow);
                }
            }
        }
    }

    public void alterarContato(int id) {
        Intent it = new Intent(this, AlterarContatoActivity.class);
        Contato c = new Contato();
        c = banco.consultarContato(id);
        it.putExtra("paramContato", c);
        startActivityForResult(it, 200);
    }

    public void deletar(final int idExclusao) {
        new AlertDialog.Builder(this)
                .setMessage("Tem certeza que deseja excluir?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(banco.excluirContato(idExclusao)) {
                            showToast("Contato excluído com sucesso!");
                            ConsultarActivity.super.recreate();
                        } else {
                            showToast("Erro ao excluir!");
                        }
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void voltar(View v) {
        ConsultarActivity.super.onBackPressed();
    }
}