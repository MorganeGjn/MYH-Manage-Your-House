package manageyourhouse.myh_manageyourhouse;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServiceSocket extends Service {

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    public static ClientTCP client;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){
        super.onCreate();
        // To avoid cpu-blocking, we create a background handler to run our service
        HandlerThread thread = new HandlerThread("ServiceSocket");
        // start the new handler thread
        thread.start();

        mServiceLooper = thread.getLooper();
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
        //Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();

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
            try {
                client = new ClientTCP("192.168.1.1", 12345);
                while (client.socket.isConnected()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));
                    String mess = in.readLine();
                    System.out.print(mess);
                    if (mess.equals("2")) {
                        MainActivity.Salon.setEtat(false);
                    }
                    if (mess.equals("3")) {
                        MainActivity.Salon.setEtat(true);
                    }
                    if (mess.equals("4")) {
                        MainActivity.Toilette.setEtat(false);
                    }
                    if (mess.equals("5")) {
                        MainActivity.Toilette.setEtat(true);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
