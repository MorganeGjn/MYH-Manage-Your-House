package manageyourhouse.myh_manageyourhouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;


public class Sonnette extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonnette);
        final ImageButton ButtonOuverturePorte = (ImageButton) findViewById(R.id.buttonOuvrirPorte);
        ButtonOuverturePorte.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    String Reponse = MainActivity.client.SendSetStateLight("OuvrirPorte");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}