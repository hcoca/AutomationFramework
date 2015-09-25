package org.rm.automation.tablet.pageobjects.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.TabletPage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;

public class SearchPage extends TabletPage{

	WebDriver driver;
	WebElement element;
	
	private List<WebElement> roomListSearch;
	private List<String> roomListLogin;
	
	@FindBy(id = "advanced-search") 
	WebElement advancedButton;
	@FindBy(id = "txtRoomName") 
	WebElement roomNameTextbox;
	@FindBy(id = "txtMinimumCapacity") 
	WebElement capacityTextboxPath;
	@FindBy(xpath = "//span[@ng-show='advancedSearchOn']") 
	WebElement advancedLabel;
	@FindBy(xpath="//button[@class='room-box ng-scope']")
	WebElement roomPath;
	@FindBy(xpath="//div[@class='resource-search pull-left resources-height ng-scope']")
	WebElement resourcesIconPath;
	@FindBy(xpath="//button[@class='btn-default btn btn-clear']")
	WebElement clearButtonPath;
	@FindBy(xpath="//span[contains(.,'Search')]")
	WebElement spanSearchPageTitle;
	
	public SearchPage(WebDriver driver){
		super(driver);
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
		Waiters.WaitById("advanced-search", driver);
		advancedButton.click();
		return this;
	}
	
	/**
	 * Method to set the RoomName
	 * @param roomName
	 * @return
	 */
	public SearchPage setRoomName(String roomName){
		Waiters.WaitById("txtRoomName",driver);
		roomNameTextbox.clear();
		roomNameTextbox.sendKeys(roomName);
		return this;
	}
	
	/**
	 * @param roomName
	 * @return
	 */
	public MeetingsPage selectRoom(String roomName){
		Waiters.WaitByXPath("//button[contains(.,'"+roomName+"')]",driver);
		element = driver.findElement(By.xpath("//button[contains(.,'"+roomName+"')]"));
		element.click();		
		return new MeetingsPage(driver);		
	}		
	
	/**
	 * Method to get the list of rooms in the search page
	 */
	public void getRoomList()
	{
		roomListSearch = driver.findElements(By.xpath("//button[@class='room-box ng-scope']"));
	}
	
	/**
	 * Method to verify the rooms displayed in the login page
	 * and the search page
	 * @return
	 */
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
	
	/**
	 * Method to verify the search by room name
	 * @param roomNameExpected
	 * @return
	 */
	public SearchPage verifySearchByRoomName(String roomNameExpected)
	{
		getRoomList();
		String roomNameActual = roomListSearch.get(0).getText();
		
		Assert.assertEquals(roomNameActual, roomNameExpected);
		return this;
	}
	
	/**
	 * @return
	 */
	public SearchPage verifyAdvancedSearchIsEnabled()
	{
		String label = advancedLabel.getText();
		Assert.assertEquals("Advanced", label);
		return this;
	}
	
	public SearchPage checkIfIconRedirectsToSearchPage(){
		LogManager.info("SearchPage : Verifying than the search icon redirects to the Search page");
		Waiters.WaitByXPath("//span[contains(.,'Search')]", driver);
		Assert.assertEquals("Search",spanSearchPageTitle.getText());
		return this;
	}
}
