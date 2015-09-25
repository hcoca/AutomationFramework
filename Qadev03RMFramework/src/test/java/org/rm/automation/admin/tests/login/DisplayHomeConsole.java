package org.rm.automation.admin.tests.login;

import java.util.Properties;

import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.annotations.Test;

public class DisplayHomeConsole extends TestBaseSetup{
	Properties settings = ReadPropertyValues.getPropertyFile("./Config/settings.properties");
	String server = settings.getProperty("server");
	String port = settings.getProperty("port");
	String username = settings.getProperty("username");
	String password = settings.getProperty("password");
	
	@Test
	public void homeconsoleDisplay(){		
		new LoginPage(driver)
		.SignIn(username, password)
		.homeconsoleDisplay(username, server, port)
		.SignOut();		
	}
}
