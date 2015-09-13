package org.rm.automation.utils;

import java.io.File; 
import java.io.IOException; 
import java.text.DateFormat; 
import java.text.SimpleDateFormat; 
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils; 
import org.openqa.selenium.OutputType; 
import org.openqa.selenium.TakesScreenshot; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Class that manages screenshots.
 */
public class TestListener {
	static String filePath = null;
	
	public static String takeScreenShot(String testName){
		Properties settings = ReadPropertyValues
				.getPropertyFile("./Config/settings.properties");
		
		testName = testName.replace(" ", "_");
		
		WebDriver driver = BrowserManager.getDriver(settings.getProperty("browser"));
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa"); /**/
		String destDir = "./reports/screenshots"; 
		new File(destDir).mkdirs(); 
		String destFile = testName +"-"+dateFormat.format(new Date()) + ".png"; 
		saveFile(scrFile, destFile, destDir);
		
		filePath = "..\\screenshots\\" + destFile;
		
		return filePath;
	}
	
	//The below method will save the screenshot.
	public static void saveFile(File sourceFile, String destinyFile, String destinyPath){
		try { 
			FileUtils.copyFile(sourceFile, new File(destinyPath + "/" + destinyFile)); 
			System.out.println("*** Screen shot stored in "+filePath+" ***");
		} 
		catch (IOException e) { 
			e.printStackTrace();
		} 
	}
}
