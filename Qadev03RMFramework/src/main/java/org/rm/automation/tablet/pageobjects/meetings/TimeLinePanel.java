package org.rm.automation.tablet.pageobjects.meetings;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.tablet.pageobjects.TabletPage;
import org.rm.automation.utils.Waiters;

public class TimeLinePanel extends MeetingsPage{

	@FindBy(xpath = "//div[@class='vis-group']")
	private WebElement meetingContainer;
	
	@FindBy(xpath = "//div[@class='vis-current-time']")
	private WebElement currentTimeMark;
	
	private WebDriver driver; 
	
	public TimeLinePanel(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getMeetingContainer() {
		return meetingContainer;
	}
	
	public void dragMeetingIconImage(String meetingTitle){                                            
		List<WebElement> list = meetingContainer.findElements(By.xpath("//div[@class='vis-item vis-range meeting']"));
		for(WebElement webeElement : list){
			String meetingTitleLabel = webeElement.findElement(By.xpath(".//span[@class='vis-item-content']")).getText();
			if(meetingTitleLabel.equals(meetingTitle)){                               
				webeElement.click();
				WebElement dragger = driver.findElement(By.xpath("//div[@class='vis-item vis-range meeting vis-selected']/div[@class='vis-drag-right']"));
				Waiters.WaitByVisibilityOfWebElement(dragger, driver);
				
				Actions builder = new Actions(driver); 
				Action dragAndDrop = builder.clickAndHold(dragger)
						.moveByOffset(/**(dragger.getLocation().x - currentTimeMark.getLocation().x)/4**/ -50, dragger.getLocation().y)
						.release(dragger)
						.build();
				dragAndDrop.perform();
			}
		}
	}
	
	public void moveMeetingIconImage(String meetingTitle){                                           
		List<WebElement> list = meetingContainer.findElements(By.xpath("//div[@class='vis-item vis-range meeting']"));
		for(WebElement webeElement : list){
			String meetingTitleLabel = webeElement.findElement(By.xpath(".//span[@class='vis-item-content']")).getText();
			if(meetingTitleLabel.equals(meetingTitle)){                               
				webeElement.click();
				WebElement selectedMeeting = driver.findElement(By.xpath("//div[@class='vis-item vis-range meeting vis-selected']"));
				Waiters.WaitByVisibilityOfWebElement(selectedMeeting, driver);
				
				Actions builder = new Actions(driver); 
				Action move = builder.clickAndHold(selectedMeeting)
						.moveByOffset((selectedMeeting.getLocation().x - currentTimeMark.getLocation().x)/8, selectedMeeting.getLocation().y)
						.release(selectedMeeting)
						.build();
				move.perform();
			}
		}
	}
	
	public String getSubjectText(){
		return tbSubject.getAttribute("value");
	}
	
	public String getStartTimeText(){
		return tbStartTime.getAttribute("value");
	}
	
	public String getEndTimeText(){
		return tbEndTime.getAttribute("value");
	}
}
