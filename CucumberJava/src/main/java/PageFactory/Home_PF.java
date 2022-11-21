package PageFactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import Driver.Driverfactory;

public class Home_PF {

	@FindBy(xpath="//a[text()='Create an Account']")
	private WebElement createaccount;

	public boolean flag = true; 

	private WebDriver driver;

	public Home_PF(WebDriver driver) {
		this.driver=driver;
		AjaxElementLocatorFactory ajax= new AjaxElementLocatorFactory(driver, Integer.parseInt(Driverfactory.getPropValues("Time_Unit_SECONDS")));
		PageFactory.initElements(ajax,this);
	}


	public boolean navigateCreateAccount() {
		if(createaccount.isDisplayed()) {
			createaccount.click();
			return flag;
		}
		return false;
	}


	public String checkCartURL()
	{
		return Driverfactory.getDriver().getCurrentUrl();
	}


}
