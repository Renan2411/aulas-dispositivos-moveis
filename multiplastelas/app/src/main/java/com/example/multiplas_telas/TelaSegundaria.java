package com.example.multiplas_telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaSegundaria extends Activity {

    Button bt;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.tela2);
        bt = (Button) findViewById(R.id.btOutraTela);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTela(v);
            }
        });
    }

    public void abrirTela(View v){
        Intent intent = new Intent(this, Principal.class);

        startActivity(intent);
    }



}
