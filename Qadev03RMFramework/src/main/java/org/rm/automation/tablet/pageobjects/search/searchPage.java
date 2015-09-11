package org.rm.automation.tablet.pageobjects.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.rm.automation.tablet.pageobjects.meetings.meetingsPage;
import org.rm.automation.utils.BrowserManager;
import org.rm.automation.utils.Waiters;


public class searchPage {

	public searchPage selectResource(){
		Waiters.WaitByXPath("//div[2]/div/div/div/i");
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//div[2]/div/div/div/i"));
		element.click();
		return this;
	}
	
	public searchPage enableAdvancedSearch(){
		Waiters.WaitByCss("i.fa.fa-search-plus");
		BrowserManager.getInstance().getBrowser().findElement(By.cssSelector("i.fa.fa-search-plus")).click();
		return this;
	}
	
	public searchPage typeRoomName(String roomName){
		Waiters.WaitById("txtRoomName");
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.id("txtRoomName"));
		element.sendKeys(roomName);
		return this;
	}
	
	public meetingsPage selectRoom(String roomName){
		Waiters.WaitByXPath("//button[contains(.,'"+roomName+"')]");
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//button[contains(.,'"+roomName+"')]"));
		element.click();		
		return new meetingsPage();		
	}		
}
