package manageyourhouse.myh_manageyourhouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button ButtonEclairage = (Button) findViewById(R.id.buttonEclairage);
        ButtonEclairage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Eclairage.class);
                startActivity(intent1);
            }
        });
        final Button ButtonParametre = (Button) findViewById(R.id.buttonParametres);
        ButtonParametre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Parametres.class);
                startActivity(intent2);
            }
        });

    }
}
