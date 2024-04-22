package com.example.atividade_multiplas_telas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btCadastrar, btLogin, btSobre;

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
    }

    @Override
    protected void onStart(){
        super.onStart();
        this.setComponentes();
        this.setListeners();
    }

    private void setComponentes(){
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btLogin = (Button) findViewById(R.id.btLogin);
        btSobre = (Button) findViewById(R.id.btSobre);
    }

    private void setListeners(){
        btCadastrar.setOnClickListener(this.setActionBtCadastar());
        btLogin.setOnClickListener(this.setActionBtLogin());
        btSobre.setOnClickListener(this.setActionBtSobre());
    }

    private View.OnClickListener setActionBtLogin(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Botão Login");
            }
        };
    }

    private View.OnClickListener setActionBtCadastar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Botão Cadastar");
            }
        };
    }

    private View.OnClickListener setActionBtSobre(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Botão Sobre");
            }
        };
    }
}