package tests;

import java.io.IOException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobject.AccountPage;
import pageobject.LandingPage;
import pageobject.LoginPage;
import resources.Base;

public class LoginTest extends Base{
	
	public WebDriver driver; 
	Logger log;
	
	@BeforeMethod
	public void openApplication() throws IOException {
		
		log = LogManager.getLogger(LoginTest.class.getName());
		
		driver = intializeBrowser();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
	}
	
	@Test(dataProvider="dataSupplier")
	public void login(String email,String password,String expectedStatus) {
		
		LandingPage landingpage = new LandingPage(driver);
		landingpage.myAccountLinkDropDown().click();
		log.debug("Click on My account Dropdown");
		landingpage.loginlink().click();
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddressField().sendKeys(email);
		log.debug("Add email");
		loginpage.passwordField().sendKeys(password);
		log.debug("Add password");
		loginpage.loginButton().click();
		log.debug("Click on Login button");
		
		AccountPage accountpage = new AccountPage(driver);
		
		String actualResult = null;
		try {
			 if(accountpage.editAccountInformationLink().isDisplayed())
			 actualResult= "Success";
			 log.debug("User logged In");
		}catch(Exception e) {
			 actualResult= "Failed";
			 log.debug("User didn't logged In");
		}
		
		Assert.assertEquals(actualResult, expectedStatus);
	}
	
	@DataProvider
	public Object[][] dataSupplier() {
		
		Object[][] data = {{"test_admin@gmail.com","3456","Success"},{"pritichaudhary28@gmail.com","Admin123","Failed"}};
		return data;
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
