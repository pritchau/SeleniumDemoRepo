package stepdefination;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.AccountPage;
import pageobject.LandingPage;
import pageobject.LoginPage;
import resources.Base;

public class Login extends Base{
	
	WebDriver driver;
	LandingPage landingpage;
	LoginPage loginpage;
	AccountPage accountpage;
	
	@Given ("^Open any browser$")
	public void Open_any_browser() throws IOException {
		driver = intializeBrowser();
	}
	
	@And("^Navigate to Login Page$")
	public void Navigate_to_Login_Page() {
		driver.get(prop.getProperty("url"));
		landingpage = new LandingPage(driver);
		landingpage.myAccountLinkDropDown().click();
		landingpage.loginlink().click();
	}
	
	@When("^User enter username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
	public void User_enter_username_as_something_and_password_as_something_into_the_fields(String email, String password) {
		loginpage = new LoginPage(driver);
		loginpage.emailAddressField().sendKeys(email);
		loginpage.passwordField().sendKeys(password);
	}
	
	@And("^User clicks on Login button$")
	public void User_clicks_on_Login_button() {
		loginpage.loginButton().click();
	}
	
	@Then("^Verify user is able to successfully login$")
	public void  Verify_user_is_able_to_successfully_login() {
		accountpage = new AccountPage(driver);
		Assert.assertTrue(accountpage.editAccountInformationLink().isDisplayed());
	}
	
	@After
	public void tearDown() {
		driver.close();
	}
}
