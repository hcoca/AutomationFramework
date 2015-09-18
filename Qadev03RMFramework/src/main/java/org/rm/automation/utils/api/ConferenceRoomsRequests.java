package org.rm.automation.utils.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.rm.automation.utils.ReadPropertyValues;

public class ConferenceRoomsRequests {
	static String token;
	static Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	static Properties rooms = ReadPropertyValues
			.getPropertyFile("./Config/conferenceRooms.properties");
	
	/**
	 * API endpoints
	 */
	static String roomEp = rooms.getProperty("rooms")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
	static String roomByIdEp = rooms.getProperty("roomById")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
			
	
	/**
	 * Get all the rooms
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static ArrayList<JSONObject> getRooms() throws UnsupportedOperationException, IOException
	{
		ArrayList<JSONObject> listResponse = new ArrayList<JSONObject>();
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpGet request = new HttpGet(roomEp);
			
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
	 * Update a room
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static void putRoom(String roomId, String updatedCustomDisplayName) throws UnsupportedOperationException, IOException
	{
		String url = roomByIdEp.replace("[id]", roomId);
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPut request = new HttpPut(url);
			
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
		  	body.put("customDisplayName", updatedCustomDisplayName);
		  	
			StringEntity entity = new StringEntity(body.toString());
		    request.setEntity(entity);

            HttpResponse result = httpClient.execute(request);
        } 
		catch (IOException ex) {
        }
	}
	
	/**
	 * Updates a conference room's value that's available in its API.
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static void setValue(String roomId, String parameter, String value) throws UnsupportedOperationException, IOException
	{
		String url = roomByIdEp.replace("[id]", roomId);
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPut request = new HttpPut(url);
			
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
			switch(parameter){
				case "emailAddress": body.put("emailAddress", value);break;
				case "code": body.put("code", value);break;
				case "displayName": body.put("displayName", value);break;
				case "locationId": body.put("locationId", value);break;
				case "__v": body.put("__v", value);break;
				case "customDisplayName": body.put("customDisplayName", value);break;
				case "_id": body.put("_id", value);break;
				case "serviceId": body.put("serviceId", value);break;
				case "enabled": body.put("enabled", value);break;
				case "capacity": body.put("capacity", value);break;
			}
		  	
			//body.put("customDisplayName", updatedCustomDisplayName);
		  	
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
	public static String getRoomId(String name)
	{
		String id = "";
		ArrayList<JSONObject> list;
		try {
			list = getRooms();
			for (JSONObject object : list) {
				if(object.get("customDisplayName").toString().equals(name))
					id = object.get("_id").toString();
			}
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	
	/**
	 * Get a conference room by ID.
	 * @param roomId
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static JSONObject getRoom(String roomId) throws UnsupportedOperationException, IOException{
		JSONObject res = null;
		
		ArrayList<JSONObject> list = ConferenceRoomsRequests.getRooms();
		for(JSONObject json : list){
			if(json.get("_id").toString().equals(roomId)){
				res = json;
			}
		}
		
		return res;
	}
	
	public static ArrayList<String> getResourceIdAssociatedToRoom(String roomId) throws UnsupportedOperationException, IOException{
		ArrayList<String> res = new ArrayList<String>();
		
		JSONObject room = ConferenceRoomsRequests.getRoom(roomId);
		JSONArray resourceArray = (JSONArray)room.get("resources");
		if(resourceArray.size() > 0){
			for(int i = 0; i < resourceArray.size(); i++){
				res.add(((JSONObject)resourceArray.get(i)).get("resourceId").toString());
			}
		}
		
		return res;
	}
}
