package org.rm.automation.admin.pageobjects.conferenceRooms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OutOfOrderPlanningPage extends ConferenceRoomCommonPage {
	
	@FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/div/div/div[2]/div[1]/div[3]/form/div[3]/div/label")
	private WebElement actionsLabel;
	
	@FindBy(xpath = "//h5[@class='ng-binding']")
	private WebElement emailroom;
	@FindBy (xpath  = "//h2[@class='ng-binding']")
	private WebElement nameroom;
	
	@FindBy(xpath = "//label[@class='btn btn-block btn-default']")
	private WebElement buttonOOOPEnableDis;
	
	@FindBy(xpath = "(//button[@type='button'])[54]")
	private WebElement calendarfrom;
	
	@FindBy(xpath = "(//button[@type='button'])[104]")
	private WebElement calendarto;
	
	@FindBy(xpath = "//button[contains(text(),'Today')]" )
	private WebElement buttonTodayfrom;
	
	@FindBy(xpath = "(//button[contains(text(),'Today')])[2]" )
	private WebElement buttonTodayTo;
	
	@FindBy(xpath = "//input[@ng-model='hours']")
	private WebElement horafromnoedit;
	
	@FindBy(xpath = "(//input[@ng-model='hours'])[2]")
	private WebElement horaToNoedit;
	
	@FindBy(xpath = "//a[@ng-click='incrementHours()']")
	private WebElement hoursUPfrom;
	
	@FindBy(xpath = "(//a[@ng-click='incrementHours()'])[2]")
	private WebElement hoursUPTo;
	
	@FindBy(xpath = "//a[@ng-click='decrementHours()']")
	private WebElement hoursdecrementfrom;
	
	@FindBy(xpath = "(//a[@ng-click='decrementHours()'])[2]")
	private WebElement hoursdecrementTo;
	
	@FindBy(xpath = "//a[@ng-click='incrementMinutes()']")
	private WebElement minutesUpfrom;
	
	@FindBy(xpath = "(//a[@ng-click='incrementMinutes()'])[2]")
	private WebElement minutesUpTo;
	
	@FindBy(xpath = "(//a[@ng-click='decrementMinutes()'])[2]")
	private WebElement decrementsminutesTo; 
	
	@FindBy(xpath = "//a[@ng-click='decrementMinutes()']")
	private WebElement decrementsminutesFrom; 
	
	@FindBy(xpath = "//button[@ng-click='save()']")
	private WebElement buttonSave;
	
	@FindBy(xpath = "//label[@for='title-dropdown']")
	private WebElement listOOO;
	
	@FindBy(xpath = "//input[@type='text'])[9]")
	private WebElement textTitle;
	
	
	// class
	public OutOfOrderPlanningPage(WebDriver driver){
		super(driver);
	}
	
	public String getActionsLabel(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(actionsLabel));
		
		return actionsLabel.getText();
	}
	public String getemailroom() {
		return emailroom.getText();
	}
	public String getNameRoom() {
		return nameroom.getText();
	}
	public void enableDisableBtn() {
		buttonOOOPEnableDis.click();
	}
	public void clickcalendarfrom() {
		calendarfrom.click();
	}
	public void clickcalendatTo() {
		calendarto.click();
	}
	public void clickbuttonToDayfrom() {
		(new WebDriverWait(driver, 20))
		.until(ExpectedConditions.visibilityOf(buttonTodayfrom));
		buttonTodayfrom.click();
	}
	public void clickbuttonToDayTo() {
		(new WebDriverWait(driver, 20))
		.until(ExpectedConditions.visibilityOf(buttonTodayTo));
		buttonTodayTo.click();
	}
	// section set hours and minutes
	public void setUPhorafrom(int num) {
		for(int i=0 ;i< num ; i++)
		{
			hoursUPfrom.click();
		}		
	}
	public void setUPhoraTo(int num) {
		for(int i=0 ;i< num ; i++)
		{
			hoursUPTo.click();
		}
	}
	
	public void setdownhoursFrom(int num) {
		for(int i=0 ;i< num ; i++)
		{
			hoursdecrementfrom.click();
		}
	}
	public void setdownhoursTo(int num) {
		for(int i=0 ;i< num ; i++)
		{
			hoursdecrementTo.click();
		}
	}
	public void setUpminutesFrom(int num) {
		for(int i=0 ;i< num ; i++)
		{
			minutesUpfrom.click();
		}
	}
	public void setUpminutesTo(int num) {
		for(int i=0 ;i< num ; i++)
		{
			minutesUpTo.click();
		}
	}
	public void setDownMinuteFrom(int num) {
		for(int i=0 ;i< num ; i++)
		{
			decrementsminutesFrom.click();
		}
	}
	public void setDownMinuteTo(int num) {
		for(int i=0 ;i< num ; i++)
		{
			decrementsminutesTo.click();
		}
	}
	public ConferenceRoomsPage savebuttonOOOP() {
		buttonSave.click();
		return new ConferenceRoomsPage(driver);
	}
	
	/*
	 * Temporarily Out of Order (Order , Temporarily)
	 * Closed for maintenance (maintenance)
	 * Closed for reparations {reparations}
	 * Reserved for a special event (Reserved , special)
	 * if the name in invalid this select by default first option
	*/
	public void selectTitle(String title) {
		listOOO.click();
		try {
		 List<WebElement> list =listOOO.findElements(By.xpath("//a[contains(text(),"+title+")]"));
		 for(WebElement web : list){
				if(web.getText().contains(title))
					web.click();					
		 }
		}catch (Exception e){
			//no se a encontrado se seleciona por defecto
		}
	}
	
	public ConferenceRoomsPage createOOOPactive(String tipe)
	{

		clickcalendarfrom();
		clickbuttonToDayfrom();
	
		clickcalendatTo();
		clickbuttonToDayTo();
		
		enableDisableBtn();

		setdownhoursFrom(1);		
		// value accepted is 

		selectTitle(tipe);
		return savebuttonOOOP();
	}
}
