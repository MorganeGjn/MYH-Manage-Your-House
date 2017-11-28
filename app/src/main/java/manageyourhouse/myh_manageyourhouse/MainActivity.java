package manageyourhouse.myh_manageyourhouse;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    public static ClientTCP client;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //int progress=msg.getData().getInt(PROGRESS_BAR_INCREMENT);
            // Incrémenter la ProgressBar, on est bien dans la Thread de l'IHM
            //bar.incrementProgressBy(progress);
            // On peut faire toute action qui met à jour l'IHM

        }
    };
    /**     * L'AtomicBoolean qui gère la destruction de la Thread de background     */
    AtomicBoolean isRunning = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        final Button ButtonSonnette = (Button) findViewById(R.id.buttonSonnette);
        ButtonSonnette.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, Sonnette.class);
                startActivity(intent3);
            }
        });

        }

    public void onStart() {
        super.onStart();
        // Définition de la Thread (peut être effectuée dans une classe externe ou interne)
        Thread background = new Thread(new Runnable() {
            /**
             * Le Bundle qui porte les données du Message et sera transmis au Handler
             */
            Bundle messageBundle=new Bundle();
            /**
             * Le message échangé entre la Thread et le Handler
             */
            Message myMessage;
            // Surcharge de la méthode run
            public void run() {
                try {
                    try {
                        client = new ClientTCP("192.168.1.1", 12345);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        // Envoyer le message au Handler (la méthode handler.obtainMessage est plus efficace
                        // que créer un message à partir de rien, optimisation du pool de message du Handler)
                        //Instanciation du message (la bonne méthode):
                        myMessage=handler.obtainMessage();
                        //Ajouter des données à transmettre au Handler via le Bundle
                        messageBundle.putInt("1",1);
                        //Ajouter le Bundle au message
                        myMessage.setData(messageBundle);
                        //Envoyer le message
                        handler.sendMessage(myMessage);
                } catch (Throwable t) {
                    // gérer l'exception et arrêter le traitement
                }
            }
        });
        //Initialisation des AtomicBooleans
        isRunning.set(true);
        //Lancement de la Thread
        background.start();
    }
    //Méthode appelée quand l'activité s'arrête
    public void onStop() {
        super.onStop();
        //Mise-à-jour du booléen pour détruire la Thread de background
        isRunning.set(false);
    }
}
