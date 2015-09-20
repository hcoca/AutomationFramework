package org.rm.automation.admin.pageobjects.conferenceRooms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.locators.conferenceRooms.ConferenceRoomsLocators;
import org.rm.automation.admin.pageobjects.HomePage;

public class ConferenceRoomsPage extends HomePage{
	
	@FindBy(xpath = ConferenceRoomsLocators.EnabledColumnBtnLocator)
	public WebElement enabledColumnBtn;
	
	@FindBy(xpath = ConferenceRoomsLocators.OutOfOrderColumnBtnLocator)
	public WebElement outOfOrderColumnBtn;
	
	@FindBy(xpath = ConferenceRoomsLocators.RoomColumnBtnLocator)
	public WebElement roomColumnBtn;
	
	@FindBy(xpath = ConferenceRoomsLocators.ConferenceRoomLocator)
	public WebElement conferenceRoom;
	
	@FindBy(xpath = ConferenceRoomsLocators.TotalItemsLabelLocator)
	public WebElement totalItemsLabel;
	
	@FindBy(xpath = ConferenceRoomsLocators.ConferenceRoomInfoLabelLocator)
	public WebElement conferenceRoomInfoLabel;
	
	@FindBy(xpath = ConferenceRoomsLocators.RoomsContainerLocator)
	public WebElement roomsContainer;
	
	@FindBy(xpath = ConferenceRoomsLocators.ResourceContainerLocator)
	public WebElement resourceContainer; 
	
	/*
	 * ------------------------------------------------------------------------------571---------------------------------------
	 */

	/*
	 * ------------------------------------------------------------------------------571---------------------------------------
	 */
	public ConferenceRoomsPage (WebDriver driver){
		super(driver);
		PageFactory.initElements(super.driver, this);
	}
	
	public List<WebElement> getResources(){
		(new WebDriverWait(driver, 20))
		.until(ExpectedConditions.visibilityOf(resourceContainer));
		List<WebElement> list = resourceContainer.findElements(By.xpath("//span[@ng-model='resource.isSelected']"));
		
		return list;
	}
	
	public ConferenceRoomsPage clickOnResource(String resourceName){
		ConferenceRoomsPage res = null; 
		
		List<WebElement> list = getResources();
		for(WebElement webElement : list){
			if(webElement.getText().equals(resourceName)){
				webElement.click();
				res = this;
				break;
			}
		}
		
		return res;
	}
	
	public boolean isAssociatedToResource(String resourcename, String roomName){
		boolean res = false;
		
		WebElement room = this.getConferenceRoom(roomName);
		
//		WebElement resourceAnimatedName = (new WebDriverWait(driver, 20))
//		.until(ExpectedConditions.visibilityOf(room.findElement(By.xpath(".//div[@class='animate-if ng-scope']"))));
//		System.out.println("What it contains: " + resourceAnimatedName.getAttribute("ng-if").toString());
		
		List<WebElement> list = room.findElements(By.xpath(".//div[@class='animate-if ng-scope']"));
		for(WebElement we : list){
//			System.out.println("What it contains: " + we.getAttribute("ng-if").toString());
			if(we.getAttribute("ng-if").toString().contains(resourcename)){
				res = true;
				break;
			}
		}
		
//		if(resourceAnimatedName.getAttribute("ng-if").toString().contains(resourcename)){
//			res = true;
//		}
		
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
		
		roomsContainer = new WebDriverWait(driver, 20)
			.until(ExpectedConditions.visibilityOf(roomsContainer)); // //span[@class='ng-binding']
		List<WebElement> list = roomsContainer.findElements(By.xpath(".//span[@class='ng-binding']"));// The span that contains the conference rooms.
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
	
	public WebElement getConferenceRoom(String roomName){
		WebElement res = null; 
		
		roomsContainer = new WebDriverWait(driver, 20)
			.until(ExpectedConditions.visibilityOf(roomsContainer));
		List<WebElement> list = roomsContainer.findElements(By.xpath(".//div[@ng-style='rowStyle(row)']"));// The span that contains the conference rooms.
		for(WebElement web : list){
			WebElement roomLabel = web.findElement(By.xpath(".//span[@class='ng-binding']"));
			roomLabel = new WebDriverWait(driver, 20)
					.until(ExpectedConditions.visibilityOf(roomLabel));
			if(roomLabel.getText().equals(roomName)){
				res = web;
			}
		}
		
		return res;
	}

	public boolean isValidRoom(String roomName){
		boolean res = false; 
		
		List<WebElement> list = roomsContainer.findElements(By.xpath("//span[@class='ng-binding']"));// The span that contains the conference room.
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
