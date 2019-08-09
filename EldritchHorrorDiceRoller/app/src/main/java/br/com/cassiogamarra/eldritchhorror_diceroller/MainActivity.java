package br.com.cassiogamarra.eldritchhorror_diceroller;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void diceRoll(View view){
        //Instanciar classe random
        Random gerador = new Random();
        //Variavel para receber o valor
        String valueOne, valueTwo, valueThree, valueFour;
        //Variavel para resultado do teste
        String result = "";
        String values = "";
        int testValueOne, testValueTwo, testValueThree, testValueFour;
        //Instanciar os checkbox
        RadioButton radioOne = findViewById(R.id.radioOne);
        RadioButton radioTwo = findViewById(R.id.radioTwo);
        RadioButton radioThree = findViewById(R.id.radioThree);
        RadioButton radioFour = findViewById(R.id.radioFour);

        //Gerar valor randômico de acordo com o número de valores checkados
        if(radioOne.isChecked()){
            valueOne = Integer.toString(gerador.nextInt(7));
            //Testar se o resultado foi um sucesso ou falha
            testValueOne = Integer.parseInt(valueOne);
            playSound();
            //Critério do jogo: sucessos 5 e 6
            if(testValueOne > 4){
                result = " SUCESS";
            }
            else{
                result = " FAILURE";
            }
            values = valueOne;
        }
        else if(radioTwo.isChecked()){
            valueOne = Integer.toString(gerador.nextInt(7));
            valueTwo = Integer.toString(gerador.nextInt(7));
            //Testar se o resultado foi um sucesso ou falha
            testValueOne = Integer.parseInt(valueOne);
            testValueTwo = Integer.parseInt(valueTwo);
            playSound();
            //Critério do jogo: sucessos 5 e 6
            if((testValueOne > 4)||(testValueTwo > 4)){
                result = " SUCESS";
            }
            else{
                result = " FAILURE";
            }
            values = valueOne + "|" + valueTwo;
        }
        else if(radioThree.isChecked()){
            valueOne = Integer.toString(gerador.nextInt(7));
            valueTwo = Integer.toString(gerador.nextInt(7));
            valueThree = Integer.toString(gerador.nextInt(7));
            //Testar se o resultado foi um sucesso ou falha
            testValueOne = Integer.parseInt(valueOne);
            testValueTwo = Integer.parseInt(valueTwo);
            testValueThree = Integer.parseInt(valueThree);
            playSound();
            //Critério do jogo: sucessos 5 e 6
            if((testValueOne > 4)||(testValueTwo > 4)||(testValueThree > 4)){
                result = " SUCESS";
            }
            else{
                result = " FAILURE";
            }
            values =  valueOne + "|" + valueTwo + "|" + valueThree;
        }
        else if(radioFour.isChecked()){
            valueOne = Integer.toString(gerador.nextInt(7));
            valueTwo = Integer.toString(gerador.nextInt(7));
            valueThree = Integer.toString(gerador.nextInt(7));
            valueFour = Integer.toString(gerador.nextInt(7));
            //Testar se o resultado foi um sucesso ou falha
            testValueOne = Integer.parseInt(valueOne);
            testValueTwo = Integer.parseInt(valueTwo);
            testValueThree = Integer.parseInt(valueThree);
            testValueFour = Integer.parseInt(valueFour);
            playSound();
            //Critério do jogo: sucessos 5 e 6
            if((testValueOne > 4)||(testValueTwo > 4)||(testValueThree > 4)||(testValueFour > 4)){
                result = " SUCESS";
            }
            else{
                result = " FAILURE";
            }
            values = valueOne + "|" + valueTwo + "|" + valueThree + "|" + valueFour;
        }
        else{
            Toast.makeText(this, "NO DICE", Toast.LENGTH_SHORT).show();
        }

        //Alterar text conforme valor gerado
        TextView editValue = findViewById(R.id.textValue);
        editValue.setText(values);
        TextView editResult = findViewById(R.id.textResult);
        editResult.setText(result);
    }
    //Tocar som do dado
    private void playSound(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.dice);
        mediaPlayer.start();
    }
}
