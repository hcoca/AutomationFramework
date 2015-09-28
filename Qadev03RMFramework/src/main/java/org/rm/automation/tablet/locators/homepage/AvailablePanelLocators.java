package org.rm.automation.tablet.locators.homepage;

public class AvailablePanelLocators {
	public static final String MainBusyPanelLocator = "//div[@ng-class='next._type']";
	public static final String MainFreePanelLocator = "//div[@class='tile-now free']";
	public static final String StartAvailableTimelabelLocator = "//span[@ng-bind='next._start | date:\"H:mm\"']";
	public static final String EndAvailableTimelabelLocator = "//span[@ng-bind='next._end | date:\"H:mm\"']";
	public static final String AvailableTimeLeftLocator = "//div[@ng-bind='current.remaining | timeView']";
}
