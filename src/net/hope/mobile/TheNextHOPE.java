package net.hope.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class TheNextHOPE extends Activity {
	WebView webview;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/www/schedule.html");
    }
}
