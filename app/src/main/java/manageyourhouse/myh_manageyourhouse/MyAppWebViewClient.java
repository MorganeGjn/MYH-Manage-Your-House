package manageyourhouse.myh_manageyourhouse;

import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyAppWebViewClient extends WebViewClient {

    @Override
    /*
     * La fonction shouldOverrideUrlLoading permet de restreindre l'url configurée avec loadurl
     * à une chaîne de caractère précise, soit le nom de domaine dans le script suivant
     */

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(Uri.parse(url).getHost().endsWith("http://192.168.1.1:8082/html/cam_pic_new.php")) {
            return false;
        }
        view.loadUrl(url);
        return true;
    }
}
