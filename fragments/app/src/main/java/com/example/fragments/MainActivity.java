package com.example.fragments;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorUmidade, sensorTemperatura;

    Bundle dadosEnvio;

    Button btTela2, btTela3;

    TextView tvResultado;
    boolean flag;
    Handler handler;
    int controle = 1;

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

        tvResultado = (TextView) findViewById(R.id.tvResultado);


        gerenciarSensores();

        btTela2 = (Button) findViewById(R.id.btTela2);
        btTela3 = (Button) findViewById(R.id.btTela3);

        btTela2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTela2(v);
            }
        });

        btTela3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTela3(v);
            }
        });

        handler = new Handler();
    }

    public void iniciarTarefa(View v) {
        flag = true;

        new Thread() {
            @Override
            public void run() {
                while (flag) {
                    Log.i("TT", "Tarega executand");

                    //POST
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            tvResultado.setText("");
//                            tvResultado.setText(controle + "\nTarefa executando....");
//                        }
//                    });

                    //POST COM ATRASO
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            tvResultado.setText(controle + "\nTarefa executando....");
//                        }
//                    }, 3000);


                    //RUN UI THREAD
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResultado.setText(controle + "\nTarefa executando....");
                        }
                    });


                    controle++;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

//        for (int i = 0; i < 50; i++) {
//            tvResultado.setText(tvResultado.getText().toString() + "\nTarefa executando....");
//            if(!flag){
//                break;
//            }
//        }
    }

    public void pararTarefa(View v) {
        flag = false;
        controle = 1;
    }

    @Override
    protected void onStart() {
        super.onStart();

        dadosEnvio = new Bundle();

        dadosEnvio.putString("cumprimento", "Bem vindo a tela com transferencia de dados");
        dadosEnvio.putInt("hora", 21);
        dadosEnvio.putBoolean("verdadeiro", false);
    }

    public void abrirTela2(View view) {
        Intent intent = new Intent(this, Tela2.class);

        //intent.putExtra("Cumprimento", "Bem vindo a tela passando dados entre activities");
        //intent.putExtra("Hora", 21);

        startActivity(intent);
    }

    public void abrirTela3(View view) {
        Pessoa pessoa = new Pessoa("Renan", 1.70f, 75f);

        Intent intent = new Intent(this, Tela3.class);

//        intent.putExtras(dadosEnvio);

        intent.putExtra("pessoa", pessoa);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this, sensorTemperatura);
        sensorManager.unregisterListener(this, sensorUmidade);
    }

    private void gerenciarSensores() {
//        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        sensorUmidade = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
//        sensorTemperatura = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
//
//        if (Objects.nonNull(sensorUmidade)) {
//            sensorManager.registerListener(this, sensorUmidade, SensorManager.SENSOR_DELAY_NORMAL);
//        } else {
//            Toast.makeText(this, "Sensor de umidade indisponível", Toast.LENGTH_SHORT).show();
//        }
//
//        if (Objects.nonNull(sensorTemperatura)) {
//            sensorManager.registerListener(this, sensorTemperatura, SensorManager.SENSOR_DELAY_NORMAL);
//        } else {
//            Toast.makeText(this, "Sensor de temperatura indisponível", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            Log.i("SENSOR", String.format("TEMPERATURA => %s °C", Float.toString(event.values[0])));
        }

        if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            Log.i("SENSOR", String.format("HUMIDADE => %s", Float.toString(event.values[0])));
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}