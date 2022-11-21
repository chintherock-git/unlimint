package PageFactory;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.NoSuchElementException;
import java.time.Duration;
import Driver.Driverfactory;
import Utilities.Log;

public class MyAccount_PF {


	@FindBy(xpath="//h1/span[contains(text(),'New')]")
	private WebElement adress_label;

	@FindBy(id="firstname")
	private WebElement firstname;

	@FindBy(id="lastname")
	private WebElement lastname;

	@FindBy(xpath="(//a/span[text()='Edit Address'])[2]")
	private WebElement edit_address;

	@FindBy(xpath="//span[text()='Contact Information']")
	private WebElement contact_info_label;

	@FindBy(id="telephone")
	private WebElement phone_number;

	@FindBy(id="street_1")
	private WebElement street_address_1;//number

	//locationname
	@FindBy(id="street_2")
	private WebElement street_address_2;

	//city
	@FindBy(id="city")
	private WebElement address_city;

	//state
	@FindBy(id="region_id")
	private WebElement address_state;

	//country
	@FindBy(id="country")
	private WebElement address_country;

	//zip
	@FindBy(id="zip")
	private WebElement address_zip;

	@FindBy(xpath="//button/span[text()='Save Address']")
	private WebElement save_address;

	@FindBy(xpath="///div[@class='box box-address-shipping']/div/address")
	private WebElement shipping_address;

	@FindBy(xpath="//div[@class='box box-information']/div/p")
	private WebElement contact_info;

	@FindBy(xpath="//span[text()='Women']")
	private WebElement women_tab;

	@FindBy(xpath="//a[text()='Hoodies & Sweatshirts']")
	private WebElement sweat_shirt;

	@FindBy(xpath="(//div[@class='product-item-info'])[1]")
	private WebElement first_item;

	@FindBy(xpath="(//div[@class='product details product-item-details'])[1]/strong/a")
	private WebElement first_item_link;

	@FindBy(id="product-addtocart-button")
	private WebElement addtocart;

	@FindBy(xpath="(//div[@class='swatch-option text'])[1]")
	private WebElement item_size_xs;

	@FindBy(xpath="(//div[@class='swatch-option text'])[2]")
	private WebElement item_size_s;

	@FindBy(xpath="(//div[@class='swatch-option text'])[3]")
	private WebElement item_size_m;

	@FindBy(xpath="(//div[@class='swatch-option text'])[4]")
	private WebElement item_size_l;

	@FindBy(xpath="(//div[@class='swatch-option text'])[5]")
	private WebElement item_size_xl;

	@FindBy(xpath="(//div[@class='swatch-option color'])[1]")
	private WebElement item_color;

	@FindBy(xpath="//span[@class='counter-number']")
	private WebElement cart_item_count;

	@FindBy(xpath="//a[@class='action showcart']")
	private WebElement cart_icon;

	@FindBy(id="minicart-content-wrapper")
	private WebElement cart_content;

	@FindBy(id="top-cart-btn-checkout")
	private WebElement checkout;

	@FindBy(xpath="//div[@class='shipping-address-item selected-item']")
	private WebElement address;

	@FindBy(xpath="//button/span[text()='Next']")
	private WebElement next;

	@FindBy(xpath="//div[@class='opc-block-summary']")
	private WebElement order_summary;

	@FindBy(xpath="//span[text()='Place Order']")
	private WebElement place_order;

	@FindBy(xpath="//span[@class='base']")
	private WebElement purchase_message;

	@FindBy(xpath="//a[@class='order-number']")
	private WebElement ordernumber;
	
	@FindBy(xpath="//div[@class='messages']/div/div")
	private WebElement cart_add_message;


	public boolean flag = true;


	private WebDriver driver;

	public MyAccount_PF(WebDriver driver) {
		this.driver=driver;
		AjaxElementLocatorFactory ajax= new AjaxElementLocatorFactory(driver, Integer.parseInt(Driverfactory.getPropValues("Time_Unit_SECONDS")));
		PageFactory.initElements(ajax,this);
	}

	public void waitForElementTobeVisible(WebElement element) {
		driver=Driverfactory.getDriver();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(30))
				  .pollingEvery(Duration.ofSeconds(5))
				  .ignoring(NoSuchElementException.class);

				wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOf(element),ExpectedConditions.elementToBeClickable(element)));
	}
	
	public void waitForElementTohaveText(WebElement element,String value) {
		driver=Driverfactory.getDriver();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(30))
				  .pollingEvery(Duration.ofSeconds(5))
				  .ignoring(NoSuchElementException.class);

				wait.until(ExpectedConditions.and(ExpectedConditions.textToBePresentInElement(element,value),ExpectedConditions.elementToBeClickable(element)));
	}

	public void selectItemForCart(String size) { 
			
		if (women_tab.isDisplayed()) {
			women_tab.click();
			if (sweat_shirt.isDisplayed()){
				sweat_shirt.click();
				if (first_item.isDisplayed()) {
					first_item_link.click();
					selectSize(size);
					item_color.click();
				}
			}
		}
	}
	
	
	public void checkOut() {
		try {
			waitForElementTobeVisible(cart_item_count);
			waitForElementTohaveText(cart_item_count,"1");
			Thread.sleep(5000);
			cart_icon.click();
			if(!cart_content.isDisplayed()) {
				cart_icon.click();
			}
			checkout.isDisplayed();
			checkout.click();
			if (address.isDisplayed()) {
				next.isDisplayed();
				order_summary.isDisplayed();
				String shipping=checkCartURL();
				if (shipping.contains("shipping"))
					Log.info("User in in the shipping details page of purchase order process");
				else
					Log.info("There is no shipping details page of purchase order process");
				Thread.sleep(3000);
				next.click();
				place_order.isDisplayed();
				Thread.sleep(3000);
				place_order.click();
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String validatePurchaseMessage() {
		if(purchase_message.isDisplayed())
			return purchase_message.getText();
		else
			return "No Message";
	}
	
	public String getOrderNumber() {
		if(ordernumber.isDisplayed())
			return ordernumber.getText();
		else
			return "No OrderNumber";
	}
	
	public int getCartCount() {
		int count=Integer.parseInt(cart_item_count.getText());
		return count;
	}

	public void selectSize(String size) {
		int index=1;
		if(size.equalsIgnoreCase("xtrasmall"))
			index=1;
		else if (size.equalsIgnoreCase("small"))
			index=2;
		else if (size.equalsIgnoreCase("medium"))
			index=3;
		else if (size.equalsIgnoreCase("large"))
			index=4;
		else if (size.equalsIgnoreCase("xtralarge"))
			index=5;

		switch (index) {
		case 1:
			item_size_xs.click();
			break;
		case 2:
			item_size_s.click();
			break;
		case 3:
			item_size_m.click();
			break;
		case 4:
			item_size_l.click();
			break;
		case 5:
			item_size_xl.click();
			break;
		}
	}
	public void addItemToCart() {

		if (addtocart.isDisplayed()) {
			addtocart.click();
		}
		waitForElementTobeVisible(cart_add_message);
	}

	public String checkCartURL()
	{
		//https://magento.softwaretestingboard.com/checkout/#shipping
		return Driverfactory.getDriver().getCurrentUrl();
	}

	public boolean validateContactInfo(String email) {
		if(contact_info.isDisplayed()) {
			String[] list_words = contact_info.getText().split(" ");
			for (String   text: list_words) {
				if(text.equalsIgnoreCase(email))
					break;
			}
			return flag;
		}
		return false;
	}

	public String validateFirstName() {
		String f_name="";
		if(firstname.isDisplayed()) 
			f_name=firstname.getAttribute("value").trim();
		return f_name;
	}	


	public String validateLastName() {
		String l_name="";
		if(lastname.isDisplayed()) 
			l_name=lastname.getAttribute("value").trim();
		return l_name;
	}


	public boolean addAddress(String phone,String street_number,String street_name,String city,String state,String post,String country) {
		
		if(edit_address.isDisplayed()) 
			edit_address.click();
		
		if (contact_info_label.isDisplayed()) {
			Log.info("The firtsname is "+validateFirstName());
			Log.info("The lastname is "+validateLastName());
		}
		
		if (phone_number.isDisplayed()) {
			phone_number.sendKeys(phone);
		}
		else
			flag=false;
		if (street_address_1.isDisplayed()) {
			street_address_1.sendKeys(street_number);
		}
		else
			flag=false;
		if (street_address_2.isDisplayed()) {
			street_address_2.sendKeys(street_name);
		}
		else
			flag=false;
		if (address_country.isDisplayed()) {
			Select se= new Select(address_country);
			se.selectByVisibleText(country);
		}
		else
			flag=false;
		if (address_city.isDisplayed()) {
			address_city.sendKeys(city);
		}
		else
			flag=false;
		if (address_state.isDisplayed()) {
			address_state.sendKeys(state);
		}
		else
			flag=false;
		if (address_zip.isDisplayed()) {
			address_zip.sendKeys(post);
		}
		else
			flag=false;
		if (save_address.isEnabled()) {
			save_address.click();
		}
		else flag=false;
		return flag;
	}

}
