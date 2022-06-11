package com.SweetGreen.pages;

import com.SweetGreen.constant.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class MenuItemPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = ".add-to-cart-button")
    WebElement addToBag;

    @FindBy(name = "sweetgreen home")
    WebElement homeIcon;

    public MenuItemPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        PageFactory.initElements(driver, this);
    }

    public MenuItemPage addItemToBag() throws InterruptedException {

        Thread.sleep(5000);
        wait.until(elementToBeClickable(addToBag));
        new Actions(driver).moveToElement(addToBag).click().build().perform();
        System.out.println("waiting2");
        Thread.sleep(5000);
        wait.until(elementToBeClickable(addToBag));
        new Actions(driver).moveToElement(addToBag).click().build().perform();

        return this;
    }

    public HomePage navigateBackToHomePage() throws InterruptedException {
        wait.until(elementToBeClickable(homeIcon));
        homeIcon.click();
        Thread.sleep(5000);
        return new HomePage(driver);
    }
}
