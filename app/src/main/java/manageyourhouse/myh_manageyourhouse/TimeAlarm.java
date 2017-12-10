package manageyourhouse.myh_manageyourhouse;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static android.content.Context.NOTIFICATION_SERVICE;

public class TimeAlarm extends BroadcastReceiver {

    public static String Titre;
    public static String message;

    public static void changeNotification(String t, String m){
        Titre = t;
        message = m;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (android.os.Build.VERSION.SDK_INT >= 26 ) {
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
        }
    }
}
