package com.SweetGreen.pages;

import com.SweetGreen.constant.Constants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "#locationInput input")
    WebElement searchField;

    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement acceptCookieBtn;
    @FindBy(css = ".bag-count")
    WebElement itemCount;

    @FindBy(css = ".tab-bar button")
    List<WebElement> tabBar;
    @FindBy(css = "#restaurants .card-content")
    List<WebElement> searchResult;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        PageFactory.initElements(driver, this);
    }

    public HomePage acceptCookie() {
        if (acceptCookieBtn.isDisplayed())
            acceptCookieBtn.click();
        return this;
    }

    public HomePage selectPickupOutpost() {
        wait.until(visibilityOfAllElements(tabBar));
        tabBar.get(0).click();
        return this;
    }

    public ResturantPage searchText(String text) throws InterruptedException {
        wait.until(elementToBeClickable(searchField));
        searchField.click();
        searchField.sendKeys(text);
        searchField.sendKeys(Keys.ENTER);

        Thread.sleep(5000);
        wait.until(visibilityOfAllElements(searchResult));
        searchResult.stream().findFirst()
                .orElse(null).click();
        return new ResturantPage(driver);
    }

    public int getItemsInCart(){
        wait.until(elementToBeClickable(itemCount));
        return Integer.parseInt(itemCount.getText());
    }


}
