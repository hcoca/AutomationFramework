package org.rm.automation.admin.tests.login;
import java.util.Properties;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.ReadPropertyValues;
import org.testng.annotations.Test;

public class DisplayLogon extends TestBaseSetup{
	Properties settings = ReadPropertyValues.getPropertyFile("./Config/settings.properties");
	String server = settings.getProperty("server");
	String port = settings.getProperty("port");
	
	@Test
	public void logonIsDisplayed(){
		new LoginPage(driver)
		.logonDisplay(server, port);
	}
}
