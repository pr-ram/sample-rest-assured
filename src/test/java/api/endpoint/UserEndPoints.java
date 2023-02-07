package api.endpoint;


import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import api.payload.User;



// Created to perform CRUD operation for User module api

public class UserEndPoints {
	
	public static Response creatUser(User payload){
	Response response =given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(Routes.post_Url);
	
		return response;
	
	}
	
	public static Response readUser(String userName) {
		
			Response response = given()
				.pathParam("username", userName)
			.when()
				.get(Routes.get_Url);
			return response;
		
	}
	
	
	public static Response updateUser(User payload,String userName) {
			
			Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
			.when()
				.put(Routes.put_Url);
			return response;
			
	}
	
	public static Response deleteUser(String userName) {
			
			Response response = given()
				.pathParam("username", userName)
			.when()
				.delete(Routes.delete_Url);
			return response;
			
		}

}
