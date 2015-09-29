package org.rm.automation.admin.pageobjects.emailServers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.locators.emailServers.AddEmailServerPageLocator;
import org.rm.automation.utils.LogManager;


public class AddEmailServersPage {
	
	WebDriver driver ; 

	
	  @FindBy(xpath=AddEmailServerPageLocator.textaddserverLocator)
	  WebElement textaddserver;
	  // text para agregar
	  @FindBy(id=AddEmailServerPageLocator.serverinputLocator)
	  WebElement serverinput;
	  
	  @FindBy(id=AddEmailServerPageLocator.usrnameLocator)
	  WebElement usrname;
	  
	  @FindBy(id=AddEmailServerPageLocator.pwusrnameLocator)
	  WebElement pwusrname;
	  // botones luego de ingresar datos
	  @FindBy(xpath=AddEmailServerPageLocator.addserverbtnLocator)
	  WebElement addserverbtn;
	  
	  @FindBy(xpath=AddEmailServerPageLocator.cancelserverbtnLocator)
	  WebElement cancelserverbtn;
	  
	  public AddEmailServersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	  }
	  
	  public String returntext() {
			return textaddserver.getText();
	  }
	  public void sethostname(String hostname) 
	  {
		  LogManager.info("AddEmailServerPage: input EmailServer hostname");
		  serverinput.sendKeys(hostname);
	  }
	  public void setUsrName(String usraddserver)
	  {
		  LogManager.info("AddEmailServerPage: input EmailServer User");
		  usrname.sendKeys(usraddserver);
	  }
	  public void setPassWord(String pwusr) {
		  LogManager.info("AddEmailServerPage: input EmailServer user password ");
		  pwusrname.sendKeys(pwusr);
	  }
	  public EmailServersPage saveserverbtn() {
		  (new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(addserverbtn));
		  LogManager.info("AddEmailServerPage: click save button");
		  addserverbtn.click();
		  EmailServersPage email = new EmailServersPage(driver);
		  (new WebDriverWait(driver, 50000))
			.until(ExpectedConditions.visibilityOf(email.testexangeadded));
		  return email;
		  
	  }
	  public EmailServersPage cancelserverbtn() {
		  cancelserverbtn.click();
		  LogManager.info("AddEmailServerPage: click cancel button");
		  return new EmailServersPage(driver);
	  }

}