package manageyourhouse.myh_manageyourhouse;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    public static Pieces Salon = new Pieces("Salon", false, false, 0);
    public static Pieces Toilette = new Pieces("Toilette", false, false, 0);
    public static Pieces Chambre = new Pieces("Chambre", false, false, 0);
    public static int nb = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(MainActivity.this, ServiceSocket.class);
        startService(i);
        Intent i2 = new Intent(MainActivity.this, ServiceSocketSonnette.class);
        startService(i2);
        final ImageButton ButtonEclairage = (ImageButton) findViewById(R.id.buttonEclairage);
        ButtonEclairage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Eclairage.class);
                startActivity(intent1);
            }
        });
        final ImageButton ButtonParametre = (ImageButton) findViewById(R.id.buttonParametres);
        ButtonParametre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Parametres.class);
                startActivity(intent2);
            }
        });

        final ImageButton ButtonSonnette = (ImageButton) findViewById(R.id.buttonSonnette);
        ButtonSonnette.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Sonnette.class);
                startActivity(i);
            }
        });
        }
}
