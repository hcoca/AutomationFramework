package org.rm.automation.tablet.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.rm.automation.utils.BrowserManager;
import org.rm.automation.tablet.pageobjects.homePage;

public class loginPage {
	
	WebElement element;
	
	public loginPage setUrl(String url)
	{
		element = BrowserManager.getInstance().getBrowser().findElement(By.id("service-url-input"));
		element.sendKeys(url);		
		return this;
	}
	
	public loginPage setUsername(String name)
	{
		element = BrowserManager.getInstance().getBrowser().findElement(By.id("username"));
		element.sendKeys(name);		
		return this;
	}
	
	public loginPage setPassword(String password)
	{
		element = BrowserManager.getInstance().getBrowser().findElement(By.id("password"));
		element.sendKeys(password);		
		return this;
	}
	
	public loginPage setRoom(){

		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//div[@type='button']"));
		element.click();
		
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//section[@id='rm-account-status']/div[3]/div[2]/div/rm-select-item/div/div[2]/div/a"));
		element.click();
		
		return this;
	}
	
	public loginPage login()
	{		
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//div[@type='submit']"));
		element.click();		
		return this;	
	}
	
	public homePage access()
	{		
		element = BrowserManager.getInstance().getBrowser().findElement(By.cssSelector("button.btn.btn-primary"));
		element.click();		
		return new homePage();	
	}
}
