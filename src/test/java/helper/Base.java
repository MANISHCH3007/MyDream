package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Base {
	RequestSpecification req;
	public static RequestSpecification setup() throws IOException {
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri(getConfigData("baseURI")).addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
		.build();
		return req;
		
	}
public static String getConfigData(String key) throws IOException {
	FileInputStream file = new FileInputStream(System.getProperty("user.dir")+ "/src/test/java/resource/env.properties");
	Properties prop = new Properties();
	prop.load(file);
	return prop.getProperty(key);
	
	
	
}
}
