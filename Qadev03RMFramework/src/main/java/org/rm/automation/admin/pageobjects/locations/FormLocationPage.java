package org.rm.automation.admin.pageobjects.locations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.NavigationBarPage;

public class FormLocationPage {
	private WebDriver driver;
	
	private By infoLink = By.xpath("//a[contains(.,'Location Info')]");
	private By associatedLink = By.xpath("//a[contains(.,'Location Associations')]");
	private By nameTextBox = By.id("location-add-name");
	private By displayNameTextBox = By.id("location-add-display-name");
	private By parentDropDown = By.id("location-add-parent-location");
	private By descriptionTextBox = By.id("location-add-description");
	private By saveBut = By.xpath("//button[@ng-click='save()']");
	private By cancelBut = By.xpath("//button[@ng-click='cancel()']");
	
	public FormLocationPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterName(String name) {
		WebElement nameTxtBox = driver.findElement(nameTextBox);
		if(nameTxtBox.isDisplayed())
		{
			nameTxtBox.clear();
			nameTxtBox.sendKeys(name);
		}
	}
	
	public void enterDisplayName(String displayName) {
		WebElement displayNameTxtBox = driver.findElement(displayNameTextBox);
		if(displayNameTxtBox.isDisplayed())
		{
			displayNameTxtBox.clear();
			displayNameTxtBox.sendKeys(displayName);
		}
	}
	public void clickOnSave() {
		WebElement saveBtn = driver.findElement(saveBut);
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
		WebElement associateLnk = driver.findElement(associatedLink);
		if(associateLnk.isDisplayed())
			associateLnk.click();
		return new FormAssociateRoomPage(driver);
	}
}
