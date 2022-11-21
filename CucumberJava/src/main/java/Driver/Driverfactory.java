package Driver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Utilities.Log;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driverfactory {

	protected static WebDriver driver;
	public static String config_path = System.getProperty("user.dir")+System.getProperty("file.separator")+"src/main/java/Utilities/config.properties";
	public static Properties prop;


	
	//This function reads the Config Property File
	public void readProperty() {
		Log.info("******   Current Working directory  *********"+System.getProperty("user.dir"));
		try {
			prop=new Properties();
			FileInputStream in = new FileInputStream(config_path);
			prop.load(in);

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		Log.info("****** Initializing the Property file *****"+getClass().getName());
	}

	//This function initializes the driver
	public void setDriver() {
		initialize();
		getDriver().get(prop.getProperty("URL"));
		Log.info("****** Initializing the WebDriver *****"+getClass().getName());
	}

	public static String getPropValues(String key) {
		return prop.getProperty(key);
	}
	
	public static void setPropValues(String key,String value) {
		try {
		prop.put(key, value);
		FileOutputStream ou = new FileOutputStream(config_path);
		prop.store(ou, "File appended");
		ou.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//Log.info("****** Writing  to the Property file *****"+getClass().getName());
	}

	public void initialize() {
		if (driver==null) {
			createNewDriverInstance();
		}
	}

	private void createNewDriverInstance() {
		String browser = prop.getProperty("Browser");
		if(browser.equalsIgnoreCase("chrome")){
			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.addArguments("start-maximized");
			driver =WebDriverManager.chromedriver().capabilities(browserOptions).create();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			}

	}
	
	//This function will fetch the driver instance
	public static WebDriver getDriver() {
		return driver;
	}

	//This function Ends the driver Session
	public void quitDriver() {
		driver.quit();
		driver=null;
		Log.info("****** Quitting the WebDriver Session from StepDefination After Hook *****"+getClass().getName());
	}

}
