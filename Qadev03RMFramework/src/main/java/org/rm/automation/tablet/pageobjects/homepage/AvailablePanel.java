package org.rm.automation.tablet.pageobjects.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.Waiters;

public class AvailablePanel extends HomePage{

	@FindBy(xpath = "//div[@ng-class='next._type']")
	private WebElement mainBusyPanel;
	
	@FindBy(xpath = "//div[@class='tile-now free']")
	private WebElement mainFreePanel;
	
	@FindBy(xpath = "//span[@ng-bind='next._start | date:\"H:mm\"']")
	private WebElement startAvailableTimelabel; 
	
	@FindBy(xpath = "//span[@ng-bind='next._end | date:\"H:mm\"']")
	private WebElement endAvailableTimelabel;
	
	@FindBy(xpath = "//div[@ng-bind='current.remaining | timeView']")
	private WebElement availableTimeLeft;
	
	public AvailablePanel(WebDriver driver) {
		super(driver);
	}
	
	public MeetingsPage clickOnMainFreePanel(){
		Waiters.WaitByVisibilityOfWebElement(mainFreePanel, driver);
		mainFreePanel.click();
		
		return new MeetingsPage(driver);
	}
	
	public void waitForMainBusyPanel(){
		Waiters.WaitByVisibilityOfWebElement(mainBusyPanel, driver);
	}
	
	public void waitForMainFreePanel(){
		Waiters.WaitByVisibilityOfWebElement(mainFreePanel, driver);
	}
	
	public String getStartAvailableTimeLabelText(){
		Waiters.WaitByVisibilityOfWebElement(startAvailableTimelabel, driver);
		
		return startAvailableTimelabel.getText();
	}
	
	public String getEndAvailableTimeLabelText(){
		Waiters.WaitByVisibilityOfWebElement(endAvailableTimelabel, driver);
		
		return endAvailableTimelabel.getText();
	}
	
	public String getAvailableTimeLeftText(){
		Waiters.WaitByVisibilityOfWebElement(availableTimeLeft, driver);
		
		return availableTimeLeft.getText();
	}
}
