package org.rm.automation.admin.tests.login;
import java.util.Properties;
import org.testng.annotations.Test;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.ReadPropertyValues;

public class DisplayMainMenu extends TestBaseSetup{
	Properties settings = ReadPropertyValues.getPropertyFile("./Config/settings.properties");
	String server = settings.getProperty("server");
	String port = settings.getProperty("port");
	String username = settings.getProperty("username");
	String password = settings.getProperty("password");

	@Test
	public void mainMenuDisplay(){		
		new LoginPage(driver)
		.SignIn(username, password)
		.mainMenuIsDisplayed()
		.SignOut();
	}
}
