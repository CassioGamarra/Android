package br.com.cassiogamarra.eldritchhorror_diceroller;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                exibirMainActivity();
            }
        }, 3000);
    }
    private void exibirMainActivity(){
        Intent intent = new Intent(
                SplashActivity.this,MainActivity.class
        );
        startActivity(intent);
        finish();
    }
}
