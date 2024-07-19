package com.example.atividade_multiplas_telas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaSobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_sobre);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvHome = (TextView) findViewById(R.id.tvHomeSobre);
        Button btSaibaMais = (Button) findViewById(R.id.btSaibaMais);

        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionarParaHome();
            }
        });

        btSaibaMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionarParaSiteUfms();
            }
        });
    }

    public void redirecionarParaHome(){
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }

    public void redirecionarParaSiteUfms(){
        Uri uri = Uri.parse("https://cpan.ufms.br/sistemas-de-informacao/");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        System.out.println("aqui");
        startActivity(it);
    }
}