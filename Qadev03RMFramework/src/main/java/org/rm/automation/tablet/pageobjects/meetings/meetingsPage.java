package org.rm.automation.tablet.pageobjects.meetings;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.rm.automation.utils.BrowserManager;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;

public class meetingsPage {	
	
	WebElement element;
	
	public meetingsPage setOrganizer(String name){		
		Waiters.WaitById("txtOrganizer");
		WebElement element = BrowserManager.getInstance().getBrowser().findElement(By.id("txtOrganizer"));
		element.sendKeys(name);
		return this;
	}
	public meetingsPage setSubject(String subject){
				
		Waiters.WaitById("txtSubject");
		element = BrowserManager.getInstance().getBrowser().findElement(By.id("txtSubject"));
		element.clear();
		element.sendKeys(subject);
		return this;
	}
	public meetingsPage setAtendees(String name){
		Waiters.WaitByXPath("(//input[@type='text'])[3]");
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("(//input[@type='text'])[3]"));
		element.clear();
		element.sendKeys(name);
		element.sendKeys(Keys.ENTER);
		return this;
	}
	public meetingsPage setBody(String body){
		Waiters.WaitById("txtBody");
		element = BrowserManager.getInstance().getBrowser().findElement(By.id("txtBody"));
		element.clear();
		element.sendKeys(body);		
		return this;
	}	
	public meetingsPage setDates(String begin, String end){
		Waiters.WaitByXPath("//input[@type='time']");
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//input[@type='time']"));
		element.clear();
		element.sendKeys(begin);
		
		Waiters.WaitByXPath("(//input[@type='time'])[2]");
		WebElement element2 = BrowserManager.getInstance().getBrowser().findElement(By.xpath("(//input[@type='time'])[2]"));
		element2.clear();
		element2.sendKeys(end);
		
		return this;
	}
	
	public meetingsPage confirmMeeting(){
		Waiters.WaitByXPath("//button[@class='clean item item-btn']");
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//button[@class='clean item item-btn']"));
		element.click();
		return this;
	}
	
	public meetingsPage confirmUser(String password){
		Waiters.WaitByXPath("//input[@type='password']");
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//input[@type='password']"));
		element.sendKeys(password);
		return this;
	}
	
	public meetingsPage saveMeeting(){
		Waiters.WaitByXPath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]");
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]"));
		element.click();		
		return this;
	}
	
	public meetingsPage saveDeleteMeeting(){
		Waiters.WaitByXPath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]");
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]"));
		element.click();
		
		return this;
	}
	
	public meetingsPage selectMeeting(){
		Waiters.WaitByXPath("//div[@id='timelinePanel']/rm-vis/div/div[4]/div/div/div[2]/div/div");
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//div[@id='timelinePanel']/rm-vis/div/div[4]/div/div/div[2]/div/div"));
		element.click();
		return this;
	}
	
	public meetingsPage updateMeeting(){
		Waiters.WaitByXPath("//button[contains(.,'Update')]");
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//button[contains(.,'Update')]"));
		element.click();		
		return this;
	}
	
	public meetingsPage deleteMeeting(){
		Waiters.WaitByXPath("//button[contains(.,'Remove')]");
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//button[contains(.,'Remove')]"));
		element.click();		
		return this;
	} 
	
	public meetingsPage meetingIsCreated(String expect){
		Waiters.WaitByXPath("//span[@class='vis-item-content']");
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//span[@class='vis-item-content']"));
		Assert.assertEquals(expect,element.getText());		
		return this;
	}
	
	public meetingsPage isTheRightRoom(String roomName){
		Waiters.WaitByXPath("//html/body/div[2]/div/div/div[1]/div[2]/div[2]");
		element = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//html/body/div[2]/div/div/div[1]/div[2]/div[2]"));
		Assert.assertEquals(roomName, element.getText());	
		return this;
	}
}
