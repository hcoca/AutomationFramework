package org.rm.automation.tablet.pageobjects.homepage;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.tablet.pageobjects.search.SearchPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.Waiters;

public class HomePage {	
	
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String server = settings.getProperty("server");
	private String port = settings.getProperty("port");
	private String url = "http://"+server+":"+port;
	
	protected WebDriver driver;
	private By scheduleButton = By.cssSelector("rm-panel-option.tile-column-option.tile-column-option-landscape > div.tile-button-schedule");	
	private By searchButton = By.cssSelector("rm-panel-option.tile-column-option.tile-column-option-landscape > div.tile-button-search");
	private By homeButton = By.id("go-home");
	
	@FindBy(xpath = "//span[@class='room-label ng-binding']")
	private WebElement labelRoomName;
	
	
	@FindBy(xpath = "//span[@ng-bind='currentTime']")
	private WebElement cuerrentTime;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
	
	public String getRoomNamelabel() {
		Waiters.WaitByVisibilityOfWebElement(labelRoomName, driver);
		return labelRoomName.getText();
	}
	
	
	public WebDriver getDriver(){
		return driver;
	}
	/*
	 * return the current Time
	 * */
	public String currentTime() {
		Waiters.WaitByVisibilityOfWebElement(cuerrentTime, driver);
		return cuerrentTime.getText();
	}
	
	public boolean checkIfIconRedirectsToHomePage(){
		LogManager.info("MeetingsPage : Verifying than the home icon redirects to the Home page");				
		boolean result = false;
		result = driver.getCurrentUrl().equals(url+"/tablet/#/home")?true:false;
		return result;
	}
}
