package org.rm.automation.tablet.pageobjects.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rm.automation.tablet.pageobjects.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.BrowserManager;
import org.rm.automation.utils.Waiters;

public class SearchPage extends HomePage{
	
	public SearchPage(WebDriver driver){
		super(driver);
	}

	public SearchPage selectResource(){
		Waiters.WaitByXPath("//div[2]/div/div/div/i",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//div[2]/div/div/div/i"));
		element.click();
		return this;
	}
	
	public SearchPage enableAdvancedSearch(){
		Waiters.WaitByCss("i.fa.fa-search-plus",driver);
		BrowserManager.getInstance().getBrowser().findElement(By.cssSelector("i.fa.fa-search-plus")).click();
		return this;
	}
	
	public SearchPage typeRoomName(String roomName){
		Waiters.WaitById("txtRoomName",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.id("txtRoomName"));
		element.sendKeys(roomName);
		return this;
	}
	
	public MeetingsPage selectRoom(String roomName){
		Waiters.WaitByXPath("//button[contains(.,'"+roomName+"')]",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//button[contains(.,'"+roomName+"')]"));
		element.click();		
		return new MeetingsPage(driver);		
	}		
}
