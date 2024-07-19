package com.example.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela3 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = (TextView) findViewById(R.id.tvTela3);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        boolean letra;
        int hora;
        String cumprimento;

        Intent intent = getIntent(); //pega a intent recebida

        Pessoa pessoa = (Pessoa) intent.getSerializableExtra("pessoa");

        pessoa.calcularImc();

//        letra = intent.getExtras().getBoolean("verdadeiro");
//        hora = intent.getExtras().getInt("hora");
//        cumprimento = intent.getExtras().getString("cumprimento");

        textView.setText("NOME: " + pessoa.getName() + "\nALTURA: " + pessoa.getAltura() + "\nPESO:" + pessoa.getPeso() + "\nIMC: " + pessoa.getImc());
    }
}