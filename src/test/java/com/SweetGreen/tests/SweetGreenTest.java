package com.SweetGreen.tests;

import com.SweetGreen.pages.HomePage;
import com.SweetGreen.pages.MenuItemPage;
import com.SweetGreen.pages.ResturantPage;
import com.SweetGreen.tests.common.BaseTest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.Test;

import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

public class SweetGreenTest extends BaseTest {


    @Test
    public void verifySaladAddedToBag() throws InterruptedException {
        homepage = new HomePage(driver);
        ResturantPage resturantPage = homepage
//				.acceptCookie()
                .selectPickupOutpost().searchText("Marina del Rey");

        MenuItemPage menuItem = resturantPage.selectSalad("Kale Caesar");
        menuItem.addItemToBag();
        System.out.println(menuItem.navigateBackToHomePage().getItemsInCart());

        assertThat(menuItem.navigateBackToHomePage().getItemsInCart(), is(greaterThanOrEqualTo(1)));
    }

    @Test
    public void verifySaladAvailableInAPIResponse() {
        String menuId = sweetGreenAPICaller.getMenuIdFromRestaurantName("marina-del-rey");
        JsonObject menu_response = JsonParser.parseString(sweetGreenAPICaller.getMenuFromMenuIdF(menuId)).getAsJsonObject();
        JsonArray products = menu_response.getAsJsonArray("products");
        assertThat("kale caesar was not fount ", StreamSupport.stream(products.spliterator(), false)
                .map(JsonElement::getAsJsonObject)
                .anyMatch(product -> product.get("name").getAsString().equals("kale caesar")));
    }
}
