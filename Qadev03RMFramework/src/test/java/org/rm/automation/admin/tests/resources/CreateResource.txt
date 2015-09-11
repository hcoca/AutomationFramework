package org.rm.automation.admin.tests.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.admin.pageobjects.StartTest;
import org.rm.automation.utils.BrowserManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ResourcesRequests;
import org.testng.annotations.*;

public class CreateResource {
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	String username = settings.getProperty("username");
	String password = settings.getProperty("password");
	String name = "newResource";
	String displayName = "newResource";
	
	@Test
	public void CreateResource()
	{
		StartTest.getLogin()
		.setUsername(username)
		.setPassword(password)
		.login()
		.SelectResourcesOption()
		.AddResource()
		.setName(name)
		.setDisplayName(displayName)
		.Save()
		.VerifyResourceWasCreated(name, displayName)
		.SingOut();
	}
	
	@AfterTest
	public void Postconditions()
	{
		String id = "";
		try {
		id = ResourcesRequests.getResourceId(name);
		ResourcesRequests.deleteResource(id);
		BrowserManager.getInstance().getBrowser().quit();
	
		} catch (UnsupportedOperationException | IOException e) {
		e.printStackTrace();
		}
	}
}
