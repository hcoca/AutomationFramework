package org.rm.automation.admin.pageobjects;

public class StartTest {
	
	public StartTest()
	{
		getLogin();
	}
	
	public static LoginPage getLogin() { 
          return new LoginPage();
	}
}
