package com.example.dados_entre_telas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaBundler extends AppCompatActivity {

    TextView tvInformacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_bundler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvInformacao = (TextView) findViewById(R.id.tvInformacao);

        Intent intent = getIntent();

        String titulo = intent.getExtras().getString("titulo");
        String lancamento = intent.getExtras().getString("lancamento");
        String genero = intent.getExtras().getString("genero");
        String modos = intent.getExtras().getString("modos");
        String produtora = intent.getExtras().getString("produtora");

        tvInformacao.setText(
                String.format("TITULO => %s\n" +
                        "LANÇAMENTO => %s\n" +
                        "GENERO => %s\n" +
                        "MODOS => %s\n" +
                        "PRODUTORA => %s\n", titulo, lancamento, genero, modos, produtora)
        );

    }
}