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

import bsh.util.Httpd;

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

	static String servicespost = services.getProperty("servicespost")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
	static String ServiceEPRemove = services.getProperty("servicesremove")
			.replace("[server]", settings.getProperty("server"))
			.replace("[port]", settings.getProperty("port"));
	/**
	 * vars add service
	 */
			
	static String Serverurs = settings.getProperty("userES");
	static String ServerPW = settings.getProperty("passwordES");
	static String ServerDom = settings.getProperty("hostnameDomain");
	/**
	 * Get all the services
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
	
	/**
	 * Get the service's id
	 * @return
	 */
	public static String getServiceId()
	{
		try {
			ArrayList<JSONObject> list = getServices();
			String id = list.get(0).get("_id").toString();
			LogManager.info("SERVICEREQUESTS:return service ID ");
			return id;
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
			LogManager.error("SERVICEREQUESTS: there aren't services return null");
		}		
		return null;
	}
	


	/**
	 * post a service
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static void AddServices() throws UnsupportedOperationException, IOException
	{
		if(getServiceId()==null){
			try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
				HttpPost request = new HttpPost(servicespost);
				LogManager.info("SERVICEREQUESTS: add service");
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
				LogManager.error("SERVICEREQUESTS: error when try add a service");
	        }
		}else{
			LogManager.warn("SERVICEREQUESTS: the service no is added because there is a service added");
		}
	}
	
	public static void RemoveService() throws UnsupportedOperationException, IOException
	{
		if(getServiceId()!=null){
			try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
				HttpDelete request = new HttpDelete(ServiceEPRemove.replace("[serviceId]", getServiceId()));
				LogManager.info("SERVICEREQUESTS: delete the service by API");
				token = LoginRequests.getToken();
				/**
				 * Setting the headers
				 */
				request.addHeader("Content-type", "application/json");
				request.setHeader("Accept", "application/json");
				request.setHeader("Authorization", "jwt "+ token);
	
	            HttpResponse result = httpClient.execute(request);
	        } 
			catch (IOException ex) {
				LogManager.error("SERVICEREQUESTS: delete the service by API FAIL");
	        }
		}else{
			LogManager.warn("SERVICEREQUESTS: no there aren't any service for remove");
		}
	}
	
	
	
}
