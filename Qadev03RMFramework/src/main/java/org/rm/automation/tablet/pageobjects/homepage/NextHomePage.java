package org.rm.automation.tablet.pageobjects.homepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NextHomePage {
	@FindBy(xpath = "//span[@ng-bind='next._start | date:"+"H:mm"+"']")
	WebElement timeNextMeeting;
	
	
	public NextHomePage(){
		
	}
}