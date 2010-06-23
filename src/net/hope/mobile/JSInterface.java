package net.hope.mobile;

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
        return data;*/
		
		return "";
	}
}
