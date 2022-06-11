package com.SweetGreen.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SweetGreen.constant.Constants;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class ResturantPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(css = ".product-card-wrap")
	List<WebElement> salads;

	public ResturantPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
		PageFactory.initElements(driver, this);
	}


	public MenuItemPage selectSalad(String saladName){
		wait.until(visibilityOfAllElements(salads));
		WebElement item = salads.stream().filter(salad->salad.getText().contains(saladName)).findFirst().orElseThrow(null);
		wait.until(elementToBeClickable(item));
		item.click();
		return new MenuItemPage(driver);
	}
}
