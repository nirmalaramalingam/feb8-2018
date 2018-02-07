package Generic_Component;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import Scenario_Component.ExtentReports;
import io.appium.java_client.android.AndroidDriver;

public class Base_Class {
public Process process;
public AndroidDriver driver;

public static ExtentReports extentreport;
public static ExtentTest extenttest;

public void Start_server() throws IOException
{
	String start="D:\\AllSoftware\\AppiumInstalled\\node.exe D:\\AllSoftware\\AppiumInstalled\\node_modules\\appium\\bin\\appium.js";
	process = Runtime.getRuntime().exec(start);
	if(process != null)
	{
		System.out.println("Appium server started");
	}
	else
	{
		System.out.println("Appium server not started");
	}
	extentreport.endtest();
	extentreport.flush();
}

public void Stop_server()
{
	if(process != null)
	{
		System.out.println("Appium server not stopped");
		process.destroy();
	}
}
public void Launch_App() throws MalformedURLException, InterruptedException
{
	DesiredCapabilities cap= new DesiredCapabilities();
	cap.setCapability("deviceName", "GalaxyJ7");
	cap.setCapability("platformName", "Android");
	cap.setCapability("platformVersion", "6.0.1");
	
	cap.setCapability("appActivity", "com.bigbasket.mobileapp.activity.SplashActivity");
	cap.setCapability("appPackage", "com.bigbasket.mobileapp");
	
	driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
	Thread.sleep(20000);
	}
	public String Capture_Screenshot(String TC_ID, String Order_Set) throws IOException
	{
		Date date = new Date();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String str= df.format(date) + ".png";
		
		TakesScreenshot screenshot =(TakesScreenshot) driver;
		File scr= screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr, new File("D:\\Mobile easylearn\\Framework_Data\\BB_6Feb2018\\" + TC_ID + "-" + Order_Set + "-"+ str));
		
		String path="D:\\Mobile easylearn\\Framework_Data\\BB_6Feb2018\\" + TC_ID + "-" + Order_Set + "-" + str;
		return path;
	}
	public void Explicit_wait(WebElement element, long ti)
	{
		WebDriverWait wait = new WebDriverWait(driver, ti);
		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}
	@BeforeSuite
	public void Reports()
	{
		Date date= new Date();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String str2= df.format(date);
		
		extentreports = new ExtentReports("D:\\Mobile easylearn\\Framework_Data\\BB_6Feb2018\\" + "BB_Project" + "-" + str2 + ".html",false);
	}
}
