package br.com.cassiogamarra.rhdigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iniciarMainAcitivy();
            }
        },5000);
    }

    private void iniciarMainAcitivy(){
        Intent intent = new Intent(
                SplashActivity.this,MainActivity.class
        );
        startActivity(intent);
        finish();
    }
}