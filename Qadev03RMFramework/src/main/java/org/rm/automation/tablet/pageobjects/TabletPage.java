package org.rm.automation.tablet.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.tablet.pageobjects.search.SearchPage;
import org.rm.automation.utils.LogManager;

//import framework.conferenceRooms.ConferenceRoomsPage;

public class TabletPage {	
	protected WebDriver driver;
	private By scheduleButton = By.cssSelector("rm-panel-option.tile-column-option.tile-column-option-landscape > div.tile-button-schedule");	
	private By searchButton = By.cssSelector("rm-panel-option.tile-column-option.tile-column-option-landscape > div.tile-button-search");
	private By homeButton = By.id("go-home");
	
	public TabletPage(WebDriver driver) {
		this.driver = driver;
	}

	public MeetingsPage selectSchedulePage()
	{
		LogManager.info("HomePage <=> TabletPage : Clicking on Schedule button");
		WebElement scheduleBtn = driver.findElement(scheduleButton);
		if(scheduleBtn.isDisplayed())
			scheduleBtn.click();
		return new MeetingsPage(driver);
	}
	
	public SearchPage selectSearchPage(){
		LogManager.info("HomePage <=> TabletPage : Clicking on Search button");
		WebElement searchBtn = driver.findElement(searchButton);
		if(searchBtn.isDisplayed())
			searchBtn.click();
		return new SearchPage(driver);
	}
	
	public HomePage goHomePage(){
		LogManager.info("HomePage <=> TabletPage : Clicking on Home icon");
		WebElement homeBtn = driver.findElement(homeButton);
		if(homeBtn.isDisplayed())
			homeBtn.click();
		return new HomePage(driver);
	}
}
