package org.rm.automation.admin.pageobjects.locations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormAssociateRoomPage {
	private WebDriver driver;
	private By searchTextBox = By.xpath("//input[@ng-model='searchDisplayName']");
	private By addRoomBtn = By.xpath("//button[@ng-click='assignRoom(room)']");
	private By infoLink = By.xpath("//a[contains(.,'Location Info')]");
	
	public FormAssociateRoomPage(WebDriver driver) {
		this.driver=driver;
	}
	public void searchConferenceRoom(String roomName) {
		WebElement searchTxtBox = driver.findElement(searchTextBox);
		if(searchTxtBox.isDisplayed())
			searchTxtBox.sendKeys(roomName);
	}
	public void addLocationsAssociate() {
		WebElement addRoomBut = driver.findElement(addRoomBtn);
		if(addRoomBut.isDisplayed())
			addRoomBut.click();		
	}
	public FormLocationPage associateConferenceRoom(String roomName) {
		searchConferenceRoom(roomName);
		addLocationsAssociate();
		WebElement infoLnk = driver.findElement(infoLink);
		if(infoLnk.isDisplayed())
			infoLnk.click();		
		return new FormLocationPage(driver);
	}

}
