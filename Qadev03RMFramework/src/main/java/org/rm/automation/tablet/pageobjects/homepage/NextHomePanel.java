package org.rm.automation.tablet.pageobjects.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.utils.Waiters;

public class NextHomePanel extends HomePage {
	
	@FindBy(xpath = "//div[@class='tile-next meeting']")
	protected WebElement mainPanel;
	
	@FindBy(xpath = "//div[@ng-bind='next._title']")
	WebElement tittlenext;
	
	@FindBy(xpath = "//div[@ng-bind='next._organizer']")
	WebElement organizerNext;
	
	@FindBy(xpath = "//div[4]/div/div/span")
	WebElement timenextStar;
	////div[4]/div/div/span
	//span[@ng-bind='next._start | date:"+"H:mm"+"']
	
	
	@FindBy(xpath = "//span[3]")
	WebElement timeNextEnd;
	//span[3]
	//span[ng-bind='next._end | date:"+"H:mm"+"']
	public NextHomePanel(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void waitForMainPanel(){
		Waiters.WaitByVisibilityOfWebElement(mainPanel, driver);
	}
	
	public String getTitleNext(){
		Waiters.WaitByVisibilityOfWebElement(tittlenext, driver);
		return tittlenext.getText();
	}
	
	public String getOrganizer() {
		Waiters.WaitByVisibilityOfWebElement(organizerNext, driver);
		return organizerNext.getText();
	}
	
	public String getTimeNextStar(){
		Waiters.WaitByVisibilityOfWebElement(timenextStar, driver);
		return timenextStar.getText();
	}
	
	public String getTimeNextEnd(){
		Waiters.WaitByVisibilityOfWebElement(timeNextEnd, driver);
		return timeNextEnd.getText();
	}

	
}