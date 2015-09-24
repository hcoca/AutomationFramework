package org.rm.automation.tablet.pageobjects.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.tablet.pageobjects.LoginPage;

import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;

public class SearchPage {

	WebDriver driver;
	WebElement element;

	private final String roomPath = "//button[@class='room-box ng-scope']";
	private String resourcesIconPath = "//div[@class='resource-search pull-left resources-height ng-scope']";
	private final String advancedButtonPath = "advanced-search";//id
	private String advancedLabelPath = "//span[@ng-show='advancedSearchOn']";
	private final String roomNameTextboxPath = "txtRoomName";//id
	private String capacityTextboxPath = "txtMinimumCapacity";//id
	private String clearButtonPath = "//button[@class='btn-default btn btn-clear']";
	
	private List<WebElement> roomListSearch;
	private List<String> roomListLogin;
	
	@FindBy(id = advancedButtonPath) WebElement advancedButton;
	@FindBy(id = roomNameTextboxPath) WebElement roomNameTextbox;
	
	public SearchPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public SearchPage selectResource(){
		Waiters.WaitByXPath("//div[2]/div/div/div/i",driver);
		element = driver.findElement(By.xpath("//div[2]/div/div/div/i"));
		element.click();
		return this;
	}
	
	/**
	 * Method to select the advanced search button
	 * @return
	 */
	public SearchPage enableAdvancedSearch(){
		Waiters.WaitById(advancedButtonPath, driver);
		advancedButton.click();
		return this;
	}
	
	/**
	 * Method to set the RoomName
	 * @param roomName
	 * @return
	 */
	public SearchPage setRoomName(String roomName){
		Waiters.WaitById(roomNameTextboxPath,driver);
		roomNameTextbox.clear();
		roomNameTextbox.sendKeys(roomName);
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
	
	public SearchPage verifySearchByRoomName(String roomNameExpected)
	{
		getRoomList();
		String roomNameActual = roomListSearch.get(0).getText();
		
		Assert.assertEquals(roomNameActual, roomNameExpected);
		return this;
	}
}
