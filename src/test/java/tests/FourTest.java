package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.Base;

public class FourTest extends Base{
	
	public WebDriver driver;
	
	@Test
	public void testFour() throws IOException, InterruptedException {
		
		System.out.println("Inside Four Test");
		driver = intializeBrowser();
		driver.get(prop.getProperty("url"));
		Thread.sleep(2000);
		Assert.assertTrue(false);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
