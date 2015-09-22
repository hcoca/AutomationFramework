package org.rm.automation.tablet.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rm.automation.tablet.pageobjects.TabletPage;

public class HomePage extends TabletPage {
	
	@FindBy(xpath = "//span[@class='room-label ng-binding']")
	WebElement labelRoomName;
	
	public HomePage(WebDriver driver){
		super(driver);		
	}
	
	public String getRoomNamelabel() {
		return labelRoomName.getText();
	}
}
