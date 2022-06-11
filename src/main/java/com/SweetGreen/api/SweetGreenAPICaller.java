package com.SweetGreen.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpStatus;

public class SweetGreenAPICaller {
	Properties prop;
	public APIHelper api;
	protected String apiUrl;
	protected String basePath;
	protected String apiKey;

	public SweetGreenAPICaller(Properties prop) {
		this.prop = prop;
		apiUrl = prop.getProperty("api.baseURI");
		basePath = prop.getProperty("api.basePath");
		api = new APIHelper();
		api.setBaseURI(apiUrl);
		api.setBasePath(basePath);

	}

	public String getMenuIdFromRestaurantName(String restaurantName) {
		api.initiateRequest();
		api.setAPIEndpoint("/restaurants/{restaurant_name}");
		api.updatePathParam("restaurant_name",restaurantName);
		api.getResource();
		assertThat(api.response.getStatusCode(), is(equalTo(HttpStatus.SC_OK)));

		return api.response.jsonPath().getString("restaurant.menu_id");
	}

	public String getMenuFromMenuIdF(String menuId) {
		api.initiateRequest();
		api.setAPIEndpoint("/menus/{menu_id}");
		api.updatePathParam("menu_id",menuId);
		api.getResource();
		assertThat(api.response.getStatusCode(), is(equalTo(HttpStatus.SC_OK)));

		return api.response.asPrettyString();
	}
}
