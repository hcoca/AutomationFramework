package org.rm.automation.utils.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.rm.automation.utils.ReadPropertyValues;

public class ServicesRequests {

	static String token;
	static Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	static Properties services = ReadPropertyValues
			.getPropertyFile("./Config/services.properties");
	
	/**
	 * API endpoints
	 */
	static String serviceEp = services.getProperty("services")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
	
	/**
	 * Get all the resources
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static ArrayList<JSONObject> getServices() throws UnsupportedOperationException, IOException
	{
		ArrayList<JSONObject> listResponse = new ArrayList<JSONObject>();
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpGet request = new HttpGet(serviceEp);
			
			token = LoginRequests.getToken();
			
            request.setHeader("Content-type", "application/json");
            request.setHeader("Authorization", "jwt "+ token);
            HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            
            try {
                JSONParser parser = new JSONParser();
                Object resultObject = parser.parse(json);

                if (resultObject instanceof JSONArray) {
                    JSONArray array=(JSONArray)resultObject;
                    
                    for (Object object : array) {
                        JSONObject obj =(JSONObject)object;
                        listResponse.add(obj);
                    }

                }else if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;                    
                    listResponse.add(obj);
                }
                return listResponse;

            } 
            catch (Exception e) {
            }
        } 
		catch (IOException ex) {
        }
		return listResponse;
	}
	
	public static String getServiceId()
	{
		try {
			ArrayList<JSONObject> list = getServices();
			String id = list.get(0).get("_id").toString();
			return id;
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
