package org.rm.automation.tablet.pageobjects;

import org.rm.automation.tablet.pageobjects.LoginPage;;

public class StartTest {
	
	public StartTest(){		
		getLogin();
	}
	public static LoginPage getLogin() { 
        return new LoginPage();
	}
}
