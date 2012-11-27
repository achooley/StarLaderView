package com.AlisaSoft.starladernews;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

   WebView web; 	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        web=(WebView)findViewById(R.id.WebLogin);
        CookieManager manager=CookieManager.getInstance();
        manager.removeAllCookie();
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("https://oauth.vk.com/authorize?client_id=2827360&scope=audio&redirect_uri=http://oauth.vk.com/blank.html&display=wap&response_type=token");
        web.setWebViewClient(new WebViewClient(){
        	@Override
        	public void onPageFinished(WebView view, String url){
        		Log.e("URL ",url);
        		super.onPageFinished(view, url);
        	}        	
        });
         
       
        
        
        		      
      
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
        //Code changes
        //add new code
    }

 
}
