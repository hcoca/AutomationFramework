package org.rm.automation.admin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.rm.automation.utils.BrowserManager;
import org.rm.automation.utils.Waiters;

public class HomePage extends NavigationBarPage{	
	
	public LoginPage SingOut()
	{
		Waiters.WaitByCss("a > span");;
		
		WebElement singout = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.cssSelector("a > span"));
		singout.click();
		
		return new LoginPage();
	}
}
