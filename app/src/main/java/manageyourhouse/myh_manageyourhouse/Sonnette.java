package manageyourhouse.myh_manageyourhouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import java.io.IOException;


public class Sonnette extends AppCompatActivity {

    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonnette);
        mWebView = (WebView) findViewById(R.id.WebViewVideo);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.loadUrl("http://192.168.1.1:8082/html/cam_pic_new.php");
        mWebView.flingScroll(150,150);
        mWebView.setWebViewClient(new MyAppWebViewClient() {
            // Fonction qui permet l'affichage de la page lorsque tout est chargé (événement onPageFinished)
            @Override
            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.WebViewVideo).setVisibility(View.VISIBLE);
            }
        });
        final ImageButton ButtonOuverturePorte = (ImageButton) findViewById(R.id.buttonOuvrirPorte);
        ButtonOuverturePorte.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    ServiceSocket.client.SendSetStateLight("Porte");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        });
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}