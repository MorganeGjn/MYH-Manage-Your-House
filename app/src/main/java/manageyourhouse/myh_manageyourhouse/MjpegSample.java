package manageyourhouse.myh_manageyourhouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class MjpegSample extends Activity {
    private static final String TAG = "MjpegActivity";

    private MjpegView mv;
    String URL = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mv = new MjpegView(this);
        setContentView(mv);

        Bundle extras = getIntent().getExtras();
        if (extras != null && !extras.getString("ip").equals("")) {
            URL = extras.getString("ip");
        } else {
            URL = "http://webcam.aui.ma/axis-cgi/mjpg/video.cgi?resolution=CIF&amp";
        }

        new DoRead().execute(URL);
    }

    public void onPause() {
        super.onPause();
        mv.stopPlayback();
        finish();
    }

    public class DoRead extends AsyncTask<String, Void, MjpegInputStream> {
        protected MjpegInputStream doInBackground(String... url) {
            HttpURLConnection  httpclient = null;
            try {
                java.net.URL url1 = new URL("http://www.mysite.se/index.asp?data=99");

                httpclient = (HttpURLConnection) url1
                        .openConnection();
                InputStream in = httpclient.getInputStream();

                InputStreamReader isw = new InputStreamReader(in);
            /*HttpResponse res = null;
            DefaultHttpClient httpclient = new DefaultHttpClient();
            Log.d(TAG, "1. Sending http request");
            try {
                res = httpclient.execute(new HttpGet(URI.create(url[0])));
                Log.d(TAG, "2. Request finished, status = "
                        + res.getStatusLine().getStatusCode());
                if (res.getStatusLine().getStatusCode() == 401) {
                    return null;
                }
                return new MjpegInputStream(res.getEntity().getContent());*/
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "Request failed-IOException", e);
            }

            return null;
        }

        protected void onPostExecute(MjpegInputStream result) {
            mv.setSource(result);
            mv.setDisplayMode(MjpegView.SIZE_BEST_FIT);
            mv.showFps(true);
        }
    }
}
