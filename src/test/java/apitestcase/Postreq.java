package apitestcase;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Postreq {
	String PlaceId;
	@Test
	public void Postreq() {
	RestAssured.baseURI="https://rahulshettyacademy.com";
	
	Response res= given().log().all().queryParam("key","qaclick123").header("Content_Type","application/json").body("{\r\n"
			+ "  \"location\": {\r\n"
			+ "    \"lat\": -38.383494,\r\n"
			+ "    \"lng\": 33.427362\r\n"
			+ "  },\r\n"
			+ "  \"accuracy\": 50,\r\n"
			+ "  \"name\": \"Frontline house\",\r\n"
			+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
			+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
			+ "  \"types\": [\r\n"
			+ "    \"shoe park\",\r\n"
			+ "    \"shop\"\r\n"
			+ "  ],\r\n"
			+ "  \"website\": \"http://google.com\",\r\n"
			+ "  \"language\": \"French-IN\"\r\n"
			+ "}\r\n"
			+ "").when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().response();
	String response= res.asString();
	JsonPath js1= new JsonPath(response);
	 PlaceId=js1.getString("place_Id");
	System.out.println(PlaceId);
	
	}
	
	@Test
	public void getdata() {
		Response getres = given().log().all().queryParam("key",
				  "qaclick123").queryParam("place_id", PlaceId).when()
				  .get("maps/api/place/get/json").then().log().all().assertThat().statusCode(
				  200).extract().response();
				  
				  String Getresponse = getres.asString(); 
				  JsonPath js1 = new JsonPath(Getresponse); 
				  String Language = js1.getString("language");
				  Assert.assertEquals(Language,"French-IN");
				 

	}
	
}
