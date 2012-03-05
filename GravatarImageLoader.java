package com.cyborg;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.widget.ImageView;

public class GravatarImageLoader extends ImageDownloader {

	public GravatarImageLoader(Context c) {
		super(c);
	}
	
	public void loadEmail(String email, ImageView imageView, int resid) {

		String url = String.format("http://www.gravatar.com/avatar/%1$s?d=identicon&s=160", md5(email));

		loadDrawable(url, imageView, resid);
	}

	/** pieced together from http://www.androidsnippets.com/create-a-md5-hash-and-dump-as-a-hex-string */
	public String md5(String s) {
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();
	        
	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++) {
	            String h = Integer.toHexString(0xFF & messageDigest[i]);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString();
	        
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}
}
