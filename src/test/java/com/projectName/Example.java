package com.projectName;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Example extends AbstractAndroid {

    @Test
    public void example() throws Exception{
    	WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        Date datetime = new Date();
        SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ft = new SimpleDateFormat("HH-mm-ss");
        String FPath = inprop.getProperty("filePath") + fd.format(datetime) + "/";   
        String testName="Example";
        
        try{
        	
        	} catch (Exception e) {
        		System.out.println("Test Failure: "+testName);
        		getScreenshotAs(FPath + ft.format(datetime) + testName + "_error.jpg");
        		fail(e.getMessage());
        	  }
        
    }

}
