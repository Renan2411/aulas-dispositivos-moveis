package com.example.notification;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        NotificationChannel notificationChannel = new NotificationChannel("notificaoSimples", "Premio", NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    public void criarToast(View v) {
        Toast.makeText(MainActivity.this, "Meu Primerio Toast", Toast.LENGTH_LONG).show();
    }

    public void criarAlert(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Primeiro Alert");
        alert.setMessage("Bomba");

        alert.setIcon(android.R.drawable.ic_dialog_alert);

        alert.setPositiveButton("Tudo bem?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Blz parceiro!", Toast.LENGTH_LONG).show();
            }
        });

        alert.setNegativeButton("Não tá legal?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Descansa ai parceiro!", Toast.LENGTH_LONG).show();
            }
        });

        alert.show();
    }

    @SuppressLint("MissingPermission")
    public void criarNotificacaoSimples(View v) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "notificacaoSimples");
        notificationBuilder.setSmallIcon(android.R.drawable.ic_delete);
        notificationBuilder.setContentTitle("Título da notificação");
        notificationBuilder.setContentText("Texto da notificação");

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        notificationManagerCompat.notify(1, notificationBuilder.build());

    }

}
