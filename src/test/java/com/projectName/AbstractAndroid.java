package com.projectName;

import static org.junit.Assert.assertEquals;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractAndroid {
	  
	  private AppiumDriver<WebElement> driver;
	  Properties inprop = new Properties();
      Properties outprop = new Properties();
	  
	  @Before
	  public void setUp() throws Exception {
		InputStream input = new FileInputStream("config.properties");
		inprop.load(input);
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apk");
        File app = new File(appDir, "app-prod-rc-215-archived.apk"); // apk
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", inprop.getProperty("device.udid"));
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
        capabilities.setCapability(CapabilityType.VERSION, inprop.getProperty("platform.version.android"));
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.android.settings"); // appPackage
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        System.out.println("Appium SetUp for Android is successful and Appium Driver is launched successfully");
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	  }

	  protected void getScreenshotAs(String path) {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    try {
	      FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(path));
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	  
	  protected String getHostname() {
		  InetAddress ip;
		  String hostname = "";
		  try {
			  ip = InetAddress.getLocalHost();
		      hostname = ip.getHostName();
//		      System.out.println("Your current IP address : " + ip);
//		      System.out.println("Your current Hostname : " + hostname);
		  } catch (UnknownHostException e) {
			  e.printStackTrace();
		    }
	      return hostname;
	  }
	  
	  protected void waitToClick(String id, String xpath){
		  WebDriverWait wait = new WebDriverWait(driver, 30);
		  if(id == null){
			  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	          getDriver().findElementByXPath(xpath).click();
		  }
		  else{
			  wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
	          getDriver().findElementById(id).click();
		  }
	  }
	  
	  //往下滑動
	  protected void verticalSwipe(){ 
          Dimension size = getDriver().manage().window().getSize(); 
          int starty = (int) (size.height * 0.8); 
          int endy = (int) (size.height * 0.4); 
          int startx = size.width / 2; 
          getDriver().swipe(startx, starty, startx, endy, 1000);
	  }
	  
	  //往右滑動 
      protected void swipe(){ 
          WebDriverWait wait = new WebDriverWait(driver, 30);
          wait.until(ExpectedConditions.elementToBeClickable(By.id("com.kktv.kktv:id/surface_view")));
          Dimension size = getDriver().manage().window().getSize(); 
          int startx = (int) (size.width * 0.8); 
          int endx = (int) (size.width * 0.20); 
          int starty = size.height / 2; 
          getDriver().swipe(startx, starty, endx, starty, 1000);
      }
	  
	  protected void sendKeys(String id, String xpath, String keys){
		  WebDriverWait wait = new WebDriverWait(driver, 30);
		  if(id == null){
	          wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	          getDriver().findElementByXPath(xpath).click();
	          getDriver().findElementByXPath(xpath).clear();
	          getDriver().findElementByXPath(xpath).sendKeys(keys);
          }
          else{
              wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
              getDriver().findElementById(id).click();
              getDriver().findElementById(id).clear();
              getDriver().findElementById(id).sendKeys(keys);
          }
	  }
	 
	  public AppiumDriver<WebElement> getDriver() {
	    return driver;
	  }
	  
}