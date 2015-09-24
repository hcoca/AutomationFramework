package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.AvailablePanel;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NowPanel;
import org.rm.automation.utils.MeetingManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.api.MeetingsRequests;

public class ThisTestCaseIsToVerifyXX {

//	public static void main(String[] ar){
//		Date date = new Date();
//		long x1 = date.getTime();
//		Date endDate = new Date(x1 + ((60 * 60000) * 6));
//		System.out.println(RoomManagerTime.getHomePageTimeFormat(date));
//		System.out.println(RoomManagerTime.getHomePageTimeFormat(endDate));
//	}
	
//	public static void main(String[] ar) throws UnsupportedOperationException, ParseException, IOException{
//		
//	}
	
//	public static void main(String[] ar){
//		MeetingManager.setMeetingEndTime(184 * 60000);
//		MeetingManager.setMeetingCreationTime(0);
//		
//		System.out.println(MeetingManager.getRemainingTimeInMinutes());
//		System.out.println(MeetingManager.getRemainingTimeFormated());
//	}
	
//	public static void main(String[] ar) throws UnsupportedOperationException, IOException, ParseException{
//		Properties settings = ReadPropertyValues
//				.getPropertyFile("./Config/settings.properties");
//		String userName = settings.getProperty("username");
//		String userPw = settings.getProperty("passwordES");
//		String server = settings.getProperty("server");
//		String port = settings.getProperty("port");
//		String serviceURL = "http://" + server + ":" + port;
//		
//		WebDriver driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		LoginPage login;
//		HomePage homePage;
//		NowPanel nowPanel;
//		
//		login = new LoginPage(driver);
// 		homePage = login.access(serviceURL, userName, userPw, "room571");
// 		nowPanel = new NowPanel(homePage.getDriver());
// 		nowPanel.waitForMainPanel();
// 		System.out.println("The Title is: " + nowPanel.getTitleLabelText());
//	}
	
//	public static void main(String[] ar) throws UnsupportedOperationException, IOException, ParseException{
//		MeetingsRequests.postMeeting("room571", "571FTW", RoomManagerTime.substractMinutes(1), RoomManagerTime.addMinutes(10));
//	}
	
	public static void main(String[] ar) throws UnsupportedOperationException, IOException, ParseException{// 560287dfc5c01db814c16198
//		MeetingsRequests.postMeeting("room571", "green", RoomManagerTime.substractMinutesToCurrentTime(1), RoomManagerTime.addMinutesToCurrentTime(10));
//		String meetingId = MeetingsRequests.getMeetingId("green", "room571");
//		MeetingsRequests.deleteMeeting("5603bcf623959aa022ea873b", "room571");
//		JSONObject meeting = MeetingsRequests.getMeeting(meetingId, "room571");
//		System.out.println("Start: " + meeting.get("start"));
//		System.out.println("End: " + meeting.get("end"));
//		System.out.println("Modified at: " + meeting.get("modifiedDateAtService"));
//		List<JSONObject> list = MeetingsRequests.getRoomMeetings("room571");
//		for(JSONObject json : list){
//			System.out.println(json);
//		}
		
		
		System.out.println(RoomManagerTime.addminutesCurrentTime(15));
		System.out.println(RoomManagerTime.addMinutesToCurrentTime(15));
		System.out.println(RoomManagerTime.addMinutesToCurrentTime(16));
		System.out.println(RoomManagerTime.getAvailableTimeTillEndOfDay());
		
//		Properties settings = ReadPropertyValues
//				.getPropertyFile("./Config/settings.properties");
//		String userName = settings.getProperty("username");
//		String userPw = settings.getProperty("passwordES");
//		String server = settings.getProperty("server");
//		String port = settings.getProperty("port");
//		String serviceURL = "http://" + server + ":" + port;
//		
//		WebDriver driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		LoginPage login;
//		HomePage homePage;
//		AvailablePanel availablePanel;
//		
//		login = new LoginPage(driver);
//		homePage = login.access(serviceURL, userName, userPw, "room571");
//		availablePanel = new AvailablePanel(homePage.getDriver());
//		availablePanel.waitForMainPanel();
//		System.out.println("The Title is: " + availablePanel.getStartAvailableTimeLabelText());
//		System.out.println("The Title is: " + availablePanel.getEndAvailableTimeLabelText());
	}
	
//	public static void main(String[] ar) throws UnsupportedOperationException, IOException, ParseException{
//		Date date = new Date();
//		System.out.println(date.toString());
//		System.out.println(date.toGMTString());
//		List<JSONObject> list = MeetingsRequests.getRoomMeetings("room571");
//		for(JSONObject json : list){
//			System.out.println(json);
//			System.out.println("start: " + json.get("start"));
//			System.out.println("end: " + json.get("end"));
//		} // 2015-09-23T07:27:42.000Z
//		SimpleDateFormat x = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss'.000Z'");
//		ft.setTimeZone(TimeZone.getTimeZone("GMT"));
//		System.out.println("My formated time: " + ft.format(date));
//		
//		System.out.println("------------ 571 -------------------");
//		
//		Date gregorianDate = GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
//		System.out.println(gregorianDate.toString());
//		
//		System.out.println("------------ 571 -------------------");
//		
//		final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
//
//		Date datum = new Date();
//		long t = datum.getTime();
//		Date afterAddingTenMins = new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
//		System.out.println(afterAddingTenMins);
//		System.out.println("My formated time ^_^: " + ft.format(date));
//		System.out.println("My formated time ^_^: " + ft.format(afterAddingTenMins));
//	}
	
//	public static void main(String[] ar) throws UnsupportedOperationException, IOException, ParseException{
//		List<JSONObject> list = MeetingsRequests.getRoomMeetings("room571");
//		for(JSONObject json : list){
//			System.out.println(json);
//		}
//	}
}
