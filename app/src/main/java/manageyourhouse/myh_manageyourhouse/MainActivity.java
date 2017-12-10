package manageyourhouse.myh_manageyourhouse;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    public static ClientTCP client;
    public static Boolean Connect;
    public static Pieces Salon = new Pieces("Salon", false, false, 0);
    public static Pieces Toilette = new Pieces("Toilette", true, false, 0);
    public static Pieces Chambre = new Pieces("Chambre", false, false, 0);
    public static Pieces Cuisine = new Pieces("Cuisine", false, false, 0);
    public static int nb = 1;
    public static AlarmManager am;

    public static void ajouterAlarme(Context context, int year, int month, int day, int hour, int minute)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute);

        Intent intent = new Intent(context, TimeAlarm.class);
        PendingIntent operation = PendingIntent.getBroadcast(context, nb, intent, PendingIntent.FLAG_ONE_SHOT);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), operation);
        nb = nb +1;
    }

    public static void createNotification(Context context){if (android.os.Build.VERSION.SDK_INT >= 26 ) {
        final NotificationManager mNotification = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context, "Notification1");
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker("Notif");
        builder.setSmallIcon(R.mipmap.logo);
        builder.setContentTitle("Notif");
        builder.setContentText("Tu m'embêtes");
        mNotification.notify(MainActivity.nb, builder.build());
        MainActivity.nb = MainActivity.nb +1;
    }
    else{
        final NotificationManager mNotification = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker("Notif");
        builder.setSmallIcon(R.mipmap.logo);
        builder.setContentTitle("Notif");
        builder.setContentText("Tu m'embêtes");
        mNotification.notify(MainActivity.nb, builder.build());
        MainActivity.nb = MainActivity.nb +1;
    }}

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

        final ImageButton ButtonSonnette = (ImageButton) findViewById(R.id.buttonSonnette);
        ButtonSonnette.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, Sonnette.class);
                startActivity(intent3);
            }
        });
        //am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //ajouterAlarme(MainActivity.this, 2017, 12, 10, 14, 40);
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
                        Connect = true;
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
                    Connect = false;
                }
            }
        });
        Thread reception = new Thread(new Runnable() {
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
                    /*try {
                        client = new ClientTCP("192.168.1.1", 12345);
                        Connect = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
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
                    Connect = false;
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
