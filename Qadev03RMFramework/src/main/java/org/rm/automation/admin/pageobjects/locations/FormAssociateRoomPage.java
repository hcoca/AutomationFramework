package org.rm.automation.admin.pageobjects.locations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.locators.locations.FormAssociateRoomPageLocators;
import org.rm.automation.utils.Waiters;

public class FormAssociateRoomPage {
	
	private WebDriver driver;
	@FindBy(xpath=FormAssociateRoomPageLocators.searchTextBoxPath)
	  WebElement searchTextBox;
	@FindBy(xpath=FormAssociateRoomPageLocators.addRoomBtnPath)
	  WebElement addRoomBtn;
	@FindBy(xpath=FormAssociateRoomPageLocators.infoLinkPath)
	  WebElement infoLink;
	
	public FormAssociateRoomPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void searchConferenceRoom(String roomName) {
		Waiters.WaitByVisibilityOfWebElement(searchTextBox, driver);
		if(searchTextBox.isDisplayed())
		{
			searchTextBox.sendKeys(roomName);
		}
	}
	public void addLocationsAssociate() {
		Waiters.WaitByVisibilityOfWebElement(addRoomBtn, driver);
		if(addRoomBtn.isDisplayed())
		{
			addRoomBtn.click();	
		}
	}
	public void clickOnLocationInfoLink() {
		Waiters.WaitByVisibilityOfWebElement(infoLink, driver);
		if(infoLink.isDisplayed())
		{
			infoLink.click();	
		}
	}
	public FormLocationPage associateConferenceRoom(String roomName) {
		searchConferenceRoom(roomName);
		addLocationsAssociate();
		clickOnLocationInfoLink();
		return new FormLocationPage(driver);
	}

}
