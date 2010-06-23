package net.hope.mobile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class JSInterface {
	public static final String PREFS_NAME = "TheNextHOPEPrefs";
	public static final String SCHEDULE_JSON_URL = "http://www.thenexthope.org/hope_schedule/json.php";
	
	private Context context;
	private String prefJSON;
	private String prefFavorites;
	private String prefFilter;
	private long lastDownloadedJSON = 0;
	
	public JSInterface(Context c) {
		context = c;
		
		// initialize preferences
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		prefJSON = settings.getString("json", "{ }");
		prefFavorites = settings.getString("favorites", "");
		prefFilter = settings.getString("filter", "all");
	}
	
	public String getScheduleJSON() {
		// if it's been less than 5 minutes since the last json pull, just return the stored value
		long timeDiff = System.currentTimeMillis() - lastDownloadedJSON;
		if(timeDiff < 300000) {
			//int seconds = (int)(timeDiff / 1000);
			//Toast.makeText(context, "Downloaded schedule "+seconds+" seconds ago", Toast.LENGTH_SHORT).show();
			return prefJSON;
		}
		
		// try downloading file
    	String scheduleJSON = "";
    	try {
    		DefaultHttpClient client = new DefaultHttpClient();
    		URI uri = new URI(SCHEDULE_JSON_URL);
    		HttpGet method = new HttpGet(uri);
    		HttpResponse res = client.execute(method);
    		InputStream data = res.getEntity().getContent();
    		InputStreamReader reader = new InputStreamReader(data);
        	BufferedReader buffer = new BufferedReader(reader);
        	StringBuilder sb = new StringBuilder();
        	String cur;
    		while ((cur = buffer.readLine()) != null) {
    			sb.append(cur + "\n");
    		}
    		data.close();
        	scheduleJSON = sb.toString();
    	} catch (Exception e) {
    		// failed to download, so let's just return what we've got
    		Toast.makeText(context, "Used stored schedule", Toast.LENGTH_SHORT).show();
    		return prefJSON;
    	}
    	
    	// downloaded new json successfully, now save it and return it
    	lastDownloadedJSON = System.currentTimeMillis();
    	prefJSON = scheduleJSON;
    	SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("json", prefJSON);
		editor.commit();
		Toast.makeText(context, "Downloaded latest schedule", Toast.LENGTH_SHORT).show();
		return prefJSON;
	}
	
	public String getFavorites() {
		return prefFavorites;
	}
	
	public void saveFavorites(String favorites) {
		prefFavorites = favorites;
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("favorites", prefFavorites); 
		editor.commit();
	}
	
	public String getFilter() {
		return prefFilter;
	}
	
	public void saveFilter(String filter) {
		prefFilter = filter;
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("filter", prefFilter); 
		editor.commit();
	}
}
