package org.rm.automation.admin.test.emailServices;

import org.testng.annotations.Test;
import junit.framework.Assert;

import java.io.IOException;
import java.util.Properties;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.emailServers.AddEmailServersPage;
import org.rm.automation.admin.pageobjects.emailServers.EmailServersPage;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ServicesRequests;
import org.testng.annotations.BeforeTest;


public class AddServer extends TestBaseSetup {
	
	
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	// properties login at RM
	String username = settings.getProperty("username");
	String password = settings.getProperty("password");
	// properties can added the email server
	String userES = settings.getProperty("userES");
	String pwES = settings.getProperty("passwordES");
	String hostname = settings.getProperty("hostnameDomain");
	
	
    LoginPage objLogin; 
    HomePage objHomePage;
    AddEmailServersPage addser;
    EmailServersPage emailserver;
  @Test
  public void f() {
	  LogManager.info("AddServer Test: begin Add server test cases");
	  objLogin = new LoginPage(driver);
	  objHomePage = objLogin.SignIn(username , password);
	  emailserver = objHomePage.SelectEmailServersOption();
	  addser = emailserver.clickbtnadd();
	  addser.sethostname(hostname);
	  addser.setUsrName(userES);
	  addser.setPassWord(pwES);
	  emailserver = addser.saveserverbtn();
	  String textexpect = emailserver.gettextExchange();
	  Assert.assertTrue(textexpect.contains(hostname));
  }
  @BeforeTest
  public void beforeTest() {
	try {
		LogManager.info("AddServer Test: Execute before test");
		ServicesRequests.RemoveService();
	} 
	catch (UnsupportedOperationException | IOException e) {
		e.printStackTrace();
		LogManager.warn("AddServer Test: before test failet");
	}
	  
  }
 
}
