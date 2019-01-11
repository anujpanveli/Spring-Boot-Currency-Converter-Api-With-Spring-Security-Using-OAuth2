package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Example;
import com.example.demo.model.Quotes;

@Service
public class MainService extends MappingJackson2HttpMessageConverter {
	 
	private static MainService ourInstance = new MainService();
	
	 public static MainService getInstance() {
	        return ourInstance;
	    }
	 
	    private MainService() {
	    	setPrettyPrint(true);
	    }
	
public List<Example> sendLiveRequest(BigDecimal Amount , String TargetCurrency) throws IOException{	
	
	String urlString = "http://apilayer.net/api/live?access_key=11c429b3a69c54d123428e43aaccea8f&currencies="+TargetCurrency.substring(3, 6)+"&format=1";

	RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(urlString, String.class);	    	
	    	  
    JSONObject root = new JSONObject(result); 
	
		Boolean success = null;
        String terms = null;
        String privacy = null;
        String source = null;                
        String quotes = TargetCurrency;         		
		BigDecimal amount = Amount;	
        
        List<Example> converterList = new ArrayList<>();
        
			Example emp=new Example();
			Quotes quote=new Quotes();
									
			Date timeStampDate = new Date((long)(root.getLong("timestamp")*1000)); 
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
			String formattedDate = dateFormat.format(timeStampDate);
			
				 success =  root.getBoolean("success");
				 terms =  root.getString("terms");
				 privacy =  root.getString("privacy");
				 source =  root.getString("source");                                      
				 JSONObject quotesObject = root.getJSONObject("quotes");
				 BigDecimal db = quotesObject.getBigDecimal(quotes);
				BigDecimal db1 = db.multiply(amount);
				
					emp.setSource(source);
					emp.setSuccess(success);
					emp.setTerms(terms);
					emp.setTimestamp(formattedDate);
					emp.setPrivacy(privacy);       
					quote.setQuotes(db);										
					emp.setAdditionalProperty(quotes.substring(3,6), db1);
					converterList.add(emp);					
						             
		return converterList;          
         }	    
     }