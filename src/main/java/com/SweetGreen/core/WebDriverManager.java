package com.SweetGreen.core;

import org.openqa.selenium.WebDriver;

public final class WebDriverManager {
	static WebDriver driver = null;

	public static WebDriver getDriver() {
		if(driver==null) {
			WebDriverFactory wf= new WebDriverFactory();
			driver = wf.initializeDriver();
			return driver;
		}else {
			return driver;
		}
			
	}
}
