package com.example.demo.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLCheckController {
	
	private static final String SITE_UP = "Site is Up and Running";
	private static final String SITE_DOWN = "Site is Down";
	private static final String INVALID_URL = "URL is Invalid";
	
	
	@GetMapping("check")
	public String getURLStatus(@RequestParam String url) {
		String status="";
		
		try {
			//SEt HTTP and HTTPS Proxy here or from command line
			// HTTP/HTTPS Proxy
        		//System.setProperty("http.proxyHost", "185.46.212.91");
        		//System.setProperty("http.proxyPort", 9400);
        		//System.setProperty("https.proxyHost", "185.46.212.91 ");
        		//System.setProperty("https.proxyPort", 9400);
			URL urlObj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int responseCodeCategory = connection.getResponseCode()/100;
			System.out.println("responseCodeCategory: " +responseCodeCategory);
			if(responseCodeCategory == 2 || responseCodeCategory == 3) {	
				System.out.println("UP");
				status = SITE_UP;	
			}else {
				System.out.println("Down");
				status = SITE_DOWN;	
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			status = INVALID_URL;
		} catch (IOException e) {
			e.printStackTrace();
			status = SITE_DOWN;
		}
		
		return status;
		
	}

}
