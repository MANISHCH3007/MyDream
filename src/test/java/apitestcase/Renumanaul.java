package apitestcase;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;

public class Renumanaul {
	@Test
	public void createplace() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response res=given().queryParam("key ", "qaclick123").log().all()
				.body("{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
						+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Frontline house\",\r\n"
						+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
						+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n"
						+ "    \"shoe park\",\r\n" + "    \"shop\"\r\n" + "  ],\r\n"
						+ "  \"website\": \"http://google.com\",\r\n" + "  \"language\": \"French-IN\"\r\n" + "}\r\n"
						+ "")
				.header("Content-type", "application/json").when().post("maps/api/place/add/json").then().log().all()
				.assertThat().statusCode(200).extract().response();
System.out.println(res);
	}

}
