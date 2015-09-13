package org.rm.automation.admin.test.emailServices;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Properties;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.emailServers.EmailServersPage;
import org.rm.automation.admin.pageobjects.emailServers.RemoveServerpage;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ServicesRequests;
import org.testng.annotations.BeforeTest;


public class RemoveServer extends TestBaseSetup {
 
	
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	// properties login at RM
	String username = settings.getProperty("username");
	String password = settings.getProperty("password");

	
    LoginPage objLogin;
    HomePage objHomePage;
    EmailServersPage emailserver;
    RemoveServerpage removeserv;
  @Test
  public void f() {
	  emailserver = objHomePage.SelectEmailServersOption();
	  removeserv=emailserver.clickbtnremove();
	  emailserver = removeserv.yesdelete();
	  //Assert.assertFalse(emailserver.thereisExchange());
  }
  @BeforeTest
  public void beforeTest() {
	try {
		ServicesRequests.AddServices();
	} 
	catch (UnsupportedOperationException | IOException e) {
		e.printStackTrace();
	}
	objLogin = new LoginPage(driver);
	objHomePage = objLogin.SignIn(username, password);

  }

}