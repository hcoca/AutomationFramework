package org.rm.automation.utils.api;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;

public class LoginRequests {
	
	static Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	static Properties login = ReadPropertyValues
			.getPropertyFile("./Config/login.properties");
	
	static String loginEp = login.getProperty("login")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
	
	/**
	 * Method to get the token
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static String getToken()
	{
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost request = new HttpPost(loginEp);
			
			request.addHeader("Content-type", "application/json");
			request.setHeader("Accept", "application/json");
			  
			JSONObject body=new JSONObject();
			body.put("username", settings.getProperty("username"));
			body.put("password", settings.getProperty("password"));
			body.put("authentication", settings.getProperty("authentication"));
			
			StringEntity entity = new StringEntity(body.toString());
		    request.setEntity(entity);
			
            HttpResponse result = httpClient.execute(request);
            
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            
            try {
                JSONParser parser = new JSONParser();
                Object resultObject = parser.parse(json);
                
                JSONObject obj =(JSONObject)resultObject;
                return obj.get("token").toString();

            } catch (Exception e) {
        		LogManager.error("ResourceRequests: Error parsing the json response");
            }

        } catch (IOException ex) {
			LogManager.error("ResourceRequests: Error stablishing the HTTP protocol");
        }
		return null;
	}
}
