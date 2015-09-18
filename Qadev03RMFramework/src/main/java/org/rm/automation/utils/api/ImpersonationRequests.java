package org.rm.automation.utils.api;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;

public class ImpersonationRequests {
	static String token;
	
	static Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	
	static Properties services = ReadPropertyValues
			.getPropertyFile("./Config/services.properties");
	
	static String serviceEp = services.getProperty("servicesremove")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
	
	static String settingsEp = settings.getProperty("URL")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));		
	
	public static void setImpersonationState(Boolean state){
		
		token = LoginRequests.getToken();
		serviceEp = serviceEp.replace("[serviceId]", ServicesRequests.getServiceId());
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPut request = new HttpPut(serviceEp);
			if(state)
				LogManager.info("ImpersonationRequests: Setting the impersonation on");
			else
				LogManager.info("ImpersonationRequests: Setting the impersonation off");
			
			
			/**
			 * Setting the headers
			 */
			
			request.addHeader("Content-type", "application/json");
			request.setHeader("Accept", "application/json");
			request.setHeader("Authorization", "jwt "+ token);
			
			JSONObject body = new JSONObject();			
			body.put("id",ServicesRequests.getServiceId().toString());
			body.put("impersonate",state);	
			
			StringEntity entity = new StringEntity(body.toString());
		    request.setEntity(entity);		    
            HttpResponse result = httpClient.execute(request);
            
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            
            System.out.println(result.getStatusLine().getStatusCode()+":"+result.getStatusLine().getReasonPhrase());
            //System.out.println(result.toString());
            System.out.println(json);
            
            saveImpersonationState();
        } 
		catch (IOException e) {			
			e.printStackTrace();
		} 
	}
	
	public static void saveImpersonationState(){
		token = LoginRequests.getToken();
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPut request = new HttpPut(settingsEp);
			LogManager.info("ImpersonationRequests: Saving the impersonation configuration");
			
			/**
			 * Setting the headers
			 */
			
			request.addHeader("Content-type", "application/json");
			request.setHeader("Accept", "application/json");
			request.setHeader("Authorization", "jwt "+ token);
			
			JSONObject body = new JSONObject();			
			body.put("authentication","credentials");	
			
			StringEntity entity = new StringEntity(body.toString());
		    request.setEntity(entity);		    
            HttpResponse result = httpClient.execute(request);
            
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            
            System.out.println(result.getStatusLine().getStatusCode()+":"+result.getStatusLine().getReasonPhrase());
            //System.out.println(result.toString());
            System.out.println(json);
        } 
		catch (IOException e) {			
			e.printStackTrace();
		} 
	}

}
