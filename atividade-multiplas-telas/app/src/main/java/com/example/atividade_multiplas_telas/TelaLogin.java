package com.example.atividade_multiplas_telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class TelaLogin extends AppCompatActivity {

    EditText tfNome, tfSenha;
    TextView tvErroLogin, tvErroSenha, linkHome, linkCadastro;
    Button btLogar;
    Intent telaCadastro, telaHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.setComponents();
        this.setListeners();
        this.setIntents();
    }

    private void setComponents() {
        tfNome = (EditText) findViewById(R.id.tfNome);
        tfSenha = (EditText) findViewById(R.id.tfSenha);
        tvErroLogin = (TextView) findViewById(R.id.tvErroLogin);
        tvErroSenha = (TextView) findViewById(R.id.tvErroSenha);
        btLogar = (Button) findViewById(R.id.btCadastrarDados);
        linkCadastro = (TextView) findViewById(R.id.linkLogin);
        linkHome = (TextView) findViewById(R.id.linkHome);
    }

    private void setListeners() {
        btLogar.setOnClickListener(this.setActionBtLogar());
        linkHome.setOnClickListener(this.setActionLinkHome());
        linkCadastro.setOnClickListener(this.setActionLinkCadastro());
    }

    private void setIntents() {
        telaHome = new Intent(this, MainActivity.class);
        telaCadastro = new Intent(this, TelaCadastro.class);
    }

    private View.OnClickListener setActionBtLogar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazerLogin();
            }
        };
    }

    private View.OnClickListener setActionLinkHome() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telaHome);
            }
        };
    }

    private View.OnClickListener setActionLinkCadastro() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telaCadastro);
            }
        };
    }

    private void fazerLogin() {
        verificarCampoSenha();
        verificarCampoNome();
        redirecionarParaTelaHomePosLogin();
    }

    private void verificarCampoNome() {
        if (ehCampoNomeVazio()) {
            tvErroLogin.setText(R.string.message_error_login);
        } else {
            tvErroLogin.setText("");
        }
    }

    private void verificarCampoSenha() {
        if (ehCampoSenhaVazio()) {
            tvErroSenha.setText(R.string.message_error_senha);
        } else {
            tvErroSenha.setText("");
        }
    }

    private void redirecionarParaTelaHomePosLogin() {
        if (!ehCampoNomeVazio() && !ehCampoSenhaVazio()) {
            startActivity(telaHome);
        }
    }

    private Boolean ehCampoNomeVazio() {
        return Objects.equals(tfNome.getText().toString(), "");
    }

    private Boolean ehCampoSenhaVazio() {
        return Objects.equals(tfSenha.getText().toString(), "");
    }
}