package org.rm.automation.admin.test.emailServices;

import org.testng.annotations.Test;

import junit.framework.Assert;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.emailServers.EmailServersPage;
import org.rm.automation.admin.pageobjects.emailServers.RemoveServerpage;
import org.rm.automation.base.TestBaseSetup;
import org.testng.annotations.BeforeClass;


public class RemoveServer extends TestBaseSetup {
 
    LoginPage objLogin;
    HomePage objHomePage;
    EmailServersPage emailserver;
    RemoveServerpage removeserv;
  @Test
  public void f() {
	  emailserver = objHomePage.SelectEmailServersOption();
	  removeserv=emailserver.clickbtnremove();
	  emailserver = removeserv.yesdelete();
	  Assert.assertFalse(emailserver.thereisExchange());
  }
  @BeforeClass
  public void beforeTest() {
	objLogin = new LoginPage(driver);
	objHomePage = objLogin.SignIn("rmlocal\\administrator" , "Control123!");
  }

}
