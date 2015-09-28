package org.rm.automation.admin.locators.conferenceRooms;

public class ConferenceRoomsLocators {
	
	public static final String EnabledColumnBtnLocator = "//div[text()='Enabled']";
	public static final String OutOfOrderColumnBtnLocator = "//div[contains(text(),'Order')]";
	public static final String RoomColumnBtnLocator = "//div[text()='Room']";
	public static final String ConferenceRoomLocator = "//*[@id='roomsGrid']/div[2]/div/div/div[3]/div[2]/div/span[2]";
	public static final String TotalItemsLabelLocator = "//span[@class='ngLabel ng-binding']";
	public static final String ConferenceRoomInfoLabelLocator = "//*[@id='roomsGrid']/div[1]/div[1]/div";
	public static final String RoomsContainerLocator = "//div[@class='ngCanvas']";
	public static final String ResourceContainerLocator = "//div[@class='row ng-scope']";
	public static final String OutOOrdericonOrderLocator = "//span[@popover-title='Temporarily Out of Order']";
	public static final String OutOOrdericonmaintenanceLocator = "//span[@popover-title='Closed for maintenance']";
	public static final String OutOOrdericonreparationsLocator = "//span[@popover-title='Closed for reparations']";
	public static final String OutOOrdericoneventLocator = "//span[@popover-title='Reserved for a special event']";
	public static final String RoomLocator = ".//div[@ng-style='rowStyle(row)']";
	public static final String RoomLabelLocator = ".//span[@class='ng-binding']";
	public static final String EnabledRoomLabelLocator = "//span[@ng-show='row.entity.enabled']";
	public static final String ResourceBoxLocator = ".//div[@class='animate-if ng-scope']";
	public static final String ResourceIconName = "ng-if";
	public static final String ResourceOnUpperPaneLocator = "//span[@ng-model='resource.isSelected']";
}
