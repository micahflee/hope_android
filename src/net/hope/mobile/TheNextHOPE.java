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
//import android.widget.Toast;

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
        
        // how to make popups
        // Toast.makeText(getBaseContext(), "my string here", Toast.LENGTH_SHORT).show();
        
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
        
        // check for internet connectivity
        /*if(isOnline()) {
        	// download newest schedule
        	String scheduleJSON = getUrlData("http://hope.banditdefense.com/json.php");
        	
        	// save it to a file for the webview to access
			try {
				FileOutputStream fos = openFileOutput(SCHEDULE_FILENAME, Context.MODE_PRIVATE);
				fos.write(scheduleJSON.getBytes());
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }*/
        
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
    
    public String getUrlData(String url) {
    	String websiteData = null;
    	try {
    		DefaultHttpClient client = new DefaultHttpClient();
    		URI uri = new URI(url);
    		HttpGet method = new HttpGet(uri);
    		HttpResponse res = client.execute(method);
    		InputStream data = res.getEntity().getContent();
    		websiteData = generateString(data);
    	} catch (ClientProtocolException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (URISyntaxException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return websiteData;
    }
    
    public String generateString(InputStream stream) {
    	InputStreamReader reader = new InputStreamReader(stream);
    	BufferedReader buffer = new BufferedReader(reader);
    	StringBuilder sb = new StringBuilder();
    	try {
    		String cur;
    		while ((cur = buffer.readLine()) != null) {
    				sb.append(cur + "\n");
    		}
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	try {
    		stream.close();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return sb.toString();
    }
}
