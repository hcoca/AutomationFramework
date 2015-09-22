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
	
	@FindBy(id ="service-url-input")
	private WebElement urlTextBox;

	@FindBy(id ="username")
	private WebElement usernameTextBox;

	@FindBy(id ="password")
	private WebElement passwordTextBox;
	
	@FindBy(xpath ="//div[@type='submit']")
	private WebElement signInButton;
	
	@FindBy(xpath ="//div[@type='button']")
	private WebElement roomSelector;
	
	@FindBy(xpath ="//div[@class='item-box']")
	private WebElement roomlist;
	
	@FindBy(css ="button.btn.btn-primary")
	private WebElement accessTablet;
	
	
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	
	public LoginPage(){}
	public LoginPage(WebDriver driver){
		this.driver = driver;
		driver.get(settings.getProperty("url_tablet"));
		PageFactory.initElements(driver, this);
	}
	
	public void setUrl(String url)
	{
		LogManager.info("LoginPage : Typing the URL from the RM server");
		urlTextBox.clear();
		urlTextBox.sendKeys(url);	
	}
		
	public void setUserName(String userName) {
		LogManager.info("LoginPage : Typing RM admininstrator's username");
		usernameTextBox.clear();
		usernameTextBox.sendKeys(userName);
	}
	
	public void setPassword(String password)
	{
		LogManager.info("LoginPage : Typing RM administrator's password");
		passwordTextBox.clear();
		passwordTextBox.sendKeys(password);
	}
	
	public LoginPage login()
	{
		Waiters.WaitByVisibilityOfWebElement(signInButton, driver);
		LogManager.info("LoginPage : Clicking on sign in button to authenticate the account and enable the room selection");
		signInButton.click();
		return this;
	}


	public void picker() {
		Waiters.WaitByVisibilityOfWebElement(roomSelector, driver);
		roomSelector.click();
	}


	public HomePage access(String url, String admin, String pass ,String roomName)
	{	
		LogManager.info("LoginPage : Accessing to the selected conference room");
		setUrl(url);
		setUserName(admin);
		setPassword(pass);
		login();
		selectRoom(roomName);				
		return getTabletHomePage();
	}
	
	public HomePage getTabletHomePage(){
		Waiters.WaitByVisibilityOfWebElement(accessTablet, driver);
		accessTablet.click();
		return new HomePage(driver);
	}
	

	public void selectRoom(String room){
		picker();
		List<WebElement> roomlis = roomlist.findElements(By.xpath("//strong"));
		Waiters.WaitByVisibilityOfWebElement(roomlist, driver);
		for (WebElement roomEl : roomlis) {
			if(roomEl.getText().contains(room)){
				roomEl.click();
				LogManager.info("LoginPageTablet: select room");
			}
		}
		LogManager.warn("LoginPageTablet: room non found");
	}
}
