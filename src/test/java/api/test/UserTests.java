package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

/**
 * 
 * This test cases are run by passing data from faker through POJO class
 */

public class UserTests {

	Faker faker;
	User userPayload;

	@BeforeClass
	public void setupDate() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setUsername(faker.name().username());
		userPayload.setPhone(faker.phoneNumber().phoneNumber());

	}

	/***
	 * Test to create a user
	 */
	@Test(priority = 1)
	void testPostUser() {
		Response response = UserEndPoints.creatUser(userPayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}

	/***
	 * Test to get the created user
	 */
	@Test(priority = 2)
	void testGetUser() {
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

	/***
	 * Test to update created user
	 */
	@Test(priority = 3)
	void testUpdateUser() {
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());

		Response response = UserEndPoints.updateUser(userPayload, this.userPayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		// Validation after update
		Response responseAfterupdate = UserEndPoints.readUser(userPayload.getUsername());
		AssertJUnit.assertEquals(responseAfterupdate.getStatusCode(), 200);

	}

	/***
	 * Test to delete user
	 */
	@Test(priority = 4)
	void testDeleteUser() {
		Response response = UserEndPoints.deleteUser(userPayload.getUsername());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

}
