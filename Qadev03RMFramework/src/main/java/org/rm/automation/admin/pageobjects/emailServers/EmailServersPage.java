package org.rm.automation.admin.pageobjects.emailServers;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.utils.LogManager;




public class EmailServersPage {
	WebDriver driver;
  @FindBy(xpath="//button[@ng-click='openModal()']")
  WebElement buttonadd;
  
  @FindBy(xpath="//button[@ng-click='openDeleteDialog()']")
  WebElement buttremove;
  
  
  // pannel of the servervice exchance
  @FindBy(css="h4.ng-binding")
  WebElement panelExchanges;
  // texto para add server


  @FindBy(className="ng-binding")
  WebElement testexangeadded;
  
  
  
  public EmailServersPage(WebDriver driver){
	this.driver = driver;
		
	PageFactory.initElements(driver, this);  
  }
  public boolean thereisExchange() {
	  
	  return panelExchanges.isDisplayed();
  }
  
  public String gettextExchange() {
	(new WebDriverWait(driver, 25))
		.until(ExpectedConditions.visibilityOf(testexangeadded));
	LogManager.info("EmailServerPage: return EmailServerExchage");
	return testexangeadded.getText();
  }
  

  public RemoveServerpage clickbtnremove(){
	  (new WebDriverWait(driver, 20))
		.until(ExpectedConditions.visibilityOf(buttremove));
	  LogManager.info("EmailServerPage: click button remove Emailserver");
	  buttremove.click();
	  return new RemoveServerpage(driver);
  }
  
  public AddEmailServersPage clickbtnadd(){
	  (new WebDriverWait(driver, 20))
		.until(ExpectedConditions.visibilityOf(buttonadd));
	  LogManager.info("EmailServerPage: click button add Emailserver");
	  buttonadd.click();
	  return new AddEmailServersPage(driver);
  }
  public boolean buttonAddIsVisible() {
	  (new WebDriverWait(driver, 20))
		.until(ExpectedConditions.visibilityOf(buttonadd));
	  return buttonadd.isDisplayed();
  }
}
