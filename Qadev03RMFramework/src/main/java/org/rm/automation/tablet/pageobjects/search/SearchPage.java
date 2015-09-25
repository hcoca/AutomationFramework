package org.rm.automation.tablet.pageobjects.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.tablet.pageobjects.LoginPage;

import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;

public class SearchPage {

	WebDriver driver;
	WebElement element;

	private final String roomPath = "//button[@class='room-box ng-scope']";
	private final String advancedButtonPath = "advanced-search";//id
	private final String advancedLabelPath = "//span[@ng-show='advancedSearchOn']";
	private final String roomNameTextboxPath = "txtRoomName";//id
	private final String capacityTextboxPath = "txtMinimumCapacity";//id
	private final String locationComboBoxPath = "listLocation";//id
	private final String clearButtonPath = "//button[@class='btn-default btn btn-clear']";
	private final String meetingTitlePath = "//div[@class='item-title']";
	private final String scheduleTablePath = "//div[@class='vis-foreground']";
	private final String resourcesListPath = "//div[@class='resource-search pull-left resources-height ng-scope']";
	private final String resourceButtonPath = ".//div[@class='text-center resource-button pull-left']";
	private final String notFoundMessagePath = "//div[@class='well']";
	private List<WebElement> roomListSearch;
	private List<String> roomListLogin;
	
	@FindBy(id = advancedButtonPath) WebElement advancedButton;
	@FindBy(id = roomNameTextboxPath) WebElement roomNameTextbox;
	@FindBy(id = capacityTextboxPath) WebElement capacityTextbox;
	@FindBy(id = locationComboBoxPath) WebElement locationComboBox;
	@FindBy(xpath = advancedLabelPath) WebElement advancedLabel;
	@FindBy(xpath = clearButtonPath) WebElement clearButton;
	@FindBy(xpath = scheduleTablePath) WebElement scheduleTable;
	@FindBy(xpath = resourcesListPath) List<WebElement> resourcesList;
	@FindBy(xpath = notFoundMessagePath) WebElement notFoundMessage;
	
	public SearchPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//***PAGE OBJECTS SECTION***//
	/**
	 * Select a resource
	 * @param resourceName
	 * @return
	 */
	public SearchPage selectResource(String resourceName){
		LogManager.info("SearchPage: Selecting a resource");

		Waiters.WaitByXPath(resourcesListPath, driver);

		for (WebElement resource : resourcesList) {
			if(resource .getText().equals(resourceName)){
				resource .findElement(By.xpath(resourceButtonPath)).click();
			}
		}
		return this;
	}
	
	/**
	 * Click the advanced search button
	 * @return
	 */
	public SearchPage enableAdvancedSearch(){
		LogManager.info("SearchPage: Enabling advanced search");

		Waiters.WaitById(advancedButtonPath, driver);
		advancedButton.click();
		return this;
	}
	
	/**
	 * Set the Room Name
	 * @param roomName
	 * @return
	 */
	public SearchPage setRoomName(String roomName)
	{
		LogManager.info("SearchPage: Setting a room name");

		Waiters.WaitById(roomNameTextboxPath,driver);
		roomNameTextbox.clear();
		roomNameTextbox.sendKeys(roomName);
		return this;
	}
	
	/**
	 * Set the capacity
	 * @param capacity
	 * @return
	 */
	public SearchPage setCapacity(String capacity)
	{
		LogManager.info("SearchPage: Setting a capacity");

		Waiters.WaitById(capacityTextboxPath, driver);
		capacityTextbox.clear();
		capacityTextbox.sendKeys(capacity);
		return this;
	}
	
	/**
	 * Set a location
	 * @param location
	 * @return
	 */
	public SearchPage setLocation(String location)
	{
		LogManager.info("SearchPage: Selecting a location");

		WebElement loc;
		loc = locationComboBox.findElement(By.xpath("//option[@label='"+ location +"']"));
		loc.click();
		return this;
	}
	
	/**
	 * Click in the "Clear" button
	 * @return
	 */
	public SearchPage clickClearButton()
	{
		LogManager.info("SearchPage: Clicking the clear button");

		Waiters.WaitByXPath(clearButtonPath, driver);
		clearButton.click();
		return this;
	}
	
	public MeetingsPage selectRoom(String roomName)
	{
		Waiters.WaitByXPath("//button[contains(.,'"+roomName+"')]",driver);
		element = driver.findElement(By.xpath("//button[contains(.,'"+roomName+"')]"));
		element.click();		
		return new MeetingsPage(driver);		
	}		
	
	public String getMessageNotFound()
	{
		return notFoundMessage.getText();
	}
	
	//***GET SECTION***//
	
	/**
	 * Get the list of rooms in the search page
	 */
	public void getRoomList()
	{
		roomListSearch = driver.findElements(By.xpath(roomPath));
	}
	
	/**
	 * Method to verify the rooms displayed in the login page
	 * and the search page
	 * @return
	 */
	public SearchPage verifyRoomsDisplayed()
	{
		LogManager.info("SearchPage: Verifying the rooms displayed in the login page");

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
		LogManager.info("SearchPage: Verifying the search of a room by room name");

		getRoomList();
		String roomNameActual = roomListSearch.get(0).getText();
		
		Assert.assertEquals(roomNameActual, roomNameExpected);
		return this;
	}
	
	/**
	 * Method to verify that the advanced search is enabled
	 * @return
	 */
	public SearchPage verifyAdvancedSearchIsEnabled()
	{
		LogManager.info("SearchPage: Verifying the advanced search is enabled");

		String label = advancedLabel.getText();
		Assert.assertEquals("Advanced", label);
		return this;
	}
	
	/**
	 * Method to verify the search by Capacity
	 * @param roomNameExpected
	 * @return
	 */
	public SearchPage verifySearchByCapacity(String roomNameExpected)
	{
		LogManager.info("SearchPage: Verifying the search of a room by capacity");
		
		getRoomList();
		String roomNameActual = roomListSearch.get(0).getText();
		
		Assert.assertEquals(roomNameActual, roomNameExpected);
		return this;
	}
	
	/**
	 * Method to verify the search by location
	 * @param roomNameExpected
	 * @return
	 */
	public SearchPage verifySearchByLocation(String roomNameExpected)
	{
		LogManager.info("SearchPage: Verifying the search of a room by location");
		
		getRoomList();
		String roomNameActual = roomListSearch.get(0).getText();
		
		Assert.assertEquals(roomNameActual, roomNameExpected);
		return this;
	}
	
	/**
	 * Method to verify all the fields are clear
	 * @return
	 */
	public SearchPage verifyFieldsAreEmpty()
	{
		LogManager.info("SearchPage: Verifying all the fields are empty");
		
		String roomName = roomNameTextbox.getText();
		
		WebElement loc;
		loc = locationComboBox.findElement(By.xpath("//option[@selected='selected']"));
		String location = loc.getText();
		
		String capacity = capacityTextbox.getText();
		
		Assert.assertEquals(roomName, "");
		Assert.assertEquals(location, "");
		Assert.assertEquals(capacity, "");
		return this;
	}
	
	/**
	 * Method to verify a meeting exists
	 * @return
	 */
	public SearchPage verifyMeetingExists(String meetingTitleExpected)
	{
		LogManager.info("SearchPage: Verifying the meeting exists");
		
		WebElement meetingTitle = scheduleTable.findElement(By.xpath(meetingTitlePath));
		String meetingTitleActual = meetingTitle.getText();
		
		Assert.assertEquals(meetingTitleActual, meetingTitleExpected);
		
		return this;
	}
}
