package manageyourhouse.myh_manageyourhouse;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {






    public static ClientTCP client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton ButtonEclairage = (ImageButton) findViewById(R.id.buttonEclairage);
        ButtonEclairage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    client = new ClientTCP("192.168.1.1", 12345);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        final Button ButtonSonnette = (Button) findViewById(R.id.buttonSonnette);
        ButtonSonnette.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, Sonnette.class);
                startActivity(intent3);
            }
        });

        }
}
