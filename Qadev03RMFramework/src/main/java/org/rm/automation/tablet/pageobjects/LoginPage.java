package org.rm.automation.tablet.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rm.automation.tablet.pageobjects.HomePage;

public class LoginPage {
	
	private WebDriver driver;
	private By urlTextBox = By.id("service-url-input");
	private By usernameTextBox = By.id("username");
	private By passwordTextBox = By.id("password");
	private By signInButton = By.xpath("//div[@type='submit']"); //login()
	private By roomSelector = By.xpath("//div[@type='button']"); //setRoom()
	private By roomOption = By.xpath("//section[@id='rm-account-status']/div[3]/div[2]/div/rm-select-item/div/div[2]/div/a");	//setRoom()
	private By accessTablet = By.cssSelector("button.btn.btn-primary"); //access()
	
	public LoginPage(){}
	public LoginPage(WebDriver driver){
		this.driver = driver;		
	}
	
	public void setUrl(String url)
	{
		WebElement URL = driver.findElement(urlTextBox);
		if(URL.isDisplayed()){
			URL.clear();
			URL.sendKeys(url);	
		}
	}
		
	public void setUserName(String userName) {
		WebElement usernameTxtBox = driver.findElement(usernameTextBox);
		if(usernameTxtBox.isDisplayed()){
			usernameTxtBox.clear();
			usernameTxtBox.sendKeys(userName);
		}
	}
	
	public void setPassword(String password)
	{
		WebElement passwordTxtBox = driver.findElement(passwordTextBox);
		if(passwordTxtBox.isDisplayed()){
			passwordTxtBox.clear();
			passwordTxtBox.sendKeys(password);
		}
	}
	
	public void login()
	{
		WebElement signButton = driver.findElement(signInButton);
		if(signButton.isDisplayed())			
			signButton.click();
	}
	
	public void setRoom(){

		WebElement roomPicker = driver.findElement(roomSelector);		
		if(roomPicker.isDisplayed())			
			roomPicker.click();			
		
		WebElement roomOpt = driver.findElement(roomOption);		
		if(roomOpt.isDisplayed())			
			roomOpt.click();	
	}
	
	public HomePage access(String url, String admin, String pass)
	{	
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
}
