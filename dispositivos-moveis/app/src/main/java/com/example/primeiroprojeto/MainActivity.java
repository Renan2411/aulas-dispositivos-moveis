package com.example.primeiroprojeto;

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
//implements View.OnClickListener
public class MainActivity extends AppCompatActivity  {

    EditText etNome;
    TextView tvSaida;
    Button btOk, btCancelar;

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btOk){
                tvSaida.setText(etNome.getText().toString());
            }else if(v.getId() == R.id.btCancelar){
                tvSaida.setText("");
                etNome.setText("");
            }

        }
    };

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

        etNome = (EditText) findViewById(R.id.etNome);
        tvSaida = (TextView) findViewById(R.id.tvSaida);
        btOk = (Button) findViewById(R.id.btOk);
        btCancelar = (Button) findViewById(R.id.btCancelar);

        btOk.setOnClickListener(listener);
     btCancelar.setOnClickListener(listener);

        //implementação de interface
//        btOk.setOnClickListener(this);
//        btCancelar.setOnClickListener(this);

        //Implementação anonima
//        btOk.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                tvSaida.setText(etNome.getText().toString());
//            }
//        });
//
//        btCancelar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvSaida.setText("");
//                etNome.setText("");
//            }
//        });
    }

    //Com implementação de interface

//    @Override
//    public void onClick(View v) {
//        if(v.getId() == R.id.btOk){
//            tvSaida.setText(etNome.getText().toString());
//        }else if(v.getId() == R.id.btCancelar){
//            tvSaida.setText("");
//            etNome.setText("");
//        }
//    }

//    public void enviarNome(View v){
//        tvSaida.setText(etNome.getText().toString());
//    }
}