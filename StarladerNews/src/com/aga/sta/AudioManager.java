package com.aga.sta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class AudioManager {
	
	 static InputStream is = null;
	 static JSONObject jObj = null;
	 static String json = "";
	 
    Token token;
	ArrayList<Audio> AudioList;
	
	public AudioManager(Token k){
	
		this.token=k;
		AudioList=new ArrayList<Audio>();
	 
	}
 
	public JSONObject getJSONFromUrl(String url) {
		 
        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();           
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is), 8);
           
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        // try parse the string to a JSON object
        try {
        	Log.e("Json ",json);
            jObj = new JSONObject(json);
            
            
            
            
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }
	public void RefreshAudioList(){
		JSONObject JsonaAdioList=getJSONFromUrl("https://api.vk.com/method/audio.get.json?access_token=37fd0f9c6799991d3703080df9370d1bc71372637263faf67986a9d87cc37e76520fd6f");
		Audio track;
		try {
			JSONArray list=JsonaAdioList.getJSONArray("response");
		   
			 for(int i=0;i<list.length();i++)
			 {
				
				 JSONObject Track=list.getJSONObject(i);
				 String Artist=Track.getString("artist");
				 String Name=Track.getString("title");
				 String Url=Track.getString("url");
				 track=new Audio(Artist, Name, Url);
				 Log.e("Track name ", Name);
				 AudioList.add(track);
			 }
		
		
		
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
