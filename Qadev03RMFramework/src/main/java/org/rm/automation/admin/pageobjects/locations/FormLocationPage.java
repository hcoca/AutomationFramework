package org.rm.automation.admin.pageobjects.locations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;
import org.openqa.selenium.support.FindBy;

public class FormLocationPage {
	private WebDriver driver;
	@FindBy(xpath="//a[contains(.,'Location Info')]")
	  WebElement infoLink;
	@FindBy(xpath="//a[contains(.,'Location Associations')]")
	  WebElement associatedLink;
	@FindBy(id="location-add-name")
	  WebElement nameTextBox;
	@FindBy(id="location-add-display-name")
	  WebElement displayNameTextBox;
	@FindBy(id="location-add-parent-location")
	  WebElement parentDropDown;
	@FindBy(id="location-add-description")
	  WebElement descriptionTextBox;
	@FindBy(xpath="//button[@ng-click='save()']")
	  WebElement saveBtn;
	@FindBy(xpath="//button[@ng-click='cancel()']")
	  WebElement cancelBtn;
	
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
	public void clickOnSave() {
		Waiters.WaitByXPath("//button[@ng-click='save()']", driver);
		if(saveBtn.isDisplayed())
		{
			LogManager.info("FormLocationPage: Click on Save button to save the changes");
			saveBtn.click();
		}
	}
	public HomePage fillFormAndSave(String name, String displayName) {
		enterName(name);
		enterDisplayName(displayName);		
		clickOnSave();		
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
