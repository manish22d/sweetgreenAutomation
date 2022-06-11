package com.SweetGreen.api;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIHelper {
	public RequestSpecification request;

	public String baseURI;
	public String basePath;

	public String endPoint;
	public Response response;
	public Map<String, String> headers;
	public Map<String, String> queryParams;
	public Map<String, String> pathParams;

	public APIHelper() {
		headers = new HashMap<String, String>();
		queryParams = new HashMap<String, String>();
		pathParams = new HashMap<String, String>();

	}

	public String getBaseURI() {
		return baseURI;
	}

	public void setBaseURI(String baseURI) {
		this.baseURI = baseURI;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void initiateRequest() {
		endPoint = "";
		headers = new HashMap<String, String>();
		queryParams = new HashMap<String, String>();
		pathParams = new HashMap<String, String>();

	}

	/**
	 * This method is use to update endpoint
	 * 
	 * @param endPoint
	 */
	public void setAPIEndpoint(String endPoint) {
		this.endPoint=endPoint;
//		this.basePath += basePath;
	}

	/**
	 * set header to request
	 * 
	 * @param headerKey
	 * @param headerValue
	 */
	public void setHeader(String headerKey, String headerValue) {
		headers.put(headerKey, headerValue);
	}

	/**
	 * update query param in map
	 * 
	 * @param queryParam
	 */
	public void setQueryParam(Map<String, String> queryParam) {
		queryParams.putAll(queryParam);
	}

	/**
	 * update path param
	 * 
	 * @param paramName
	 * @param paramValue
	 */
	public void updatePathParam(String paramName, String paramValue) {
		pathParams.put(paramName, paramValue);
	}

	/**
	 * returns response body
	 * 
	 * @return
	 */
	public String getResponseString() {
		return response.getBody().asString();
	}

	/**
	 * update request body
	 * 
	 * @param requestPayload
	 */
	public void updateRequest(JsonObject requestPayload) {
		request.body(requestPayload);
	}

	/**
	 * trigger Get Request
	 */
	public void getResource() {
		RestAssured.baseURI = baseURI;
		RestAssured.basePath = basePath;
		request = RestAssured.given();
		request.log().all();
		request.basePath(basePath+endPoint);
		request.pathParams(pathParams);
		request.queryParams(queryParams);
		request.headers(headers);
		response = request.get();
		response.then().log().all();
	}

	/**
	 * trigger POST Request
	 * 
	 * @param requestPayload
	 */
	public void PostRequest(String requestPayload) {
		RestAssured.baseURI = baseURI;
		RestAssured.basePath = basePath;
		request = RestAssured.given();
		request.log().all();
		request.basePath(basePath+endPoint);
		request.pathParams(pathParams);
		request.queryParams(queryParams);
		request.headers(headers);
		response = request.body(requestPayload).post();
		response.then().log().all();

	}
}
