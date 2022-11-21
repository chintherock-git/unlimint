package PageFactory;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


import java.io.File;

import org.json.JSONObject;

import Driver.Driverfactory;
import Utilities.Log;

import org.json.JSONArray;


public class FetchRandomUser {
	
	public void fetchResponse() {
		
		RestAssured.baseURI="https://randomuser.me/api/";
		int statusCode= given().header("Content-Type","application/json").when().get(RestAssured.baseURI).getStatusCode();
		String response_any=given().header("Content-Type","application/json").when().get(RestAssured.baseURI).asString();
		
		if(statusCode==200 && response_any!=null) {
		String response= given().header("Content-Type","application/json").when().get().then().extract().response().asString();
		//Object obj = new JSONParser().parse(response);
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		//int count = js.getInt(results);
		String first= js.getString("results[0].name.first");
		String last= js.getString("results[0].name.last");
		String street_number= js.getString("results[0].location.street.number");
		String street_name= js.getString("results[0].location.street.name");
		String city= js.getString("results[0].location.city");
		String state= js.getString("results[0].location.state");
		String country= js.getString("results[0].location.country");
		String code= js.getString("results[0].location.postcode");
		String email= js.getString("results[0].email");
		String password= js.getString("results[0].login.password");
		String phone= js.getString("results[0].phone");
		Log.info("FirstName fetched is "+first);
		Log.info("LastName fetched is "+last);
		Log.info("Street Number fetched is "+street_number);
		Log.info("Street Name fetched is "+street_name);
		Log.info("City fetched is "+city);
		Log.info("State fetched is "+state);
		Log.info("Country fetched is "+country);
		Log.info("Postal Code fetched is "+code);
		Log.info("Password fetched is "+password);
		Log.info("Email fetched is "+email);
		Log.info("Phone fetched is "+phone);
		//System.out.println(first+last+street_number+street_name+city+state+country+code+email+password+phone);
		
		
		Driverfactory.setPropValues("first", first);
		Driverfactory.setPropValues("last", last);
		Driverfactory.setPropValues("street_number", street_number);
		Driverfactory.setPropValues("street_name", street_name);
		Driverfactory.setPropValues("city", city);
		Driverfactory.setPropValues("state", state);
		Driverfactory.setPropValues("code", code);
		Driverfactory.setPropValues("country", country);
		Driverfactory.setPropValues("email", email);
		Driverfactory.setPropValues("phone", phone);
		Driverfactory.setPropValues("password", password);
		}
	
		else {
			Log.fatal("API did not give 200 or Response is EMPTY");
		}
	
	}

}

