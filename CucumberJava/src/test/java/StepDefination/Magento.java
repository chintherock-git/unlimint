package StepDefination;


import org.openqa.selenium.WebDriver;

import Driver.Driverfactory;

import static org.junit.Assert.*;

import PageFactory.Home_PF;
import PageFactory.Create_Account_PF;
import PageFactory.MyAccount_PF;
import Utilities.Log;
import PageFactory.FetchRandomUser;


import io.cucumber.java.en.*;

import java.util.Properties;

public class Magento {

	public static  WebDriver driver;
	public Home_PF home;
	public MyAccount_PF account;
	public Create_Account_PF createaccount;
	public FetchRandomUser random;

	public String config_path = System.getProperty("user.dir")+System.getProperty("file.separator")+"src/main/java/Utilities/config.properties";
	public static String URL="https://magento.softwaretestingboard.com/";
	public static String createAccountURL="https://magento.softwaretestingboard.com/customer/account/create/";
	public static Properties prop;
	String message_fetched="";
	String fname = Driverfactory.getPropValues("first");
	String lname = Driverfactory.getPropValues("last");
	String street_number = Driverfactory.getPropValues("street_number");
	String street_name = Driverfactory.getPropValues("street_name");
	String city = Driverfactory.getPropValues("city");
	String state = Driverfactory.getPropValues("state");
	String country = Driverfactory.getPropValues("country");
	String code = Driverfactory.getPropValues("code");
	String email = Driverfactory.getPropValues("email");
	String pwd = Driverfactory.getPropValues("password");
	String phone = Driverfactory.getPropValues("phone");
	String email_duplicate= Driverfactory.getPropValues("email_duplicate");
	String error= Driverfactory.getPropValues("error");
	String message= Driverfactory.getPropValues("success_message");
	

	
	@When("randomUser api gives response")
	public void randomUser_api_gives_response() {
		random= new FetchRandomUser();
		random.fetchResponse();
	}

	@Given("user is on home page and clicks create account")
	public void user_is_on_home_page_and_clicks_create_account() {
		System.out.println("User is in login page "+Driverfactory.getDriver().getCurrentUrl());
		assertEquals(Magento.URL,Driverfactory.getDriver().getCurrentUrl());
		home=new Home_PF(Driverfactory.getDriver());
		home.navigateCreateAccount();
		assertEquals(Magento.createAccountURL,home.checkCartURL());
	}

	@When("user enters valid fname and lname and mail and password and creates account")
	public void user_enters_valid_fname_and_lname_and_mail_and_password_and_creates_account() {
		try {
			createaccount= new Create_Account_PF(Driverfactory.getDriver());
			createaccount.createAccountValue(fname,lname,email,pwd);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	@And("user fetches the account details for email")
	public void user_fetches_the_account_details_for_email() {
		try {
			account= new MyAccount_PF(Driverfactory.getDriver());
			assertTrue(account.validateContactInfo(email));

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Then("user clicks Edit Address to add shipping address")
	public void user_clicks_EditAddress_to_add_shipping_address() {

		try {
			account.addAddress(phone, street_number, street_name, city, state, code, country);
			//String display_actual=search_pf.selectdisplay(display);
			//assertEquals(display,display_actual);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@When("add item to cart for size {string}")
	public void select_Brand_Material_as_for_size(String size) {
		account.selectItemForCart(size);
		account.addItemToCart();
	}
	
	@And("checkout item in cart and validate cart count")
	public void checkout_item_in_cart_and_validate_cart_count() {
		account.checkOut();
		Log.info("Order is successfully placed with order Number as "+account.getOrderNumber());

	}
	
	@And("validate purchase success message")
	public void validate_purchase_success_message() {
		String success= account.validatePurchaseMessage();
		assertEquals(message,success);
	}
	
	@When("user enters already existing mail and views and validates the error message")
	public void user_enters_already_existing_mail_and_views_and_validates_the_error_message() {
		try {
			message_fetched=createaccount.validateAccount(fname,lname,email_duplicate,pwd);
			assertEquals(error.trim(),message_fetched.trim());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}


