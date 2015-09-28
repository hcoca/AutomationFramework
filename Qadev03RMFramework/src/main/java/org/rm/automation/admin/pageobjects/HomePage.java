package org.rm.automation.admin.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;

import junit.framework.Assert;

public class HomePage extends NavigationBarPage{	
	@FindBy(xpath="//span[contains(.,'sign out')]")
	  WebElement signOutBtn;
	
	@FindBy(xpath="//a[@class='navbar-brand']")
	WebElement rmBanner;
	
	@FindBy(xpath="//span[@class='ng-binding']")
	WebElement rmCurrentUser;

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	public LoginPage SignOut() {
		if(signOutBtn.isDisplayed()||signOutBtn.isEnabled())
		{
			LogManager.info("HomePage: Logging out of the application");
			signOutBtn.click();
		}
		return new LoginPage(driver);
	}
	
	public HomePage homeconsoleDisplay(String username, String server, String port){
		String URL = "http://"+server+":"+port+"/admin/#/admin";
		Waiters.WaitByXPath("//a[@class='navbar-brand']", driver);
		Waiters.WaitByXPath("//span[@class='ng-binding']", driver);
		Waiters.WaitByXPath("//span[contains(.,'sign out')]",driver);
		Assert.assertEquals(true, rmBanner.isDisplayed());
		Assert.assertEquals(true, rmCurrentUser.isDisplayed());
		Assert.assertEquals(true, signOutBtn.isDisplayed());
		Assert.assertEquals(username, rmCurrentUser.getText());
		Assert.assertEquals(URL, this.driver.getCurrentUrl());
		return this;
	}
	
	
}
