package org.rm.automation.tablet.pageobjects;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.tablet.pageobjects.search.SearchPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;

//import framework.conferenceRooms.ConferenceRoomsPage;

public class TabletPage {	
	
	protected WebDriver driver;
	@FindBy(css="rm-panel-option.tile-column-option.tile-column-option-landscape > div.tile-button-schedule")
	WebElement scheduleButton;
	@FindBy(css="rm-panel-option.tile-column-option.tile-column-option-landscape > div.tile-button-search")
	WebElement searchButton;
	@FindBy(id="go-home")
	WebElement homeCommonButton;
	@FindBy(id="go-search")
	WebElement searchCommonButton;
	@FindBy(id="go-schedule")
	WebElement scheduleCommonButton;
	
	public TabletPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	public MeetingsPage selectSchedulePage()
	{
		LogManager.info("HomePage <=> TabletPage : Clicking on Schedule button");
		Waiters.WaitByCss("rm-panel-option.tile-column-option.tile-column-option-landscape > div.tile-button-schedule", driver);
		if(scheduleButton.isDisplayed())
			scheduleButton.click();
		return new MeetingsPage(driver);
	}
	
	public SearchPage selectSearchPage(){
		LogManager.info("HomePage <=> TabletPage : Clicking on Search button");
		Waiters.WaitByCss("rm-panel-option.tile-column-option.tile-column-option-landscape > div.tile-button-search", driver);
		if(searchButton.isDisplayed())
			searchButton.click();
		return new SearchPage(driver);
	}
	
	public HomePage goHomePage(){	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LogManager.info("HomePage <=> TabletPage : Clicking on Home icon");		
		Waiters.WaitById("go-home", driver);
		if(homeCommonButton.isDisplayed())
			homeCommonButton.click();
		return new HomePage(driver);
	}
	
	public SearchPage goSearchPage(){	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LogManager.info("HomePage <=> TabletPage : Clicking on Search icon");		
		Waiters.WaitById("go-search", driver);
		if(searchCommonButton.isDisplayed())
			searchCommonButton.click();
		return new SearchPage(driver);
	}
	
	public MeetingsPage goSchedulePage(){	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LogManager.info("HomePage <=> TabletPage : Clicking on Schedule icon");		
		Waiters.WaitById("go-schedule", driver);
		if(scheduleCommonButton.isDisplayed())
			scheduleCommonButton.click();
		return new MeetingsPage(driver);
	}	
}
