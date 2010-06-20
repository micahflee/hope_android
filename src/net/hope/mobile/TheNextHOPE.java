package net.hope.mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
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
        		Toast.makeText(getBaseContext(), "Loading Schedule", Toast.LENGTH_LONG).show();
        	}
        });
        favoritesButton = (Button) findViewById(R.id.favoritesButton);
        favoritesButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		webview.loadUrl("file:///android_asset/www/favorites.html");
        		Toast.makeText(getBaseContext(), "Loading Favorites", Toast.LENGTH_LONG).show();
        	}
        });
        iaciendaButton= (Button) findViewById(R.id.iaciendaButton);
        iaciendaButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getBaseContext(), "Coming Soon", Toast.LENGTH_LONG).show();
        	}
        });
        
        /*// try loading and reading file
        FileInputStream fIn = null; 
        InputStreamReader isr = null;
        char[] inputBuffer = new char[255]; 
        String data = null;
        try{
        	fIn = openFileInput(SCHEDULE_FILENAME);
        	isr = new InputStreamReader(fIn); 
        	isr.read(inputBuffer); 
        	data = new String(inputBuffer);
        } 
        catch (Exception e) {       
        	e.printStackTrace(); 
        } 
        finally { 
        	try { 
        		isr.close(); 
        		fIn.close(); 
        	} catch (IOException e) { 
        		e.printStackTrace(); 
        	} 
        }
        Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
        
        // try saving a file
        String fakeJSON = "document.innerHTML = '<h1>just a test</h1>';";
        FileOutputStream fOut = null;
        OutputStreamWriter osw = null;
        try{
        	fOut = openFileOutput(SCHEDULE_FILENAME, MODE_PRIVATE);      
        	osw = new OutputStreamWriter(fOut);
        	osw.write(fakeJSON);
        	osw.flush();
        }
        catch (Exception e) {      
        	e.printStackTrace();
        }
        finally {
        	try {
        		osw.close();
        		fOut.close();
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        }*/
        
        // initialize the web view
        JSInterface jsInterface = new JSInterface();
        jsInterface.context = getBaseContext();
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(jsInterface, "JSInterface");
        webview.loadUrl("file:///android_asset/www/schedule.html");
    }
    
    public boolean isOnline() {
    	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	return cm.getActiveNetworkInfo().isConnected();
    }
}
