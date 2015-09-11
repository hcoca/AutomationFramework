package org.rm.automation.tablet.pageobjects;

import org.rm.automation.tablet.pageobjects.loginPage;;

public class BeginTest {
	
	public BeginTest(){		
		getLogin();
	}
	public static loginPage getLogin() { 
        return new loginPage();
	}
}
