package org.rm.automation.tablet.pageobjects.homepage;

import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.tablet.locators.homepage.HomePageLocators;
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
	
	@FindBy(css = HomePageLocators.scheduleButton )
	protected WebElement scheduleButton;
	
	@FindBy(css = HomePageLocators.searchButton )
	protected WebElement searchButton;
	
	@FindBy(id = HomePageLocators.homeButton)
	protected WebElement homeButton;
	
	@FindBy(xpath = HomePageLocators.labelRoomName )
	protected WebElement labelRoomName;
	
	
	@FindBy(xpath = HomePageLocators.cuerrentTime )
	protected WebElement cuerrentTime;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public MeetingsPage selectSchedulePage()
	{
		LogManager.info("HomePage <=> TabletPage : Clicking on Schedule button");
		if(scheduleButton.isDisplayed())
			scheduleButton.click();
		return new MeetingsPage(driver);
	}
	
	public SearchPage selectSearchPage(){
		LogManager.info("HomePage <=> TabletPage : Clicking on Search button");
		if(searchButton.isDisplayed())
			searchButton.click();
		return new SearchPage(driver);
	}
	
	public HomePage goHomePage(){
		LogManager.info("HomePage <=> TabletPage : Clicking on Home icon");
		if(homeButton.isDisplayed())
			homeButton.click();
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
