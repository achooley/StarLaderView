package com.aga.sta;

import android.util.Log;

public class Token {

	private String token;
	private String UserID;
	private String expires;
	
	public Token(String ref)
	{
		int from,to;
		
		from=ref.indexOf("=");
		to=ref.indexOf("&");
		token=ref.substring(from+1,to);
		
		
		ref=ref.substring(to+1,ref.length());
		
		from=ref.indexOf("=");
		to=ref.indexOf("&");
		expires=ref.substring(from+1,to);
		
		ref=ref.substring(to+1,ref.length());
		from=ref.indexOf("=");
		UserID=ref.substring(from+1,ref.length());
		
		Log.e("test",ref);
	}
}
