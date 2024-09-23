package com.nithin.base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nithin.base.pageobject.CartPage;
import com.nithin.base.pageobject.CheckOutPage;
import com.nithin.base.pageobject.ConfirmationPage;
import com.nithin.base.pageobject.LandingPage;
import com.nithin.base.pageobject.ProductCatalog;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest {
	WebDriver driver;

	@Test
	public void testWebPage() throws InterruptedException {
		String productName = "ZARA COAT 3";
		String country = "India";

//		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo();
		ProductCatalog productCatalogue = landingpage.enterUserDetails("batman@gmail.com", "Batman123@");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean verifyProductDisplay = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(verifyProductDisplay);
		CheckOutPage checkOutPage = cartPage.goTocheckOut();
		checkOutPage.enterCountryDetails(country);
		ConfirmationPage confirMationPage = checkOutPage.submitOrder();

		String confirmMessage = confirMationPage.getConfimMSG();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();

	}

}
