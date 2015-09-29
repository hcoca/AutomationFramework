package org.rm.automation.admin.pageobjects.locations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.locators.locations.FormLocationPageLocators;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;
import org.openqa.selenium.support.FindBy;

public class FormLocationPage {
	private WebDriver driver;
	private By locationDiv;
	@FindBy(xpath=FormLocationPageLocators.infoLinkPath)
	  WebElement infoLink;
	@FindBy(xpath=FormLocationPageLocators.associatedLinkPath)
	  WebElement associatedLink;	
	@FindBy(id=FormLocationPageLocators.nameTextBoxId)
	  WebElement nameTextBox;
	@FindBy(id=FormLocationPageLocators.displayNameTextBoxId)
	  WebElement displayNameTextBox;
	@FindBy(xpath=FormLocationPageLocators.locationsListBtnPath)
	  WebElement locationsListBtn;
	@FindBy(xpath=FormLocationPageLocators.organizationListBtnPath)
	  WebElement organizationListBtn;	
	@FindBy(id=FormLocationPageLocators.descriptionTextBoxId)
	  WebElement descriptionTextBox;
	@FindBy(id=FormLocationPageLocators.parentLocationTextBoxId)
	  WebElement parentLocationTextBox;
	@FindBy(xpath=FormLocationPageLocators.saveBtnPath)
	  WebElement saveBtn;
	@FindBy(xpath=FormLocationPageLocators.cancelBtnPath)
	  WebElement cancelBtn;	
	@FindBy(xpath=FormLocationPageLocators.locationsDivPath)
	  WebElement locationsDiv;
	
	public FormLocationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterName(String name) {
		Waiters.WaitByVisibilityOfWebElement(nameTextBox, driver);
		if(nameTextBox.isDisplayed())
		{
			nameTextBox.clear();
			nameTextBox.sendKeys(name);
		}
	}
	
	public void enterDisplayName(String displayName) {
		Waiters.WaitByVisibilityOfWebElement(displayNameTextBox, driver);
		if(displayNameTextBox.isDisplayed())
		{
			displayNameTextBox.clear();
			displayNameTextBox.sendKeys(displayName);
		}
	}
	private void enterDescription(String description) {
		Waiters.WaitByVisibilityOfWebElement(descriptionTextBox, driver);
		if(descriptionTextBox.isDisplayed())
		{
			descriptionTextBox.clear();
			descriptionTextBox.sendKeys(description);
		}		
	}
	public FormLocationPage setParentLocation (String name)
	{
		Waiters.WaitByVisibilityOfWebElement(locationsListBtn, driver);
		if(locationsListBtn.isDisplayed())
		{
			locationsListBtn.click();
			Waiters.WaitByVisibilityOfWebElement(organizationListBtn, driver);
			if(organizationListBtn.isDisplayed())
			{
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
		Waiters.WaitByVisibilityOfWebElement(saveBtn, driver);
		if(saveBtn.isDisplayed())
		{
			saveBtn.click();
		}
	}
	public void clickOnCancel() {
		Waiters.WaitByVisibilityOfWebElement(cancelBtn, driver);
		if(cancelBtn.isDisplayed())
		{
			cancelBtn.click();
		}
	}
	public boolean verifyParentLocation(String nameParent)
	{
		Waiters.WaitByVisibilityOfWebElement(parentLocationTextBox, driver);
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
		Waiters.WaitByVisibilityOfWebElement(associatedLink, driver);
		if(associatedLink.isDisplayed())
		{
			associatedLink.click();
		}
		return new FormAssociateRoomPage(driver);
	}
}
