package org.rm.automation.utils.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.rm.automation.utils.ReadPropertyValues;

public class EmailServices {

	static String token;
	static Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	static Properties services = ReadPropertyValues
			.getPropertyFile("./Config/services.properties");
	
	/**
	 * API endpoints
	 */
	static String servicespost = services.getProperty("servicespost")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
	static String ServiceEPRemove = services.getProperty("servicesremove")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
			
	static String Serverurs = settings.getProperty("userES");
	static String ServerPW = settings.getProperty("passwordES");
	static String ServerDom = settings.getProperty("hostnameDomain");
	

	/**
	 * Update a room
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static void AddServices() throws UnsupportedOperationException, IOException
	{
		
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost request = new HttpPost(servicespost);
			
			token = LoginRequests.getToken();
			
			/**
			 * Setting the headers
			 */
			request.addHeader("Content-type", "application/json");
			request.setHeader("Accept", "application/json");
			request.setHeader("Authorization", "jwt "+ token);
			
			/**
			 * Request's body
			 */
			JSONObject body = new JSONObject();
			
		  	body.put("username", Serverurs);
		  	body.put("password", ServerPW);
		  	body.put("hostname", ServerDom);

		  	
			StringEntity entity = new StringEntity(body.toString());
		    request.setEntity(entity);

            HttpResponse result = httpClient.execute(request);
        } 
		catch (IOException ex) {
        }
	}
	
	/**
	 * Get a room's id providing the customDisplayName of the room
	 * @param name
	 * @return
	 */
	
}
