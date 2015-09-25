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

	//***PAGE ACTIONS SECTION***//
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
	
	//***GET SECTION***//
	/**
	 * Get the message when a room is not found
	 * @return
	 */
	public String getMessageNotFound()
	{
		return notFoundMessage.getText();
	}
	
	/**
	 * Get the list of rooms in the search page
	 */
	public void getRoomList()
	{
		roomListSearch = driver.findElements(By.xpath(roomPath));
	}
	
	/**
	 * Method to get the room name after a search
	 * @return
	 */
	public String getSearchRoomName()
	{
		getRoomList();
		String roomNameActual = roomListSearch.get(0).getText();
		
		return roomNameActual;
	}
	
	/**
	 * Method to get the label of Advanced Search
	 * @return
	 */
	public String getLabelAdvancedSearh()
	{
		String label = advancedLabel.getText();
		return label;
	}
	
	/**
	 * Get the value of the room name text field
	 * @return
	 */
	public String getRoomName()
	{
		String roomName = roomNameTextbox.getText();
		return roomName;
	}
	
	/**
	 * Get the value of the location combo box
	 * @return
	 */
	public String getLocation()
	{
		WebElement loc;
		loc = locationComboBox.findElement(By.xpath("//option[@selected='selected']"));
		String location = loc.getText();
		return location;
	}
	
	/**
	 * Get the value of the capacity text field
	 * @return
	 */
	public String getCapacity()
	{
		String capacity = capacityTextbox.getText();
		return capacity;
	}
	
	/**
	 * Get the name of a meeting
	 * @return
	 */
	public String getMeetingTitle()
	{
		WebElement meetingTitle = scheduleTable.findElement(By.xpath(meetingTitlePath));
		String meetingTitleActual = meetingTitle.getText();
		return meetingTitleActual;
	}
	
	/**
	 * Get the list of rooms in the search page
	 * @return
	 */
	public List<WebElement> getRoomListSearch()
	{
		getRoomList();
		return roomListSearch;
	}
	
	/**
	 * Get the list of rooms in the login page
	 * @return
	 */
	public List<String> getRoomListLogin()
	{
		roomListLogin = LoginPage.getRoomList();
		return roomListLogin;
	}
}
