package com.example.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor luminosidadeSensor, proximidadeSensor, temperaturaSensor, umidadeSensor, movimentoSensor;

    EditText edTemperatura, edLuminosidade, edProximidade, edUmidade;

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

        gerenciarSensores();
        registarSensores();
        registarComponentes();
    }

    private void gerenciarSensores() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        temperaturaSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        umidadeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        proximidadeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        luminosidadeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        movimentoSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

    }

    private void registarSensores() {
        registrarSensor(temperaturaSensor);
        registrarSensor(umidadeSensor);
        registrarSensor(proximidadeSensor);
        registrarSensor(luminosidadeSensor);
        registrarSensor(movimentoSensor);
    }

    private void registrarSensor(Sensor sensor) {
        if (Objects.nonNull(sensor)) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, String.format("O sensor %s não está disponível", sensor.getName()), Toast.LENGTH_SHORT).show();
        }
    }

    private void registarComponentes() {
        edTemperatura = (EditText) findViewById(R.id.edTemperatura);
        edLuminosidade = (EditText) findViewById(R.id.edLuminosidade);
        edUmidade = (EditText) findViewById(R.id.edUmidade);
        edProximidade = (EditText) findViewById(R.id.edProximidade);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            edTemperatura.setText(String.format("%s °C", Float.toString(event.values[0])));
        }

        if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            edUmidade.setText(String.format("%s %c", Float.toString(event.values[0]), '%'));
        }

        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            edLuminosidade.setText(String.format("%s lux", Float.toString(event.values[0])));
        }

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            edProximidade.setText(String.format("%s cm", Float.toString(event.values[0])));
        }

        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){

            if(event.values[0] > 0.5 || event.values[1] > 0.5 || event.values[2] > 0.5){
                edUmidade.setText("");
                edTemperatura.setText("");
                edLuminosidade.setText("");
                edProximidade.setText("");
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}