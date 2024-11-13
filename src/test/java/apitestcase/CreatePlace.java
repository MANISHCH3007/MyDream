package apitestcase;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojoResponsepayload.CreateplaceResponse;
import requestpojo.Childlocation;
import requestpojo.Createapl;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import helper.Base;

public class CreatePlace {
	String PlaceId;
	Userinformation user = new Userinformation();
	Faker f = new Faker();
	
	@Test (priority = 0, description = "creating a new place on the cloud")//, invocationCount = 1
	public void createNewPlaceGoggle() throws IOException {
		Createapl p = new Createapl();
		Childlocation c= new Childlocation();	
		p.setAccuracy(50);
		p.setAddress(f.address().city());
		p.setName(f.name().fullName());
		p.setPhone_number(f.phoneNumber().cellPhone());
		p.setLocation(c);
		c.setLat(-38.383494);
		c.setLng(33.427362);
		List<String>typelist= new ArrayList<String>();
		typelist.add("shoe park");
		typelist.add("shop");
		p.setTypes(typelist);
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
	
	
		CreateplaceResponse res = given().log().all().spec(Base.setup())
				.body(p)
				.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract()
				.response().as(CreateplaceResponse.class);

String Actualstatus=res.getStatus();
Assert.assertEquals(Actualstatus, "OK");

String Actualscope= res.getScope();
Assert.assertEquals(Actualscope, "APP");

String Place_id= res.getPlace_id();
System.out.println(Place_id);
	

	}
		
	/*
	 * // UPDATE Address
	 * 
	 * @Test (priority = 1) public void updateAddress() { Response updateRes
	 * =given().log().all().queryParam("key", "qaclick123").queryParam("place_id",
	 * PlaceId).header("Content-Type","application/json").body("{\r\n" +
	 * "\"place_id\":\""+PlaceId+"\",\r\n" + "\"address\":\"Gurgaon\",\r\n" +
	 * "\"key\":\"qaclick123\"\r\n" + "}").
	 * when().put("maps/api/place/update/json").then().log().all().assertThat().
	 * statusCode(200).body("msg",equalTo("Address successfully updated")).extract()
	 * .response();
	 * 
	 * String UpdateRes = updateRes.asString(); //"+PlaceId+"\ variable
	 * 
	 * JsonPath js2 = new JsonPath(UpdateRes);
	 * 
	 * String SuccessMsg = js2.getString("msg");
	 * 
	 * Assert.assertEquals(SuccessMsg,"Address successfully updated");
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Get the existing details
	 * 
	 * @Test (priority = 2) public void getData() { Response getres =
	 * given().log().all().queryParam("key", "qaclick123").queryParam("place_id",
	 * PlaceId).when()
	 * .get("maps/api/place/get/json").then().log().all().assertThat().statusCode(
	 * 200).extract().response();
	 * 
	 * String Getresponse = getres.asString(); JsonPath js1 = new
	 * JsonPath(Getresponse); String Language = js1.getString("language");
	 * Assert.assertEquals(Language,"French-IN");
	 * 
	 * }
	 */
	}

//https://rahulshettyacademy.com/maps/api/place/add/json?key= qaclick123 

