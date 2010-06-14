package net.hope.mobile;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import android.content.Context;

public class JSInterface {
	public Context context;
	public String getScheduleJSON() {
		// try loading and reading file
		FileInputStream fIn = null; 
        InputStreamReader isr = null;
        char[] inputBuffer = new char[255]; 
        String data = null;
        try{
        	fIn = context.openFileInput("schedule.json");
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
        return data;
	}
	
	public String getTestThing() {
		return "yada";
	}
}
