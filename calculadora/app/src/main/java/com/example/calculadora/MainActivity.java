package com.example.calculadora;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculadora.enums.Operacao;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView tvResultado;
    Button btSoma, btMultiplicar, btSubtrair, btDividir, btCalcular, btDelete;
    String display = "";
    Float resultado = 0f;
    Operacao ultimaOperacao;

    ImageView imageView;
    MediaPlayer maisSom, igualSom, multiplicarSom, patoSom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        associarComponentes();
        configurarListeners();

    }

    private void associarComponentes(){
        imageView = (ImageView) findViewById(R.id.ivPato);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        btDividir = (Button) findViewById(R.id.btDividir);
        btCalcular = (Button) findViewById(R.id.btCalcular);
        btSoma = (Button) findViewById(R.id.btSoma);
        btMultiplicar = (Button) findViewById(R.id.btMultiplicar);
        btSubtrair = (Button) findViewById(R.id.btSubtrair);
        btDelete = (Button) findViewById(R.id.btDelete);
        igualSom = MediaPlayer.create(MainActivity.this, R.raw.igual);
        maisSom = MediaPlayer.create(MainActivity.this, R.raw.mais);
        multiplicarSom = MediaPlayer.create(MainActivity.this, R.raw.multiplicar);
        patoSom = MediaPlayer.create(MainActivity.this, R.raw.pato);
    }

    private void configurarListeners(){
        imageView.setOnClickListener(setListenerImageView());
        btDelete.setOnClickListener(setListenerBtDelete());
        btDividir.setOnClickListener(setListenerBtDividir());
        btMultiplicar.setOnClickListener(setListenerBtMultiplicar());
        btSubtrair.setOnClickListener(setListenerBtSubtrair());
        btSoma.setOnClickListener(setListenerBtSoma());
        btCalcular.setOnClickListener(setListenerBtCalcular());
    }

    private View.OnClickListener setListenerImageView(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patoSom.start();
            }
        };
    }

    private View.OnClickListener setListenerBtDelete(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado = 0f;
                display = "";
                tvResultado.setText("");
            }
        };
    }

    private View.OnClickListener setListenerBtDividir(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao(Operacao.DIVISAO, v);
            }
        };
    }

    private View.OnClickListener setListenerBtMultiplicar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiplicarSom.start();
                operacao(Operacao.MULTIPLICACAO, v);
            }
        };
    }

    private View.OnClickListener setListenerBtSubtrair(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao(Operacao.SUBTRACAO, v);
            }
        };
    }

    private View.OnClickListener setListenerBtSoma(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maisSom.start();
                operacao(Operacao.SOMA, v);
            }
        };
    }

    private View.OnClickListener setListenerBtCalcular(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                igualSom.start();
                calcular(v);
            }
        };
    }

    public void setValue(View v){
        display += ((Button) v).getText();

        tvResultado.setText(display);
    }

    public void operacao(Operacao operacao, View v){
        if(Objects.nonNull(ultimaOperacao)){
            calcular(v);
        }
        ultimaOperacao = operacao;
        resultado = Float.parseFloat(display);
        display = "";
        tvResultado.setText(display);
    }

    public void calcular(View v){
        switch (ultimaOperacao){
            case SOMA:
                resultado += Float.parseFloat(display);
                break;
            case SUBTRACAO:
                resultado -= Float.parseFloat(display);
                break;
            case MULTIPLICACAO:
                resultado *= Float.parseFloat(display);
                break;
            case DIVISAO:
                resultado /= Float.parseFloat(display);
                break;
        }

        ultimaOperacao = null;
        display = resultado.toString();
        tvResultado.setText(display);
        resultado = null;
    }

}

