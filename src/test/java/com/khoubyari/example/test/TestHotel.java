package com.khoubyari.example.test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.json.JSONArray;
import org.json.simple.JSONObject;

public class TestHotel {
	
	public static final String baseURL = "http://localhost:8090/";
	
	@Test
	public void GetAll() {
		
		RestAssured.baseURI = "http://localhost:8090/example/v1/hotels";
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");
		
		//Response response = given().contentType(ContentType.JSON).when().get(baseURL+"example/v1/hotels?page=0&size=100");
		Response response = request.get("?page=0&size=100");
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());
	}
	
	@Test
	public void Post() {
		RestAssured.baseURI = "http://localhost:8090/example/v1/hotels";
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");
	
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", "Veedu Bros");
		requestParams.put("description", "Very high, big rooms");
		requestParams.put("city", "Kochi");
		requestParams.put("rating", new Random().nextInt(5));
		System.out.println(request.body(requestParams.toString()));
		Response response = request.post("/");
		System.out.println(response.getStatusCode());
	}
	
	@Test
	public void Put() {
		RestAssured.baseURI = "http://localhost:8090/example/v1/hotels/";
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");
	
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("id", 3);
		requestParams.put("name", "Veedu Bros Rival");
		requestParams.put("description", "Very high, tiny rooms");
		requestParams.put("city", "Kochi");
		requestParams.put("rating", new Random().nextInt(5));
		System.out.println(request.body(requestParams.toString()));
		Response response = request.put("/");
		System.out.println(response.getStatusCode());
	}
	
	@Test
	public void DeleteID() {
		RestAssured.baseURI = "http://localhost:8090/example/v1/hotels/";
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");
		Response response = request.delete("2");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());
	}
	
	@Test
	public void GetID() {
		RestAssured.baseURI = "http://localhost:8090/example/v1/hotels/";
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");
		Response response = request.get("3");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());
	}

}
