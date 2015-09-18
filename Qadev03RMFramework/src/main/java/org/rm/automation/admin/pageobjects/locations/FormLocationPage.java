package org.rm.automation.admin.pageobjects.locations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;
import org.openqa.selenium.support.FindBy;

public class FormLocationPage {
	private WebDriver driver;
	private By locationDiv;
	@FindBy(xpath="//a[contains(.,'Location Info')]")
	  WebElement infoLink;
	@FindBy(xpath="//a[contains(.,'Location Associations')]")
	  WebElement associatedLink;
	@FindBy(id="location-add-name")
	  WebElement nameTextBox;
	@FindBy(id="location-add-display-name")
	  WebElement displayNameTextBox;
	@FindBy(xpath="//button[@ng-click='toggleTree()']")
	  WebElement locationsListBtn;
	@FindBy(xpath="//span[@ng-click='collapse($event)']")
	  WebElement organizationListBtn;	
	@FindBy(id="location-add-description")
	  WebElement descriptionTextBox;
	@FindBy(id="location-add-parent-location")
	  WebElement parentLocationTextBox;
	@FindBy(xpath="//button[@ng-click='save()']")
	  WebElement saveBtn;
	@FindBy(xpath="//button[@ng-click='cancel()']")
	  WebElement cancelBtn;
	
	@FindBy(xpath="//div[contains(.,'aaa')]")
	  WebElement locationsDiv;
	
	public FormLocationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterName(String name) {
		Waiters.WaitById("location-add-name", driver);
		if(nameTextBox.isDisplayed())
		{
			LogManager.info("FormLocationPage: Entering the name of the location");
			nameTextBox.clear();
			nameTextBox.sendKeys(name);
		}
	}
	
	public void enterDisplayName(String displayName) {
		Waiters.WaitById("location-add-display-name", driver);
		if(displayNameTextBox.isDisplayed())
		{
			LogManager.info("FormLocationPage: Entering the Display Name of the location");
			displayNameTextBox.clear();
			displayNameTextBox.sendKeys(displayName);
		}
	}
	private void enterDescription(String description) {
		Waiters.WaitById("location-add-description", driver);
		if(descriptionTextBox.isDisplayed())
		{
			LogManager.info("FormLocationPage: Entering the description of the location");
			descriptionTextBox.clear();
			descriptionTextBox.sendKeys(description);
		}		
	}
	public FormLocationPage setParentLocation (String name)
	{
		Waiters.WaitByXPath("//button[@ng-click='toggleTree()']", driver);
		if(locationsListBtn.isDisplayed())
		{
			LogManager.info("FormLocationPage: Displaying dropDownList locations ");
			locationsListBtn.click();
			Waiters.WaitByXPath("//span[@ng-click='collapse($event)']", driver);
			if(organizationListBtn.isDisplayed())
			{
				LogManager.info("FormLocationPage: Displaying locations inside organization");
				organizationListBtn.click();
				Waiters.WaitByXPath("//b[contains(.,'"+name+"')]", driver);
				locationDiv = By.xpath("//b[contains(.,'"+name+"')]");
				WebElement element = driver.findElement(locationDiv);
				if(element.isDisplayed())
				{
					element.click();
				}
			}
		}
		return this;
	}
	public void clickOnSave() {
		Waiters.WaitByXPath("//button[@ng-click='save()']", driver);
		if(saveBtn.isDisplayed())
		{
			LogManager.info("FormLocationPage: Click on Save button to save the changes");
			saveBtn.click();
		}
	}
	public void clickOnCancel() {
		Waiters.WaitByXPath("//button[@ng-click='cancel()']", driver);
		if(cancelBtn.isDisplayed())
		{
			LogManager.info("FormLocationPage: Click on cancel button");
			cancelBtn.click();
		}
	}
	public boolean verifyParentLocation(String nameParent)
	{
		LogManager.info("FormLocationPage: Checking location parent");
		Waiters.WaitById("location-add-parent-location", driver);
		String actualParent = parentLocationTextBox.getText();
		return actualParent.equals("organization/"+nameParent);		
	}
	public HomePage fillFormAndSave(String name, String displayName) {
		enterName(name);
		enterDisplayName(displayName);		
		clickOnSave();		
		return new HomePage(driver);
	}
	public HomePage fillFormAndSave(String name, String displayName, String description) {
		enterName(name);
		enterDisplayName(displayName);	
		enterDescription(description);
		clickOnSave();		
		return new HomePage(driver);
	}	
	public HomePage fillFormAndSave() {	
		clickOnSave();		
		return new HomePage(driver);
	}
	public HomePage verifyCorrectParent(String nameParent) {
		Assert.assertTrue(verifyParentLocation(nameParent), "Location Parent doesn't match");	
		clickOnCancel();		
		return new HomePage(driver);
	}
	public FormAssociateRoomPage gotoLocationsAssociations(){
		Waiters.WaitByXPath("//a[contains(.,'Location Associations')]", driver);
		if(associatedLink.isDisplayed())
		{
			LogManager.info("FormLocationPage: Selecting Location Associations option");
			associatedLink.click();
		}
		return new FormAssociateRoomPage(driver);
	}
}
