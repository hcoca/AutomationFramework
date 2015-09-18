package org.rm.automation.admin.pageobjects.locations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;
public class RemoveLocationsPage {
	private WebDriver driver;
	@FindBy(xpath="//button[@ng-click='cancel()']")
	  WebElement cancelBtn;
	@FindBy(xpath="//button[@ng-click='removeLocations()']")
	  WebElement removeBtn;
	public RemoveLocationsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public HomePage confirmRemoveLocation() {
		Waiters.WaitByXPath("//button[@ng-click='removeLocations()']", driver);
		if(removeBtn.isDisplayed()||removeBtn.isEnabled())
		{
			LogManager.info("LocationsPage: Click on Remove button to confirm remove location");
			removeBtn.click();
		}
		return new HomePage(driver);
	}

}
