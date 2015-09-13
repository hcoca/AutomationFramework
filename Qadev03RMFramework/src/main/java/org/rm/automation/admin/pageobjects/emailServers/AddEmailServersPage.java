package org.rm.automation.admin.pageobjects.emailServers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.utils.LogManager;


public class AddEmailServersPage {
	
	WebDriver driver ; 
	  @FindBy(xpath="//h3")
	  WebElement textaddserver;
	  // text para agregar
	  @FindBy(id="add-mailserver-hostname")
	  WebElement serverinput;
	  
	  @FindBy(id="add-mailserver-username")
	  WebElement usrname;
	  
	  @FindBy(id="add-mailserver-password")
	  WebElement pwusrname;
	  // botones luego de ingresar datos
	  @FindBy(xpath="//button[@ng-click='sendRequest()']")
	  WebElement addserverbtn;
	  
	  @FindBy(xpath="//button[@ng-click='cancel()']")
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
		  serverinput.sendKeys(hostname);
	  }
	  public void setUsrName(String usraddserver)
	  {
		  usrname.sendKeys(usraddserver);
	  }
	  public void setPassWord(String pwusr) {
		  pwusrname.sendKeys(pwusr);
	  }
	  public EmailServersPage saveserverbtn() {
		  (new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(addserverbtn));
		  LogManager.info(" -- > click save button on panel Emailserver");
		  addserverbtn.click();
		  EmailServersPage email = new EmailServersPage(driver);
		  (new WebDriverWait(driver, 50000))
			.until(ExpectedConditions.visibilityOf(email.testexangeadded));
		  return email;
		  
	  }
	  public EmailServersPage cancelserverbtn() {
		  cancelserverbtn.click();
		  return new EmailServersPage(driver);
	  }

}
