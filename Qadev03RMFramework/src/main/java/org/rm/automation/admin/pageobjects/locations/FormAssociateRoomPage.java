package org.rm.automation.admin.pageobjects.locations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.utils.LogManager;

public class FormAssociateRoomPage {
	private WebDriver driver;
	@FindBy(xpath="//input[@ng-model='searchDisplayName']")
	  WebElement searchTextBox;
	@FindBy(xpath="//button[@ng-click='assignRoom(room)']")
	  WebElement addRoomBtn;
	@FindBy(xpath="//a[contains(.,'Location Info')]")
	  WebElement infoLink;
	
	public FormAssociateRoomPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void searchConferenceRoom(String roomName) {
		if(searchTextBox.isDisplayed())
		{
			LogManager.info("FormAssociateRoomPage: Searching a conference room");
			searchTextBox.sendKeys(roomName);
		}
	}
	public void addLocationsAssociate() {
		if(addRoomBtn.isDisplayed())
		{
			LogManager.info("FormAssociateRoomPage: Associating the conference room with the location");
			addRoomBtn.click();	
		}
	}
	public FormLocationPage associateConferenceRoom(String roomName) {
		searchConferenceRoom(roomName);
		addLocationsAssociate();
		if(infoLink.isDisplayed())
		{
			LogManager.info("FormAssociateRoomPage: Selecting Location Info option");
			infoLink.click();	
		}
		return new FormLocationPage(driver);
	}

}
