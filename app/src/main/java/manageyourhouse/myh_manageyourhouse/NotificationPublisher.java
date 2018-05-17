package manageyourhouse.myh_manageyourhouse;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class NotificationPublisher extends AppCompatActivity{

    public void createNotification(Context context){if (android.os.Build.VERSION.SDK_INT >= 26 ) {
        final NotificationManager mNotification = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(context, "Notification1");
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker("Notif");
        builder.setSmallIcon(R.mipmap.logo);
        builder.setContentTitle("MYH: Manage Your House");
        builder.setContentText("Lumière allumée");
        builder.setContentIntent(contentIntent);
        mNotification.notify(MainActivity.nb, builder.build());
        MainActivity.nb = MainActivity.nb +1;
    }
    else{
        final NotificationManager mNotification = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker("Notif");
        builder.setSmallIcon(R.mipmap.logo);
        builder.setContentTitle("MYH: Manage Your House");
        builder.setContentText("Lumière allumée");
        builder.setContentIntent(contentIntent);
        mNotification.notify(MainActivity.nb, builder.build());
        MainActivity.nb = MainActivity.nb +1;
    }}

    @Override
    public void onCreate(Bundle coucou) {
        super.onCreate(coucou);
        createNotification(this);
        Intent intent1 = new Intent(this, Eclairage.class);
        startActivity(intent1);
    }
}
