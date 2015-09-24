package org.rm.automation.utils.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;

public class ResourcesRequests {
	static String token;
	static Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	static Properties resources = ReadPropertyValues
			.getPropertyFile("./Config/resources.properties");
	
	/**
	 * API endpoints
	 */
	static String resourceEp = resources.getProperty("resources")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
	static String resourceByIdEp = resources.getProperty("resourceById")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
	
	/**
	 * Get all the resources
	 */
	public static ArrayList<JSONObject> getResources()
	{
		ArrayList<JSONObject> listResponse = new ArrayList<JSONObject>();
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpGet request = new HttpGet(resourceEp);
			
            request.setHeader("Content-type", "application/json");
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
            } 
            catch (Exception e) {
        		LogManager.error("ResourceRequests: Error parsing the json response");
            }
        } 
		catch (IOException ex) {
			LogManager.error("ResourceRequests: Error stablishing the HTTP protocol");
        }
		return listResponse;
	}

	/**
	 * Create a resource
	 */
	public static void postResource(String name, String custom, String icon, String description)
	{
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost request = new HttpPost(resourceEp);
			
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
		  	body.put("name", name);
		  	body.put("customName", custom);
		  	body.put("fontIcon", icon);
		  	body.put("from", "");
		  	body.put("description", description);
		  	
			StringEntity entity = new StringEntity(body.toString());
		    request.setEntity(entity);

            httpClient.execute(request);
        } 
		catch (IOException ex) {
			LogManager.error("ResourceRequests: Error stablishing the HTTP protocol");
        }
	}
	
	/**
	 * Method to delete a resource
	 * @param resourceId
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static void deleteResource(String resourceId)
	{
		String url = resourceByIdEp.replace("[id]", resourceId);
		token = LoginRequests.getToken();
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpDelete request = new HttpDelete(url);

			request.setHeader("Content-type", "application/json");
            request.setHeader("Accept", "application/json");
			request.setHeader("Authorization", "jwt "+ token);

			httpClient.execute(request);
        } 
		catch (IOException ex) {
			LogManager.error("ResourceRequests: Error stablishing the HTTP protocol");
       }
	}
	
	/**
	 * Get a resource's id providing the name of the resource
	 * @param name
	 * @return id
	 */
	public static String getResourceId(String name)
	{
		String id = "";
		ArrayList<JSONObject> list;
		
			list = getResources();
			for (JSONObject object : list) {
				if(object.get("name").toString().equals(name))
					id = object.get("_id").toString();
			}
		return id;
	}
	
	/**
	 * Get a resource providing the id
	 * @param id
	 * @return
	 */
	public static JSONObject getResource(String id)
	{
		ArrayList<JSONObject> list;
		
			list = getResources();
			for (JSONObject object : list) {
				if(object.get("_id").toString().equals(id))
					return object;
			}
		return null;
	}
}
