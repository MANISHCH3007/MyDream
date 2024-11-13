package apitestcase;

import static io.restassured.RestAssured.*;

import java.security.PublicKey;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import resource.payload;

public class Reqres {

	@Test(priority = 0, description = "creating a new place on the cloud", invocationCount =3)
	public void createdata() {
		RestAssured.baseURI = "https://reqres.in";
		Response res = given().log().all().queryParam("page", "2").body(payload.Bodyid())
				.header("Content-type", "application/json").when().post("api/users").then().log().all().assertThat()
				.statusCode(201).extract().response();
	}

	@Test(priority = 1)
	public void updatedata() {
		Response res1 = given().log().all().queryParam("user", "2")
				.body(" {\r\n" + "    \"email\": \"george.bluth@reqres.in\",\r\n"
						+ "    \"first_name\": \"manish\",\r\n" + "    \"last_name\": \"CHAUHAN\",\r\n"
						+ "    \"avatar\": \"https://reqres.in/img/faces/1-image.jpg\"\r\n" + "  }")
				.header("Content-type", "application/json").when().put("api/users/2").then().log().all().assertThat()
				.statusCode(200).extract().response();
	}

	@Test(priority = 2)
	public void Retrivedata() {
		Response res2 = given().log().all().queryParam("page", "2").header("Content-type", "application/json").when()
				.get("api/users").then().log().all().assertThat().statusCode(200).extract().response();
	}

	@Test(priority = 2)
	public void Deletedata() {
		Response res3 = given().log().all().queryParam("users", "1").header("Content-type", "application/json").when()
				.delete("api/users").then().log().all().assertThat().statusCode(204).extract().response();
	}
}
