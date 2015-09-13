package org.rm.automation.admin.test.emailServices;

import org.testng.annotations.Test;


import junit.framework.Assert;

import java.util.Properties;

import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.emailServers.AddEmailServersPage;
import org.rm.automation.admin.pageobjects.emailServers.EmailServersPage;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.ReadPropertyValues;
import org.testng.annotations.BeforeClass;


public class addserver extends TestBaseSetup {
	
	
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
	  emailserver = objHomePage.SelectEmailServersOption();
	  addser = emailserver.clickbtnadd();
	  addser.sethostname("rmlocal.local");
	  addser.setUsrName("Administrator");
	  addser.setPassWord("Control123!");
	  emailserver = addser.saveserverbtn();
	  String textexpect = emailserver.gettextExchange();
	  Assert.assertTrue(textexpect.contains("rmlocal"));
  }
  @BeforeClass
  public void beforeTest() {
	  objLogin = new LoginPage(driver);
	  objHomePage = objLogin.SignIn("rmlocal\\administrator" , "Control123!");
  }
 
}
