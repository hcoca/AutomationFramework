package org.rm.automation.tablet.pageobjects.meetings;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;

public class MeetingsPage{
	
	WebDriver driver;
	WebElement element;
	
	public MeetingsPage(WebDriver driver){
		this.driver = driver;
	}	
	
	public MeetingsPage setOrganizer(String name){	
		LogManager.info("MeetingsPage : Typing the organizer's account username");
		Waiters.WaitById("txtOrganizer",driver);
		element = driver.findElement(By.id("txtOrganizer"));
		element.sendKeys(name);
		return this;
	}
	public MeetingsPage setSubject(String subject){
		LogManager.info("MeetingsPage : Typing the meeting subject");
		Waiters.WaitById("txtSubject",driver);
		element = driver.findElement(By.id("txtSubject"));
		element.clear();
		element.sendKeys(subject);
		return this;
	}
	public MeetingsPage setAtendees(String name){
		LogManager.info("MeetingsPage : Typing adding the attendees accounts");
		Waiters.WaitByXPath("(//input[@type='text'])[3]",driver);
		element = driver.findElement(By.xpath("(//input[@type='text'])[3]"));
		element.clear();
		element.sendKeys(name);
		element.sendKeys(Keys.ENTER);
		return this;
	}
	public MeetingsPage setBody(String body){
		LogManager.info("MeetingsPage : Typing the meetings body (optional field)");
		Waiters.WaitById("txtBody",driver);
		element = driver.findElement(By.id("txtBody"));
		element.clear();
		element.sendKeys(body);		
		return this;
	}	
	public MeetingsPage setDates(String begin, String end){
		LogManager.info("MeetingsPage : Typing the begin and end time of the meeting");
		Waiters.WaitByXPath("//input[@type='time']",driver);
		element = driver.findElement(By.xpath("//input[@type='time']"));
		element.clear();
		element.sendKeys(begin);
		
		Waiters.WaitByXPath("(//input[@type='time'])[2]",driver);
		WebElement element2 = driver.findElement(By.xpath("(//input[@type='time'])[2]"));
		element2.clear();
		element2.sendKeys(end);
		
		return this;
	}
	
	public MeetingsPage confirmMeeting(){
		LogManager.info("MeetingsPage : Clicking on Create button to confirm the meeting creation");
		Waiters.WaitByXPath("//button[@class='clean item item-btn']",driver);
		element = driver.findElement(By.xpath("//button[@class='clean item item-btn']"));
		element.click();
		return this;
	}
	
	public MeetingsPage confirmUser(String password){
		LogManager.info("MeetingsPage : Typing the organizer's account password");
		Waiters.WaitByXPath("//input[@type='password']",driver);
		element = driver.findElement(By.xpath("//input[@type='password']"));
		element.sendKeys(password);
		return this;
	}
	
	public MeetingsPage saveMeeting(){
		LogManager.info("MeetingsPage : Saving the meeting");
		Waiters.WaitByXPath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]",driver);
		element = driver.findElement(By.xpath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]"));
		element.click();		
		return this;
	}
	
	public MeetingsPage saveDeleteMeeting(){
		LogManager.info("MeetingsPage : Deleting the meeting");
		Waiters.WaitByXPath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]",driver);
		element = driver.findElement(By.xpath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]"));
		element.click();
		
		return this;
	}
	
	public MeetingsPage selectMeeting(){
		Waiters.WaitByXPath("//div[@id='timelinePanel']/rm-vis/div/div[4]/div/div/div[2]/div/div",driver);
		element = driver.findElement(By.xpath("//div[@id='timelinePanel']/rm-vis/div/div[4]/div/div/div[2]/div/div"));
		element.click();
		return this;
	}
	
	public MeetingsPage updateMeeting(){
		LogManager.info("MeetingsPage : Clicking Update button");
		Waiters.WaitByXPath("//button[contains(.,'Update')]",driver);
		element = driver.findElement(By.xpath("//button[contains(.,'Update')]"));
		element.click();		
		return this;
	}
	
	public MeetingsPage deleteMeeting(){
		LogManager.info("MeetingsPage : Clicking Remove button");
		Waiters.WaitByXPath("//button[contains(.,'Remove')]",driver);
		element = driver.findElement(By.xpath("//button[contains(.,'Remove')]"));
		element.click();		
		return this;
	} 
	
	public MeetingsPage meetingIsCreated(String expect){
		LogManager.info("MeetingsPage : Verifying if the meeting has been created");
		Waiters.WaitByXPath("//span[@class='vis-item-content']",driver);
		element = driver.findElement(By.xpath("//span[@class='vis-item-content']"));
		Assert.assertEquals(expect,element.getText());		
		return this;
	}
	
	public MeetingsPage isTheRightRoom(String roomName){
		LogManager.info("MeetingsPage : Verifying if the current room is the selected by the user");
		Waiters.WaitByXPath("//html/body/div[2]/div/div/div[1]/div[2]/div[2]",driver);
		element = driver.findElement(By.xpath("//html/body/div[2]/div/div/div[1]/div[2]/div[2]"));
		Assert.assertEquals(roomName, element.getText());	
		return this;
	}
}
