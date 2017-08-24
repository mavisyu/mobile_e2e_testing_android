package com.projectName;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SetUpAndroid{
	  
	  private AppiumDriver<WebElement> driver;
	  Properties inprop = new Properties();
      Properties outprop = new Properties();
	  
	  @Before
	  public void setUp() throws Exception {
		InputStream input = new FileInputStream("config.properties");
		inprop.load(input);
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apk");
        File app = new File(appDir, "app-dev-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", inprop.getProperty("device.udid"));
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
        capabilities.setCapability(CapabilityType.VERSION, inprop.getProperty("platform.version.android"));
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.appPackageName");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        System.out.println("Appium SetUp for Android is successful and Appium Driver is launched successfully");
	  }
	  
}