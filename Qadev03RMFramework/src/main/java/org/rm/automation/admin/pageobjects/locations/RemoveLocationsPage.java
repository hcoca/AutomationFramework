package org.rm.automation.admin.pageobjects.locations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;
public class RemoveLocationsPage {
	private WebDriver driver;
	private By confRoomColumn;
	/*@FindBy(xpath="//button[@ng-click='cancel()']")
	  WebElement cancelBtn;*/
	@FindBy(xpath="//button[@ng-click='removeLocations()']")
	  WebElement removeBtn;
	
	
	public RemoveLocationsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	private boolean verifyConferenceAssociated(String roomName) {
		LogManager.info("RemoveLocationsPage: Verifying conference room associated");
		confRoomColumn = By.xpath("//span[@class='ng-binding' and contains(.,'"+roomName+"')]");
		WebElement element = driver.findElement(confRoomColumn);
		if(element.isDisplayed())
		{
			return true;
		}
		else
			return false;
	}
	public LocationsPage confirmRemoveLocation(String roomName) {
		Assert.assertTrue(verifyConferenceAssociated(roomName));
		Waiters.WaitByXPath("//button[@ng-click='removeLocations()']", driver);
		if(removeBtn.isDisplayed()||removeBtn.isEnabled())
		{
			LogManager.info("RemoveLocationsPage: Click on Remove button to confirm remove location");
			removeBtn.click();
		}
		return new LocationsPage(driver);
	}
	
	public LocationsPage confirmRemoveLocation() {
		Waiters.WaitByXPath("//button[@ng-click='removeLocations()']", driver);
		if(removeBtn.isDisplayed()||removeBtn.isEnabled())
		{
			LogManager.info("RemoveLocationsPage: Click on Remove button to confirm remove location");
			removeBtn.click();
		}
		return new LocationsPage(driver);
	}

}
