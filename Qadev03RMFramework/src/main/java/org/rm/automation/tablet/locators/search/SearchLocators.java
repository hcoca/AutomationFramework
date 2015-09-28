package org.rm.automation.tablet.locators.search;

public class SearchLocators {
	
	public static final String roomPath = "//button[@class='room-box ng-scope']";
	public static final String advancedButtonPath = "advanced-search";//id
	public static final String advancedLabelPath = "//span[@ng-show='advancedSearchOn']";
	public static final String roomNameTextboxPath = "txtRoomName";//id
	public static final String capacityTextboxPath = "txtMinimumCapacity";//id
	public static final String locationComboBoxPath = "listLocation";//id
	public static final String clearButtonPath = "//button[@class='btn-default btn btn-clear']";
	public static final String meetingTitlePath = "//div[@class='item-title']";
	public static final String scheduleTablePath = "//div[@class='vis-foreground']";
	public static final String resourcesListPath = "//div[@class='resource-search pull-left resources-height ng-scope']";
	public static final String resourceButtonPath = ".//div[@class='text-center resource-button pull-left']";
	public static final String notFoundMessagePath = "//div[@class='well']";
	public static final String roomTitle = "//span[contains(.,'Search')]";
	public static final String locationOptionPath = "//option[@label='location']";
	public static final String selectedPath = "//option[@selected='selected']";
}
