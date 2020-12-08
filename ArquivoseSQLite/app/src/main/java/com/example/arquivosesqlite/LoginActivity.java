package com.example.arquivosesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    RequestQueue queue = null;
    EditText editLogin, editSenha;
    ProgressBar progressBar;

    String login="", senha="";
    public static final String TAG = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editLogin = findViewById(R.id.editTextLogin);
        editSenha = findViewById(R.id.editTextPassword);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void loginClick(View view) {
        progressBar.setVisibility(View.VISIBLE);

        login = editLogin.getText().toString();
        senha = editSenha.getText().toString();

        if(login.length() == 0) {
            Toast.makeText(LoginActivity.this, "Preencha o login...", Toast.LENGTH_SHORT).show();
        } else if(senha.length() == 0) {
            Toast.makeText(LoginActivity.this, "Preencha a senha...", Toast.LENGTH_SHORT).show();
        } else {
            queue = Volley.newRequestQueue(this);

            String url = "https://cassiogamarra.herokuapp.com/login.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String resposta) {
                            progressBar.setVisibility(View.INVISIBLE);
                            if(resposta.trim().equals("200")){
                                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(it);
                            }
                            else if (resposta.trim().equals("403")){
                                Toast.makeText(LoginActivity.this, "Login ou senha incorretos!", Toast.LENGTH_SHORT).show();
                            } else if(resposta.trim().equals("404")) {
                                Toast.makeText(LoginActivity.this, "Login não encontrado!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) { //erro de requisição
                    progressBar.setVisibility(View.INVISIBLE);//esconde a barra de progresso
                    Toast.makeText(LoginActivity.this, "Erro:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("login", login);
                    params.put("password", senha);
                    return params;
                }
            };
            //da uma tag para a requisição
            stringRequest.setTag(TAG);
            //backOffMultiplier: multiplicador do timout de resposta do servidor, para esperar mais tempo nas tentativas subsequentes
            //1a tentativa: 10seg
            //2a tentativa: 10seg (t1) + 2*10seg = 30seg
            //3a tentativa: 30seg + 2*30seg = 150seg
            //...
            RetryPolicy policy = new DefaultRetryPolicy(10000, 1, 2);
            stringRequest.setRetryPolicy(policy);
            //adiciono a requisição na fila de requisições para que ela seja dispachada
            queue.add(stringRequest);
        }
    }

    public void cadastroClick(View view) {
        Intent it = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(it);
    }
}