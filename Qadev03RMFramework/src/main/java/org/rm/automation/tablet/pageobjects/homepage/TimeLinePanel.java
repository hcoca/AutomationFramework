package org.rm.automation.tablet.pageobjects.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.Waiters;

public class TimeLinePanel extends HomePage	{

	@FindBy(xpath = "//div[@class='vis-timeline vis-bottom']")
	private WebElement mainPanel;
	
	public TimeLinePanel(WebDriver driver) {
		super(driver);
	}
	
	public MeetingsPage clickOnMainPanel(){
		Waiters.WaitByVisibilityOfWebElement(mainPanel, driver);
		mainPanel.click();
		
		return new MeetingsPage(driver);
	}
}
