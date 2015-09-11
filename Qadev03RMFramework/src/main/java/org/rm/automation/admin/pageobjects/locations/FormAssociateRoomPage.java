package org.rm.automation.admin.pageobjects.locations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
			searchTextBox.sendKeys(roomName);
	}
	public void addLocationsAssociate() {
		if(addRoomBtn.isDisplayed())
			addRoomBtn.click();		
	}
	public FormLocationPage associateConferenceRoom(String roomName) {
		searchConferenceRoom(roomName);
		addLocationsAssociate();
		if(infoLink.isDisplayed())
			infoLink.click();		
		return new FormLocationPage(driver);
	}

}
