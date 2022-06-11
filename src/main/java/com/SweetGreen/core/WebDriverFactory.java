package com.SweetGreen.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.SweetGreen.constant.Constants;
import com.SweetGreen.listeners.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
	static WebDriver driver = null;
	public WebEventListener eventListener;
	public EventFiringWebDriver e_driver;

	protected WebDriver initializeDriver() {
//		DriverType browser = DriverType.valueOf(System.getProperty("browser").toUpperCase());
		DriverType browser = DriverType.valueOf("chrome".toUpperCase());
		switch (browser) {
		case FIREFOX:
			driver = getFirefoxDriver();
			break;
		case CHROME:
			driver = getChromeDriver();
			break;
		case EDGE:
			driver = getInternetExplorerDriver();
			break;
		}

		e_driver = new EventFiringWebDriver(driver);

		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);

		return driver;
	}

	private WebDriver getFirefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	}

	private WebDriver getChromeDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		chromeOptions.setExperimentalOption("prefs", prefs);

		WebDriverManager.chromedriver().setup();
		return new ChromeDriver(chromeOptions);
	}

	private WebDriver getInternetExplorerDriver() {
		WebDriverManager.iedriver().setup();
		return new InternetExplorerDriver();
	}
}
