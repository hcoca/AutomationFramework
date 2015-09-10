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
import org.rm.automation.utils.ReadPropertyValues;

public class LocationsRequests {
	static String token;
	static Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	static Properties locations = ReadPropertyValues
			.getPropertyFile("./Config/locations.properties");
	
	/**
	 * API endpoints
	 */
	static String locationEp = locations.getProperty("locations")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
	static String locationByIdEp = locations.getProperty("locationById")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
			
	
	/**
	 * Get all the locations
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static ArrayList<JSONObject> getLocations() throws UnsupportedOperationException, IOException
	{
		ArrayList<JSONObject> listResponse = new ArrayList<JSONObject>();
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpGet request = new HttpGet(locationEp);
			
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
                return listResponse;

            } 
            catch (Exception e) {
            }
        } 
		catch (IOException ex) {
        }
		return listResponse;
	}

	/**
	 * Create a resource
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static void postLocation() throws UnsupportedOperationException, IOException
	{
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost request = new HttpPost(locationEp);
			
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
		  	body.put("name", "newLocation");
		  	body.put("customName", "newLocation");		  	
		  	
			StringEntity entity = new StringEntity(body.toString());
		    request.setEntity(entity);

            HttpResponse result = httpClient.execute(request);

        } 
		catch (IOException ex) {
        }
	}
	
	public static void deleteLocation(String locationId) throws UnsupportedOperationException, IOException
	{
		String url = locationByIdEp.replace("[id]", locationId);
		token = LoginRequests.getToken();
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpDelete request = new HttpDelete(url);

			request.setHeader("Content-type", "application/json");
            request.setHeader("Accept", "application/json");
			request.setHeader("Authorization", "jwt "+ token);

			HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
        } 
		catch (IOException ex) {
        }
	}
	
	public static String getLocationId(String name)
	{
		String id = "";
		ArrayList<JSONObject> list;
		try {
			list = getLocations();
			for (JSONObject object : list) {
				if(object.get("name").toString().equals(name))
					id = object.get("_id").toString();
			}
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
		return id;
	}
}
