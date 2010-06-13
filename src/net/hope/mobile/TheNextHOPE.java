package net.hope.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class TheNextHOPE extends Activity {
	private WebView webview;
	private Button scheduleButton;
	private Button favoritesButton;
	private Button twitterButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // initialize the web view
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/www/schedule.html");
        
        // initialize the buttons
        scheduleButton = (Button) findViewById(R.id.scheduleButton);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		webview.loadUrl("file:///android_asset/www/schedule.html");
        	}
        });
        favoritesButton = (Button) findViewById(R.id.favoritesButton);
        favoritesButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		webview.loadUrl("file:///android_asset/www/favorites.html");
        	}
        });
        twitterButton= (Button) findViewById(R.id.twitterButton);
        twitterButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		webview.loadUrl("file:///android_asset/www/twitter.html");
        	}
        });
    }
    
    /* schedule button */
    
    
    /* favorites button */
    
    /* twitter button */
}
