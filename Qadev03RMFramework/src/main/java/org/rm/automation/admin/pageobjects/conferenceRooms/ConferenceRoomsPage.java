package org.rm.automation.admin.pageobjects.conferenceRooms;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.pageobjects.HomePage;

public class ConferenceRoomsPage extends HomePage{
	
	@FindBy(xpath = "//div[text()='Enabled']")
	public WebElement enabledColumnBtn;
	
	@FindBy(xpath = "//div[contains(text(),'Order')]")
	public WebElement outOfOrderColumnBtn;
	
	@FindBy(xpath = "//div[text()='Room']")
	public WebElement roomColumnBtn;
	
	/*
	 * The conference rooms container.   <------ Needs to be reviewed. 
	 */
	@FindBy(css = "div[class='ngViewport ng-scope']")
	public WebElement roomContainer;
	
	/*
	 * This WebElement should be reviewed.
	 */
	//@FindBy(xpath = "//*[@id='roomsGrid']/div[2]/div/div/div[3]")
	//@FindBy(xpath = "//*[@id='roomsGrid']/div[2]/div/div")
	////*[@id="roomsGrid"]/div[2]/div/div/div[3]
	//                 //*[@id="roomsGrid"]/div[2]/div/div/div[3]/div[2]/div/span[2]   <------ It works as well. 
	//               //div[@id='roomsGrid']/div[2]/div/div/div[3]/div[2]/div   <------ The one that works.
	@FindBy(xpath = "//*[@id='roomsGrid']/div[2]/div/div/div[3]/div[2]/div/span[2]")
	public WebElement conferenceRoom;
	
	@FindBy(xpath = "//*[@id='roomsGrid']/div[3]/div/div[1]/div[1]/span[1]")
	public WebElement totalItemsLabel;
	
	@FindBy(xpath = "//*[@id='roomsGrid']/div[1]/div[1]/div")
	public WebElement conferenceRoomInfoLabel;
	
	// //div[@class='ngCanvas'] <-------- The xpath where the rooms are located
	@FindBy(xpath = "//div[@class='ngCanvas']")
	public WebElement theRoomsContainer;
	
	/*
	 * It might be deprecated.
	 */
	public ArrayList<String> listOfRoomNames = null;
	
	/*
	 * ------------------------------------------------------------------------------571---------------------------------------
	 */

	/*
	 * ------------------------------------------------------------------------------571---------------------------------------
	 */
	public ConferenceRoomsPage (WebDriver driver){
		super(driver);
		PageFactory.initElements(super.driver, this);
		
		listOfRoomNames = fillListOfRoomsNames();
	}
	
	private ArrayList<String> fillListOfRoomsNames() {
		List<WebElement> aux = theRoomsContainer.findElements(By.xpath("div"));
		ArrayList<String> res = new ArrayList<>(); 
		
		if(aux.size() != 0){
			for(WebElement div : aux){
				res.add(div.getText().trim());
			}
		}
		
		return res;
	}
	
//	public boolean isValidRoom(String roomName){
//		return listOfRoomNames.contains(roomName) ? true : false;
//	}
	
	public ConferenceRoomsPage clickEnabledColumnBtn(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(enabledColumnBtn));
		enabledColumnBtn.click();
		
		return new ConferenceRoomsPage(driver);
	}
	
	public ConferenceRoomsPage clickOutOfOrderColumnBtn(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(outOfOrderColumnBtn));
		outOfOrderColumnBtn.click();
		
		return new ConferenceRoomsPage(driver);
	}

	public ConferenceRoomsPage clickRoomColumnBtn(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(roomColumnBtn));
		roomColumnBtn.click();
		
		return new ConferenceRoomsPage(driver);
	}
	
	/*
	 * It should receive the conference room name.
	 */
	public RoomInfoPage doubleClickConferenceRoom(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(conferenceRoom));
		Actions builder = new Actions(driver);
		builder.doubleClick(conferenceRoom).perform();
		
		return new RoomInfoPage(driver);
	}

	public RoomInfoPage doubleClickConferenceRoom(String roomName){
		RoomInfoPage res = null; 
		
		List<WebElement> list = theRoomsContainer.findElements(By.xpath("//span[@class='ng-binding']"));// The span that contains the conference room.
		for(WebElement web : list){
			if(web.getText().equals(roomName)){
				Actions builder = new Actions(driver);
				builder.doubleClick(web).perform();
				res = new RoomInfoPage(driver);
				break;
			}
		}
		
		return res;
	}

	public boolean isValidRoom(String roomName){
		boolean res = false; 
		
		List<WebElement> list = theRoomsContainer.findElements(By.xpath("//span[@class='ng-binding']"));// The span that contains the conference room.
		for(WebElement web : list){
			if(web.getText().equals(roomName)){
				res = true;
			}
		}
		
		return res;
	}
	
	public String getTotalItemsLabelValue(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(totalItemsLabel));
		
		return totalItemsLabel.getText();
	}
	
	public String getConferenceRoomInfoLabel(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(conferenceRoomInfoLabel));
		
		return conferenceRoomInfoLabel.getText();
	}
}
