package api.test;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
/**
 * This test cases are run by passing data from excel file through POJO class
 */
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoint.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDrivenTests {
	
	/**
	 * Testing create user 
	 * */
	
	@Test(priority=1, dataProvider = "Data",dataProviderClass = DataProviders.class)	
	void testPostUser(String UserId, String UserName, String FirstName, String LastName, String Email, String Password, String Phone) {
		
		User payLoad = new User();
		payLoad.setId(Integer.parseInt(UserId));
		payLoad.setFirstname(FirstName);
		payLoad.setLastname(LastName);
		payLoad.setEmail(Email);
		payLoad.setUsername(UserName);
		payLoad.setPhone(Phone);
		payLoad.setPassword(Password);
		
		
		Response response=UserEndPoints.creatUser(payLoad);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority=2, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	void testDeleteUser(String UserName) {
		
		Response response =UserEndPoints.deleteUser(UserName);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
}
