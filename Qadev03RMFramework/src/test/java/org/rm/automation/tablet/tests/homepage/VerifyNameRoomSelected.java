package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.annotations.BeforeTest;

public class VerifyNameRoomSelected {
	
	
	// room properties 
	private String roomName;

	
	// login properties
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");

	
 	@BeforeTest
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
 	}
}
