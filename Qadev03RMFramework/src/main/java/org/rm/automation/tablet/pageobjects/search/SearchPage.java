package org.rm.automation.tablet.pageobjects.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;

public class SearchPage{
	
	WebDriver driver;
	WebElement element;
	
	private final String roomPath = "//button[@class='room-box ng-scope']";
	
	private List<WebElement> roomListSearch;
	private List<String> roomListLogin;
	
	
	//room-box ng-scope
	public SearchPage(WebDriver driver){
		this.driver = driver;
	}

	public SearchPage selectResource(){
		Waiters.WaitByXPath("//div[2]/div/div/div/i",driver);
		element = driver.findElement(By.xpath("//div[2]/div/div/div/i"));
		element.click();
		return this;
	}
	
	public SearchPage enableAdvancedSearch(){
		Waiters.WaitByCss("i.fa.fa-search-plus",driver);
		driver.findElement(By.cssSelector("i.fa.fa-search-plus")).click();
		return this;
	}
	
	public SearchPage typeRoomName(String roomName){
		Waiters.WaitById("txtRoomName",driver);
		element = driver.findElement(By.id("txtRoomName"));
		element.sendKeys(roomName);
		return this;
	}
	
	public MeetingsPage selectRoom(String roomName){
		Waiters.WaitByXPath("//button[contains(.,'"+roomName+"')]",driver);
		element = driver.findElement(By.xpath("//button[contains(.,'"+roomName+"')]"));
		element.click();		
		return new MeetingsPage(driver);		
	}		
	
	public void getRoomList()
	{
		roomListSearch = driver.findElements(By.xpath(roomPath));
	}
	
	public SearchPage verifyRoomsDisplayed()
	{
		getRoomList();
		roomListLogin = LoginPage.getRoomList();
		String roomLogin, roomSearch;
		
		Assert.assertEquals(roomListLogin.size(), roomListSearch.size());
		
		for(int i = 0; i < roomListSearch.size(); i++)
		{
			roomLogin = roomListLogin.get(i);
			roomSearch = roomListSearch.get(i).getText();
			
			Assert.assertEquals(roomLogin, roomSearch);
		}
		return this;
	}
}
