package net.hope.mobile;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class TheNextHOPE extends Activity {
	private WebView webview;
	private Button scheduleButton;
	private Button favoritesButton;
	private Button iaciendaButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // initialize the buttons
        scheduleButton = (Button) findViewById(R.id.scheduleButton);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		webview.loadUrl("file:///android_asset/www/schedule.html");
        		Toast.makeText(getBaseContext(), "Loading Schedule", Toast.LENGTH_SHORT).show();
        	}
        });
        favoritesButton = (Button) findViewById(R.id.favoritesButton);
        favoritesButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		webview.loadUrl("file:///android_asset/www/favorites.html");
        		Toast.makeText(getBaseContext(), "Loading Favorites", Toast.LENGTH_SHORT).show();
        	}
        });
        iaciendaButton= (Button) findViewById(R.id.iaciendaButton);
        iaciendaButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getBaseContext(), "Iacienda Coming Soon", Toast.LENGTH_SHORT).show();
        	}
        });
        
        // initialize the web view
        JSInterface jsInterface = new JSInterface(getBaseContext());
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setFocusable(true);
        webview.setFocusableInTouchMode(false);
        webview.addJavascriptInterface(jsInterface, "JSInterface");
        webview.setWebViewClient(new WebViewClient() {  
        	@Override
        	public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		view.loadUrl(url);
        		return true;
        	}
        	public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        		Toast.makeText(getBaseContext(), "Oh no! "+description, Toast.LENGTH_SHORT).show();
        	}
        });
        webview.loadUrl("file:///android_asset/www/schedule.html");
    }
    
    public boolean isOnline() {
    	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	return cm.getActiveNetworkInfo().isConnected();
    }
}
