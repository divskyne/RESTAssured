package com.khoubyari.example.test;

import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class CucumberTestHotel {
	
	public static final String baseURL = "http://localhost:8090/";
	
	private RequestSpecification request;
	private Response response;
	
	@Given("^the content type is JSON$")
	public void the_content_type_is_JSON() {
		RestAssured.baseURI = "http://localhost:8090/example/v1/hotels";
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
	}

	@When("^a user retrieves all the hotels$")
	public void a_user_retrieves_all_the_hotels() {
		response = request.get("?page=0&size=100");
	}

	@Then("^the status code reads (\\d+)$")
	public void the_status_code_reads(int arg1) {
		Assert.assertEquals(arg1, response.getStatusCode());
	}

	@When("^a user creates a hotel with name \"([^\"]*)\"$")
	public void a_user_creates_a_hotel_with_name(String arg1) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("city", "Kochi");
		requestParams.put("description", "Very high, big rooms");
		requestParams.put("name", arg1);
		requestParams.put("rating", new Random().nextInt(5));
		request.body(requestParams.toString());
		response = request.post("/");
		Assert.assertEquals(201,response.getStatusCode());
	}
	
	@Then("^the hotel with name (\\d+) and \"([^\"]*)\" must be there$")
	public void the_hotel_with_name_and_must_be_there(int arg1, String arg2) throws Throwable {
		response = request.get("/"+arg1);
		JSONObject obj = new JSONObject (response.body().asString());
		Assert.assertEquals(arg2, obj.get("name"));
	}

	@Given("^a hotel exists with the ID (\\d+)$")
	public void a_hotel_exists_with_the_ID(int arg1) {
		the_content_type_is_JSON();
		response = request.get("/"+arg1);
		JSONObject obj = new JSONObject (response.body().asString());
		Assert.assertEquals(arg1, obj.get("id"));
	}

	@When("^a user retrieves the hotel by the id (\\d+)$")
	public void a_user_retrieves_the_hotel_by_the_id(int arg1) {
		response = request.get("/"+arg1);
	}

	@Then("^there is a name available$")
	public void there_is_a_name_available() {
		JSONObject obj = new JSONObject (response.body().asString());
		Assert.assertEquals(String.class, obj.get("name").getClass());
	}

	@Then("^the name is equal to \"([^\"]*)\"$")
	public void the_name_is_equal_to(String arg1) {
		JSONObject obj = new JSONObject (response.body().asString());
		Assert.assertEquals(arg1, obj.get("name"));
	}
	
	@Then("^all the \"([^\"]*)\" are valid$")
	public void all_the_are_valid(String arg1) {
		JSONObject obj = new JSONObject (response.body().asString());
		JSONArray jArray = obj.getJSONArray("content");
		  for (Object object : jArray) 
		  {
			  JSONObject o = (JSONObject) object;
			  System.out.println(o.getString("name"));
			  if (o.getString("name").equals(arg1)) {
				  Assert.assertEquals(arg1, o.getString("name"));
			}
		  }
	}
}
