package org.rm.automation.admin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.utils.LogManager;

public class HomePage extends NavigationBarPage{	
	@FindBy(xpath="//span[contains(.,'sign out')]")
	  WebElement signOutBtn;

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
}
