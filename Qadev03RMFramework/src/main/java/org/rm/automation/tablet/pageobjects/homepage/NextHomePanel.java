package org.rm.automation.tablet.pageobjects.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rm.automation.tablet.locators.homepage.NextHomePanelLocators;
import org.rm.automation.utils.Waiters;

public class NextHomePanel extends HomePage {
	
	@FindBy(xpath = NextHomePanelLocators.MainPanelLocator)
	protected WebElement mainPanel;
	
	@FindBy(xpath = NextHomePanelLocators.TitleNextLocator)
	protected WebElement tittlenext;
	
	@FindBy(xpath = NextHomePanelLocators.OrganizerNextLocator)
	protected WebElement organizerNext;
	
	@FindBy(xpath = NextHomePanelLocators.TimeNextStartLocator)
	protected WebElement timenextStar;
	
	@FindBy(xpath = NextHomePanelLocators.TimeNextEndLocator)
	protected WebElement timeNextEnd;

	public NextHomePanel(WebDriver driver){
		super(driver);
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