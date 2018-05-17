package manageyourhouse.myh_manageyourhouse;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServiceSocketSonnette extends Service {

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    private Socket socketserver;

    public void createNotification(Context context){if (android.os.Build.VERSION.SDK_INT >= 26 ) {
        final NotificationManager mNotification = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, Sonnette.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(context, "Notification1");
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker("Notif");
        builder.setSmallIcon(R.mipmap.logo);
        builder.setContentTitle("MYH: Manage Your House");
        builder.setContentText("Ça sonne");
        builder.setContentIntent(contentIntent);
        mNotification.notify(MainActivity.nb, builder.build());
        MainActivity.nb = MainActivity.nb +1;
    }
    else{
        final NotificationManager mNotification = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, new Intent(this, Sonnette.class), 0);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker("Notif");
        builder.setSmallIcon(R.mipmap.logo);
        builder.setContentTitle("MYH: Manage Your House");
        builder.setContentText("Ça sonne");
        builder.setContentIntent(contentIntent);
        mNotification.notify(MainActivity.nb, builder.build());
        MainActivity.nb = MainActivity.nb +1;
    }}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){
        super.onCreate();
        // To avoid cpu-blocking, we create a background handler to run our service
        HandlerThread threadSonnette = new HandlerThread("ServiceSocketSonnette");
        // start the new handler thread
        threadSonnette.start();

        mServiceLooper = threadSonnette.getLooper();
        // start the service using the background handler
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartService = new Intent(getApplicationContext(),
                this.getClass());
        restartService.setPackage(getPackageName());
        PendingIntent restartServicePI = PendingIntent.getService(
                getApplicationContext(), 1, restartService,
                PendingIntent.FLAG_ONE_SHOT);

        //Restart the service once it has been killed android


        AlarmManager alarmService = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmService.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() +100, restartServicePI);

    }

    @Override

    public int onStartCommand(Intent intent, int flags, int startId){
        super.onStartCommand(intent, flags, startId);
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();

        // call a new service handler. The service ID can be used to identify the service
        Message message = mServiceHandler.obtainMessage();
        message.arg1 = startId;
        mServiceHandler.sendMessage(message);

        return START_STICKY;
    }

    protected void showToast(final String msg) {
        //gets the main thread
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                // run this code in the main thread
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final class ServiceHandler extends Handler {

        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            // Well calling mServiceHandler.sendMessage(message); from onStartCommand,
            // this method will be called.
            String mess;
            try {
                socketserver = new Socket("192.168.1.1", 12347);
                while(socketserver.isConnected()){
                    BufferedReader in = new BufferedReader(new InputStreamReader(socketserver.getInputStream()));
                    mess = in.readLine();
                    System.out.print(mess);
                    if (mess.equals("1")){
                        createNotification(getApplicationContext());
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void scheduleNotification(Context context, int delay) {
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, contentIntent);
    }
}

