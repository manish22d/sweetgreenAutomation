package com.SweetGreen.tests.common;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.SweetGreen.api.SweetGreenAPICaller;
import com.SweetGreen.core.WebDriverManager;
import com.SweetGreen.listeners.TestListener;
import com.SweetGreen.pages.ResturantPage;
import com.SweetGreen.pages.HomePage;
import com.SweetGreen.utils.PropertyUtils;

@Listeners(TestListener.class)
public class BaseTest {
	protected WebDriver driver;
	public Properties property;
	protected String appUrl;

	public SweetGreenAPICaller sweetGreenAPICaller;
	public HomePage homepage;
	public ResturantPage cityPage;

	@BeforeSuite
	public void loadConfig() {
		property = PropertyUtils.getProperty();
		appUrl = property.getProperty("app.url");

	}

	@BeforeClass(alwaysRun = true)
	public void classSetup(ITestContext context) {
		driver = WebDriverManager.getDriver();
		driver.navigate().to(appUrl);

		context.setAttribute("webdriver", driver);
		sweetGreenAPICaller = new SweetGreenAPICaller(property);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
