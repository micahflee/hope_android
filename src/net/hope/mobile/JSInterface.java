package net.hope.mobile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;

public class JSInterface {
	public String SCHEDULE_FILENAME = "schedule.json";
	public Context context;
	
	public String getScheduleJSON() {
		/*// try downloading file
    	String scheduleJSON;
    	try {
    		DefaultHttpClient client = new DefaultHttpClient();
    		URI uri = new URI("http://hope.banditdefense.com/json.php");
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
    		e.printStackTrace();
    	}
		
		// try loading and reading file
		FileInputStream fIn = null; 
        InputStreamReader isr = null;
        char[] inputBuffer = new char[255]; 
        String data = null;
        try{
        	fIn = context.openFileInput(SCHEDULE_FILENAME);
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
        return data;*/
		
		return "{ }";
	}
}
