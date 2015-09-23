package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NowPanel;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.api.MeetingsRequests;

public class ThisTestCaseIsToVerifyXX {
	
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
//		MeetingsRequests.postMeeting("b21", "green", RoomManagerTime.substractMinutes(1), RoomManagerTime.addMinutes(3));
//		MeetingsRequests.deleteMeeting("5602b92a980daccc0ed7ec13", "b21");
//		String meetingId = MeetingsRequests.getMeetingId("Damn");
//		System.out.println(meetingId);
		List<JSONObject> list = MeetingsRequests.getRoomMeetings("b21");
		for(JSONObject json : list){
			System.out.println(json);
		}
		String start = RoomManagerTime.substractMinutes(1);
		String end = RoomManagerTime.addMinutes(3);
		String actual = RoomManagerTime.getRoomManagerTime();
		System.out.println(start);
		System.out.println(end);
		System.out.println(actual);
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
