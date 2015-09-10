package org.rm.automation.admin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.rm.automation.utils.BrowserManager;

public class LoginPage {	
	
	public LoginPage setUsername(String name)
	{
		WebElement username = BrowserManager.getInstance().getBrowser().findElement(By.id("loginUsername"));
		username.sendKeys(name);
				
		return this;
	}
	
	public LoginPage setPassword(String password)
	{
		WebElement pass = BrowserManager.getInstance().getBrowser().findElement(By.id("loginPassword"));
		pass.sendKeys(password);
		
		return this;
	}
	
	public HomePage login()
	{
		//driver.findElement(By.xpath("//button[@type='submit']")).click();
		WebElement login = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//button[@type='submit']"));
		login.click();
		
		return new HomePage();
	}
}
