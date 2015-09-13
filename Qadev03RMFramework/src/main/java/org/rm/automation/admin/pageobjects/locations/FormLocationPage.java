package org.rm.automation.admin.pageobjects.locations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.pageobjects.HomePage;
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
		if(nameTextBox.isDisplayed())
		{
			nameTextBox.clear();
			nameTextBox.sendKeys(name);
		}
	}
	
	public void enterDisplayName(String displayName) {
		if(displayNameTextBox.isDisplayed())
		{
			displayNameTextBox.clear();
			displayNameTextBox.sendKeys(displayName);
		}
	}
	public void clickOnSave() {
		if(saveBtn.isDisplayed())
			saveBtn.click();
	}
	public HomePage fillFormAndSave(String name, String displayName) {
		enterName(name);
		enterDisplayName(displayName);		
		clickOnSave();		
		return new HomePage(driver);
	}
	
	public FormAssociateRoomPage gotoLocationsAssociations(){
		if(associatedLink.isDisplayed())
			associatedLink.click();
		return new FormAssociateRoomPage(driver);
	}
}
