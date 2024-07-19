package com.example.dados_entre_telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    private EditText edTitulo, edLancamento, edGenero, edProdutora, edModos;
    private Button btBuffer, btSerializable;

    Intent telaBundle, telaSerializable;

    Bundle dadosEnvio;

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

        edTitulo = (EditText) findViewById(R.id.edTitulo);
        edGenero = (EditText) findViewById(R.id.edGenero);
        edProdutora = (EditText) findViewById(R.id.edProdutora);
        edModos = (EditText) findViewById(R.id.edModos);
        edLancamento = (EditText) findViewById(R.id.edLancamento);

        btBuffer = (Button) findViewById(R.id.btBuffer);
        btSerializable = (Button) findViewById(R.id.btSerializable);

        telaBundle = new Intent(this, TelaBundler.class);
        telaSerializable = new Intent(this, telaSerializable.class);

        btBuffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dadosEnvio = new Bundle();

                dadosEnvio.putString("titulo", edTitulo.getText().toString());
                dadosEnvio.putString("genero", edGenero.getText().toString());
                dadosEnvio.putString("produtora", edProdutora.getText().toString());
                dadosEnvio.putString("modos", edModos.getText().toString());
                dadosEnvio.putString("lacamento", edLancamento.getText().toString());

                telaBundle.putExtras(dadosEnvio);

                startActivity(telaBundle);
            }
        });

        btSerializable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jogo jogo = new Jogo(
                        edTitulo.getText().toString(),
                        edGenero.getText().toString(),
                        edLancamento.getText().toString(),
                        edModos.getText().toString(),
                        edProdutora.getText().toString()
                );

                telaSerializable.putExtra("jogo", jogo);
                startActivity(telaSerializable);
            }
        });
    }
}