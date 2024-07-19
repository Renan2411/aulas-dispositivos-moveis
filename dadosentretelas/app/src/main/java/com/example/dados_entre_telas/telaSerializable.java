package com.example.dados_entre_telas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class telaSerializable extends AppCompatActivity {

    TextView tvInformacaoSerializada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_serializable);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvInformacaoSerializada = (TextView) findViewById(R.id.tvInformacaoSerializada);

        Intent intent = getIntent();

        Jogo jogo = (Jogo) intent.getSerializableExtra("jogo");

        tvInformacaoSerializada.setText(
                String.format("TITULO => %s\n" +
                        "LANÇAMENTO => %s\n" +
                        "GENERO => %s\n" +
                        "MODOS => %s\n" +
                        "PRODUTORA => %s\n", jogo.getTitulo(), jogo.getLancamento(), jogo.getGenero(), jogo.getModos(), jogo.getProdutora())
        );
    }
}