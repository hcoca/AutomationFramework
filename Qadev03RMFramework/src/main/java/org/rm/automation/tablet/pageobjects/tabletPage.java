package org.rm.automation.tablet.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.rm.automation.tablet.pageobjects.meetings.meetingsPage;
import org.rm.automation.tablet.pageobjects.search.searchPage;
import org.rm.automation.utils.BrowserManager;

//import framework.conferenceRooms.ConferenceRoomsPage;

public class tabletPage {	
	
	public meetingsPage selectSchedulePage()
	{
		BrowserManager.getInstance().getBrowser().get("http://172.20.208.105:4040/tablet/#/schedule");	
		return new meetingsPage();
	}
	
	public searchPage selectSearchPage(){
		BrowserManager.getInstance().getBrowser().get("http://172.20.208.105:4040/tablet/#/search");
		return new searchPage();
	}
		
	private boolean isElementPresent(By by) {
	    try {
	    	BrowserManager.getInstance().getBrowser().findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }
	
	


}
