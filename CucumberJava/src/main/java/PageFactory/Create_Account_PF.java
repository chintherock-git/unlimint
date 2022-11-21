package PageFactory;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import java.util.List;
//Importing required classes
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;

import Driver.Driverfactory;

public class Create_Account_PF {


	@FindBy(xpath="//h1/span[contains(text(),'New')]")
	private WebElement create_account_label;

	@FindBy(id="firstname")
	private WebElement firstname;

	@FindBy(id="lastname")
	private WebElement lastname;
	
	@FindBy(id="email_address")
	private WebElement email;

	@FindBy(id="password")
	private WebElement password;

	@FindBy(id="password-confirmation")
	private WebElement confirm_password;

	@FindBy(xpath="//button/span[text()='Create an Account']")
	private WebElement create_button;
	
	@FindBy(xpath="//div[@class='page messages']/div/div/div/div")
	private WebElement error_message;
	
	public boolean flag = true; 
	private WebDriver driver;

	public Create_Account_PF(WebDriver driver) {
		this.driver=driver;
		AjaxElementLocatorFactory ajax= new AjaxElementLocatorFactory(driver, Integer.parseInt(Driverfactory.getPropValues("Time_Unit_SECONDS")));
		PageFactory.initElements(ajax,this);
	}



	public boolean createAccountValue(String fname,String lname,String mail, String passwd) {
		if(create_account_label.isDisplayed()) {
			String k_pwd="@123";
				if (firstname.isDisplayed())
					firstname.sendKeys(fname);
				if (lastname.isDisplayed())
					lastname.sendKeys(lname);
				if (email.isDisplayed())
					email.sendKeys(mail);
				if (password.isDisplayed())
					password.sendKeys(passwd+k_pwd);
				if (confirm_password.isDisplayed())
					confirm_password.sendKeys(passwd+k_pwd);
				if (create_button.isDisplayed())
					create_button.click();
				
			return flag;
		}
		return false;
	}
	
	public String validateAccount(String fname,String lname,String mail_duplicate, String passwd) {
		if(create_account_label.isDisplayed()) {
			String k_pwd="@123";
			String mess="";
				if (firstname.isDisplayed())
					firstname.sendKeys(fname);
				if (lastname.isDisplayed())
					lastname.sendKeys(lname);
				if (email.isDisplayed())
					email.sendKeys(mail_duplicate);
				if (password.isDisplayed())
					password.sendKeys(passwd+k_pwd);
				if (confirm_password.isDisplayed())
					confirm_password.sendKeys(passwd+k_pwd);
				if (create_button.isDisplayed())
					create_button.click();
				if (error_message.isDisplayed())
					mess= error_message.getText();
				return mess;
		}
		return "";
	}

	
}