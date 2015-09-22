package org.rm.automation.tablet.pageobjects;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.Waiters;

public class LoginPage {
	
	private WebDriver driver;
	private By urlTextBox = By.id("service-url-input");
	private By usernameTextBox = By.id("username");
	private By passwordTextBox = By.id("password");
	private By signInButton = By.xpath("//div[@type='submit']"); //login()
	private By roomSelector = By.xpath("//div[@type='button']"); //setRoom()
	private By roomOption = By.xpath("//section[@id='rm-account-status']/div[3]/div[2]/div/rm-select-item/div/div[2]/div/a");	//setRoom()
	private By accessTablet = By.cssSelector("button.btn.btn-primary"); //access()
	
	private List<WebElement> listRooms;
	private final String roomListPath = "//div[@class='list-group ng-scope']";
	private final String roomNamePath = "//strong";
	
	@FindBy(xpath=roomListPath) WebElement roomList;
	
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	
	public LoginPage(){}
	public LoginPage(WebDriver driver){
		this.driver = driver;
		driver.get(settings.getProperty("url_tablet"));
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage setUrl(String url)
	{
		LogManager.info("LoginPage : Typing the URL from the RM server");
		WebElement URL = driver.findElement(urlTextBox);
		if(URL.isDisplayed()){
			URL.clear();
			URL.sendKeys(url);	
		}
		
		return this;
	}
		
	public LoginPage setUserName(String userName) {
		LogManager.info("LoginPage : Typing RM admininstrator's username");
		WebElement usernameTxtBox = driver.findElement(usernameTextBox);
		if(usernameTxtBox.isDisplayed()){
			usernameTxtBox.clear();
			usernameTxtBox.sendKeys(userName);
		}
		
		return this;
	}
	
	public LoginPage setPassword(String password)
	{
		LogManager.info("LoginPage : Typing RM administrator's password");
		WebElement passwordTxtBox = driver.findElement(passwordTextBox);
		if(passwordTxtBox.isDisplayed()){
			passwordTxtBox.clear();
			passwordTxtBox.sendKeys(password);
		}
		
		return this;
	}
	
	public LoginPage login()
	{
		LogManager.info("LoginPage : Clicking on sign in button to authenticate the account and enable the room selection");
		WebElement signButton = driver.findElement(signInButton);
		if(signButton.isDisplayed())			
			signButton.click();
		
		return this;
	}
	
	public LoginPage setRoom(){
		LogManager.info("LoginPage : Selecting the conference room to manage");

		WebElement roomPicker = driver.findElement(roomSelector);		
		if(roomPicker.isDisplayed())			
			roomPicker.click();			
		
		setRoomList();
		
		WebElement roomOpt = driver.findElement(roomOption);		
		if(roomOpt.isDisplayed())			
			roomOpt.click();
		
		return this;
	}

	public HomePage access(String url, String admin, String pass)
	{	
		LogManager.info("LoginPage : Accessing to the selected conference room");
		setUrl(url);
		setUserName(admin);
		setPassword(pass);
		login();
		setRoom();
		WebElement accessTab = driver.findElement(accessTablet);		
		if(accessTab.isDisplayed())			
			accessTab.click();	
		return new HomePage(driver);
	}
	
	public HomePage getTabletHomePage(){
		WebElement accessTab = driver.findElement(accessTablet);
		Waiters.WaitByVisibilityOfWebElement(accessTab, driver);
		accessTab.click();
		
		return new HomePage(driver);
	}
	
	/**
	 * Method to save in a list the rooms that display in the login page
	 */
	private void setRoomList() {
		listRooms = roomList.findElements(By.xpath(roomNamePath));
	}
	
	/**
	 * Method to get the rooms displayed in the login page
	 * @return
	 */
	public List<WebElement> getRoomList()
	{
		return listRooms;
	}
}
