package com.example.atividade_multiplas_telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaCadastro extends AppCompatActivity {

    EditText tfLogin, tfSenha, tfNomeCompleto, tfTelefone, tfNascimento;
    RadioGroup rgSexo;
    TextView tvErroSexo, tvErroLogin, tvErroSenha, tvErroNomeCompleto, tvErroTelefone, tvErroNascimento, linkHome, linkLogin;
    String sexo = "";
    Button btCadastro;
    Intent telaLogin, telaHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_cadastro);
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
        tfLogin = (EditText) findViewById(R.id.tfNome);
        tfNomeCompleto = (EditText) findViewById(R.id.tfNomeCompleto);
        tfSenha = (EditText) findViewById(R.id.tfSenha);
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
        tfTelefone = (EditText) findViewById(R.id.tfTelefone);
        tfNascimento = (EditText) findViewById(R.id.tfNascimento);
        tvErroNomeCompleto = (TextView) findViewById(R.id.tvErroNomeCompleto);
        tvErroTelefone = (TextView) findViewById(R.id.tvErroTelefone);
        tvErroNascimento = (TextView) findViewById(R.id.tvErroNascimento);
        tvErroLogin = (TextView) findViewById(R.id.tvErroLogin);
        tvErroSexo = (TextView) findViewById(R.id.tvErroSexo);
        tvErroSenha = (TextView) findViewById(R.id.tvErroSenha);
        btCadastro = (Button) findViewById(R.id.btCadastrarDados);
        linkLogin = (TextView) findViewById(R.id.linkLogin);
        linkHome = (TextView) findViewById(R.id.linkHome);
    }

    private void setListeners() {
        btCadastro.setOnClickListener(this.setActionBtCadastrar());
        linkHome.setOnClickListener(this.setActionLinkHome());
        linkLogin.setOnClickListener(this.setActionLinkLogin());

        rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                sexo = radioButton.getText().toString();
            }
        });
    }

    private void setIntents() {
        telaHome = new Intent(this, MainActivity.class);
        telaLogin = new Intent(this, TelaLogin.class);
    }

    private View.OnClickListener setActionBtCadastrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
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

    private View.OnClickListener setActionLinkLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telaLogin);
            }
        };
    }

    private void cadastrar() {
        verificarCampoLogin();
        verificarCampoNascimento();
        verificarCampoNomeCompleto();
        verificarCampoSexo();
        verificarCampoTelefone();
        verificarCampoSenha();
        redirecionarParaTelaHomePosLogin();
    }

    public void verificarCampoLogin(){
        if(ehCampoLoginVazio()){
            tvErroLogin.setText(R.string.message_error_login);
        }else{
            tvErroLogin.setText("");
        }
    }

    public void verificarCampoNomeCompleto(){
        if(ehCampoNomeCompletoVazio()){
            tvErroNomeCompleto.setText(R.string.message_error_nome_completo);
        }else{
            tvErroNomeCompleto.setText("");
        }
    }

    public void verificarCampoNascimento(){
        if(ehCampoNascimentoVazio()){
            tvErroNascimento.setText(R.string.message_error_nascimento);
        }else{
            tvErroNascimento.setText("");
        }
    }

    public void verificarCampoTelefone(){
        if(ehCampoTelegoneVazio()){
            tvErroTelefone.setText(R.string.message_error_telefone);
        }else{
            tvErroTelefone.setText("");
        }
    }

    public void verificarCampoSenha(){
        if(ehCampoSenhaVazio()){
            tvErroSenha.setText(R.string.message_error_senha);
        }else{
            tvErroSenha.setText("");
        }
    }

    public void verificarCampoSexo(){
        if(ehCampoSexoVazio()){
            tvErroSexo.setText(R.string.message_error_senha);
        }else{
            tvErroSexo.setText("");
        }
    }

    private void redirecionarParaTelaHomePosLogin() {
        if (!ehCampoLoginVazio() && !ehCampoSenhaVazio() && !ehCampoSexoVazio() && !ehCampoTelegoneVazio() && !ehCampoNascimentoVazio() && !ehCampoNascimentoVazio()) {
            startActivity(telaHome);
        }
    }

    private Boolean ehCampoLoginVazio(){
        System.out.println(tfLogin.getText().toString().equals(""));
        return tfLogin.getText().toString().equals("");
    }

    private Boolean ehCampoNomeCompletoVazio(){
        return tfNomeCompleto.getText().toString().equals("");
    }

    private Boolean ehCampoSenhaVazio(){
        return tfSenha.getText().toString().equals("");
    }

    private Boolean ehCampoSexoVazio(){
        return sexo.equals("");
    }

    private Boolean ehCampoNascimentoVazio(){
        return tfNascimento.getText().toString().equals("");
    }

    private Boolean ehCampoTelegoneVazio(){
        return tfTelefone.getText().toString().equals("");
    }

}