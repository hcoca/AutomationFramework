package org.rm.automation.admin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.rm.automation.admin.pageobjects.LoginPage;

public class HomePage extends NavigationBarPage{	
	private By signOutBtn = By.xpath("//span[contains(.,'sign out')]");

	/*---------------------------------------*/
	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	public LoginPage SignOut() {
		WebElement element=driver.findElement(signOutBtn);
		if(element.isDisplayed()||element.isEnabled())
			element.click();
		return new LoginPage(driver);
	}
}
