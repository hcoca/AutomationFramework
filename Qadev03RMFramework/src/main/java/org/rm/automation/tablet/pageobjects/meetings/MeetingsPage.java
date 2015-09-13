package org.rm.automation.tablet.pageobjects.meetings;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rm.automation.tablet.pageobjects.HomePage;
import org.rm.automation.utils.BrowserManager;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;

public class MeetingsPage extends HomePage {
	
	public MeetingsPage(WebDriver driver){
		super(driver);
	}	
	
	public MeetingsPage setOrganizer(String name){		
		Waiters.WaitById("txtOrganizer",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.id("txtOrganizer"));
		element.sendKeys(name);
		return this;
	}
	public MeetingsPage setSubject(String subject){
				
		Waiters.WaitById("txtSubject",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.id("txtSubject"));
		element.clear();
		element.sendKeys(subject);
		return this;
	}
	public MeetingsPage setAtendees(String name){
		Waiters.WaitByXPath("(//input[@type='text'])[3]",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("(//input[@type='text'])[3]"));
		element.clear();
		element.sendKeys(name);
		element.sendKeys(Keys.ENTER);
		return this;
	}
	public MeetingsPage setBody(String body){
		Waiters.WaitById("txtBody",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.id("txtBody"));
		element.clear();
		element.sendKeys(body);		
		return this;
	}	
	public MeetingsPage setDates(String begin, String end){
		Waiters.WaitByXPath("//input[@type='time']",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//input[@type='time']"));
		element.clear();
		element.sendKeys(begin);
		
		Waiters.WaitByXPath("(//input[@type='time'])[2]",driver);
		WebElement element2 = BrowserManager.getInstance().getBrowser().findElement(By.xpath("(//input[@type='time'])[2]"));
		element2.clear();
		element2.sendKeys(end);
		
		return this;
	}
	
	public MeetingsPage confirmMeeting(){
		Waiters.WaitByXPath("//button[@class='clean item item-btn']",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//button[@class='clean item item-btn']"));
		element.click();
		return this;
	}
	
	public MeetingsPage confirmUser(String password){
		Waiters.WaitByXPath("//input[@type='password']",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//input[@type='password']"));
		element.sendKeys(password);
		return this;
	}
	
	public MeetingsPage saveMeeting(){
		Waiters.WaitByXPath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]"));
		element.click();		
		return this;
	}
	
	public MeetingsPage saveDeleteMeeting(){
		Waiters.WaitByXPath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]"));
		element.click();
		
		return this;
	}
	
	public MeetingsPage selectMeeting(){
		Waiters.WaitByXPath("//div[@id='timelinePanel']/rm-vis/div/div[4]/div/div/div[2]/div/div",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//div[@id='timelinePanel']/rm-vis/div/div[4]/div/div/div[2]/div/div"));
		element.click();
		return this;
	}
	
	public MeetingsPage updateMeeting(){
		Waiters.WaitByXPath("//button[contains(.,'Update')]",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//button[contains(.,'Update')]"));
		element.click();		
		return this;
	}
	
	public MeetingsPage deleteMeeting(){
		Waiters.WaitByXPath("//button[contains(.,'Remove')]",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//button[contains(.,'Remove')]"));
		element.click();		
		return this;
	} 
	
	public MeetingsPage meetingIsCreated(String expect){
		Waiters.WaitByXPath("//span[@class='vis-item-content']",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//span[@class='vis-item-content']"));
		Assert.assertEquals(expect,element.getText());		
		return this;
	}
	
	public MeetingsPage isTheRightRoom(String roomName){
		Waiters.WaitByXPath("//html/body/div[2]/div/div/div[1]/div[2]/div[2]",driver);
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//html/body/div[2]/div/div/div[1]/div[2]/div[2]"));
		Assert.assertEquals(roomName, element.getText());	
		return this;
	}
}
