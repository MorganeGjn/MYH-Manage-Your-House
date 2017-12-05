package manageyourhouse.myh_manageyourhouse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class Parametres extends AppCompatActivity {

    private Button add_notification;





    //Ajouts Lamine
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parametres);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.ListePiece);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapterParametres());

        add_notification = (Button) findViewById(R.id.buttonNotif);
        add_notification.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getBaseContext(), "Ajout d'une notification", Toast.LENGTH_SHORT).show();
            }
        });


        };
    /*private final void createNotification(){
        //Récupération du notification Manager
        final NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        //Création de la notification avec spécification de l'icône de la notification et le texte qui apparait à la création de la notification
        final Notification notification = new Notification(R.drawable.notification, notificationTitle, System.currentTimeMillis());

        //Définition de la redirection au moment du clic sur la notification. Dans notre cas la notification redirige vers notre application
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, Parametres.class), 0);

        //Récupération du titre et description de la notification
        final String notificationTitle = getResources().getString(R.string.notification_title);
        final String notificationDesc = getResources().getString(R.string.notification_desc);

        //Notification & Vibration
        notification.setLatestEventInfo(this, notificationTitle, notificationDesc, pendingIntent);
        notification.vibrate = new long[] {0,200,100,200,100,200};

        notificationManager.notify(NOTIFICATION_ID, notification);
    }
    */
}




