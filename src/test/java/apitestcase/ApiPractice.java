package apitestcase;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload.BodyId;
import pojoResponsepayload.CreateplaceResponse;
import requestpojo.Childlocation;
import requestpojo.CreateData;

import static io.restassured.RestAssured.*;// 1. imp.
import static org.hamcrest.Matchers.*;// 2. imp.
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class ApiPractice {
	
	CreateData r = new CreateData();
	Childlocation c = new Childlocation();

	public static String placeid;

	@Test(priority = 0)
	public void createplace() {
		r.setLocation(c);
		r.setAccuracy(30);
		r.setName("Lucky");
		r.setPhone_number("987542345");
		r.setAddress("Noida");
		r.setWebsite("google.com");
		r.setLanguage("Punjabi");
		c.setLat(-38.383494);
		c.setLng(33.427362);

		List<String> typelist = new ArrayList<String>();
		typelist.add("test");
		typelist.add("Automation");
		r.setTypes(typelist);

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		CreateplaceResponse p = given().log().all().queryParam("key", "qaclick123").body(r)
				.header("Content-Type", "application/json").when().post("maps/api/place/add/json").then().log().all()
				.assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)").body("status", equalTo("OK"))
				.body("scope", equalTo("APP")).extract().response().as(CreateplaceResponse.class);
		String status = p.getStatus();
		assertEquals(status, "OK");
		
		String scope = p.getScope();
		assertEquals(scope, "APP");

		placeid= p.getPlace_id();

//		String response = res.asString();
//		JsonPath js = new JsonPath(response);
//		placeid = js.getString("place_id");

		// System.out.println(placeid);
	}

	@Test(priority = 1)
	public void updateplaceData() {

		Response res = given().log().all().queryParam("key", "qaclick123")
				.body("{\r\n" + "\"place_id\":\"" + placeid + "\",\r\n" + "\"address\":\"Gurgaon\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.header("Content-Type", "application/json").when().put("maps/api/place/update/json").then().log().all()
				.assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response();
		String updateres = res.asString();
		JsonPath js = new JsonPath(updateres);
		String sucessMessage = js.getString("msg");
		assertEquals(sucessMessage, "Address successfully updated");

	}

	@Test(priority = 2)
	public void getplaceid() {

		Response getRes = given().log().all().queryParam("place_id", placeid).queryParam("key", "qaclick123")
				.header("Content-Type", "application/json").when().get("maps/api/place/get/json").then().log().all()
				.assertThat().statusCode(200).extract().response();

		String response = getRes.asString();
		JsonPath js = new JsonPath(response);
		String address = js.getString("address");
		assertEquals("Gurgaon", address);

		String accuracy = js.getString("accuracy");
		assertEquals("30", accuracy);

	}

	@Test(priority = 3)
	public void deleteplaceid() {
		Response res = given().log().all().queryParam("key", "qaclick123")
				.body("{\r\n" + "\r\n" + "    \"place_id\":\"" + placeid + "\"\r\n" + "}\r\n" + "")
				.header("Content-Type", "application/json").when().delete("maps/api/place/delete/json").then().log()
				.all().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response();
		String deleteres = res.asString();
		JsonPath js = new JsonPath(deleteres);
		String actualstatus = js.getString("status");
		assertEquals(actualstatus, "OK");

	}

}
